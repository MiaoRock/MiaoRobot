package com.miao.robot.ding.service;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.client.QingyunkeClient;
import com.miao.robot.Enum.RobotMode;
import com.miao.robot.client.TulingClient;
import com.miao.robot.ding.request.DingRequest;
import com.miao.robot.ding.response.DingResponse;
import com.miao.robot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RobotDing {

    @Autowired
    private CheckDing checkDing;

    public DingResponse execute(DingRequest dingRequest) {
        DingResponse dingResponse = new DingResponse();
        if (checkDing.execute(dingRequest)) {
            return null;
        }
        try {
            dingRequest.getText().setContent(dingRequest.getText().getContent().trim().toLowerCase());
            if (StringUtils.isEmpty(dingRequest.getText().getContent())) {
                dingResponse.setMsgtype("text");
                DingResponse.Text text = new DingResponse.Text();
                text.setContent("啥也不说，AT我干嘛");
                dingResponse.setText(text);
                log.info("Req[" + dingRequest.getText().getContent() + "]Res[" + dingResponse.getText().getContent() + "]");
            } else {
                String robotMode = InitParams.miaoParams.get("mode");
                DingResponse.Text text = new DingResponse.Text();
                switch (RobotMode.valueOf(robotMode)) {
                    case normal:
                        break;
                    case qingyunke:
                        QingyunkeClient qingyunkeClient = new QingyunkeClient();
                        String qingyunkeRes = qingyunkeClient.execute(dingRequest.getText().getContent());
                        dingResponse.setMsgtype("text");
                        text.setContent(qingyunkeRes);
                        dingResponse.setText(text);
                        break;
                    case tuling:
                        TulingClient tulingClient = new TulingClient();
                        String tulingRes = tulingClient.execute(dingRequest.getText().getContent());
                        dingResponse.setMsgtype("text");
                        text.setContent(tulingRes);
                        dingResponse.setText(text);
                        break;
                }
            }
        } catch (MiaoException e) {
            log.error(e.toString());
            dingResponse.setMsgtype("text");
            DingResponse.Text text = new DingResponse.Text();
            text.setContent(e.getErrCode());
        }
        return dingResponse;
    }
}
