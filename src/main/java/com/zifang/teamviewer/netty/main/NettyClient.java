package com.zifang.teamviewer.netty.main;

import com.zifang.teamviewer.netty.endecoder.PacketDecoder;
import com.zifang.teamviewer.netty.endecoder.PacketEncoder;
import com.zifang.teamviewer.netty.handler.ControlResponseHandler;
import com.zifang.teamviewer.netty.handler.ImageResponseHandler;
import com.zifang.teamviewer.netty.handler.LoginResponseHandler;
import com.zifang.teamviewer.netty.handler.MessageResponseHandler;
import com.zifang.teamviewer.netty.packet.ControlRequestPacket;
import com.zifang.teamviewer.netty.packet.ImageRequestPacket;
import com.zifang.teamviewer.netty.packet.LoginRequestPacket;
import com.zifang.teamviewer.netty.packet.MessageRequestPacket;
import com.zifang.teamviewer.netty.utils.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private int MAX_RETRY = 5;
    private String host = "127.0.0.1";
    private int port = 8000;
    private Channel channel;

    public NettyClient(){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new ImageResponseHandler());
                        ch.pipeline().addLast(new ControlResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(future.isSuccess()){
            channel = future.channel();
        }
    }

    public void login(String userName,String password){
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(userName);
        loginRequestPacket.setPassword(password);
        loginRequestPacket.setUsername(userName);
        channel.writeAndFlush(loginRequestPacket);
    }

    public void control(String controllId){
        ControlRequestPacket controlRequestPacket = new ControlRequestPacket();
        controlRequestPacket.setUserTo(controllId);
        controlRequestPacket.setUserId(controllId);
        channel.writeAndFlush(controlRequestPacket);
//        ImageRequestPacket imageRequestPacket = new ImageRequestPacket();
//        imageRequestPacket.setControllerId(controllId);

        //channel.writeAndFlush(controlRequestPacket);
    }

    public void message(){
//        //message u3 aaaaa
//        String userTo = line.split(" ")[1];
//        String message = line.split(" ")[2];
//
//        MessageRequestPacket messageRequestPacket = new MessageRequestPacket(message);
//        messageRequestPacket.setUserTo(userTo);
//
//        channel.writeAndFlush(messageRequestPacket);
    }
}