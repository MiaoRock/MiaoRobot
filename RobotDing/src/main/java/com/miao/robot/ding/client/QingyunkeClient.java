package com.miao.robot.ding.client;

import com.miao.robot.ding.annotation.RequestHeaderAndBody;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("qingyunke")
public interface QingyunkeClient {
    @PostMapping("/robot/qingyunke")
    DefaultMiaoResponse execute(@RequestHeaderAndBody DefaultMiaoRequest request);
}
