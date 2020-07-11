package com.miao.robot.request;

import com.miao.robot.response.QingyunkeResponse;

import java.util.HashMap;
import java.util.Map;

public class QingyunkeRequest extends MiaoRequest<QingyunkeResponse> {

    private String key;
    private String appid;
    private String text;

    public QingyunkeRequest(String key, String appid, String text) {
        this.key = key;
        this.appid = appid;
        this.text = text;
        Map<String, String> param = new HashMap();
        param.put("key", key);
        param.put("appid", appid);
        param.put("msg", text);
        this.setParams(param);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Class<QingyunkeResponse> getResponseClass() {
        return QingyunkeResponse.class;
    }
}
