package com.dingwd.netty.study.socketExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/4/28 0028 11:47
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 服务器段收到任意客户端发来的信息 channelRead0都会被调用
     *
     * @param ctx ChannelHandlerContext 保存着远程地址| 获取Channel对象
     * @param msg 获取请求参数
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress() + ", " + msg);

        //ctx.channel().writeAndFlush("from server:" + UUID.randomUUID());

        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发来消息: " + msg + "\n");
            } else {
                ch.writeAndFlush("自己 发来消息: " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        logger.info("IP地址为 [{}] 上线", channel.remoteAddress());
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelInactive()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        logger.info("IP地址为 [{}] 下线", channel.remoteAddress());
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        logger.info("服务器 [{}] 加入", channel.remoteAddress());

        channelGroup.writeAndFlush("服务器 - " + channel.remoteAddress() + "加入\n");

        channelGroup.add(channel);
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        logger.info("服务器 [{}] 退出", channel.remoteAddress());

        channelGroup.writeAndFlush("服务器 - " + channel.remoteAddress() + "退出\n");

        //ChannelGroup (GlobalEventExecutor.INSTANCE) 会自动执行remove方法
        //channelGroup.remove(channel);
    }
}
