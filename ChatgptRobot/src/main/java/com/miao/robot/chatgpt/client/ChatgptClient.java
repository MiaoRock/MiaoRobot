package com.miao.robot.chatgpt.client;

import com.alibaba.fastjson2.JSONObject;
import com.miao.robot.Exception.MiaoException;
import com.miao.robot.Exception.ResponseMiaoException;
import com.miao.robot.chatgpt.app.service.ChatService;
import com.miao.robot.chatgpt.app.service.KeyManager;
import com.miao.robot.chatgpt.entity.ChatParam;
import com.miao.robot.chatgpt.entity.Result;
import com.miao.robot.client.MiaoClient;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.response.MiaoResponse;
import com.miao.robot.utils.HttpUtils;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
@Service
public class ChatgptClient extends MiaoClient {

    @Value("${chatgpt.url}")
    private String url;
    @Value("${chatgpt.model}")
    private String model;
    private final String TEXT = "text";
    private final ChatService chatService;
    private final KeyManager keyManager;
    private List<Message> messages = new ArrayList<>();

    public ChatgptClient(ChatService chatService, KeyManager keyManager) {
        this.chatService = chatService;
        this.keyManager = keyManager;
    }

    public DefaultMiaoResponse execute(DefaultMiaoRequest request) {
        DefaultMiaoResponse response = new DefaultMiaoResponse();
        Message message = new Message("user", request.getText());
        messages.add(message);
        ChatParam chatParam = new ChatParam(messages);
        chatParam.setApiKey(keyManager.getSingleKey());
        chatParam.setModel(model);
        chatParam.setApiHost(url);
        chatParam.setMax_tokens(1000);
        chatParam.setTemperature(0.00);
        try {
            String type = request.getType();
            switch (type) {
                case TEXT:
                    ChatCompletionResponse chatCompletionResponse = chatService.chat(chatParam);
                    log.info("chatgpt返回结果: {}", JSONObject.toJSONString(chatCompletionResponse));
                    response.setText(chatCompletionResponse.getChoices().get(0).getMessage().getContent());
                    break;
                default:
                    response.setType(TEXT);
                    response.setText("请求类型不支持");
                    throw new ResponseMiaoException();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setType(TEXT);
            response.setText("Miao异常-" + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    protected <T extends MiaoResponse> T _execute(MiaoRequest<T> request) throws MiaoException {
        return HttpUtils.doPost(getUrl(), request);
    }
}
