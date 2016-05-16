package Maalgm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
 
public class MDBJavaServer {
    public static void main(String[] args) {
      MongoClient mongoClient = new MongoClient();
      MongoDatabase db = mongoClient.getDatabase("test");
    } // main
     
} // TcpServerTest
 
/*
 * 
 * 결과
 * [03:33:41] 서버가 준비되었습니다.
 * [03:33:41] 연결요청을 기다립니다.
 * [03:33:43]/127.0.0.1 로부터 연결요청이 들어왔습니다.
 * [03:33:43] 데이터를 전송했습니다.
 * [03:33:43] 연결요청을 기다립니다.
 * 
 */
