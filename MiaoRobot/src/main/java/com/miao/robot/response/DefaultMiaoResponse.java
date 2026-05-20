package com.miao.robot.response;

import lombok.Data;

@Data
public class DefaultMiaoResponse extends MiaoResponse {
    private String type;
    private String text;
    private String imageUrl;
    private String audioUrl;
    private String videoUrl;
    private String fileUrl;
}
