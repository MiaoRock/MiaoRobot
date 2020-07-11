package com.miao.robot.ding.client;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DingMiaoClient {

    public static String access = "346b9182eaa24e76e2d0ad3039b1395fa4b4653c5c6b2b35a0289d0d5a27eb73";

    public static void main(String[] args) throws ApiException {
        String serverUrl = "https://oapi.dingtalk.com/robot/send?access_token=" + DingMiaoClient.access;
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();

        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("我已经升完级了，开心哟~");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("+86-18640093452"));
//        at.setIsAtAll(true);
        request.setAt(at);

        OapiRobotSendResponse response = client.execute(request);
    }
}
