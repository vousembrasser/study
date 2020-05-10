package com.dingwd.netty.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/9 0009 17:07
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        System.out.println("inputChannel: "+inputChannel.size());
        while (true) {
            //在使用一系列通道读取或放置操作填充此缓冲区之前，请调用此方法。
            System.out.println("inputChannel.position: "+inputChannel.position());
            byteBuffer.clear();
            System.out.println("inputChannel.position: "+inputChannel.position());

            System.out.println("clear position: " + byteBuffer.position());
            System.out.println("clear limit: " + byteBuffer.limit());
            System.out.println("clear capacity: " + byteBuffer.capacity());

            int read = inputChannel.read(byteBuffer);


            //System.out.println("read: "+ read);

            if (-1 == read) {
                break;
            }

            System.out.println("position: " + byteBuffer.position());
            System.out.println("limit: " + byteBuffer.limit());
            System.out.println("capacity: " + byteBuffer.capacity());
            byteBuffer.flip();

            outputChannel.write(byteBuffer);
            System.out.println("flip position: " + byteBuffer.position());
            System.out.println("flip limit: " + byteBuffer.limit());
            System.out.println("flip capacity: " + byteBuffer.capacity());
        }

        inputChannel.close();
        outputChannel.close();
    }
}
