package com.dingwd.netty.study.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/11 0011 14:37
 */
public class NioTest9{

    public static void main(String[] args) throws Exception{
        // mode r	以只读的方式打开文本，也就意味着不能用write来操作文件
        //rw	读操作和写操作都是允许的
        //rws	每当进行写操作，同步的刷新到磁盘，刷新内容和元数据
        //rwd	每当进行写操作，同步的刷新到磁盘，刷新内容
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        /*fileChannel.map() 三个参数
        1。映射模式: 读模式，写模式，读写模式
        2。position起始位置
        3。size 映射多少
         */
        MappedByteBuffer mappedByteBuffer =fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');

        randomAccessFile.close();
    }
}
