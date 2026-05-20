package com.miao.robot.chatgpt.app.service;

import com.miao.robot.chatgpt.config.KeyConfig;
import com.miao.robot.chatgpt.utils.CircularBlockQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述： key管理
 * 所属包名： com.wechat.chatgpt.app.service
 * 创建时间： 2024/1/26 5:31 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@Slf4j
@Service
public class KeyManager implements ApplicationRunner {
    private static CircularBlockQueueUtil<String> keyQueue = new CircularBlockQueueUtil<>();
    private final KeyConfig keyConfig;

    public KeyManager(KeyConfig keyConfig) {
        this.keyConfig = keyConfig;
    }

    public synchronized String getKey() {
        return keyQueue.next();
    }

    public synchronized String getSingleKey() {
        return keyQueue.peek();
    }


    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("开始配置KEY队列");
            List<String> list = keyConfig.getList();
            int size = list.size();
            log.info("找到{}个配置的KEY", size);
            for (String key : list) {
                keyQueue.add(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
