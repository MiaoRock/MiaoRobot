package com.miao.robot.chatgpt.app.service;

import java.net.Proxy;

public interface ProxyService {

    Boolean isEnable();

    Proxy useProxy();
}
