package com.miao.robot.chatgpt.entity;

import lombok.Data;

/**
 * 功能描述：
 * 所属包名： com.wechat.chatgpt.app.DTO
 * 创建时间： 2024/1/26 5:36 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@Data
public class Result {
    String status = "success";
    String msg;
    String message;
    String raw_message;

    public static Result ok(String text) {
        Result result = new Result();
        result.setRaw_message(text);
        result.buildMsg();
        return result;
    }
    public static Result error(String message) {
        Result result = new Result();
        result.setMessage(message);
        result.setRaw_message(message);
        return result;
    }
    public void buildMsg() {
        message = "<p>" + raw_message + "</p>\n";
    }
}
