package com.miao.robot.chatgpt.request;

import com.miao.robot.chatgpt.response.ChatgptResponse;
import com.miao.robot.lang.MiaoObject;
import com.miao.robot.request.MiaoRequest;
import lombok.Data;

@Data
public class ChatgptRequest extends MiaoRequest<ChatgptResponse> {

    private int reqType; // 输入类型:0-文本(默认)、1-图片、2-音频
    private Perception perception; // 输入信息 M
    private UserInfo userInfo; // 用户参数 M

    public ChatgptRequest() {
        this.perception = new Perception();
        this.userInfo = new UserInfo();
    }

    @Data
    public static class Perception extends MiaoObject {
        private InputText inputText; // 文本信息
        private InputImage inputImage; // 图片信息
        private InputMedia inputMedia; // 音频信息
        private SelfInfo selfInfo; // 直接输入文本

        public Perception() {
            this.inputText = new InputText();
            this.inputImage = new InputImage();
            this.inputMedia = new InputMedia();
            this.selfInfo = new SelfInfo();
        }

        @Data
        public static class InputText extends MiaoObject {
            private String text; // 直接输入文本 1-128字符
        }

        @Data
        public static class InputImage extends MiaoObject {
            private String url; // 图片地址
        }

        @Data
        public static class InputMedia extends MiaoObject {
            private String url; // 音频地址
        }

        @Data
        public static class SelfInfo extends MiaoObject {
            private Location location; // 地理位置信息

            public SelfInfo() {
                this.location = new Location();
            }

            @Data
            public static class Location extends MiaoObject {
                private String city; // 所在城市 M
                private String province; // 省份
                private String street; // 街道
            }
        }
    }

    @Data
    public static class UserInfo extends MiaoObject {
        private String apiKey; // 机器人标识 M
        private String userId; // 用户唯一标识 M 32位
        private String groupId; // 群聊唯一标识 64位
        private String userIdName; // 群内用户昵称 64位
    }

    @Override
    public Class<ChatgptResponse> getResponseClass() {
        return ChatgptResponse.class;
    }
}
