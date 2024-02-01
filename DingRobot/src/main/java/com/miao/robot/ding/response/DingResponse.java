package com.miao.robot.ding.response;

import com.miao.robot.lang.MiaoObject;
import com.miao.robot.response.MiaoResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@Data
public class DingResponse extends MiaoResponse {

    private String msgtype;
    private Text text;
    private Markdown markdown;
    private ActionCard actionCard;
    private FeedCard feedCard;
    private At at;

    public DingResponse() {
        this.text = new Text();
        this.markdown = new Markdown();
        this.actionCard = new ActionCard();
        this.feedCard = new FeedCard();
        this.at = new At();
    }

    @Data
    public static class Text extends MiaoObject {
        private String content; // 消息文本
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
    @Data
    public static class Markdown extends MiaoObject {
        private String title; // 首屏会话透出的展示内容
        private String text; // markdown格式的消息内容
    }

    @Data
    public static class ActionCard extends MiaoObject {
        private String title; // 首屏会话透出的展示内容
        private String text; // markdown格式的消息内容

        private String singleTitle; // 单个按钮的标题
        private String singleURL; // 单个按钮的跳转链接

        private String hideAvatar; //
        private String btnOrientation; // 0-按钮竖直排列，1-按钮横向排列
        private ArrayList<Btn> btns; // 按钮

        @Data
        public static class Btn {
            private String title; // 按钮标题
            private String actionURL; // 点击按钮触发的URL
        }
    }

    @Data
    public static class FeedCard extends MiaoObject {
        private ArrayList<Link> links; // 链接

        @Data
        public static class Link extends MiaoObject {
            private String title; // 单条信息文本
            private String messageURL; // 单条信息跳转链接
            private String picURL; // 单条信息后面图片的URL
        }
    }

    @Data
    public static class At extends MiaoObject {
        private ArrayList<String> atMobiles; // 被@人的手机号
        private boolean isAtAll; // @所有人是true，否则为false
    }
}
