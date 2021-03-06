package com.zifang.teamviewer.common.interfaces;

public interface Command {

    Byte COMMON_REQUEST = 0;

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte IMAGE_REQUEST = 5;

    Byte IMAGE_RESPONSE = 6;

    Byte CONTROL_REQUEST = 7;

    Byte CONTROL_RESPONSE = 8;
}
