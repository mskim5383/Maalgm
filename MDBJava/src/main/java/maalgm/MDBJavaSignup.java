package maalgm;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
 
public class MDBJavaSignup {
    public static void main(String[] args) {
      MongoClient mongoClient = new MongoClient();
      MongoDatabase db = mongoClient.getDatabase("grpcdb");
      /*
       * To signup, get the username, passwd, realname from the frontend somehow, 
       * then put them directly in the append() statements below.
       */
      db.getCollection("peruser").insertOne(
          new Document()
              .append("username", "username001")
              .append("passwd", "1234567890")
              .append("realname", "John Smith")
              .append("urllist", asList())
              .append("urlcount", 0));
    }    
}
