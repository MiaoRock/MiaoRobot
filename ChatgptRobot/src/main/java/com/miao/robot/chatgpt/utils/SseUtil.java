package com.miao.robot.chatgpt.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 功能描述： sse工具类
 * 所属包名： com.wechat.chatgpt.util
 * 创建时间： 2024/1/26 3:43 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@UtilityClass
public class SseUtil {
    public void complete(SseEmitter sseEmitter) {
        try {
            sseEmitter.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(SseEmitter sseEmitter, Object data) {
        try {
            sseEmitter.send(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
