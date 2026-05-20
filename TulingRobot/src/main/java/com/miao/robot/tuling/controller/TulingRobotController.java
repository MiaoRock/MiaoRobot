package com.miao.robot.tuling.controller;

import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.tuling.annotation.RequestHeaderAndBody;
import com.miao.robot.tuling.client.TulingClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/robot")
public class TulingRobotController {
    @Autowired
    private TulingClient client;

    @ResponseBody
    @PostMapping(value = "/tuling")
    public DefaultMiaoResponse TulingRobot(@RequestHeaderAndBody DefaultMiaoRequest request) {
        return client.execute(request);
    }
}
