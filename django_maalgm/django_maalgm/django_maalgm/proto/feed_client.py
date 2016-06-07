from grpc.beta import implementations
import feed_server_pb2


_TIMEOUT_SECONDS = 10


def run():
    channel = implementations.insecure_channel('localhost', 21035)

    stub = feed_server_pb2.beta_create_feed_stub(channel)
    response = stub.Test(feed_server_pb2.FeedRequest(number=3),
                         _TIMEOUT_SECONDS)
    print("Greeter client received: " + response.message)
    return response.message


if __name__ == '__main__':
    run()
