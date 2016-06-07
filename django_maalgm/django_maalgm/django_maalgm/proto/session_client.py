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

if __name__ == '__main__':
    try_login()
