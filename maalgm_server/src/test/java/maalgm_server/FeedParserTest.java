package maalgm_server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class FeedParserTest {
  
  private FeedParser parser;
  private JSONParser jsonparser;
  private JSONObject resultjson;

  @Before
  public void setUp() {
    parser = new FeedParser();
  }

  @Test
  public void test() {
    jsonparser = new JSONParser();
    String result = parser.getFeed("http://javacan.tistory.com/rss");
    try {
      resultjson = (JSONObject) jsonparser.parse(result);
    } catch (ParseException e) {
      fail("not a json object");
    }
    assertEquals(resultjson.get("result"), "success");
  }
}
