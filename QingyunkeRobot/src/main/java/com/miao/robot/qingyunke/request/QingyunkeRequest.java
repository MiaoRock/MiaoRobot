package com.miao.robot.qingyunke.request;

import com.miao.robot.qingyunke.response.QingyunkeResponse;
import com.miao.robot.request.MiaoRequest;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class QingyunkeRequest extends MiaoRequest<QingyunkeResponse> {

    private String key;
    private String appid;
    private String text;

    public QingyunkeRequest(String key, String appid, String text) {
        this.key = key;
        this.appid = appid;
        this.text = text;
        Map<String, String> param = new HashMap<>();
        param.put("key", key);
        param.put("appid", appid);
        param.put("msg", text);
        this.setParams(param);
    }

    @Override
    public Class<QingyunkeResponse> getResponseClass() {
        return QingyunkeResponse.class;
    }
}
