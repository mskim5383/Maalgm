package kr.uwcj.cs492;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.grpc.stub.StreamObserver;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MaalgmServerTest {
  private MaalgmServer.MaalgmLoginImpl impl;

  @Before
  public void setUp() {
    impl = new MaalgmServer.MaalgmLoginImpl();
  }


  @Test
  public void test() {
    Session.LoginRequest request = Session.LoginRequest.newBuilder()
        .setUsername("username").setPassword("12345").build();
        StreamObserver<Session.LoginResponse> responseObserver =
            new StreamObserver<Session.LoginResponse>;
    assertEquals(impl.login(request, responseObserver), "200");

  }


  @After
  public void tearDown() {

  }

}
