package com.dingwd.netty.study.grpc;


import com.dingwd.netty.study.grpc.proto.MyRequest;
import com.dingwd.netty.study.grpc.proto.MyResponse;
import com.dingwd.netty.study.grpc.proto.StreamRequest;
import com.dingwd.netty.study.grpc.proto.StreamResponse;
import com.dingwd.netty.study.grpc.proto.StudentServiceGrpc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/6 0006 16:17
 */
public class GrpcClient {

    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub;

    public GrpcClient(Channel channel) {
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        MyRequest request = MyRequest.newBuilder().setUsername(name).build();
        MyResponse response;
        try {
            response = blockingStub.getRealNameByUsername(request);
            System.out.println(response.getRealName());
        } catch (StatusRuntimeException e) {
            System.out.println("error: " + e.getStatus());
            return;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);
        ManagedChannel managedChannel = null;
        try {
            //System.out.println("----------------------------");
            //String user = "张三";
            //
            //ManagedChannel channel = ManagedChannelBuilder.forAddress("192.168.111.188",8899)
            //        .usePlaintext().build();
            //
            //    GrpcClient client = new GrpcClient(channel);
            //
            //    client.greet(user);
            //} finally {
            //    // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            //    // resources the channel should be shut down when it will no longer be used. If it may be used
            //    // again leave it running.
            //    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            //}
            //System.out.println("----------------------------");


            //getStudentsByAge方法
            //System.out.println("----------------------------");
            //managedChannel = ManagedChannelBuilder.forAddress("192.168.111.188", 8899)
            //        .usePlaintext().build();
            //MyResponse myResponse;
            //StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
            //try {
            //    blockingStub = StudentServiceGrpc
            //            .newBlockingStub(managedChannel);
            //
            //    myResponse = blockingStub
            //            .getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
            //    System.out.println(myResponse.getRealName());
            //
            //    Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
            //
            //    while (iterator.hasNext()) {
            //        StudentResponse studentResponse = iterator.next();
            //
            //        System.out.println(studentResponse.getName() + " " + studentResponse.getAge() + " " + studentResponse.getCity());
            //    }
            //    System.out.println("----------------------------");

            //getStudentsWrapperByAge方法
            //System.out.println("----------------------------");
            //managedChannel = ManagedChannelBuilder.forAddress("192.168.111.188", 8899)
            //        .usePlaintext().build();
            //StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
            //StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            //    @Override
            //    public void onNext(StudentResponseList value) {
            //        value.getStudentResponseList().forEach((student) -> {
            //            System.out.println(student.getName());
            //            System.out.println(student.getAge());
            //            System.out.println(student.getCity());
            //            System.out.println("****************");
            //        });
            //    }
            //
            //    @Override
            //    public void onError(Throwable t) {
            //
            //    }
            //
            //    @Override
            //    public void onCompleted() {
            //        System.out.println("onCompleted!");
            //    }
            //};
            //
            //StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWrapperByAge(studentResponseListStreamObserver);
            //studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
            //studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
            //studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
            //studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
            //studentRequestStreamObserver.onCompleted();
            //
            //Thread.sleep(1000 * 2);
            //System.out.println("----------------------------");


            System.out.println("----------------------------");
            managedChannel = ManagedChannelBuilder
                    .forAddress("localhost", 8899)
                    .usePlaintext()
                    .build();
            StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
            //在客户端上使用手动流控制和背压时，ClientResponseObserver会同时处理 request and response streams.
            ClientResponseObserver<StreamRequest, StreamResponse> clientResponseObserver
                    = new ClientResponseObserver<StreamRequest, StreamResponse>() {
                ClientCallStreamObserver<StreamRequest> requestStream;

                @Override
                public void beforeStart(ClientCallStreamObserver<StreamRequest> requestStream) {
                    this.requestStream = requestStream;
                    // @1设置手动流量控制
                    requestStream.disableAutoInboundFlowControl();

                    //为response stream设置一个可感知背压的生成器。 当使用方具有足够的缓冲区空间来接收更多消息时，将调用onReadyHandler。

                    /*
                     消息被序列化到特定于传输的传输缓冲区中。
                     根据此缓冲区的大小，可能会缓冲许多消息，但是尚未将其发送到服务器。
                     服务器必须调用request（）从客户端提取缓冲的消息。
                     */

                    /*
                    注意：onReadyHandler的调用与传入的StreamObserver的onNext（），onError（）和onComplete（）处理程序在同一线程池上序列化。
                    阻塞onReadyHandler将防止传入的StreamObserver处理其他消息。
                    onReadyHandler必须及时返回，否则消息处理吞吐量会受到影响。
                     */
                    // Server端需要调用request()向Client拉取消息
                    // @2 当Consumer端有足够空间时自动回调
                    // 序列化protobuf先发送到缓存区（还未到Server端）
                    requestStream.setOnReadyHandler(new Runnable() {
                        Iterator<String> iterator = names().iterator();

                        @Override
                        public void run() {

                            // Start generating values from where we left off on a non-gRPC thread.
                            while (requestStream.isReady()) {
                                if (iterator.hasNext()) {
                                    // Send more messages if there are more messages to send.
                                    // @3 将消息发送到缓存区
                                    String name = iterator.next();
                                    System.out.println("--> " + name);
                                    StreamRequest request = StreamRequest.newBuilder().setRequestInfo(name).build();
                                    requestStream.onNext(request);
                                } else {
                                    // Signal completion if there is nothing left to send.
                                    // @4 标记Client发送完成
                                    requestStream.onCompleted();
                                }
                            }

                        }
                    });
                }

                @Override
                public void onNext(StreamResponse value) {
                    // @5 接受Server端返回信息
                    System.out.println("<-- " + value.getResponseInfo());
                    // Signal the sender to send one message.
                    // @6 通知Client继续发送
                    requestStream.request(1);
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                    done.countDown();
                }

                @Override
                public void onCompleted() {
                    System.out.println("All Done");
                    done.countDown();
                }
            };

            stub.bidirectionalTalk(clientResponseObserver);//todo

            done.await();

            //StreamObserver<StreamRequest> requestStreamObserver = stub.bidirectionalTalk(new StreamObserver<StreamResponse>() {
            //    @Override
            //    public void onNext(StreamResponse value) {
            //        System.out.println(value.getResponseInfo());
            //    }
            //
            //    @Override
            //    public void onError(Throwable t) {
            //        System.out.println(t.getMessage());
            //    }
            //
            //    @Override
            //    public void onCompleted() {
            //        System.out.println("onCompleted");
            //    }
            //});
            //
            //for (int i = 0; i < 10; i++) {
            //    requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            //}
            //Thread.sleep(1000 * 5);
            System.out.println("----------------------------");
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            if (null != managedChannel) {
                managedChannel.shutdownNow().awaitTermination(10, TimeUnit.SECONDS);
            }
        }

    }

