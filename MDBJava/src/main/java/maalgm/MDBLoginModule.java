package maalgm;

//import com.google.common.annotations.VisibleForTesting;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.text.DateFormat;
import java.text.ParseException;
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
          new Document("username", username));
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

  public static JSONObject insertURL (String sessionid, String URL) {
    JSONObject ret = new JSONObject();
    JSONObject session_info = new JSONObject();
    try {
      FindIterable <Document> iterable = ClientDB.getdb().getCollection("sessiontable").find(
          new Document("sessionID", sessionid));
      iterable.forEach(new Block<Document>() {
        @Override
        public void apply(final Document document) {
          session_info.put("username", String.valueOf(document.get("username")));
          session_info.put("timestamp", String.valueOf(document.get("timestamp")));
        }
      });
      if (((new Date()).getTime() - Long.parseLong(String.valueOf(session_info.get("timestamp")))) < 60000){ 
        ClientDB.getdb().getCollection("peruser").updateOne(
            new Document("username", String.valueOf(session_info.get("username"))),
            new Document("$push", new Document("urllist", URL)));
        ret.put("status", 200);
      } else {
        logout(sessionid);
        ret.put("status", 404);
      }
    } catch (Exception e) {
      ret.put("status", 500);
    }
    return ret;
  }
}
