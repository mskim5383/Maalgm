from grpc.beta import implementations
import session_pb2

_TIMEOUT_SECONDS = 10


def create_stub(ip, port):
    channel = implementations.insecure_channel(ip, port)
    stub = session_pb2.beta_create_session_stub(channel)
    return stub


def login(username, password, ip, port):
    '''
    channel = implementations.insecure_channel('localhost', 21035)
    stub = session_pb2.beta_create_session_stub(channel)
    '''
    stub = create_stub(ip, port)
    response = stub.login(
        session_pb2.LoginRequest(username=username, password=password),
        _TIMEOUT_SECONDS)
    print("received session id is : " + str(response.sessionId))
    return response.sessionId


def logout(sessionId, ip, port):
    stub = create_stub(ip, port)
    response = stub.logout(
        session_pb2.SessionId(sessionId=sessionId),
        _TIMEOUT_SECONDS)
    # check status and do something?
    return response.status


def sign_up(username, password, ip, port):
    stub = create_stub(ip, port)
    response = stub.signUp(
        session_pb2.SignUpRequest(username=username, password=password),
        _TIMEOUT_SECONDS)
    return response.status


def get_session_data(sessionId, ip, port):
    stub = create_stub(ip, port)
    response = stub.getSessionData(
        session_pb2.SessionId(sessionId=sessionId),
        _TIMEOUT_SECONDS)
    print("received get_username response : " + str(response.username))
    return response.username


def get_feed_list(sessionId, url, ip, port):
    stub = create_stub(ip, port)
    response = stub.getFeedList(
        session_pb2.FeedListRequest(sessionId=sessionId, url=url),
        _TIMEOUT_SECONDS)
    print("received get_feed_list response : " + str(response.feedList))
    return response.feedList


def get_url_list(sessionId, ip, port):
    stub = create_stub(ip, port)
    response = stub.getUrlList(
        session_pb2.SessionId(sessionId=sessionId),
        _TIMEOUT_SECONDS)
    return response.urlList


def insert_url(sessionId, url, ip, port):
    stub = create_stub(ip, port)
    response = stub.insertUrl(
        session_pb2.InsertUrlRequest(sessionId=sessionId, url=url),
        _TIMEOUT_SECONDS)
    return response.status


if __name__ == '__main__':
    login("zoonoo", "password", "localhost", 21035)
