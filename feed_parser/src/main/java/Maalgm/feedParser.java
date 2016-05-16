package Maalgm;

import java.net.URL;
import java.util.List;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class feedParser {
  public static void main(String[] args) {
    String rssUrl = "http://javacan.tistory.com/rss";
    try {
      URL feedUrl = new URL(rssUrl);
      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(feedUrl));

      System.out.println("RSS title: " + feed.getTitle());
      System.out.println("RSS author: " + feed.getAuthor());

      List entries = feed.getEntries();
      for (int i = 0; i < entries.size(); i++) {
        SyndEntry entry = (SyndEntry) entries.get(i);
        System.out.println("--- Entry " + i);
        System.out.println(entry.getTitle());
        System.out.println(entry.getAuthor());
        System.out.println(entry.getDescription().getValue());
        System.out.println(entry.getLink());
      }
    } catch (Exception e) {
      System.out.println("Exception");
    }
  }
}
