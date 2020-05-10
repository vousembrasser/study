package com.dingwd.netty.study.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/9 0009 14:24
 */
public class NioTest1 {
    public static void main(String[] args) {
        //分配一个存放整数大小为10的缓冲区
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("capacity" + buffer.capacity());

        //for (int i = 0; i < buffer.capacity(); i++) {
        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        System.out.println("before flip Limit: "+ buffer.limit());

        //反转
        buffer.flip();

        System.out.println("after flip Limit: "+ buffer.limit());

        while (buffer.hasRemaining()) {
            System.out.println("position: " + buffer.position());
            System.out.println("limit: " + buffer.limit());
            System.out.println("capacity: " + buffer.capacity());

            System.out.println(buffer.get());
        }
    }
}
