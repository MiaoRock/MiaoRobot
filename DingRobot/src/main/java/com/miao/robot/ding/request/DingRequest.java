package com.miao.robot.ding.request;

import com.miao.robot.ding.response.DingResponse;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.lang.MiaoObject;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
public class DingRequest extends MiaoRequest<DingResponse> {

    // body
    // 消息类型 text-文本消息 picture-图片消息 richText-富文本消息 audio-语音 video-视频消息 file-文件消息 required
    // 目前支持 text
    private String msgtype;
    private Text text; // 文本消息内容
    private Content content; // 消息内容
    private String msgId; // 加密的消息ID，暂无使用场景，可忽略 required
    private String createAt; // 消息的时间戳，单位毫秒 required
    private String conversationType; // 1-单聊 2-群聊 required
    private String conversationId; // 会话ID required
    private String conversationTitle; // 群聊时才有的会话标题
    private String senderId; // 加密的发送者ID 使用senderStaffId，作为发送者userid值 required
    private String senderNick; // 发送者昵称 required
    private String senderCorpId; // 企业内部群有的发送者当前群的企业corpId
    private String senderStaffId; // 企业内部群中@该机器人的成员userid 该字段在机器人发布线上版本后，才会返回
    private String sessionWebhook; // 当前会话的Webhook地址 required
    private Long sessionWebhookExpiredTime; // 当前会话的Webhook地址过期时间 required
    private boolean isAdmin; // 是否为管理员 机器人发布上线后生效
    private String chatbotCorpId; // 机器人所在的企业corpId
    private boolean isInAtList; // 是否在@列表中
    private String chatbotUserId; // 加密的机器人ID，暂无使用场景，可忽略
    private ArrayList<AtUser> atUsers; // 被@人的信息

    // header
    private String sign;

    @Override
    public Class<DingResponse> getResponseClass() {
        return DingResponse.class;
    }

    @Data
    public static class Text extends MiaoObject {
        private String content; // 文本
    }

    @Data
    public static class Content extends MiaoObject {
        private String downloadCode; // 各种类型的下载代码
        private String fileName; // 文件名
        private String duration;
        private String videoType; // 视频格式
        private String recognition;
    }

    @Data
    public static class AtUser extends MiaoObject {
        private String dingtalkId; // 加密的发送者ID
        private String staffId; // 企业内部群有的发送者在企业内的userid
    }
}
