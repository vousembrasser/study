package com.dingwd.netty.study.grpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/6 0006 16:10
 */
public class GrpcServer {

    private Server server;

    private void start() throws IOException {
        int port = 8899;
        this.server = ServerBuilder.forPort(port)
                .addService(new StudentServiceImpl())
                .build().start();

        System.out.println("server started!");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.out.println("********");
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    GrpcServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });

        System.out.println("执行到这里");
    }

    private void stop() throws InterruptedException {
        if (null != this.server) {
            this.server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcServer server = new GrpcServer();
        server.start();

        //不加这个main方法会直接退出
        server.awaitTermination();
    }
}
