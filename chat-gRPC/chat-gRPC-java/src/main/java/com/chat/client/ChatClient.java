package com.chat.client;

import com.chat.grpc.ServerService.FromClientMessage;
import com.chat.grpc.ServerService.JoinRequest;
import com.chat.grpc.ServerService.QuitRequest;
import com.chat.grpc.serverGrpc.serverBlockingStub;
import com.chat.grpc.serverGrpc.serverStub;
import com.chat.utils.HardDriveImageSaver;
import com.google.protobuf.ByteString;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.chat.grpc.serverGrpc.newBlockingStub;
import static com.chat.grpc.serverGrpc.newStub;

public class ChatClient {
    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());
    private ManagedChannel channel;
    private final serverBlockingStub serverBlockingStub;
    private final serverStub serverNonBlockingStub;
    private StreamObserver<FromClientMessage> clientMessageStreamObserver;
    private final ServerMessageStreamObserver serverMessageStreamObserver;
    private Context.CancellableContext withCancellation;
    private String clientName;
    private final String address;
    private final int port;

    public ChatClient(String address, int port) {
        this.address = address;
        this.port = port;
        // channel
        this.channel = ManagedChannelBuilder.forAddress(this.address, this.port).usePlaintext().build();
        // blocking stub
        this.serverBlockingStub = newBlockingStub(channel);
        // non blocking stub
        this.serverNonBlockingStub = newStub(channel);
        // server message stream observer
        this.serverMessageStreamObserver = new ServerMessageStreamObserver(
                this,
                new HardDriveImageSaver("data"));
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("127.0.0.2", 50051);
        chatClient.start();
    }

    public void start() {
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Client name>");
            this.clientName = br.readLine();

            if (this.clientMessageStreamObserver == null){
                this.clientMessageStreamObserver = this.serverNonBlockingStub
                        .withWaitForReady()
                        .sendMessage(this.serverMessageStreamObserver);
                this.withCancellation = Context.current().withCancellation();
                this.withCancellation.addListener(context -> this.onStreamCancelled(), ForkJoinPool.commonPool());
            }

            do {
                System.out.print("command>");
                line = br.readLine();
                switch (line) {
                    case "join":
                        System.out.print("groupId>");
                        int groupId = Integer.parseInt(br.readLine());
                        this.sendJoinRequest(groupId);
                        break;
                    case "quit":
                        this.sendQuitRequest();
                        break;
                    case "q":
                        this.shutdown();
                        return;
                    case "c":
                        this.withCancellation.cancel(null);
                        break;
                    case "message1":
                        this.sendMessage("response", 3, "", "");
                        break;
                    case "message2":
                        this.sendMessage("another response", 5, "", "");
                        break;
                    case "image1":
                        this.sendMessage("sending cat image", 0, "./img/kitten.jpg", ".jpg");
                        break;
                    case "image2":
                        this.sendMessage("sending dog image", 0, "./img/dog.png", ".png");
                        break;
                    default:
                        this.sendMessage(line, 0, "", "");
                }
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            this.closeStream();
            this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendJoinRequest(int groupId) {
        // request
        JoinRequest joinRequest = JoinRequest.newBuilder()
                .setClientName(this.clientName)
                .setGroupId(groupId)
                .build();

        // send
        try {
            this.serverBlockingStub.withWaitForReady().join(joinRequest);
        } catch (StatusRuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }

    private void sendQuitRequest() {
        // request
        QuitRequest quitRequest = QuitRequest.newBuilder()
                .setClientName(this.clientName)
                .build();

        // send
        try {
            this.serverBlockingStub.withWaitForReady().quit(quitRequest);
        } catch (StatusRuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void sendMessage(String message, int messageQuotedId, String imagePath, String imageType) {
        FromClientMessage fromClientMessage;
        try {
            // message
            if (imagePath.isEmpty()){
                fromClientMessage = FromClientMessage.newBuilder()
                        .setClientName(this.clientName)
                        .setMessage(message)
                        .setMessageQuotedId(messageQuotedId)
                        .build();
            } else {
                fromClientMessage = FromClientMessage.newBuilder()
                        .setClientName(this.clientName)
                        .setMessage(message)
                        .setMessageQuotedId(messageQuotedId)
                        .setImageType(imageType)
                        .setImageData(ByteString.copyFrom(Files.readAllBytes(Paths.get(imagePath))))
                        .build();
            }


            // send
            this.clientMessageStreamObserver.onNext(fromClientMessage);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't read all bytes");
        }

    }

    private void closeStream() {
        this.clientMessageStreamObserver.onCompleted();
    }

    public void onStreamCancelled() {
        logger.log(Level.SEVERE, "Service UNAVAILABLE! Trying to retrieve connection.");
        try {
            Thread.sleep(1000);
            clientMessageStreamObserver = serverNonBlockingStub.sendMessage(serverMessageStreamObserver);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
