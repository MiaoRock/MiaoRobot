package com.miao.robot.ding.client;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class DingMiaoClient {

    public static String access = "XXXX";

    public static void main(String[] args) throws ApiException {
        String serverUrl = "https://oapi.dingtalk.com/robot/send?access_token=" + DingMiaoClient.access;
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();

        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试一下哦，别在意~");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("+86-XXXXXXXXXXX"));
//        at.setIsAtAll(false);
        request.setAt(at);

        OapiRobotSendResponse response = client.execute(request);
    }
}
