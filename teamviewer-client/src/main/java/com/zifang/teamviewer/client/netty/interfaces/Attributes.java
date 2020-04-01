package com.zifang.teamviewer.client.netty.interfaces;

import com.zifang.teamviewer.client.netty.utils.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
