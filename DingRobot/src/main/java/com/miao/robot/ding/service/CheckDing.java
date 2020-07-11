package com.miao.robot.ding.service;

import com.miao.robot.Exception.IllegalMiaoException;
import com.miao.robot.Exception.MiaoException;
import com.miao.robot.ding.request.DingRequest;
import com.miao.robot.lang.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Service
public class CheckDing {

    private String secret = "olQPKLwtpR8dvPMRe0gEsy-YzZX9QOb-dvSNs0kPeM2sdEV7PtdnoEfHx7-KpEGs";

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
