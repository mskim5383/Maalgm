package kr.uwcj.cs492;


import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;

import io.grpc.stub.StreamObserver;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class GrpcServer {

  private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());

  private Server server;
  private static final int port = 21035;


  public static void main(String[] args) throws IOException, InterruptedException {
    final GrpcServer server = new GrpcServer();
    server.start();
    server.blockUntilShutdown();
  }

  @VisibleForTesting
  void start() throws IOException {
    server = ServerBuilder.forPort(port)
        .addService(feedGrpc.bindService(new FeedImpl()))
        .build()
        .start();
    logger.info("Server started, listening on port : " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("*** shutting down gRPC server since JVM is shutting down.");
        GrpcServer.this.stop();
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
  static class FeedImpl implements feedGrpc.feed {

    @Override
    public void test(FeedServer.FeedRequest request,
                     StreamObserver<FeedServer.FeedReply> responseObserver) {
      FeedServer.FeedReply reply = FeedServer.FeedReply.newBuilder()
          .setMessage(produceMessage(request.getNumber()))
          .build();
      System.out.println("RUNNING");
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }

    private String produceMessage(long number) {
      return "THIS IS A NEW MESSAGE";
    }

  }
}
