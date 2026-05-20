package com.miao.robot.chatgpt.app.service.impl;

import com.miao.robot.chatgpt.app.service.ProxyService;
import com.plexpt.chatgpt.util.Proxys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Proxy;

@Data
@Service
public class ProxyServiceImpl implements ProxyService {
    @Value("${proxy.enable}")
    private Boolean enable;
    @Value("${proxy.type}")
    private String type;
    @Value("${proxy.host}")
    private String host;
    @Value("${proxy.port}")
    private int port;

    @Override
    public Boolean isEnable() {
        return enable;
    }

    @Override
    public Proxy useProxy() {
        switch (type) {
            case "http":
                return Proxys.http(host, port);
            case "socks5":
                return Proxys.socks5(host, port);
            default:
                throw new RuntimeException("创建代理失败，请检查代理服务器是否可用");
        }
    }
}
