from grpc.beta import implementations
import session_pb2

_TIMEOUT_SECONDS = 10


def try_login():
    channel = implementations.insecure_channel('localhost', 21035)
    stub = session_pb2.beta_create_session_stub(channel)
    response = stub.login(
        session_pb2.LoginRequest(username='zoonoo', password='12345'),
        _TIMEOUT_SECONDS)
    print("received session id is : " + str(response.sessionId))
    return response.sessionId

def get_username():
    dummy_id = 50
    channel = implementations.insecure_channel('localhost', 21035)
    stub = session_pb2.beta_create_session_stub(channel)
    response = stub.getUsername(
        session_pb2.SessionRequest(sessionId=dummy_id),
        _TIMEOUT_SECONDS)
    print("received get_username response : " + str(response.username))
    return response.username

def get_feed_list():
    dummy_id = 50
    channel = implementations.insecure_channel('localhost', 21035)
    stub = session_pb2.beta_create_session_stub(channel)
    response = stub.getFeedList(
        session_pb2.SessionRequest(sessionId=dummy_id),
        _TIMEOUT_SECONDS)
    print("received get_feed_list response : " + str(response.feedList))
    return response.feedList

if __name__ == '__main__':
    get_feed_list()
