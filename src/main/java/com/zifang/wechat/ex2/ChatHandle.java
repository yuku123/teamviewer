package com.zifang.wechat.ex2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 处理消息的handler,用于在ws中专门处理消息的对象
 *
 * */
public class ChatHandle  extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于管理所有的chnnela
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //前端传送过来的东西
        String content =  msg.text();
        System.out.println("收到"+content);

        for(Channel channel : channels){
            channel.writeAndFlush(new TextWebSocketFrame("[serve]"+ LocalDateTime.now()+content));
        }
        //批量处理
//        channels.writeAndFlush()

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //新开界面的时候，打开了新的连接，会添加到这个地方
        channels.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发这个方法就会自动移除hannel
        //channels.remove(ctx.channel());
        System.out.println("移除，长id为："+ctx.channel().id().asLongText());
        super.handlerRemoved(ctx);
    }
}
