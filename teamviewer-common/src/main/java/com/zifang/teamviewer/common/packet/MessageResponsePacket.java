package com.zifang.teamviewer.common.packet;


import static com.zifang.teamviewer.common.interfaces.Command.MESSAGE_RESPONSE;

public class MessageResponsePacket extends Packet {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
