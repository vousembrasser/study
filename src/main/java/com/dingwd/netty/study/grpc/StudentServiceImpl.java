package com.dingwd.netty.study.grpc;


import com.dingwd.netty.study.grpc.proto.HelloReply;
import com.dingwd.netty.study.grpc.proto.MyRequest;
import com.dingwd.netty.study.grpc.proto.MyResponse;
import com.dingwd.netty.study.grpc.proto.StreamRequest;
import com.dingwd.netty.study.grpc.proto.StreamResponse;
import com.dingwd.netty.study.grpc.proto.StudentRequest;
import com.dingwd.netty.study.grpc.proto.StudentResponse;
import com.dingwd.netty.study.grpc.proto.StudentResponseList;
import com.dingwd.netty.study.grpc.proto.StudentServiceGrpc;

import io.grpc.Status;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/6 0006 16:06
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    /**
     * @param responseObserver 返回值填入这个参数中
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息: " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealName("zhangsan").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息: " + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("zhangsan1").setAge(20).setCity("南京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("zhangsan2").setAge(30).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("zhangsan3").setAge(40).setCity("东京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("zhangsan4").setAge(50).setCity("天津").build());

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAge(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("oneNext:" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError:" + t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse0 = StudentResponse.newBuilder().setName("zhang1").setAge(20).setCity("南京").build();
                StudentResponse studentResponse1 = StudentResponse.newBuilder().setName("zhang2").setAge(30).setCity("北京").build();

                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(studentResponse0)
                        .addStudentResponse(studentResponse1)
                        .build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> bidirectionalTalk(StreamObserver<StreamResponse> responseObserver) {
        // Set up manual flow control for the request stream. It feels backwards to configure the request
        // stream's flow control using the response stream's observer, but this is the way it is.
        /*
        为请求流设置手动流控制。 使用响应流的观察者配置请求流的流控制是一种倒退的感觉，但是事实就是这样。
         */
        ServerCallStreamObserver<StreamResponse> serverCallStreamObserver = (ServerCallStreamObserver<StreamResponse>) responseObserver;
        // @2 禁止自动流控模式，开启手动流控
        serverCallStreamObserver.disableAutoInboundFlowControl();
        // Set up a back-pressure-aware consumer for the request stream. The onReadyHandler will be invoked
        // when the consuming side has enough buffer space to receive more messages.
        /*
        为请求流设置一个可感知背压的使用者。 当使用方具有足够的缓冲区空间来接收更多消息时，将调用onReadyHandler。
         */
        //
        // Note: the onReadyHandler's invocation is serialized on the same thread pool as the incoming StreamObserver's
        // onNext(), onError(), and onComplete() handlers. Blocking the onReadyHandler will prevent additional messages
        // from being processed by the incoming StreamObserver. The onReadyHandler must return in a timely manner or
        // else message processing throughput will suffer.
        /*
        注意：onReadyHandler的调用与传入的StreamObserver的onNext（），onError（）和onComplete（）处理程序在同一线程池上序列化。
        阻塞onReadyHandler将防止传入的StreamObserver处理其他消息。 onReadyHandler必须及时返回，否则消息处理吞吐量会受到影响。
         */
        // @3 背压模式流控，当消费端有足够空间时将会回调OnReadyHandler
        // 默认空间大小为65536字节
        class OnReadyHandler implements Runnable {
            // Guard against spurious onReady() calls caused by a race between onNext() and onReady(). If the transport
            // toggles isReady() from false to true while onNext() is executing, but before onNext() checks isReady(),
            // request(1) would be called twice - once by onNext() and once by the onReady() scheduled during onNext()'s
            // execution.
            /*
            防止因 onNext（）和 onReady（）之间的竞争而导致虚假的onReady（）调用。
            如果在onNext（）执行期间但在onNext（）检查isReady（）之前，传输将isReady（）从false切换为true，
            则request（1）将被调用两次-一次由onNext（）和一次由计划的onReady（）进行 在onNext（）执行期间。
             */
            private boolean wasReady = false;

            @Override
            public void run() {
                if (serverCallStreamObserver.isReady() && !wasReady) {
                    wasReady = true;
                    System.out.println("READY");
                    // Signal the request sender to send one message. This happens when isReady() turns true, signaling that
                    // the receive buffer has enough free space to receive more messages. Calling request() serves to prime
                    // the message pump.
                    /*
                    向请求发送者发送信号以发送一条消息。
                    当isReady（）变为true时，就会发生这种情况，表示接收缓冲区有足够的可用空间来接收更多消息。
                    调用request（）用于启动消息泵。
                     */
                    // @4 向HTTP/2流请求读取并解压(x)条消息
                    // 即发信号通知发送端发送继续发消息
                    System.out.println("isReady() && !wasReady");
                    serverCallStreamObserver.request(1);
                }
            }
        }
        final OnReadyHandler onReadyHandler = new OnReadyHandler();
        serverCallStreamObserver.setOnReadyHandler(onReadyHandler);

        // Give gRPC a StreamObserver that can observe and process incoming requests.
        // @5 处理具体进来的请求
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest request) {
                // Process the request and send a response or an error.
                try {
                    // Accept and enqueue the request.
                    String name = request.getRequestInfo();
                    //System.out.println("--> " + name);

                    // Simulate server "work"
                    Thread.sleep(100);

                    // Send a response.
                    String message = "Hello " + name;
                    //System.out.println("<-- " + message);
                    StreamResponse reply = StreamResponse.newBuilder().setResponseInfo(message).build();
                    // @6 向Client发送请求
                    responseObserver.onNext(reply);

                    // Check the provided ServerCallStreamObserver to see if it is still ready to accept more messages.
                    if (serverCallStreamObserver.isReady()) {
                        // Signal the sender to send another request. As long as isReady() stays true, the server will keep
                        // cycling through the loop of onNext() -> request(1)...onNext() -> request(1)... until the client runs
                        // out of messages and ends the loop (via onCompleted()).
                        //
                        // If request() was called here with the argument of more than 1, the server might runs out of receive
                        // buffer space, and isReady() will turn false. When the receive buffer has sufficiently drained,
                        // isReady() will turn true, and the serverCallStreamObserver's onReadyHandler will be called to restart
                        // the message pump.
                        // @7 向HTTP/2流请求读取并解压(x)条消息
                        System.out.println("isReady()");
                        serverCallStreamObserver.request(1);
                    } else {
                        // If not, note that back-pressure has begun.
                        System.out.println("isReady is false");
                        onReadyHandler.wasReady = false;
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    responseObserver.onError(
                            Status.UNKNOWN.withDescription("Error handling request").withCause(throwable).asException());
                }
            }

            @Override
            public void onError(Throwable t) {
                // End the response stream if the client presents an error.
                t.printStackTrace();
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                // Signal the end of work when the client ends the request stream.
                System.out.println("COMPLETED");
                responseObserver.onCompleted();
            }
        };

        //return new StreamObserver<StreamRequest>() {
        //    @Override
        //    public void onNext(StreamRequest value) {
        //        System.out.println("接收到客户端消息: "+value.getRequestInfo());
        //
        //        responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
        //    }
        //
        //    @Override
        //    public void onError(Throwable t) {
        //        System.out.println("报错: "+t.getMessage());
        //    }
        //
        //    @Override
        //    public void onCompleted() {
        //        responseObserver.onCompleted();
        //    }
        //};

    }
}
