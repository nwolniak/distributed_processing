package com.chat.client;

import com.chat.grpc.ServerService.FromServerMessage;
import com.chat.utils.HardDriveImageSaver;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerMessageStreamObserver implements StreamObserver<FromServerMessage> {
    private static final Logger logger = Logger.getLogger(ServerMessageStreamObserver.class.getName());
    private final ChatClient chatClient;
    private ByteArrayOutputStream imageDataStream;
    private HardDriveImageSaver hardDriveImageSaver;

    public ServerMessageStreamObserver(ChatClient chatClient, HardDriveImageSaver hardDriveImageSaver){
        this.chatClient = chatClient;
        this.hardDriveImageSaver = hardDriveImageSaver;
        this.imageDataStream = new ByteArrayOutputStream();
    }

    @Override
    public void onNext(FromServerMessage fromServerMessage) {
        int messageId = fromServerMessage.getMessageId();
        String clientName = fromServerMessage.getClientName();
        String message = fromServerMessage.getMessage();
        String messageQuoted = fromServerMessage.getMessageQuoted();

        String imageType = fromServerMessage.getImageType();
        ByteString imageData = fromServerMessage.getImageData();

        try {
            if (!imageType.isEmpty()){
                imageData.writeTo(imageDataStream);
                this.hardDriveImageSaver.save(clientName, imageType, imageDataStream);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Data stream error");
            e.printStackTrace();
            return;
        } catch (NullPointerException e){
            System.out.println("lul" + e.getMessage());
            return;
        }

        System.out.println();
        System.out.flush();
        if (messageQuoted.isEmpty()){
            System.out.println("[" + messageId + "][" + clientName + "]:" + message);
        } else {
            System.out.println("[" + messageId + "][" + clientName + "]:" + message + " (" + messageQuoted + ")");
        }
        System.out.flush();
    }

    @Override
    public void onError(Throwable throwable) {
        Status status = Status.fromThrowable(throwable);
        if (status.getCode() == Status.UNAVAILABLE.getCode()){
            logger.log(Level.SEVERE, "EXECUTING ON CANCELLED");
            this.chatClient.onStreamCancelled();
        }
        logger.log(Level.SEVERE, throwable.getMessage() + throwable.getCause());
    }

    @Override
    public void onCompleted() {
        logger.log(Level.SEVERE, "Stream completed");
    }
}
