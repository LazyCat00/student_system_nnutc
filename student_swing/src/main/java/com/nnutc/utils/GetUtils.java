package com.nnutc.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetUtils {
    //无参方式
    public static void get(String url, SuccessListener sListener, FailListener fListener) {
        getWithParams(url, new HashMap<>(), sListener, fListener);
    }

    //有参方式
    public static void getWithParams(String url, Map<String, Object> params, SuccessListener sListener, FailListener fListener) {
//        HttpClient是一个Java库，用于发送HTTP请求和接收HTTP响应。
//        HttpClientBuilder提供了一种方便的方式来配置HttpClient的参数，例如连接超时、读取超时、代理设置等。
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(CookiesHolder.getCookieStore()).build();
        CloseableHttpResponse response = null;
        try {
            // 创建Get请求
            url = joinParam(url, params);
//            HttpGet 是 HttpClient 中的一个类，用于表示一个 HTTP GET 请求。
//            您可以通过在 HttpGet 对象中设置 URL 来指定请求的目标。
            HttpGet httpGet = new HttpGet(url);
//            RequestConfig 是 HttpClient 中的一个类，用于封装请求的配置信息。
//            通过 RequestConfig，您可以设置请求的超时时间、是否允许重定向、连接管理等。
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(3000) //服务器响应超时时间
                    .setConnectTimeout(3000) //连接服务器超时时间
                    .build();
            httpGet.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            这一行代码是用于从 HTTP 响应中获取实体（entity）对象。
//            在 Apache HttpClient 库中，HTTP 实体是一个抽象的概念，
//            它代表着 HTTP 消息中的内容，可以是一个字节流，也可以是一个字符串。
//            这个实体对象通常用于处理 HTTP 响应体，例如读取响应内容、检查响应状态等。
//            你可以根据具体的需求对这个实体对象进行适当的处理。
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
//                在处理 HTTP 请求时，有时我们需要将响应的内容转换为字符串以便于后续处理。
//                EntityUtils.toString 方法允许您将 HTTP 响应实体转换为字符串。
                sListener.success(EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fListener.fail();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //    返回url
    private static String joinParam(String url, Map<String, Object> params) {
        if (params == null || params.size() == 0) {
            return url;
        }
//        如果你有一个字符串 str，并且你想要在其末尾添加一个新的字符串，
//        你可以使用 StringBuilder 来完成这个操作：
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");

        int counter = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key == null) {
                continue;
            }

            if (counter == 0) {
                urlBuilder.append(key).append("=").append(value);
            } else {
                urlBuilder.append("&").append(key).append("=").append(value);
            }
            counter++;
        }

        return urlBuilder.toString();
    }
}
