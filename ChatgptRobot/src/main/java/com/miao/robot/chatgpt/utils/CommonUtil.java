package com.miao.robot.chatgpt.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.experimental.UtilityClass;
import net.dreamlu.mica.http.HttpRequest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

/**
 * 功能描述：
 * 所属包名： com.wechat.chatgpt.util
 * 创建时间： 2024/1/29 5:30 PM
 * 当前版本： 1.0
 *
 * @author zhaodezhi
 */
@UtilityClass
public class CommonUtil {
    String URL_USAGE = "api/trpc/public.queryKeyUsage";

    public static String getUsage(String key, String host) {
        DateTime start = DateUtil.beginOfMonth(new Date());
        DateTime end = DateUtil.offsetDay(new Date(), 1);
        String response = HttpRequest.get(host + URL_USAGE)
//                .setHeader("Authorization", "Bearer " + key)
//                .setHeader("Content-type", "application/json")
//                .query("start_date", formatDate(start))
//                .query("end_date", formatDate(end))
                .setHeader("input", "%7B%22json%22%3A%22" + key + "%22%7D")
                .connectTimeout(Duration.ofSeconds(6))
                .readTimeout(Duration.ofSeconds(6))
                .writeTimeout(Duration.ofSeconds(6))
                .execute()
                .asString();
        return response;
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(date);
    }


}
