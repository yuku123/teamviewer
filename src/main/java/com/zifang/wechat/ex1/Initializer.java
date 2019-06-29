package com.zifang.wechat.ex1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

//初始化器
//channel注册后
public class Initializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // http的编解码器
        ch.pipeline().addLast(new HttpServerCodec());
        // 自定义
        ch.pipeline().addLast(new MyHandler());

    }
}
