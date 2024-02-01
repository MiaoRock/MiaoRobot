package com.miao.robot.ding.service;

import com.miao.robot.Exception.IllegalMiaoException;
import com.miao.robot.ding.request.DingRequest;
import com.miao.robot.lang.Encoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Data
@Service
@ConfigurationProperties(prefix = "ding.check")
public class CheckDing {

    private String secret;

    public boolean execute(DingRequest dingRequest) {
        try {
            this.checkTimestamp(dingRequest.getTimestamp());
            this.checkSign(dingRequest.getTimestamp(), this.secret, dingRequest.getSign());
        } catch (IllegalMiaoException e) {
            log.error(e.toString());
            return true;
        }
        return false;
    }

    public void checkTimestamp(long timestamp) throws IllegalMiaoException {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMillis = calendar.getTimeInMillis();
        log.info("[" + currentTimeMillis + "][" + timestamp + "]");
        if (Math.abs(currentTimeMillis - timestamp) > 1000 * 60 * 60) { // 1小时内
            throw new IllegalMiaoException("IllegalTimestampRequest", "请求timestamp值非法");
        }
    }

    public void checkSign(long timestamp, String secret, String sign) throws IllegalMiaoException {
        String signCal = Encoder.getSign(timestamp, secret);
        if (!signCal.equals(sign)) {
            throw new IllegalMiaoException("IllegalSignRequest", "请求sign值非法");
        }
    }
}
