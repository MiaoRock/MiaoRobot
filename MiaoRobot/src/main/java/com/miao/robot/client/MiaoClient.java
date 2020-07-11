package com.miao.robot.client;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.MiaoResponse;

public interface MiaoClient {

    <T extends MiaoResponse> T execute(MiaoRequest<T> request) throws MiaoException;
}
