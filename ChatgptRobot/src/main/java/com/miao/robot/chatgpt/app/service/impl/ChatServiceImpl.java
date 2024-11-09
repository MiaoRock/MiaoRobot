package com.miao.robot.chatgpt.app.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.miao.robot.chatgpt.app.service.ChatService;
import com.miao.robot.chatgpt.app.service.KeyManager;
import com.miao.robot.chatgpt.app.service.ProxyService;
import com.miao.robot.chatgpt.entity.ChatParam;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述： 对话服务
 * 所属包名： com.miao.robot.chatgpt.app.service
 * 创建时间： 2024/2/1 5:37 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private final KeyManager keyManager;
    private final ProxyService proxyService;

    public ChatServiceImpl(KeyManager keyManager, ProxyService proxyService) {
        this.keyManager = keyManager;
        this.proxyService = proxyService;
    }

    @Override
    public ChatCompletionResponse chat(ChatParam param) {
        log.info("正在提问: {}", param.getMessages().get(0).getContent());
        ChatCompletionResponse completion = getText(param);
        return completion;
    }

    private ChatCompletionResponse getText(ChatParam param) {

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(param.getApiKey())
                .timeout(50)
                .apiHost(param.getApiHost())
                .build()
                .init();

        if (proxyService.isEnable()) {
            chatGPT.setProxy(proxyService.useProxy());
        }
        try {
            ChatCompletion chatCompletion = ChatCompletion.builder()
                    .model(param.getModel())
                    .messages(param.getMessages())
                    .maxTokens(param.getMax_tokens())
                    .temperature(param.getTemperature())
                    .stream(false)
                    .build();
            ChatCompletionResponse completion = chatGPT.chatCompletion(chatCompletion);
            log.info("chatgpt返回结果: {}", JSONObject.toJSONString(completion.getChoices()));
            return completion;
        } catch (Exception e) {
            log.error("API调用出错：{}", e);
            throw new RuntimeException("服务器挤爆了，请检查KEY， 网络。请输入你的APIKEY后试用: " + e.getMessage());
        }
    }
}
