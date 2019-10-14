package com.hrb.duyi.util;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

import java.io.IOException;

public class HttpClientUtil {
    public static String sendGet(String url) {
        if (url == null || "".equals(url)) {
            return "请添加请求地址";
        }
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3600).setConnectionRequestTimeout(7200).setSocketTimeout(7200).build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return "请求异常";
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * post方法
     * @param url   请求地址
     * @param parameters 请求需要携带的参数
     * @return
     */
    public static String sendPost(String url, Map<String, Object> parameters) {
        if ( null == url || "".equals(url) ) return "请填写不带参数URL";
        if ( url.contains("?") ) return "提示：请传入不带参数的URL";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3600).setConnectionRequestTimeout(7200).setSocketTimeout(7200).build();
        httpPost.setConfig(requestConfig);
        try {
            if (null != parameters && parameters.size() != 0) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                Set<Map.Entry<String, Object>> entrySet = parameters.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> next = iterator.next();
                    list.add(new BasicNameValuePair(next.getKey(), next.getValue().toString()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            }
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
                if (null != httpClient) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }


}
