package com.miao.robot.request;

import com.miao.robot.lang.MiaoObject;
import com.miao.robot.response.TulingResponse;

public class TulingRequest extends MiaoRequest<TulingResponse> {

    private int reqType; // 输入类型:0-文本(默认)、1-图片、2-音频
    private Perception perception; // 输入信息 M
    private UserInfo userInfo; // 用户参数 M

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public Perception getPerception() {
        return perception;
    }

    public void setPerception(Perception perception) {
        this.perception = perception;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static class Perception extends MiaoObject {
        private InputText inputText; // 文本信息
        private InputImage inputImage; // 图片信息
        private InputMedia inputMedia; // 音频信息
        private SelfInfo selfInfo; // 直接输入文本

        public InputText getInputText() {
            return inputText;
        }

        public void setInputText(InputText inputText) {
            this.inputText = inputText;
        }

        public InputImage getInputImage() {
            return inputImage;
        }

        public void setInputImage(InputImage inputImage) {
            this.inputImage = inputImage;
        }

        public InputMedia getInputMedia() {
            return inputMedia;
        }

        public void setInputMedia(InputMedia inputMedia) {
            this.inputMedia = inputMedia;
        }

        public SelfInfo getSelfInfo() {
            return selfInfo;
        }

        public void setSelfInfo(SelfInfo selfInfo) {
            this.selfInfo = selfInfo;
        }

        public static class InputText extends MiaoObject {
            private String text; // 直接输入文本 1-128字符

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        public static class InputImage extends MiaoObject {
            private String url; // 图片地址

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class InputMedia extends MiaoObject {
            private String url; // 音频地址

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class SelfInfo extends MiaoObject {
            private Location location; // 地理位置信息

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public static class Location extends MiaoObject {
                private String city; // 所在城市 M
                private String province; // 省份
                private String street; // 街道

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }
            }
        }
    }

    public static class UserInfo extends MiaoObject {
        private String apiKey; // 机器人标识 M
        private String userId; // 用户唯一标识 M 32位
        private String groupId; // 群聊唯一标识 64位
        private String userIdName; // 群内用户昵称 64位

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getUserIdName() {
            return userIdName;
        }

        public void setUserIdName(String userIdName) {
            this.userIdName = userIdName;
        }
    }

    @Override
    public Class<TulingResponse> getResponseClass() {
        return TulingResponse.class;
    }
}
