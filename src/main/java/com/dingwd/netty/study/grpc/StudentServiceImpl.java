package com.dingwd.netty.study.grpc;


import com.dingwd.netty.study.grpc.proto.MyRequest;
import com.dingwd.netty.study.grpc.proto.MyResponse;
import com.dingwd.netty.study.grpc.proto.StudentServiceGrpc;

import io.grpc.stub.StreamObserver;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/6 0006 16:06
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    /**
     *
     * @param request
     * @param responseObserver 返回值填入这个参数中
     */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息: " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("zhangsan").build());
        responseObserver.onCompleted();

    }
}
