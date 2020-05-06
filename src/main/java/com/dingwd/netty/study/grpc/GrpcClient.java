package com.dingwd.netty.study.grpc;


import com.dingwd.netty.study.grpc.proto.MyRequest;
import com.dingwd.netty.study.grpc.proto.MyResponse;
import com.dingwd.netty.study.grpc.proto.StudentServiceGrpc;

import java.util.concurrent.TimeUnit;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

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
        try {
            StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
                    .newBlockingStub(managedChannel);

            myResponse = blockingStub
                    .getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
        System.out.println(myResponse.getRealname());

    }

}
