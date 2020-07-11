package com.miao.robot.ding.controller;

import com.miao.robot.ding.annotation.RequestHeaderAndBody;
import com.miao.robot.ding.entity.MiaoParams;
import com.miao.robot.ding.service.RobotDing;
import com.miao.robot.ding.request.DingRequest;
import com.miao.robot.ding.response.DingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/robot")
public class RobotDingController {

    @Autowired
    private RobotDing robotDing;

    @ResponseBody
    @RequestMapping(value = "/ding", method = RequestMethod.POST)
    public DingResponse DingRobot(@RequestHeaderAndBody DingRequest dingRequest) {
        return robotDing.execute(dingRequest);
    }

}
