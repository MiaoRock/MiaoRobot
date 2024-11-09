package com.miao.robot.chatgpt.controller;

import com.miao.robot.chatgpt.client.ChatgptClient;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.chatgpt.annotation.RequestHeaderAndBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/robot")
public class ChatgptRobotController {

    private ChatgptClient client;

    public ChatgptRobotController(ChatgptClient client) {
        this.client = client;
    }

    @ResponseBody
    @PostMapping(value = "/chatgpt")
    public DefaultMiaoResponse chatGPT(@RequestHeaderAndBody DefaultMiaoRequest request) {
        return client.execute(request);
    }
}
