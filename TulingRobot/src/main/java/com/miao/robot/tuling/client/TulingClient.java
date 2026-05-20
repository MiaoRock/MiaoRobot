package com.miao.robot.tuling.client;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.Exception.ResponseMiaoException;
import com.miao.robot.client.MiaoClient;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.response.MiaoResponse;
import com.miao.robot.tuling.request.TulingRequest;
import com.miao.robot.tuling.response.TulingResponse;
import com.miao.robot.utils.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
@ConfigurationProperties(prefix = "tuling")
public class TulingClient extends MiaoClient {

    private String url;
    private String apkKey;
    private String userId;
    private static final String TEXT = "text";

    public DefaultMiaoResponse execute(DefaultMiaoRequest request) {
        DefaultMiaoResponse response = new DefaultMiaoResponse();
        TulingRequest tulingRequest = new TulingRequest();
        try {
            tulingRequest.getUserInfo().setApiKey(apkKey);
            tulingRequest.getUserInfo().setUserId(userId);
            String type = request.getType();
            switch (type) {
                case TEXT:
                    String text = request.getText();
                    tulingRequest.setReqType(0);
                    tulingRequest.getPerception().getInputText().setText(text);
                    break;
                default:
                    response.setType(TEXT);
                    response.setText("请求类型不支持");
                    throw new ResponseMiaoException();
            }
            TulingResponse tulingResponse = null;
            try {
                tulingResponse = this.execute(tulingRequest);
            } catch (MiaoException e) {
                log.error(e.getMessage());
                response.setType(TEXT);
                response.setText("Miao异常-" + e.getErrCode() + "-" + e.getErrMsg());
                throw new ResponseMiaoException();
            }
            if (tulingResponse.getIntent().getCode() != 0) {
                response.setType(TEXT);
                response.setText("tuling-err-" + tulingResponse.getIntent().getCode());
                throw new ResponseMiaoException();
            }
            String resultType = tulingResponse.getResults().get(0).getResultType();
            switch (resultType) {
                case TEXT:
                    response.setType(TEXT);
                    response.setText(tulingResponse.getResults().get(0).getValues().get(resultType));
                    log.info("Client[tuling]Req[" + request.getText() + "]Res[" + response.getText() + "]");
                    break;
                default:
                    response.setType(TEXT);
                    response.setText("响应类型不支持");
                    throw new ResponseMiaoException();
            }
        } catch (ResponseMiaoException e) {
            log.error("Client[tuling]Req[" + request.getText() + "]Res[" + response.getText() + "]");
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
