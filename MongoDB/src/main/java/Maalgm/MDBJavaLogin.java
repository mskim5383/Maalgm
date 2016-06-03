package Maalgm;

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
 
public class MDBJavaLogin {
    public static void main(String[] args) {
      MongoClient mongoClient = new MongoClient();
      MongoDatabase db = mongoClient.getDatabase("grpcdb");
      /*
       * To login, get the username, passwd from the frontend somehow,
       * then put them consequently instead of "username001", "1234567890" below.
       */ 
      FindIterable<Document> iterable = db.getCollection("peruser").find(
          new Document("username", "username001").append("passwd", "1234567890"));
      iterable.forEach(new Block<Document>() {
          @Override
          public void apply(final Document document) {
            System.out.println(document);
          }
      });
    }    
}
