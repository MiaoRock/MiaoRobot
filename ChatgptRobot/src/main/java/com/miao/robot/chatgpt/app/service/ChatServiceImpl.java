package com.miao.robot.chatgpt.app.service;

import com.miao.robot.chatgpt.entity.ChatParam;
import com.miao.robot.chatgpt.entity.Result;
import com.miao.robot.utils.StringUtils;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ChatServiceImpl implements ChatService{
    @Autowired
    KeyManager keyManager;
    //上下文
    private static Map<String, List<Message>> context = new HashMap<>();
    @Override
    public Result chat(ChatParam param, String balance) {
        if(StringUtils.isNotEmpty(balance)){
            //TODO 余额查询
        }
        log.info("正在提问: " + param.getMessage());
        Message message = getText(param);
        String text = message.getContent();


        log.info("问题：" + param.getMessage() + "\n回答：" + text);
        return Result.ok(text);
    }
    private Message getText(ChatParam param) {
        boolean haskey = param.hasKey();

        String key = keyManager.getKey();

        if (haskey) {
            key = param.getKey();
            System.out.println("key:" + key);
        }

        String prompt = param.buildPrompt();


        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(key)
                .apiHost(param.getUrl())
                .timeout(50)
                .build()
                .init();

//        if (proxyService.isEnable()) {
//            chatGPT.setProxy(proxyService.useProxy());
//        }
        try {
            Message message = Message.of(prompt);

            List<Message> messages = context.get(param.getId());
            if (messages == null) {
                messages = new ArrayList<>();
                context.put(param.getId(), messages);
            }
            messages.add(message);

            ChatCompletionResponse completion = chatGPT.chatCompletion(messages);
            Message message1 = completion.getChoices().get(0).getMessage();


            return message1;

        } catch (Exception e) {
            log.error("API调用出错：{}", e);
            throw new RuntimeException("服务器挤爆了，请检查KEY， 网络。请输入你的APIKEY后试用: " + e.getMessage());
        }
    }
}
