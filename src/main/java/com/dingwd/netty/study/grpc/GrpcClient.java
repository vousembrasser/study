package com.dingwd.netty.study.grpc;


import com.dingwd.netty.study.grpc.proto.MyRequest;
import com.dingwd.netty.study.grpc.proto.MyResponse;
import com.dingwd.netty.study.grpc.proto.StreamRequest;
import com.dingwd.netty.study.grpc.proto.StreamResponse;
import com.dingwd.netty.study.grpc.proto.StudentRequest;
import com.dingwd.netty.study.grpc.proto.StudentResponse;
import com.dingwd.netty.study.grpc.proto.StudentResponseList;
import com.dingwd.netty.study.grpc.proto.StudentServiceGrpc;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

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
            System.out.println(response.getRealname());
        } catch (StatusRuntimeException e) {
            System.out.println("error: " + e.getStatus());
            return;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //String user = "张三";
        //
        //ManagedChannel channel = ManagedChannelBuilder.forAddress("192.168.111.188",8899)
        //        .usePlaintext().build();
        //
        //try {
        //    GrpcClient client = new GrpcClient(channel);
        //
        //    client.greet(user);
        //} finally {
        //    // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
        //    // resources the channel should be shut down when it will no longer be used. If it may be used
        //    // again leave it running.
        //    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        //}


        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("192.168.111.188", 8899)
                .usePlaintext().build();
        MyResponse myResponse;
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
        try {
            blockingStub = StudentServiceGrpc
                    .newBlockingStub(managedChannel);

            myResponse = blockingStub
                    .getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
            System.out.println(myResponse.getRealname());

            System.out.println("----------------------------");
            Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

            while (iterator.hasNext()) {
                StudentResponse studentResponse = iterator.next();

                System.out.println(studentResponse.getName() + " " + studentResponse.getAge() + " " + studentResponse.getCity());
            }
            System.out.println("----------------------------");


            System.out.println("----------------------------");

            StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
            StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
                @Override
                public void onNext(StudentResponseList value) {
                    value.getStudentResponseList().forEach((student) -> {
                        System.out.println(student.getName());
                        System.out.println(student.getAge());
                        System.out.println(student.getCity());
                        System.out.println("****************");
                    });
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    System.out.println("onCompleted!");
                }
            };

            StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWrapperByAge(studentResponseListStreamObserver);
            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
            studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
            studentRequestStreamObserver.onCompleted();

            Thread.sleep(1000 * 2);
            System.out.println("----------------------------");

            System.out.println("----------------------------");
            StreamObserver<StreamRequest> requestStreamObserver = stub.bidirectionalTalk(new StreamObserver<StreamResponse>() {
                @Override
                public void onNext(StreamResponse value) {
                    System.out.println(value.getResponseInfo());
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("onCompleted");
                }
            });

            for (int i = 0; i < 10; i++) {
                requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            }
            Thread.sleep(1000*5);
            System.out.println("----------------------------");
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            managedChannel.shutdownNow().awaitTermination(10, TimeUnit.SECONDS);
        }
    }


}
