package com.miao.robot.chatgpt.client;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.Exception.ResponseMiaoException;
import com.miao.robot.client.MiaoClient;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.response.MiaoResponse;
import com.miao.robot.chatgpt.request.ChatgptRequest;
import com.miao.robot.chatgpt.response.ChatgptResponse;
import com.miao.robot.utils.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@ConfigurationProperties(prefix = "chatgpt")
public class ChatgptClient extends MiaoClient {

    private String url;
    private String apkKey;
    private String userId;
    private static final String TEXT = "text";

    public DefaultMiaoResponse execute(DefaultMiaoRequest request) {
        DefaultMiaoResponse response = new DefaultMiaoResponse();
        ChatgptRequest chatgptRequest = new ChatgptRequest();
        try {
            chatgptRequest.getUserInfo().setApiKey(apkKey);
            chatgptRequest.getUserInfo().setUserId(userId);
            String type = request.getType();
            switch (type) {
                case TEXT:
                    String text = request.getText();
                    chatgptRequest.setReqType(0);
                    chatgptRequest.getPerception().getInputText().setText(text);
                    break;
                default:
                    response.setType(TEXT);
                    response.setText("请求类型不支持");
                    throw new ResponseMiaoException();
            }
            ChatgptResponse chatgptResponse = null;
            try {
                chatgptResponse = this.execute(chatgptRequest);
            } catch (MiaoException e) {
                log.error(e.getMessage());
                response.setType(TEXT);
                response.setText("Miao异常-" + e.getErrCode() + "-" + e.getErrMsg());
                throw new ResponseMiaoException();
            }
            if (chatgptResponse.getIntent().getCode() != 0) {
                response.setType(TEXT);
                response.setText("Chatgpt-err-" + chatgptResponse.getIntent().getCode());
                throw new ResponseMiaoException();
            }
            String resultType = chatgptResponse.getResults().get(0).getResultType();
            switch (resultType) {
                case TEXT:
                    response.setType(TEXT);
                    response.setText(chatgptResponse.getResults().get(0).getValues().get(resultType));
                    log.info("Client[Chatgpt]Req[" + request.getText() + "]Res[" + response.getText() + "]");
                    break;
                default:
                    response.setType(TEXT);
                    response.setText("响应类型不支持");
                    throw new ResponseMiaoException();
            }
        } catch (ResponseMiaoException e) {
            log.error("Client[Chatgpt]Req[" + request.getText() + "]Res[" + response.getText() + "]");
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
