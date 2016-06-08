package maalgm_server;

import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MaalgmServer {

  private static final Logger logger = Logger.getLogger("maalgm_server");

  private Server server;
  private static final int port = 21035;

  public static void main(String[] args) throws IOException, InterruptedException {
    final MaalgmServer server = new MaalgmServer();
    server.start();
    server.blockUntilShutdown();
  }


  @VisibleForTesting
  void start() throws IOException {
    server = ServerBuilder.forPort(port)
        .addService(sessionGrpc.bindService(new MaalgmLoginImpl()))
        .build()
        .start();

    logger.info(
        "Maalgm server started, listening on port : " + port + ". Waiting for login request.");

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("*** shutting down gRPC server since JVM is shutting down.");
        MaalgmServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  @VisibleForTesting
  static class MaalgmLoginImpl implements sessionGrpc.session {

    @Override
    public void login(Session.LoginRequest request,
                      StreamObserver<Session.LoginResponse> responseObserver) {

      String username = request.getUsername();
      String password = request.getPassword();
      System.out.println("username : " + username + "\npassword : " + password);

      JSONObject dbResponse = MDBLoginModule.login(username, password);

      Session.LoginResponse.Builder resBuilder = Session.LoginResponse.newBuilder();
      resBuilder.setStatus(dbResponse.get("status").toString());

      if (dbResponse.get("status").toString().equals("200")) {
        logger.info("status : 200. Successfully logged in. Username : " + username);
        resBuilder.setSessionId(dbResponse.get("sessionID").toString());
      }
      else {
        logger.info("status" + dbResponse.get("status").toString());
      }
      Session.LoginResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    @Override
    public void logout(Session.SessionId request, StreamObserver<Session.StatusResponse> responseObserver) {
      System.out.println(request.getSessionId());
      JSONObject dbResponse = MDBLoginModule.logout(request.getSessionId());
      Session.StatusResponse.Builder resBuilder = Session.StatusResponse.newBuilder();
      resBuilder.setStatus(dbResponse.get("status").toString());
      Session.StatusResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    @Override
    public void signUp(Session.SignUpRequest request, StreamObserver<Session.StatusResponse> responseObserver) {
      JSONObject dbResponse = MDBLoginModule.signUp(request.getUsername(), request.getPassword());
      Session.StatusResponse.Builder resBuilder = Session.StatusResponse.newBuilder();
      resBuilder.setStatus(
          dbResponse.get("status").toString());
      Session.StatusResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    @Override
    public void getSessionData(Session.SessionId request, StreamObserver<Session.SessionDataResponse> responseObserver) {
      JSONObject dbResponse = MDBLoginModule.getSessionData(request.getSessionId());
      Session.SessionDataResponse.Builder resBuilder = Session.SessionDataResponse.newBuilder();
      resBuilder.setStatus(
          dbResponse.get("status").toString());
      if (resBuilder.getStatus().equals("200")) {
        resBuilder.setUsername(
            dbResponse.get("username").toString()
        );
      }
      Session.SessionDataResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    @Override
    public void getFeedList(Session.FeedListRequest request, StreamObserver<Session.FeedListResponse> responseObserver) {
      System.out.println("getFeedList Request called.");

      List<String> arrayFeedList = new ArrayList<String>();
      arrayFeedList.add("http://www.bbc.co.uk");
      arrayFeedList.add("http://www.nytimes.com");
      arrayFeedList.add("http://www.startupweekly.com");
      arrayFeedList.add("http://www.google.com");
      String jsonFeedList = JSONArray.toJSONString(arrayFeedList);

      Session.FeedListResponse.Builder resBuilder = Session.FeedListResponse.newBuilder();
      resBuilder.setFeedList(jsonFeedList.toString());
      Session.FeedListResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    @Override
    public void getUrlList(Session.SessionId request, StreamObserver<Session.UrlListResponse> responseObserver) {

      List<String> arrayUrlList = new ArrayList<String>();
      arrayUrlList.add("http://www.bbc.co.uk");
      arrayUrlList.add("http://www.nytimes.com");
      arrayUrlList.add("http://www.startupweekly.com");
      arrayUrlList.add("http://www.google.com");
      String jsonUrlList = JSONArray.toJSONString(arrayUrlList);

      Session.UrlListResponse.Builder resBuilder = Session.UrlListResponse.newBuilder();
      resBuilder.setUrlList(jsonUrlList);
      Session.UrlListResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    @Override
    public void insertUrl(Session.InsertUrlRequest request, StreamObserver<Session.StatusResponse> responseObserver) {

      JSONObject dbResponse = MDBLoginModule.signUp(request.getSessionId(), request.getUrl());
      Session.StatusResponse.Builder resBuilder = Session.StatusResponse.newBuilder();
      resBuilder.setStatus(
          dbResponse.get("status").toString());
      Session.StatusResponse response = resBuilder.build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();

    }






  }
}
