package com.chat.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.1)",
    comments = "Source: server.proto")
public final class serverGrpc {

  private serverGrpc() {}

  public static final String SERVICE_NAME = "server";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getJoinMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.chat.grpc.ServerService.JoinRequest,
      com.chat.grpc.ServerService.ServerResponse> METHOD_JOIN = getJoinMethod();

  private static volatile io.grpc.MethodDescriptor<com.chat.grpc.ServerService.JoinRequest,
      com.chat.grpc.ServerService.ServerResponse> getJoinMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.chat.grpc.ServerService.JoinRequest,
      com.chat.grpc.ServerService.ServerResponse> getJoinMethod() {
    io.grpc.MethodDescriptor<com.chat.grpc.ServerService.JoinRequest, com.chat.grpc.ServerService.ServerResponse> getJoinMethod;
    if ((getJoinMethod = serverGrpc.getJoinMethod) == null) {
      synchronized (serverGrpc.class) {
        if ((getJoinMethod = serverGrpc.getJoinMethod) == null) {
          serverGrpc.getJoinMethod = getJoinMethod = 
              io.grpc.MethodDescriptor.<com.chat.grpc.ServerService.JoinRequest, com.chat.grpc.ServerService.ServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "server", "join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chat.grpc.ServerService.JoinRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chat.grpc.ServerService.ServerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new serverMethodDescriptorSupplier("join"))
                  .build();
          }
        }
     }
     return getJoinMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getQuitMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.chat.grpc.ServerService.QuitRequest,
      com.chat.grpc.ServerService.ServerResponse> METHOD_QUIT = getQuitMethod();

  private static volatile io.grpc.MethodDescriptor<com.chat.grpc.ServerService.QuitRequest,
      com.chat.grpc.ServerService.ServerResponse> getQuitMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.chat.grpc.ServerService.QuitRequest,
      com.chat.grpc.ServerService.ServerResponse> getQuitMethod() {
    io.grpc.MethodDescriptor<com.chat.grpc.ServerService.QuitRequest, com.chat.grpc.ServerService.ServerResponse> getQuitMethod;
    if ((getQuitMethod = serverGrpc.getQuitMethod) == null) {
      synchronized (serverGrpc.class) {
        if ((getQuitMethod = serverGrpc.getQuitMethod) == null) {
          serverGrpc.getQuitMethod = getQuitMethod = 
              io.grpc.MethodDescriptor.<com.chat.grpc.ServerService.QuitRequest, com.chat.grpc.ServerService.ServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "server", "quit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chat.grpc.ServerService.QuitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chat.grpc.ServerService.ServerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new serverMethodDescriptorSupplier("quit"))
                  .build();
          }
        }
     }
     return getQuitMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendMessageMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.chat.grpc.ServerService.FromClientMessage,
      com.chat.grpc.ServerService.FromServerMessage> METHOD_SEND_MESSAGE = getSendMessageMethod();

  private static volatile io.grpc.MethodDescriptor<com.chat.grpc.ServerService.FromClientMessage,
      com.chat.grpc.ServerService.FromServerMessage> getSendMessageMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.chat.grpc.ServerService.FromClientMessage,
      com.chat.grpc.ServerService.FromServerMessage> getSendMessageMethod() {
    io.grpc.MethodDescriptor<com.chat.grpc.ServerService.FromClientMessage, com.chat.grpc.ServerService.FromServerMessage> getSendMessageMethod;
    if ((getSendMessageMethod = serverGrpc.getSendMessageMethod) == null) {
      synchronized (serverGrpc.class) {
        if ((getSendMessageMethod = serverGrpc.getSendMessageMethod) == null) {
          serverGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<com.chat.grpc.ServerService.FromClientMessage, com.chat.grpc.ServerService.FromServerMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "server", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chat.grpc.ServerService.FromClientMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chat.grpc.ServerService.FromServerMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new serverMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static serverStub newStub(io.grpc.Channel channel) {
    return new serverStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static serverBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new serverBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static serverFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new serverFutureStub(channel);
  }

  /**
   */
  public static abstract class serverImplBase implements io.grpc.BindableService {

    /**
     */
    public void join(com.chat.grpc.ServerService.JoinRequest request,
        io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinMethod(), responseObserver);
    }

    /**
     */
    public void quit(com.chat.grpc.ServerService.QuitRequest request,
        io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getQuitMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.FromClientMessage> sendMessage(
        io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.FromServerMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chat.grpc.ServerService.JoinRequest,
                com.chat.grpc.ServerService.ServerResponse>(
                  this, METHODID_JOIN)))
          .addMethod(
            getQuitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chat.grpc.ServerService.QuitRequest,
                com.chat.grpc.ServerService.ServerResponse>(
                  this, METHODID_QUIT)))
          .addMethod(
            getSendMessageMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.chat.grpc.ServerService.FromClientMessage,
                com.chat.grpc.ServerService.FromServerMessage>(
                  this, METHODID_SEND_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class serverStub extends io.grpc.stub.AbstractStub<serverStub> {
    private serverStub(io.grpc.Channel channel) {
      super(channel);
    }

    private serverStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected serverStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new serverStub(channel, callOptions);
    }

    /**
     */
    public void join(com.chat.grpc.ServerService.JoinRequest request,
        io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void quit(com.chat.grpc.ServerService.QuitRequest request,
        io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQuitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.FromClientMessage> sendMessage(
        io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.FromServerMessage> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class serverBlockingStub extends io.grpc.stub.AbstractStub<serverBlockingStub> {
    private serverBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private serverBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected serverBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new serverBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.chat.grpc.ServerService.ServerResponse join(com.chat.grpc.ServerService.JoinRequest request) {
      return blockingUnaryCall(
          getChannel(), getJoinMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.chat.grpc.ServerService.ServerResponse quit(com.chat.grpc.ServerService.QuitRequest request) {
      return blockingUnaryCall(
          getChannel(), getQuitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class serverFutureStub extends io.grpc.stub.AbstractStub<serverFutureStub> {
    private serverFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private serverFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected serverFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new serverFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chat.grpc.ServerService.ServerResponse> join(
        com.chat.grpc.ServerService.JoinRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chat.grpc.ServerService.ServerResponse> quit(
        com.chat.grpc.ServerService.QuitRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQuitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN = 0;
  private static final int METHODID_QUIT = 1;
  private static final int METHODID_SEND_MESSAGE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final serverImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(serverImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN:
          serviceImpl.join((com.chat.grpc.ServerService.JoinRequest) request,
              (io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.ServerResponse>) responseObserver);
          break;
        case METHODID_QUIT:
          serviceImpl.quit((com.chat.grpc.ServerService.QuitRequest) request,
              (io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.ServerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendMessage(
              (io.grpc.stub.StreamObserver<com.chat.grpc.ServerService.FromServerMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class serverBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    serverBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.chat.grpc.ServerService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("server");
    }
  }

  private static final class serverFileDescriptorSupplier
      extends serverBaseDescriptorSupplier {
    serverFileDescriptorSupplier() {}
  }

  private static final class serverMethodDescriptorSupplier
      extends serverBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    serverMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (serverGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new serverFileDescriptorSupplier())
              .addMethod(getJoinMethod())
              .addMethod(getQuitMethod())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
