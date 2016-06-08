package maalgm_server;

import maalgm_server.FeedParser;

public class FeedParserSample {
  public static void main(String[] args) {
    FeedParser feedParser = new FeedParser();
    System.out.println(feedParser.getFeed("http://javacan.tistory.com/rss"));
  }
}
