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

public class MDBLoginSimpleTest {

  public static void  main (String[] args) {
    JSONObject ret = new JSONObject();
    ret = MDBModule.signUp("username001", "123123");
    FindIterable<Document> iterable = ClientDB.getdb().getCollection("peruser").find(
        new Document("username", "username001"));
    iterable.forEach(new Block<Document>() {
      @Override
      public void apply(final Document document) {
        System.out.println(document);
      }
    });
    ret = MDBModule.login("username001", "123123");
    iterable = ClientDB.getdb().getCollection("sessiontable").find(
        new Document("username", "username001"));
    iterable.forEach(new Block<Document>() {
      @Override
      public void apply(final Document document) {
        System.out.println(document);
      }
    });
    ret = MDBModule.logout(ret.get("sessionID")+"");

    iterable = ClientDB.getdb().getCollection("sessiontable").find(
        new Document("username", "username001"));
    iterable.forEach(new Block<Document>() {
      @Override
      public void apply(final Document document) {
        System.out.println(document);
      }
    });

    if(iterable.first() == null){
      System.out.println("user logout succeed");
    }
  }

}

