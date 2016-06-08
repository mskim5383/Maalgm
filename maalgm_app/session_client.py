from grpc.beta import implementations
import session_pb2

_TIMEOUT_SECONDS = 10


def create_stub(ip, port):
    channel = implementations.insecure_channel(ip, port)
    stub = session_pb2.beta_create_session_stub(channel)
    return stub


def login(username, password, ip, port):
    stub = create_stub(ip, port)
    response = stub.login(
        session_pb2.LoginRequest(username=username, password=password),
        _TIMEOUT_SECONDS)
    print("LOGIN response : sessionId: " +
          response.sessionId + ", status: " + response.status)
    return response


def logout(sessionId, ip, port):
    stub = create_stub(ip, port)
    response = stub.logout(
        session_pb2.SessionId(sessionId=sessionId),
        _TIMEOUT_SECONDS)
    # check status and do something?
    print("LOGOUT response : status: " + response.status)
    return response


def sign_up(username, password, ip, port):
    stub = create_stub(ip, port)
    response = stub.signUp(
        session_pb2.SignUpRequest(username=username, password=password),
        _TIMEOUT_SECONDS)
    print("SIGN_UP response : status: " + response.status)
    return response


def get_session_data(sessionId, ip, port):
    stub = create_stub(ip, port)
    response = stub.getSessionData(
        session_pb2.SessionId(sessionId=sessionId),
        _TIMEOUT_SECONDS)
    print("GET_SESSION_DATA response : status: " + response.status
          + ", username: " + response.username)
    return response


def get_feed_list(sessionId, url, ip, port):
    stub = create_stub(ip, port)
    response = stub.getFeedList(
        session_pb2.FeedListRequest(sessionId=sessionId, url=url),
        _TIMEOUT_SECONDS)
    print("GET_FEED_LIST response : " + str(response.feedList))
    return response


def get_url_list(sessionId, ip, port):
    stub = create_stub(ip, port)
    response = stub.getUrlList(
        session_pb2.SessionId(sessionId=sessionId),
        _TIMEOUT_SECONDS)
    print("GET_URL_LIST response : " + str(response.urlList))
    return response


def insert_url(sessionId, url, ip, port):
    stub = create_stub(ip, port)
    response = stub.insertUrl(
        session_pb2.InsertUrlRequest(sessionId=sessionId, url=url),
        _TIMEOUT_SECONDS)
    print("INSERT_URL response : " + response.status)
    return response


if __name__ == '__main__':
    login("zoonoo", "password", "localhost", 21035)
    logout("sessionId", "localhost", 21035)
    sign_up("zoonoo", "password", "localhost", 21035)
    get_session_data("sessionId", "localhost", 21035)
    get_feed_list("sessionId", "url", "localhost", 21035)
    get_url_list("sessionId", "localhost", 21035)
    insert_url("sessionId", "url", "localhost", 21035)
