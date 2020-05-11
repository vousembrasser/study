package com.dingwd.netty.study.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * 关于Buffer的Scattering与Gathering
 *
 * @author: dingwd
 * @date: 2020/5/11 0011 14:58
 */
public class NioTest11 {

    /*Scattering将一个Channel中的数据读取到多个ByteBuffer中
      总是按照顺序写入，只有把第一个ByteBuffer写满之后才会写入下一个ByteBuffer
     */
    /*
    Gathering 将ByteBuffer中的数据写入到Channel中按顺序写
    第一个ByteBuffer写完之后第二个再写
     */
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("192.168.111.188",8899);
        serverSocketChannel.socket().bind(address);

        ByteBuffer[] byteBuffer = new ByteBuffer[3];
        byteBuffer[0] = ByteBuffer.allocate(2);
        byteBuffer[1] = ByteBuffer.allocate(3);
        byteBuffer[2] = ByteBuffer.allocate(4);

        int messageLength = 2 + 3 + 4;

        //accept方法会等待客户端和服务端连接 没有连接上会阻塞
        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;
            while (bytesRead < messageLength) {
                long r = socketChannel.read(byteBuffer);
                bytesRead += r;

                System.out.println("byteRead: " + bytesRead);

                Arrays.stream(byteBuffer).map(buffer -> "position: " + buffer.position() + " , limit: " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffer).forEach(buffer-> {
                buffer.flip();
            });

            long bytesWritten = 0;
            while (bytesWritten < messageLength) {
                long r = socketChannel.write(byteBuffer);
                bytesWritten += r;
            }

            Arrays.asList(byteBuffer).forEach(ByteBuffer::clear);

            System.out.println("bytesRead: " + bytesRead + " , bytesWritten: " + bytesWritten+ ", messageLength: "+ messageLength);
        }
    }

}
