package kr.uwcj.cs492;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GrpcClient {
  private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

  private final ManagedChannel channel;
  private final feedGrpc.feedBlockingStub blockingStub;

  public GrpcClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext(true)
        .build();
    blockingStub = feedGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    logger.info("Terminating client...");
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  public void askMessage(int number) {
    logger.info("Send message with the input number : " + number);
    FeedServer.FeedRequest request = FeedServer.FeedRequest.newBuilder()
        .setNumber(number)
        .build();

    FeedServer.FeedReply response;
    try {
      response = blockingStub.test(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }

    if (response.getMessage().equals("True")) {
      logger.info("The return value with input number : " + number + " is True");
    } else {
      logger.info("The return value with input number : " + number + " is False");
    }
  }

  public static void main(String[] args) throws IOException {
    GrpcClient client = new GrpcClient("localhost", 21035);

    while (true) {
      Scanner reader = new Scanner(System.in);
      //logger.info("Enter a integer(try \"exit\" to terminate program): ");
      String input = reader.nextLine();

      if (input.trim().equalsIgnoreCase("exit")) {
        break;
      }
      else {
        try {
          int number = Integer.parseInt(input);
          client.askMessage(number);
        } catch (NumberFormatException e) {
          logger.warning("Cannot parse input as integer");
        }
      }
    }
    try {
      client.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
