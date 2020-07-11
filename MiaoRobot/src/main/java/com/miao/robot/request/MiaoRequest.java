package com.miao.robot.request;

import com.miao.robot.response.MiaoResponse;

import java.io.Serializable;
import java.util.Map;

public abstract class MiaoRequest<T extends MiaoResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, String> headerMap; // HTTP请求头参数
    private Map<String, String> params; // 自定义参数
    private long timestamp; // 请求时间戳

    public abstract Class<T> getResponseClass();

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
