package com.miao.robot.client;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.MiaoResponse;

public abstract class DefaultMiaoClient implements MiaoClient {
    protected String serverUrl;

    public DefaultMiaoClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public abstract <T extends MiaoResponse> T execute(MiaoRequest<T> request) throws MiaoException;
}
