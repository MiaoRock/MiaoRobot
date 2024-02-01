package com.miao.robot.utils;

import com.alibaba.fastjson.JSONObject;
import com.miao.robot.Exception.MiaoException;
import com.miao.robot.request.MiaoRequest;
import com.miao.robot.response.MiaoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Slf4j
public class HttpUtils {

    /**
     * 无参的get请求
     */
    public static String doGet(String url) throws MiaoException {
        return doGet(url, (Map<String, String>) null);
    }

    /**
     * 有参的get请求
     */
    public static String doGet(String url, Map<String, String> params) throws MiaoException {
        return _doGet(url, params);
    }

    /**
     * 有参的get请求-req形式
     */
    public static <T extends MiaoResponse> T doGet(String url, MiaoRequest<T> request) throws MiaoException {
        String responseString = _doGet(url, request.getParams());
        log.info(responseString);
        return JSONObject.parseObject(responseString, request.getResponseClass());
    }

    private static String _doGet(String url, Map<String, String> params) throws MiaoException {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultJson = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            // 如果有参数则拼上参数
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200

            if (response.getCode() == 200) {
                resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                throw new MiaoException("http-err-" + response.getCode());
            }
        } catch (Exception e) {
            throw new MiaoException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                throw new MiaoException(e);
            }
        }
        return resultJson;
    }

    /**
     * 无参的post请求
     */
    public static String doPost(String url) throws MiaoException {
        return doPost(url, (String) null);
    }

    /**
     * 有参的post请求
     */
    public static String doPost(String url, String params) throws MiaoException {
        return _doPost(url, params, ContentType.APPLICATION_JSON);
    }

    /**
     * 有参的post请求-req形式
     */
    public static <T extends MiaoResponse> T doPost(String url, MiaoRequest<T> request) throws MiaoException {
        log.info(JSONObject.toJSONString(request));
        String responseJson = _doPost(url, JSONObject.toJSONString(request), ContentType.APPLICATION_JSON);
        log.info(responseJson);
        return JSONObject.parseObject(responseJson, request.getResponseClass());
    }

    private static String _doPost(String url, String params, ContentType contentType) throws MiaoException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (params != null) {
                StringEntity entity = new StringEntity(params, contentType);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw new MiaoException(e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new MiaoException(e);
            }
        }

        return resultString;
    }
}
