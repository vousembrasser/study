package com.dingwd.netty.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/11 0011 13:36
 */
public class NIoTest8 {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        //ByteBuffer.allocate()和 ByteBuffer.allocateDirect() 小数据传输用前者 大数据传输用后者 频繁小数据io操作用前者
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);


        System.out.println("inputChannel: "+inputChannel.size());
        while (true) {
            //在使用一系列通道读取或放置操作填充此缓冲区之前，请调用此方法。
            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);


            //System.out.println("read: "+ read);

            if (-1 == read) {
                break;
            }

            byteBuffer.flip();

            outputChannel.write(byteBuffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
