package com.miao.robot.client;

import com.alibaba.fastjson.JSONObject;
import com.miao.robot.Enum.RobotMode;
import com.miao.robot.Exception.MiaoException;
import com.miao.robot.client.DefaultMiaoClient;
import com.miao.robot.client.MiaoClient;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.request.QingyunkeRequest;
import com.miao.robot.response.MiaoResponse;
import com.miao.robot.response.QingyunkeResponse;
import com.miao.robot.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class QingyunkeClient extends DefaultMiaoClient {

    private static final String url = "http://api.qingyunke.com/api.php";
    private final String key = "free";
    private final String appid = "0";
    private final RobotMode mode = RobotMode.qingyunke;

    public QingyunkeClient() {
        super(url);
    }

    public String execute(String text) throws MiaoException {
        QingyunkeRequest qingyunkeRequest = new QingyunkeRequest(key, appid, text);
        QingyunkeResponse qingyunkeResponse = this.execute(qingyunkeRequest);
        String qingyunkeResString = qingyunkeResponse.getContent();
        log.info("Client[" + mode + "]Req[" + text + "]Res[" + qingyunkeResString + "]");
        return qingyunkeResString.replace("菲菲", "小喵");
    }

    private <T extends MiaoResponse> T _execute(MiaoRequest<T> request) throws MiaoException {
        String responseString = HttpUtils.doGet(url, request.getParams());
        log.info(responseString);
        return JSONObject.parseObject(responseString, request.getResponseClass());
    }

    @Override
    public <T extends MiaoResponse> T execute(MiaoRequest<T> request) throws MiaoException {
        return _execute(request);
    }
}
