package com.miao.robot.chatgpt.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述： 读取配置文件中的key
 * 所属包名： com.wechat.chatgpt.app.handle
 * 创建时间： 2024/1/26 3:19 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "chatgpt.keys")
public class KeyConfig {
    List<String> list = new ArrayList<>();
}
