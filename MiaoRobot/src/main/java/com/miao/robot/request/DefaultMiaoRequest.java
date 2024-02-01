package com.miao.robot.request;

import com.miao.robot.response.DefaultMiaoResponse;
import lombok.Data;

@Data
public class DefaultMiaoRequest extends MiaoRequest<DefaultMiaoResponse> {
    private String robot;
    private String type;
    private String text;
    private String imageUrl;
    private String audioUrl;
    private String videoUrl;
    private String fileUrl;
    @Override
    public Class<DefaultMiaoResponse> getResponseClass() {
        return DefaultMiaoResponse.class;
    }
}
