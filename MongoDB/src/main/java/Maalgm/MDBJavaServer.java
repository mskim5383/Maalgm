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
 
public class MDBJavaServer {
    public static void main(String[] args) {
      MongoClient mongoClient = new MongoClient();
      MongoDatabase db = mongoClient.getDatabase("grpcdb");
      db.getCollection("peruser").insertOne(
          new Document()
              .append("username", "username001")
              .append("passwd", "1234567890")
              .append("realname", "John Smith")
              .append("urllist", asList())
              .append("urlcount", 0));
      FindIterable<Document> iterable = db.getCollection("peruser").find();
      iterable.forEach(new Block<Document>() {
        @Override
        public void apply(final Document document) {
          System.out.println(document);
        }
      });
    }    
}
