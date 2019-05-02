package com.zifang.teamviewer.netty.handler;

import com.zifang.teamviewer.netty.utils.Session;
import com.zifang.teamviewer.netty.utils.SessionUtil;
import com.zifang.teamviewer.netty.packet.LoginRequestPacket;
import com.zifang.teamviewer.netty.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        System.out.println(new Date() + ": 收到客户端登录请求……");
        //System.out.println("人的信息是"+GsonUtil.objectToJsonStr(loginRequestPacket));

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        String userId = loginRequestPacket.getUserId();
        String userName = loginRequestPacket.getUsername();

        SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
