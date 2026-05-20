package com.miao.robot.tuling.response;

import com.miao.robot.lang.MiaoObject;
import com.miao.robot.response.MiaoResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TulingResponse extends MiaoResponse {
    private Intent intent; // 请求意图 M
    private List<Results> results; // 输出结果集

    @Data
    public static class Intent extends MiaoObject {
        private int code; // 输出功能code M
        private String intentName; // 意图名称
        private String actionName; // 意图动作名称
        private Map<String, String> parameters; // 功能相关参数
    }

    @Data
    public static class Results extends MiaoObject {
        private String resultType; // 输出类型 文本(text);连接(url);音频(voice);视频(video);图片(image);图文(news)
        private Map<String, String> values; // 输出值
        private int groupType; // ‘组’编号:0为独立输出，大于0时可能包含同组相关内容 (如：音频与文本为一组时说明内容一致)
    }
}
