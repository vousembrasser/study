package com.dingwd.netty.study.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/9 0009 15:11
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] messages = "hello world welcome, nihao".getBytes();

        for (byte message : messages) {
            byteBuffer.put(message);
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
}
