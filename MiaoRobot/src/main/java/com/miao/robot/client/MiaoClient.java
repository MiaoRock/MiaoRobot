package com.miao.robot.client;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.MiaoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MiaoClient {

    public abstract String getUrl();

    public final <T extends MiaoResponse> T execute(MiaoRequest<T> request) throws MiaoException {
        return _execute(request);
    }

    protected abstract <T extends MiaoResponse> T _execute(MiaoRequest<T> request) throws MiaoException;
}
