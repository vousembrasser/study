package com.dingwd.netty.study.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/9 0009 15:04
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("NIoTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while(byteBuffer.remaining()>0){
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char)b);
        }

        fileInputStream.close();
    }
}
