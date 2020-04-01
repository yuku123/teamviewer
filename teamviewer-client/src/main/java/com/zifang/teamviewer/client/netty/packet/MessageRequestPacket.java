package com.zifang.teamviewer.client.netty.packet;

import static com.zifang.teamviewer.client.netty.interfaces.Command.MESSAGE_REQUEST;

public class MessageRequestPacket extends Packet {

    private String message;
    private String userTo;

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
