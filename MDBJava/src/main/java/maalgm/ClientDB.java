package maalgm;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ClientDB {

  private static MongoClient client;
  private static MongoDatabase db;
  private static int sessionid = 0;
  
  public static MongoClient getclient() {
    if (client == null) {
      client = new MongoClient();
    }
    return client;
  }

  public static MongoDatabase getdb() {
    if (db == null) {
      db = getclient().getDatabase("grpcdb");
    }
    return db;
  }

  public static String getsessionid() {
    sessionid = sessionid + 1;
    return sessionid+"";
  }
}
