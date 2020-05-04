package com.dingwd.netty.study.httpHelloWorld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/4/27 21:38
 */
public class TestServer {
    public static void main(String[] args) throws Exception{
        //接受连接 给 workerGroup 死循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //请求处理 死循环
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //启动服务端的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitalizer());//自定义处理器

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
