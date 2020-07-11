package com.miao.robot.ding.request;

import com.miao.robot.ding.response.DingResponse;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.lang.MiaoObject;

import java.util.ArrayList;

public class DingRequest extends MiaoRequest<DingResponse> {

    // body
    private String msgtype;
    private Text text;
    private String msgId;
    private String createAt;
    private String conversationType;
    private String conversationId;
    private String conversationTitle;
    private String senderId;
    private String senderNick;
    private String senderCorpId;
    private String senderStaffId;
    private String chatbotUserId;
    private ArrayList<AtUser> atUsers;

    // header
    private String sign;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

//    public void setText(String text) {
//        DingRequest.Text textClass = new DingRequest.Text();
//        textClass.setContent(text);
//        this.text = textClass;
//    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getConversationType() {
        return conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationTitle() {
        return conversationTitle;
    }

    public void setConversationTitle(String conversationTitle) {
        this.conversationTitle = conversationTitle;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderNick() {
        return senderNick;
    }

    public void setSenderNick(String senderNick) {
        this.senderNick = senderNick;
    }

    public String getSenderCorpId() {
        return senderCorpId;
    }

    public void setSenderCorpId(String senderCorpId) {
        this.senderCorpId = senderCorpId;
    }

    public String getSenderStaffId() {
        return senderStaffId;
    }

    public void setSenderStaffId(String senderStaffId) {
        this.senderStaffId = senderStaffId;
    }

    public String getChatbotUserId() {
        return chatbotUserId;
    }

    public void setChatbotUserId(String chatbotUserId) {
        this.chatbotUserId = chatbotUserId;
    }

    public ArrayList<AtUser> getAtUsers() {
        return atUsers;
    }

    public void setAtUsers(ArrayList<AtUser> atUsers) {
        this.atUsers = atUsers;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public Class<DingResponse> getResponseClass() {
        return DingResponse.class;
    }

    public static class Text extends MiaoObject {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class AtUser extends MiaoObject {
        private String dingtalkId;
        private String staffId;

        public String getDingtalkId() {
            return dingtalkId;
        }

        public void setDingtalkId(String dingtalkId) {
            this.dingtalkId = dingtalkId;
        }

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }
    }
}
