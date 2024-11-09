package com.miao.robot.chatgpt.entity;

import com.plexpt.chatgpt.entity.chat.Message;
import lombok.Data;
import lombok.NonNull;
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
    private String apiKey;

    String model;

    @NonNull
    private List<Message> messages;

    Integer max_tokens;
    Double temperature;
    Boolean stream;
    String message;
    List<List<String>> context;
    String key;
    String id;
    private String apiHost;

    public boolean hasKey() {
        return !StringUtils.isEmpty(key);
    }
}
