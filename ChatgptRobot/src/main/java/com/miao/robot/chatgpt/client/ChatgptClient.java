package com.miao.robot.chatgpt.client;

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
import com.miao.robot.chatgpt.request.ChatgptRequest;
import com.miao.robot.utils.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Service
public class ChatgptClient extends MiaoClient {

    @Autowired
    ChatService chatService;

    @Autowired
    KeyManager keyManager;
    @Value("${chatgpt.url}")
    private String url;
    private static final String TEXT = "text";

    public DefaultMiaoResponse execute(DefaultMiaoRequest request) {
        DefaultMiaoResponse response = new DefaultMiaoResponse();
        ChatParam chatParam = new ChatParam();
        try {

            String type = request.getType();
            switch (type) {
                case TEXT:
                    String text = request.getText();
                    chatParam.setMessage(text);
                    chatParam.setKey(keyManager.getKey());
                    chatParam.setUrl(url);
                    Result result = chatService.chat(chatParam, "");
                    response.setText(result.getMsg());
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
