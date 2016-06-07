package kr.uwcj.cs492;

import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;


public class MaalgmServer {

  private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());

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

    private int retrieveSessionId(String username, String password) {
      //check the login info
      //get a new session id from the DB
      return 1;
    }

    @Override
    public void login(Session.LoginRequest request,
                      StreamObserver<Session.SessionResponse> responseObserver) {

      String username = request.getUsername();
      String password = request.getPassword();
      System.out.println("username : " + username + "\npassword : " + password);

      Session.SessionResponse response = Session.SessionResponse.newBuilder()
          .setSessionId(
              retrieveSessionId(username, password)
          ).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }


}
