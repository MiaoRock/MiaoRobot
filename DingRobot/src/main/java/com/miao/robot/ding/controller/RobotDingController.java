package com.miao.robot.ding.controller;

import com.miao.robot.ding.annotation.RequestHeaderAndBody;
import com.miao.robot.ding.request.DingRequest;
import com.miao.robot.ding.response.DingResponse;
import com.miao.robot.ding.service.RobotDing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/robot")
public class RobotDingController {

    @Autowired
    private RobotDing robotDing;

    @ResponseBody
    @PostMapping(value = "/ding")
    public DingResponse DingRobot(@RequestHeaderAndBody DingRequest dingRequest) {
        return robotDing.execute(dingRequest);
    }

}
