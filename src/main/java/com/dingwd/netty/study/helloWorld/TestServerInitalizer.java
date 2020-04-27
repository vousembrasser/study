package com.dingwd.netty.study.helloWorld;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/4/27 21:45
 */
public class TestServerInitalizer extends ChannelInitializer<SocketChannel> {
    //连接被注册之后会执行该方法
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());
    }
}
