package com.miao.robot.qingyunke.controller;

import com.miao.robot.qingyunke.annotation.RequestHeaderAndBody;
import com.miao.robot.qingyunke.client.QingyunkeClient;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/robot")
public class QingyunkeRobotController {

    @Autowired
    private QingyunkeClient client;

    @ResponseBody
    @PostMapping(value = "/qingyunke")
    public DefaultMiaoResponse TulingRobot(@RequestHeaderAndBody DefaultMiaoRequest request) {
        return client.execute(request);
    }
}
