package com.miao.robot.ding.response;

import com.miao.robot.response.MiaoResponse;
import com.miao.robot.lang.MiaoObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class DingResponse extends MiaoResponse {

    private String msgtype;
    private Text text;
    private Markdown markdown;
    private ActionCard actionCard;
    private FeedCard feedCard;
    private At at;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }

    public ActionCard getActionCard() {
        return actionCard;
    }

    public void setActionCard(ActionCard actionCard) {
        this.actionCard = actionCard;
    }

    public FeedCard getFeedCard() {
        return feedCard;
    }

    public void setFeedCard(FeedCard feedCard) {
        this.feedCard = feedCard;
    }

    public At getAt() {
        return at;
    }

    public void setAt(At at) {
        this.at = at;
    }

    public static class Text extends MiaoObject {
        private String content; // 消息文本

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * Markdown 语法
     * <p>
     * 标题
     * # 一级标题
     * ## 二级标题
     * ### 三级标题
     * #### 四级标题
     * ##### 五级标题
     * ###### 六级标题
     * <p>
     * 引用
     * > A man who stands for nothing will fall for anything.
     * <p>
     * 文字加粗、斜体
     * **bold**
     * *italic*
     * <p>
     * 链接
     * [this is a link](https://www.dingtalk.com/)
     * <p>
     * 图片
     * ![](http://name.com/pic.jpg)
     * <p>
     * 无序列表
     * - item1
     * - item2
     * <p>
     * 有序列表
     * 1. item1
     * 2. item2
     */
    public static class Markdown extends MiaoObject {
        private String title; // 首屏会话透出的展示内容
        private String text; // markdown格式的消息内容

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class ActionCard extends MiaoObject {
        private String title; // 首屏会话透出的展示内容
        private String text; // markdown格式的消息内容

        private String singleTitle; // 单个按钮的标题
        private String singleURL; // 单个按钮的跳转链接

        private String hideAvatar; //
        private String btnOrientation; // 0-按钮竖直排列，1-按钮横向排列
        private ArrayList<Btn> btns; // 按钮

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSingleTitle() {
            return singleTitle;
        }

        public void setSingleTitle(String singleTitle) {
            this.singleTitle = singleTitle;
        }

        public String getSingleURL() {
            return singleURL;
        }

        public void setSingleURL(String singleURL) {
            this.singleURL = singleURL;
        }

        public String getHideAvatar() {
            return hideAvatar;
        }

        public void setHideAvatar(String hideAvatar) {
            this.hideAvatar = hideAvatar;
        }

        public String getBtnOrientation() {
            return btnOrientation;
        }

        public void setBtnOrientation(String btnOrientation) {
            this.btnOrientation = btnOrientation;
        }

        public ArrayList<Btn> getBtns() {
            return btns;
        }

        public void setBtns(ArrayList<Btn> btns) {
            this.btns = btns;
        }

        public static class Btn {
            private String title; // 按钮标题
            private String actionURL; // 点击按钮触发的URL

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getActionURL() {
                return actionURL;
            }

            public void setActionURL(String actionURL) {
                this.actionURL = actionURL;
            }
        }
    }

    public static class FeedCard extends MiaoObject {
        private ArrayList<Link> links; // 链接

        public ArrayList<Link> getLinks() {
            return links;
        }

        public void setLinks(ArrayList<Link> links) {
            this.links = links;
        }

        public static class Link extends MiaoObject {
            private String title; // 单条信息文本
            private String messageURL; // 单条信息跳转链接
            private String picURL; // 单条信息后面图片的URL

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMessageURL() {
                return messageURL;
            }

            public void setMessageURL(String messageURL) {
                this.messageURL = messageURL;
            }

            public String getPicURL() {
                return picURL;
            }

            public void setPicURL(String picURL) {
                this.picURL = picURL;
            }
        }
    }

    public static class At extends MiaoObject {
        private ArrayList<String> atMobiles; // 被@人的手机号
        private boolean isAtAll; // @所有人是true，否则为false

        public ArrayList<String> getAtMobiles() {
            return atMobiles;
        }

        public void setAtMobiles(ArrayList<String> atMobiles) {
            this.atMobiles = atMobiles;
        }

        public boolean isAtAll() {
            return isAtAll;
        }

        public void setAtAll(boolean atAll) {
            isAtAll = atAll;
        }
    }
}
