package maalgm_server;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
public class MaalgmServerTest {
  private MaalgmServer.MaalgmLoginImpl impl;
  private String sessionId;


  @Before
  public void setUp() {
    impl = new MaalgmServer.MaalgmLoginImpl();
    ClientDB.getdb().getCollection("peruser").drop();
  }

  @Test
  public void allLoginTest() {
    Session.SignUpRequest request = Session.SignUpRequest.newBuilder()
        .setUsername("username").setPassword("12345").build();
    JSONObject dbResponse = MDBLoginModule.signUp(request.getUsername(), request.getPassword());
    assertEquals("200", dbResponse.get("status").toString());


    //Wrong Password Login
    Session.LoginRequest LoginRequest = Session.LoginRequest.newBuilder()
        .setUsername("username").setPassword("54321").build();
    dbResponse = MDBLoginModule
        .login(LoginRequest.getUsername(), LoginRequest.getPassword());
    assertEquals("404", dbResponse.get("status").toString());


    //Valid Login Test
    LoginRequest = Session.LoginRequest.newBuilder()
        .setUsername("username").setPassword("12345").build();
    dbResponse = MDBLoginModule
        .login(LoginRequest.getUsername(), LoginRequest.getPassword());
    assertEquals("200", dbResponse.get("status").toString());

    sessionId = dbResponse.get("sessionID").toString();


    //LogoutTest
    Session.SessionId LogOutrequest = Session.SessionId.newBuilder()
        .setSessionId(sessionId).build();

    dbResponse = MDBLoginModule.logout(LogOutrequest.getSessionId());
    assertEquals("200", dbResponse.get("status").toString());
  }


  @Test
  public void getDataTest() {

    Session.SignUpRequest request = Session.SignUpRequest.newBuilder()
        .setUsername("username2").setPassword("12345").build();
    JSONObject dbResponse = MDBLoginModule.signUp(request.getUsername(), request.getPassword());
    assertEquals("200", dbResponse.get("status").toString());


    Session.LoginRequest LoginRequest = Session.LoginRequest.newBuilder()
        .setUsername("username2").setPassword("12345").build();
    JSONObject LoginDBResponse = MDBLoginModule.login(LoginRequest.getUsername(), LoginRequest.getPassword());
    assertEquals("200", LoginDBResponse.get("status").toString());
    sessionId = LoginDBResponse.get("sessionID").toString();

    //getSessionData
    JSONObject sessionDBResponse = MDBLoginModule.getSessionData(sessionId);
    assertEquals("200", sessionDBResponse.get("status").toString());
    assertEquals("username2", sessionDBResponse.get("username").toString());

    //insertUrl
    JSONObject inserUrlDBResponse = MDBLoginModule.insertURL(sessionId, "http://www.daum.net");
    assertEquals("200", sessionDBResponse.get("status").toString());

    //getFeedList
    JSONObject feedListDBResponse = MDBLoginModule.getFeedList(sessionId, "http://www.daum.net");
    assertEquals("200", feedListDBResponse.get("status").toString());
    assertTrue(feedListDBResponse.get("rssfeedlist").toString().length() != 0);

    //getUrlList
    JSONObject urlDBResponse = MDBLoginModule.getURLList(sessionId);
    assertEquals("200", feedListDBResponse.get("status").toString());
  }
}