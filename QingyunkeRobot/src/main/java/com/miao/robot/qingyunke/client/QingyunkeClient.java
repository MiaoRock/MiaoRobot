package com.miao.robot.qingyunke.client;

import com.miao.robot.Exception.MiaoException;
import com.miao.robot.Exception.ResponseMiaoException;
import com.miao.robot.client.MiaoClient;
import com.miao.robot.qingyunke.request.QingyunkeRequest;
import com.miao.robot.qingyunke.response.QingyunkeResponse;
import com.miao.robot.request.DefaultMiaoRequest;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.DefaultMiaoResponse;
import com.miao.robot.response.MiaoResponse;
import com.miao.robot.utils.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;


@Slf4j
@Data
@Service
@ConfigurationProperties(prefix = "qingyunke")
public class QingyunkeClient extends MiaoClient {

    private String url;
    private String key;
    private String appid;
    private static final String TEXT = "text";

    public DefaultMiaoResponse execute(DefaultMiaoRequest request) {
        DefaultMiaoResponse response = new DefaultMiaoResponse();
        QingyunkeRequest qingyunkeRequest;
        try {
            String type = request.getType();
            switch (type) {
                case TEXT:
                    String text = request.getText();
                    qingyunkeRequest = new QingyunkeRequest(key, appid, text);
                    break;
                default:
                    response.setType(TEXT);
                    response.setText("请求类型不支持");
                    throw new ResponseMiaoException();
            }
            QingyunkeResponse qingyunkeResponse;
            try {
                qingyunkeResponse = this.execute(qingyunkeRequest);
            } catch (MiaoException e) {
                response.setType(TEXT);
                response.setText("Miao异常-" + e.getErrCode() + "-" + e.getErrMsg());
                throw new ResponseMiaoException();
            }
            if (qingyunkeResponse.getResult() == 0) {
                response.setType(TEXT);
                response.setText("qingyunke-err-" + qingyunkeResponse.getResult());
                throw new ResponseMiaoException();
            }
            response.setType(TEXT);
            String resContent = qingyunkeResponse.getContent();
            response.setText(resContent.replace("菲菲", "小喵"));
            log.info("Client[qingyunke]Req[" + request.getText() + "]Res[" + response.getText() + "]");
        } catch (ResponseMiaoException e) {
            log.error("Client[qingyunke]Req[" + request.getText() + "]Res[" + response.getText() + "]");
        }
        return response;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    protected <T extends MiaoResponse> T _execute(MiaoRequest<T> request) throws MiaoException {
        return HttpUtils.doGet(url, request);
    }
}
