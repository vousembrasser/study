package com.dingwd.netty.study.grpc.proto;

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
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.dingwd.netty.study.grpc.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.MyRequest,
      com.dingwd.netty.study.grpc.proto.MyResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUsername",
      requestType = com.dingwd.netty.study.grpc.proto.MyRequest.class,
      responseType = com.dingwd.netty.study.grpc.proto.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.MyRequest,
      com.dingwd.netty.study.grpc.proto.MyResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.MyRequest, com.dingwd.netty.study.grpc.proto.MyResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod =
              io.grpc.MethodDescriptor.<com.dingwd.netty.study.grpc.proto.MyRequest, com.dingwd.netty.study.grpc.proto.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUsername"))
              .build();
        }
      }
    }
    return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StudentRequest,
      com.dingwd.netty.study.grpc.proto.StudentResponse> getGetStudentsByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsByAge",
      requestType = com.dingwd.netty.study.grpc.proto.StudentRequest.class,
      responseType = com.dingwd.netty.study.grpc.proto.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StudentRequest,
      com.dingwd.netty.study.grpc.proto.StudentResponse> getGetStudentsByAgeMethod() {
    io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StudentRequest, com.dingwd.netty.study.grpc.proto.StudentResponse> getGetStudentsByAgeMethod;
    if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsByAgeMethod = getGetStudentsByAgeMethod =
              io.grpc.MethodDescriptor.<com.dingwd.netty.study.grpc.proto.StudentRequest, com.dingwd.netty.study.grpc.proto.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentsByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.StudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsByAge"))
              .build();
        }
      }
    }
    return getGetStudentsByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StudentRequest,
      com.dingwd.netty.study.grpc.proto.StudentResponseList> getGetStudentsWrapperByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsWrapperByAge",
      requestType = com.dingwd.netty.study.grpc.proto.StudentRequest.class,
      responseType = com.dingwd.netty.study.grpc.proto.StudentResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StudentRequest,
      com.dingwd.netty.study.grpc.proto.StudentResponseList> getGetStudentsWrapperByAgeMethod() {
    io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StudentRequest, com.dingwd.netty.study.grpc.proto.StudentResponseList> getGetStudentsWrapperByAgeMethod;
    if ((getGetStudentsWrapperByAgeMethod = StudentServiceGrpc.getGetStudentsWrapperByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsWrapperByAgeMethod = StudentServiceGrpc.getGetStudentsWrapperByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsWrapperByAgeMethod = getGetStudentsWrapperByAgeMethod =
              io.grpc.MethodDescriptor.<com.dingwd.netty.study.grpc.proto.StudentRequest, com.dingwd.netty.study.grpc.proto.StudentResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentsWrapperByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.StudentResponseList.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsWrapperByAge"))
              .build();
        }
      }
    }
    return getGetStudentsWrapperByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StreamRequest,
      com.dingwd.netty.study.grpc.proto.StreamResponse> getBidirectionalTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BidirectionalTalk",
      requestType = com.dingwd.netty.study.grpc.proto.StreamRequest.class,
      responseType = com.dingwd.netty.study.grpc.proto.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StreamRequest,
      com.dingwd.netty.study.grpc.proto.StreamResponse> getBidirectionalTalkMethod() {
    io.grpc.MethodDescriptor<com.dingwd.netty.study.grpc.proto.StreamRequest, com.dingwd.netty.study.grpc.proto.StreamResponse> getBidirectionalTalkMethod;
    if ((getBidirectionalTalkMethod = StudentServiceGrpc.getBidirectionalTalkMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getBidirectionalTalkMethod = StudentServiceGrpc.getBidirectionalTalkMethod) == null) {
          StudentServiceGrpc.getBidirectionalTalkMethod = getBidirectionalTalkMethod =
              io.grpc.MethodDescriptor.<com.dingwd.netty.study.grpc.proto.StreamRequest, com.dingwd.netty.study.grpc.proto.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BidirectionalTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dingwd.netty.study.grpc.proto.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("BidirectionalTalk"))
              .build();
        }
      }
    }
    return getBidirectionalTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub>() {
        @java.lang.Override
        public StudentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceStub(channel, callOptions);
        }
      };
    return StudentServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub>() {
        @java.lang.Override
        public StudentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceBlockingStub(channel, callOptions);
        }
      };
    return StudentServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub>() {
        @java.lang.Override
        public StudentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceFutureStub(channel, callOptions);
        }
      };
    return StudentServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *参数: 对象  返回值: 对象
     * </pre>
     */
    public void getRealNameByUsername(com.dingwd.netty.study.grpc.proto.MyRequest request,
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     * <pre>
     *参数: 对象  返回值: 流对象 java中的 Iterator
     * </pre>
     */
    public void getStudentsByAge(com.dingwd.netty.study.grpc.proto.StudentRequest request,
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentsByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     *参数: 流对象  返回值: 对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentRequest> getStudentsWrapperByAge(
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentsWrapperByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     *参数 流 返回值 流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StreamRequest> bidirectionalTalk(
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBidirectionalTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dingwd.netty.study.grpc.proto.MyRequest,
                com.dingwd.netty.study.grpc.proto.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetStudentsByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.dingwd.netty.study.grpc.proto.StudentRequest,
                com.dingwd.netty.study.grpc.proto.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGE)))
          .addMethod(
            getGetStudentsWrapperByAgeMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.dingwd.netty.study.grpc.proto.StudentRequest,
                com.dingwd.netty.study.grpc.proto.StudentResponseList>(
                  this, METHODID_GET_STUDENTS_WRAPPER_BY_AGE)))
          .addMethod(
            getBidirectionalTalkMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.dingwd.netty.study.grpc.proto.StreamRequest,
                com.dingwd.netty.study.grpc.proto.StreamResponse>(
                  this, METHODID_BIDIRECTIONAL_TALK)))
          .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractAsyncStub<StudentServiceStub> {
    private StudentServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *参数: 对象  返回值: 对象
     * </pre>
     */
    public void getRealNameByUsername(com.dingwd.netty.study.grpc.proto.MyRequest request,
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *参数: 对象  返回值: 流对象 java中的 Iterator
     * </pre>
     */
    public void getStudentsByAge(com.dingwd.netty.study.grpc.proto.StudentRequest request,
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentsByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *参数: 流对象  返回值: 对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentRequest> getStudentsWrapperByAge(
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetStudentsWrapperByAgeMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *参数 流 返回值 流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StreamRequest> bidirectionalTalk(
        io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBidirectionalTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *参数: 对象  返回值: 对象
     * </pre>
     */
    public com.dingwd.netty.study.grpc.proto.MyResponse getRealNameByUsername(com.dingwd.netty.study.grpc.proto.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *参数: 对象  返回值: 流对象 java中的 Iterator
     * </pre>
     */
    public java.util.Iterator<com.dingwd.netty.study.grpc.proto.StudentResponse> getStudentsByAge(
        com.dingwd.netty.study.grpc.proto.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentsByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *参数: 对象  返回值: 对象
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dingwd.netty.study.grpc.proto.MyResponse> getRealNameByUsername(
        com.dingwd.netty.study.grpc.proto.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_STUDENTS_BY_AGE = 1;
  private static final int METHODID_GET_STUDENTS_WRAPPER_BY_AGE = 2;
  private static final int METHODID_BIDIRECTIONAL_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.dingwd.netty.study.grpc.proto.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENTS_BY_AGE:
          serviceImpl.getStudentsByAge((com.dingwd.netty.study.grpc.proto.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentResponse>) responseObserver);
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
        case METHODID_GET_STUDENTS_WRAPPER_BY_AGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentsWrapperByAge(
              (io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StudentResponseList>) responseObserver);
        case METHODID_BIDIRECTIONAL_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.bidirectionalTalk(
              (io.grpc.stub.StreamObserver<com.dingwd.netty.study.grpc.proto.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dingwd.netty.study.grpc.proto.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetStudentsByAgeMethod())
              .addMethod(getGetStudentsWrapperByAgeMethod())
              .addMethod(getBidirectionalTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
