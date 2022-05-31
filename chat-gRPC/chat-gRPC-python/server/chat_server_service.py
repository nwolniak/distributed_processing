import threading

from collections import defaultdict

import grpc

from generated.server_pb2_grpc import serverServicer
from generated.server_pb2 import ServerResponse
from generated.server_pb2 import FromServerMessage


class ChatServerService(serverServicer):
    def __init__(self):
        self.groups = {}
        self.message_get_counter = defaultdict(int)
        self.message_ack_counter = defaultdict(lambda: defaultdict(int))
        self.messages = defaultdict(list)

    def join(self, request, context):
        client_name = request.clientName
        group_id = request.groupId

        if client_name in self.groups:
            context.set_code(grpc.StatusCode.ALREADY_EXISTS)
            context.set_details(
                "Can't join. " + client_name + " is already in " + str(self.groups[client_name]) + " group!")
            print("Can't join. " + client_name + " is already in " + str(self.groups[client_name]) + " group!")
            return ServerResponse()

        self.groups[client_name] = group_id
        self.__init_ack_counter(client_name, group_id)
        return ServerResponse()

    def quit(self, request, context):
        client_name = request.clientName
        try:
            del self.groups[client_name]
        except KeyError:
            context.set_code(grpc.StatusCode.NOT_FOUND)
            context.set_details("Can't quit. " + client_name + " is not in any group!")
            print("Can't quit. " + client_name + " is not in any group!")
            return ServerResponse()

        return ServerResponse()

    def sendMessage(self, request_iterator, context):
        try:
            first_message = next(request_iterator)
        except StopIteration:
            print("Stream closed")
            return

        client_name = self.__get_client_name(first_message)
        try:
            self.__init_ack_counter(client_name, self.__get_group_id_by_name(client_name))
        except RuntimeError:
            context.set_code(grpc.StatusCode.NOT_FOUND)
            context.set_details("Can't process message. " + client_name + " is not in any group!")
            print("Can't process message. " + client_name + " is not in any group!")
            return FromServerMessage()

        def out_data():
            while True:
                try:
                    group_id = self.__get_group_id_by_name(client_name)
                except RuntimeError:
                    continue

                get_count = self.__get_counter(group_id)
                acked_count = self.__ack_counter(client_name, group_id)
                if get_count > acked_count and client_name in self.groups:
                    for msg in self.messages[group_id][acked_count:]:
                        self.__update_ack_counter(client_name, group_id)
                        msg_quoted_id = self.__get_message_quoted_id(msg)
                        if msg_quoted_id != 0:
                            msg_quoted = self.__get_message_by_idx(group_id, self.__get_message_quoted_id(msg))
                        else:
                            msg_quoted = ""

                        yield {
                            'messageId': self.__ack_counter(client_name, group_id),
                            'clientName': self.__get_client_name(msg),
                            'message': self.__get_message(msg),
                            'messageQuoted': msg_quoted,
                            'image_type': self.__get_image_type(msg),
                            'image_data': self.__get_image_data(msg)
                        }

        def in_data():
            while True:
                try:
                    try:
                        received = next(request_iterator)
                    except StopIteration:
                        print("Stream closed")
                        return
                    received_client_name = self.__get_client_name(received)
                    received_group_id = self.__get_group_id(received)
                    if received_client_name == client_name:
                        self.__update_ack_counter(client_name, received_group_id)
                    self.__update_messages(received)
                except RuntimeError:
                    context.set_code(grpc.StatusCode.NOT_FOUND)
                    context.set_details("Can't process message. " + client_name + " is not in any group!")
                    print("Can't process message. " + client_name + " is not in any group!")
                    return FromServerMessage()
                except grpc.RpcError as e:
                    print("Connection lost from " + client_name + " !")
                    return FromServerMessage()

        thread = threading.Thread(target=in_data)
        thread.daemon = True
        thread.start()

        output_stream = out_data()

        while True:
            yield FromServerMessage(**next(output_stream))

    def __update_messages(self, msg):
        group_id = self.__get_group_id(msg)
        self.__update_get_counter(group_id)
        self.messages[group_id].append(msg)

    def __init_ack_counter(self, client_name, group_id):
        self.message_ack_counter[group_id][client_name] = self.__get_counter(group_id)

    def __get_group_id(self, msg):
        client_name = msg.clientName
        if client_name in self.groups:
            return self.groups.get(client_name)
        else:
            raise RuntimeError()

    def __get_group_id_by_name(self, client_name):
        if client_name in self.groups:
            return self.groups.get(client_name)
        else:
            raise RuntimeError()

    def __get_client_name(self, msg):
        return msg.clientName

    def __get_message(self, msg):
        return msg.message

    def __get_message_quoted_id(self, msg):
        return msg.messageQuotedId

    def __get_message_by_idx(self, group_id, idx):
        return self.messages.get(group_id)[idx - 1].message

    def __update_get_counter(self, group_id):
        self.message_get_counter[group_id] += 1

    def __update_ack_counter(self, client_name, group_id):
        self.message_ack_counter[group_id][client_name] += 1

    def __get_counter(self, group_id):
        return self.message_get_counter[group_id]

    def __ack_counter(self, client_name, group_id):
        return self.message_ack_counter[group_id][client_name]

    def __get_image_type(self, msg):
        return msg.image_type

    def __get_image_data(self, msg):
        return msg.image_data
