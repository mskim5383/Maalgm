package maalgm_server;

//import com.google.common.annotations.VisibleForTesting;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.net.URL;
import java.util.List;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

public class MDBLoginModule {
  
  /*
   * singUp used for user signup request with (username, passwd)
   * result: {"status": code}
   *
   * status code
   * 200 : succeed
   * 404 : requested username already exists
   * 500 : server error occurred
   */
  public static JSONObject signUp (String username, String passwd) {
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("peruser").find(new Document("username", username));
      if (iterable.first() == null) {
        ClientDB.getdb().getCollection("peruser").insertOne(
            new Document()
                .append("username", username)
                .append("passwd", passwd)
                .append("urllist", asList()));
        ret.put("status", 200);
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }

  /*
   * to handle login request, make a new session table for logined user.
   * result: {"status": code, "sessionID", sid}
   *
   * status code
   * 200 : succeed
   * 404 : no user exists with given username
   * 500 : server error occurred
   */
  public static JSONObject login (String username, String passwd) {
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("peruser").find(
          new Document("username", username).append("passwd", passwd));
      if (iterable.first() != null) {
        String sid = ClientDB.getsessionid();
        ClientDB.getdb().getCollection("sessiontable").insertOne(
            new Document()
                .append("sessionID", sid)
                .append("username", username)
                .append("timestamp", ((new Date()).getTime())));
        ret.put("status", 200);
        ret.put("sessionID", sid);
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }

  /*
   * to handle logout request, delete a session table for the requested session id.
   * result: {"status": code}
   *
   * status code
   * 200 : succeed
   * 404 : no session table found with given sessionid
   * 500 : server error occurred
   */
  public static JSONObject logout (String sessionid) {
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("sessiontable").find(
          new Document("sessionID", sessionid));
      if (iterable.first() != null) {
        ClientDB.getdb().getCollection("sessiontable").deleteMany(new Document("sessionID", sessionid));
        ret.put("status", 200);
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }

  /*
   * whenever user adds a new rss URL, the request is handled by insertURL.
   * result: {"status": code}
   *
   * status code
   * 200 : succeed
   * 403 : already added URL
   * 404 : no session table found with given sessionid, or session time passed.
   * 500 : server error occurred
   */
  public static JSONObject insertURL (String sessionid, String URL) {
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("sessiontable").find(
          new Document("sessionID", sessionid));
      if (iterable.first() != null){
        String username = String.valueOf(iterable.first().get("username"));
        String timestamp = String.valueOf(iterable.first().get("timestamp"));
        Long newtimestamp = (new Date()).getTime();
        if ((newtimestamp - Long.parseLong(timestamp)) < 60000){
          ClientDB.getdb().getCollection("sessiontable").updateOne(
              new Document("sessionID", sessionid),
              new Document("$set", new Document("timestamp", String.valueOf(newtimestamp))));
          iterable = ClientDB.getdb().getCollection("peruser").find(
              new Document("username", username)); 
          if(String.valueOf(iterable.first().get("urllist")).contains(URL)){
            ret.put("status", 403);
          } else {
            String feedJSONstr = FeedParser.getFeed(URL);
            try{
              JSONParser parser = new JSONParser();
              JSONObject feedJSON = (JSONObject) parser.parse(feedJSONstr);
              ClientDB.getdb().getCollection("peruser").updateOne(
                  new Document("username", username),
                  new Document("$push", new Document("urllist", "{\"URL\":\""+URL+"\",\"RSSTitle\":\""+String.valueOf(feedJSON.get("RSSTitle"))+"\"}")));
            } catch (ParseException e) {
              e.printStackTrace();
            }
            iterable = ClientDB.getdb().getCollection("perurl").find(
                new Document("URL", URL));
            if (iterable.first() == null){
              ClientDB.getdb().getCollection("perurl").insertOne(
                  new Document()
                      .append("URL", URL)
                      .append("rssJSON", feedJSONstr)
                      .append("related_urllist", asList())
                      .append("dirty_urllist", asList())
                      .append("timestamp", ((new Date()).getTime())));
            } else {
              iterable = ClientDB.getdb().getCollection("perurl").find(
                  new Document("URL", URL));
              timestamp = String.valueOf(iterable.first().get("timestamp")); 
              newtimestamp = (new Date()).getTime();           
              if ((newtimestamp - Long.parseLong(timestamp)) >= 60000){
                ClientDB.getdb().getCollection("perurl").updateOne(
                    new Document("URL", URL),
                    new Document("$set",
                        new Document("rssJSON", FeedParser.getFeed(URL))
                            .append("timestamp", String.valueOf(newtimestamp))));
              }
            }
            ret.put("status", 200);
          }
        } else {
          logout(sessionid);
          ret.put("status", 404);
        }
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }

  /*
   * returns a logined user's URL list.
   * result: {"status": code, "urllist": user's url list}
   *
   * status code
   * 200 : succeed
   * 404 : no session table found with given sessionid, or session time passed
   * 500 : server error occurred
   */
  public static JSONObject getURLList (String sessionid) {
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("sessiontable").find(
          new Document("sessionID", sessionid));
      if (iterable.first() != null) {
        String username = String.valueOf(iterable.first().get("username"));
        String timestamp = String.valueOf(iterable.first().get("timestamp"));
        Long newtimestamp = (new Date()).getTime();
        if ((newtimestamp - Long.parseLong(timestamp)) < 60000){
          ClientDB.getdb().getCollection("sessiontable").updateOne(
              new Document("sessionID", sessionid),
              new Document("$set", new Document("timestamp", String.valueOf(newtimestamp))));
             
          iterable = ClientDB.getdb().getCollection("peruser").find(
              new Document("username", username));
          ret.put("status", 200);
          ret.put("urllist", iterable.first().get("urllist"));
        } else {
          logout(sessionid);
          ret.put("status", 404);
        }
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }

  /*
   * get the rss feed data of the given URL
   * result: {"status": code, "rssfeedlist", rssfeedlist}
   *
   * status code
   * 200 : succeed
   * 403 : user does not have given URL in the urllist
   * 404 : no session table found with given sessionid, or session time passed
   * 500 : server error occurred
   */
  public static JSONObject getFeedList (String sessionid, String URL) { 
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("sessiontable").find(
          new Document("sessionID", sessionid));
      if (iterable.first() != null) {
        String username = String.valueOf(iterable.first().get("username"));
        String timestamp = String.valueOf(iterable.first().get("timestamp")); 
        Long newtimestamp = (new Date()).getTime();
        if ((newtimestamp - Long.parseLong(timestamp)) < 60000){
          ClientDB.getdb().getCollection("sessiontable").updateOne(
              new Document("sessionID", sessionid),
              new Document("$set", new Document("timestamp", String.valueOf(newtimestamp))));
          iterable = ClientDB.getdb().getCollection("peruser").find(
              new Document("username", username));
          if (!String.valueOf(iterable.first().get("urllist")).contains(URL)){
            ret.put("status", 403);
          } else { 
            iterable = ClientDB.getdb().getCollection("perurl").find(
                new Document("URL", URL));
            timestamp = String.valueOf(iterable.first().get("timestamp"));
            newtimestamp = (new Date()).getTime();           
            if ((newtimestamp - Long.parseLong(timestamp)) >= 60000){
              ClientDB.getdb().getCollection("perurl").updateOne(
                  new Document("URL", URL),
                  new Document("$set", 
                      new Document("rssJSON", FeedParser.getFeed(URL))
                          .append("timestamp", String.valueOf(newtimestamp))));
            }
            String retfeedlist = String.valueOf(ClientDB.getdb().getCollection("perurl").find(
                new Document("URL", URL)).first().get("rssJSON"));
            ret.put("status", 200);
            ret.put("rssfeedlist", retfeedlist);
          }
        } else {
          logout(sessionid);
          ret.put("status", 404);
        }
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }
  
  /*
   * returns the session data of the logined user
   * result: {"status": code, "username": username}
   *
   * status code
   * 200 : succeed
   * 404 : no session table found with given sessionid, or session time passed
   * 500 : server error occurred
   */
  public static JSONObject getSessionData (String sessionid) { 
    JSONObject ret = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("sessiontable").find(
          new Document("sessionID", sessionid));
      if (iterable.first() != null) {
        String username = String.valueOf(iterable.first().get("username"));
        String timestamp = String.valueOf(iterable.first().get("timestamp"));
        Long newtimestamp = (new Date()).getTime();
        if ((newtimestamp - Long.parseLong(timestamp)) < 60000){
          ClientDB.getdb().getCollection("sessiontable").updateOne(
              new Document("sessionID", sessionid),
              new Document("$set", new Document("timestamp", String.valueOf(newtimestamp)))); 
          ret.put("status", 200);
          ret.put("username", username);
        } else {
          logout(sessionid);
          ret.put("status", 404);
        }
      } else {
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }
}
