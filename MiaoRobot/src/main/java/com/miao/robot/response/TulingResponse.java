package com.miao.robot.response;

import com.miao.robot.lang.MiaoObject;

import java.util.List;
import java.util.Map;

public class TulingResponse extends MiaoResponse {
    private Intent intent; // 请求意图 M
    private List<Results> results; // 输出结果集

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Intent extends MiaoObject {
        private int code; // 输出功能code M
        private String intentName; // 意图名称
        private String actionName; // 意图动作名称
        private Map<String, String> parameters; // 功能相关参数

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getIntentName() {
            return intentName;
        }

        public void setIntentName(String intentName) {
            this.intentName = intentName;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public Map<String, String> getParameters() {
            return parameters;
        }

        public void setParameters(Map<String, String>parameters) {
            this.parameters = parameters;
        }
    }

    public static class Results extends MiaoObject {
        private String resultType; // 输出类型 文本(text);连接(url);音频(voice);视频(video);图片(image);图文(news)
        private Map<String, String> values; // 输出值
        private int groupType; // ‘组’编号:0为独立输出，大于0时可能包含同组相关内容 (如：音频与文本为一组时说明内容一致)

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public Map<String, String> getValues() {
            return values;
        }

        public void setValues(Map<String, String> values) {
            this.values = values;
        }

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }
    }
}
