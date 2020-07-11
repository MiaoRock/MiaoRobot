package com.miao.robot.lang;

import com.miao.robot.Exception.IllegalMiaoException;
import com.miao.robot.Exception.MiaoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class Encoder {
    public static String getSignUrl(long timestamp, String appSecret) throws IllegalMiaoException {
        try {
            return URLEncoder.encode(getSign(timestamp, appSecret), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalMiaoException(e);
        }
    }

    public static String getSign(long timestamp, String appSecret) throws IllegalMiaoException {
        try {
            String stringToSign = timestamp + "\n" + appSecret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = new String(Base64.encodeBase64(signData));
            return sign;
        } catch (Exception e) {
            throw new IllegalMiaoException(e);
        }
    }
}