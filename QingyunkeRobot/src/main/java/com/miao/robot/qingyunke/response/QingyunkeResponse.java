package com.miao.robot.qingyunke.response;

import com.miao.robot.response.MiaoResponse;
import lombok.Data;

@Data
public class QingyunkeResponse extends MiaoResponse {
    private int result;
    private String content;
}
