package com.miao.robot.chatgpt.entity;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 功能描述： 对话参数
 * 所属包名： com.wechat.chatgpt.app.DTO
 * 创建时间： 2024/1/29 11:44 AM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@Data
public class ChatParam {
    List<String> defaultPrompt = Arrays.asList("我问你个问题，你告诉我答案。你是谁？",
            "我是人工智能语言模型，被称为GPT。我的设计目的是与用户进行对话并提供信息、答案和帮助。有任何问题，随时问我都可以。");
    public static String Question = "Q:\n";
    public static String Answer = "A:\n";

    String message;
    String url;
    List<List<String>> context;
    String key;
    String id;

    /**
     * 判断是否有key
     * @return
     */
    public boolean hasKey() {
        return !StringUtils.isEmpty(key);
    }

    /**
     * 构建提示，上下文整合
     * @return
     */
    public String buildPrompt() {
        if (CollectionUtils.isEmpty(context)) {
            context = Arrays.asList(defaultPrompt);
        }
        String result = "";
        for (List<String> pro : context) {
            result = result + Question + pro.get(0) + "\n";
            result = result + Answer + pro.get(1) + "\n";
        }
        result = result + Question + message + "\n" + Answer;

        return result;
    }
}