    private static List<String> names() {
        return Arrays.asList(
                "Sophia",
                "Jackson",
                //"Emma",
                //"Aiden",
                //"Olivia",
                //"Lucas",
                //"Ava",
                //"Liam",
                //"Mia",
                //"Noah",
                //"Isabella",
                //"Ethan",
                //"Riley",
                //"Mason",
                //"Aria",
                //"Caden",
                //"Zoe",
                //"Oliver",
                //"Charlotte",
                //"Elijah",
                //"Lily",
                //"Grayson",
                //"Layla",
                //"Jacob",
                //"Amelia",
                //"Michael",
                //"Emily",
                //"Benjamin",
                //"Madelyn",
                //"Carter",
                //"Aubrey",
                //"James",
                //"Adalyn",
                //"Jayden",
                //"Madison",
                //"Logan",
                //"Chloe",
                //"Alexander",
                //"Harper",
                //"Caleb",
                //"Abigail",
                //"Ryan",
                //"Aaliyah",
                //"Luke",
                //"Avery",
                //"Daniel",
                //"Evelyn",
                //"Jack",
                //"Kaylee",
                //"William",
                //"Ella",
                //"Owen",
                //"Ellie",
                //"Gabriel",
                //"Scarlett",
                //"Matthew",
                //"Arianna",
                //"Connor",
                //"Hailey",
                //"Jayce",
                //"Nora",
                //"Isaac",
                //"Addison",
                //"Sebastian",
                //"Brooklyn",
                //"Henry",
                //"Hannah",
                //"Muhammad",
                //"Mila",
                //"Cameron",
                //"Leah",
                //"Wyatt",
                //"Elizabeth",
                //"Dylan",
                //"Sarah",
                //"Nathan",
                //"Eliana",
                //"Nicholas",
                //"Mackenzie",
                //"Julian",
                //"Peyton",
                //"Eli",
                //"Maria",
                //"Levi",
                //"Grace",
                //"Isaiah",
                //"Adeline",
                //"Landon",
                //"Elena",
                //"David",
                //"Anna",
                //"Christian",
                //"Victoria",
                //"Andrew",
                //"Camilla",
                //"Brayden",
                //"Lillian",
                //"John",
                //"Natalie",
                "Lincoln"
        );
    }

}
