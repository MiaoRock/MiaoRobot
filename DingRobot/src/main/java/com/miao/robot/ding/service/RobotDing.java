package com.miao.robot.ding.service;

import com.miao.robot.Exception.ResponseMiaoException;
import com.miao.robot.ding.client.QingyunkeClient;
import com.miao.robot.ding.client.TulingClient;
import com.miao.robot.ding.request.DingRequest;
import com.miao.robot.ding.response.DingResponse;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RobotDing {

    @Autowired
    private CheckDing checkDing;
    @Autowired
    private TulingClient tulingClient;
    @Autowired
    private QingyunkeClient qingyunkeClient;

    private static final String TEXT = "text";
    private static final String TULING = "tuling";
    private static final String QINGYUNKE = "qingyunke";
    private static final String CHATGPT = "chatgpt";

    public DingResponse execute(DingRequest dingRequest) {
        DingResponse dingResponse = new DingResponse();
        if (checkDing.execute(dingRequest)) {
            return null;
        }

        // 去空格 转小写
        dingRequest.getText().setContent(dingRequest.getText().getContent().trim().toLowerCase());
        DefaultMiaoRequest request = new DefaultMiaoRequest();
        DefaultMiaoResponse response = new DefaultMiaoResponse();
        try {
            if (StringUtils.isEmpty(dingRequest.getText().getContent())) {
                dingResponse.setMsgtype(TEXT);
                dingResponse.getText().setContent("叫我干嘛");
                throw new ResponseMiaoException();
            } else {
                String msgType = dingRequest.getMsgtype();

                switch (msgType) {
                    case TEXT:
                        String text = dingRequest.getText().getContent();
                        request.setType(TEXT);
                        request.setText(text);
                        break;
                    default:
                        dingResponse.setMsgtype(TEXT);
                        dingResponse.getText().setContent("请求类型暂不支持");
                        throw new ResponseMiaoException();
                }

                String robotMode = InitParams.miaoParams.get("mode");
                switch (robotMode) {
                    case "normal":
                        break;
                    case QINGYUNKE:
                        request.setRobot(QINGYUNKE);
                        response = qingyunkeClient.execute(request);
                        break;
                    case TULING:
                        request.setRobot(TULING);
                        response = tulingClient.execute(request);
                        break;
                }
                String resType = response.getType();
                switch (resType) {
                    case TEXT:
                        dingResponse.setMsgtype(TEXT);
                        dingResponse.getText().setContent(response.getText());
                        log.info("Type[text]Req[" + dingRequest.getText().getContent() + "]Res[" + dingResponse.getText().getContent() + "]");
                        break;
                    default:
                        dingResponse.setMsgtype(TEXT);
                        dingResponse.getText().setContent("响应类型暂不支持");
                        throw new ResponseMiaoException();
                }
            }
        } catch (ResponseMiaoException e) {
            log.error("Type[text]Req[" + dingRequest.getText().getContent() + "]Res[" + dingResponse.getText().getContent() + "]");
        }
        return dingResponse;
    }
}
