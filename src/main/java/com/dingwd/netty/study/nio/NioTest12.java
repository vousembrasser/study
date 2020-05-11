package com.dingwd.netty.study.nio;

import java.nio.channels.Selector;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/11 0011 16:57
 */
public class NioTest12 {
    public static void main(String[] args) throws Exception{
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();


    }
}
