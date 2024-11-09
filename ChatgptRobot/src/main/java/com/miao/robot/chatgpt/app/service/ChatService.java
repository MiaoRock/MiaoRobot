package com.miao.robot.chatgpt.app.service;

import com.miao.robot.chatgpt.entity.ChatParam;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;

/**
 * 功能描述：
 * 所属包名： com.miao.robot.chatgpt.app.service
 * 创建时间： 2024/2/1 5:36 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
public interface ChatService {
    /**
     * 对话
     * @param param
     * @return
     */
    ChatCompletionResponse chat(ChatParam param);
}
