package com.dingwd.netty.study.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/11 0011 14:54
 */
public class NioTest10 {
    /*
    锁
     */
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        //true共享锁 false排他锁
        FileLock fileLock = fileChannel.lock(3,6,true);
        System.out.println("vaild: " + fileLock.isValid());
        System.out.println("lock type: " + fileLock.isShared());
        fileLock.release();
        randomAccessFile.close();
    }
}
