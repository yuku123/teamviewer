package com.zifang.wechat.ex2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSInitialer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //ws 基于http协议
        channelPipeline.addLast(new HttpServerCodec());
        //写大数据流
        channelPipeline.addLast(new ChunkedWriteHandler());
        //http的聚合器 聚合成FullHttp
        channelPipeline.addLast(new HttpObjectAggregator(1024*60));
        // -------支持http支持--------
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        channelPipeline.addLast(null);
    }
}
