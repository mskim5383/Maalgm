package Maalgm;

import com.google.common.annotations.VisibleForTesting;

import java.net.URL;
import java.util.List;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FeedParser {
  @VisibleForTesting
  public static String getFeed(String rssUrl) {
    JSONObject ret = new JSONObject();
    try {
      URL feedUrl = new URL(rssUrl);
      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(feedUrl));

      ret.put("RSSTitle", feed.getTitle());
      ret.put("RSSAuthor", feed.getAuthor());

      JSONArray feedList = new JSONArray();

      List<SyndEntry> entries = feed.getEntries();
      for (SyndEntry entry : entries) {
        JSONObject feedjson = new JSONObject();
        feedjson.put("feedTitle", entry.getTitle());
        feedjson.put("feedAuthor", entry.getAuthor());
        feedjson.put("feedDescription", entry.getDescription().getValue());
        feedjson.put("feedLink", entry.getLink());

        feedList.add(feedjson);
      }
      ret.put("feedList", feedList);
      ret.put("result", "success");
    } catch (Exception e) {
      ret.put("result", "error");
    }
    return ret.toJSONString();
  }
}
