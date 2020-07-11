package com.miao.robot.client;

import com.alibaba.fastjson.JSONObject;
import com.miao.robot.Enum.RobotMode;
import com.miao.robot.Exception.MiaoException;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.request.TulingRequest;
import com.miao.robot.response.MiaoResponse;
import com.miao.robot.response.TulingResponse;
import com.miao.robot.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TulingClient extends DefaultMiaoClient {

    private static final String url = "http://openapi.tuling123.com/openapi/api/v2";
    private static final String apkKey = "1042551aec434bdc8d409ff30b5e8637";
    private static final String userId = "640596";
    private final RobotMode mode = RobotMode.tuling;

    public TulingClient() {
        super(url);
    }

    public String execute(String text) throws MiaoException {
        TulingRequest tulingRequest = new TulingRequest();
        TulingRequest.Perception perception = new TulingRequest.Perception();
        TulingRequest.Perception.InputText inputText = new TulingRequest.Perception.InputText();
        inputText.setText(text);
        perception.setInputText(inputText);
        tulingRequest.setPerception(perception);
        TulingRequest.UserInfo userInfo = new TulingRequest.UserInfo();
        userInfo.setApiKey(apkKey);
        userInfo.setUserId(userId);
        tulingRequest.setUserInfo(userInfo);
        TulingResponse tulingResponse = this.execute(tulingRequest);
        String tulingResString = tulingResponse.getResults().get(0).getValues().get(tulingResponse.getResults().get(0).getResultType());
        log.info("Client[" + mode + "]Req[" + text + "]Res[" + tulingResString + "]");
        String resp = tulingResString;
        return resp;
    }

    private <T extends MiaoResponse> T _execute(MiaoRequest<T> request) throws MiaoException {
        log.info(JSONObject.toJSONString(request));
        String responseString = HttpUtils.doPost(url, JSONObject.toJSONString(request));
        log.info(responseString);
        T tRsp = JSONObject.parseObject(responseString, request.getResponseClass());
        return tRsp;
    }

    @Override
    public <T extends MiaoResponse> T execute(MiaoRequest<T> request) throws MiaoException {
        return _execute(request);
    }
}
