package com.miao.robot.response;

import java.io.Serializable;
import java.util.Map;

public abstract class MiaoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Map<String, String> headerContent; // header

    private String body; // body

    private String requestUrl;

    private Map<String, String> params; // request parameter map

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getHeaderContent() {
        return headerContent;
    }

    public void setHeaderContent(Map<String, String> headerContent) {
        this.headerContent = headerContent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
