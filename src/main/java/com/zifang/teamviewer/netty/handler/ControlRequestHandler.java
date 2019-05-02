package com.zifang.teamviewer.netty.handler;

import com.zifang.teamviewer.netty.utils.Session;
import com.zifang.teamviewer.netty.utils.SessionUtil;
import com.zifang.teamviewer.netty.packet.ControlRequestPacket;
import com.zifang.teamviewer.netty.packet.ControlResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ControlRequestHandler extends SimpleChannelInboundHandler<ControlRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ControlRequestPacket controlRequestPacket) {
        ControlResponsePacket controlResponsePacket = new ControlResponsePacket();

        Session session = SessionUtil.getSession(ctx.channel());
        controlResponsePacket.setUserId(controlRequestPacket.getUserId());
        controlResponsePacket.setUserTo(controlRequestPacket.getUserTo());
        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(controlRequestPacket.getUserTo());

        if(toUserChannel == null){
            System.out.println(controlRequestPacket.getUserTo() +"::::将要被控制的user不存在");
            controlResponsePacket.setMessage("控制失败");
            ctx.channel().writeAndFlush(controlResponsePacket);
        }else{
            toUserChannel.writeAndFlush(controlResponsePacket);
        }
    }
}
