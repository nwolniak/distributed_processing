import grpc
import logging
from concurrent.futures import ThreadPoolExecutor
from generated import server_pb2_grpc
from chat_server_service import ChatServerService


def start_server():
    server = grpc.server(ThreadPoolExecutor(max_workers=16))
    server_pb2_grpc.add_serverServicer_to_server(ChatServerService(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print("Server started")
    server.wait_for_termination()


if __name__ == '__main__':
    logging.basicConfig()
    start_server()

