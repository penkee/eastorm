/*
 * Copyright (C),2016-2016. eastorm
 * FileName: HttpClientUtil.java
 * Author:   江东大人
 * Date:     2016-12-06 05 : 12:50
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <admin>  <2016-12-06 05 : 12:50> <version>   <desc>
 */

package com.eastorm.core.base.utils;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http访问
 * raw的适用于并发低的，比httpclient高
 * 并发高的httpclient性能更好
 * 8个线程，50个并发，测试数据如下：
 *            原生   httpclient      httpAsyncClient
 * 总花费ms    3974	   4044	            7224
 * @author 江东
 */
public class HttpClientUtil extends HttpConnectionUtil{
	private static Logger log = Logger.getLogger(HttpClientUtil.class);

    /**
     * 发送HTTP_GET请求
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL 请求地址
     * @param params 默认编码解码UTF-8
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getByHttpClient(String reqURL, Map<String, String> params) throws UnsupportedEncodingException{
        return getByHttpClient(reqURL+"?"+ mapToString(params,"UTF-8"),"UTF-8");
    }
    /**
     * 发送HTTP_GET请求（HttpClient）
     * 该方法会自动关闭连接,释放资源
     * @param reqURL    请求地址(含参数)
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用ContentType解码
     * @return 非200返回空
     */
	public static String getByHttpClient(String reqURL, String decodeCharset){
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 String content=null;
         try {  
            HttpGet httpget = new HttpGet(reqURL);
             /*
            *   setConnectTimeout：设置连接超时时间，单位毫秒。
                setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
            * */
            RequestConfig requestConfig = RequestConfig.custom()
                     .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                     .setSocketTimeout(10000).build();

            httpget.setConfig(requestConfig);
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();
                if (entity != null) {  
                    // 打印响应内容
                    if(response.getStatusLine().getStatusCode()>=200&&response.getStatusLine().getStatusCode()<300){
                        content= EntityUtils.toString(entity,decodeCharset);
                    }
                }
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace(); 
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return content;  
    }
    /**
     * 发送HTTP_POST请求（HttpClient）
     * @param reqURL   请求地址
     * @param params 编码解码UTF-8
     * @return
     */
    public static String postByHttpClient(String reqURL, Map<String,String> params) throws UnsupportedEncodingException{
        return postByHttpClient(reqURL,params,"UTF-8","UTF-8");
    }

    /**
     * 发送HTTP_POST请求
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL   请求地址
     * @param params 汉字自行编码
     * @return
     */
    public static String postByHttpClient(String reqURL, Map<String,String> params ,String encodecharset,String decodecharset) throws UnsupportedEncodingException{
        List<NameValuePair> nvpMap = new ArrayList<>();

        if(params!=null){
            for (Map.Entry<String,String> entry:params.entrySet()) {
                nvpMap.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return postByHttpClient(reqURL,nvpMap,encodecharset,decodecharset);
    }

    /**
     * 发送HTTP_POST请求
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL   请求地址
     * @param params
     * @return
     */
    public static String postByHttpClient(String reqURL, List<NameValuePair> params, String encodecharset, String decodecharset) throws UnsupportedEncodingException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        String content=null;
        HttpPost httppost = new HttpPost(reqURL);
        httppost.setEntity(new UrlEncodedFormEntity(params,encodecharset));

        CloseableHttpResponse response = null;
        try {
            /*
            *   setConnectTimeout：设置连接超时时间，单位毫秒。
                setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
            * */
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                    .setSocketTimeout(10000).build();

            httppost.addHeader(new BasicHeader("Cookie","JSESSIONID=1rb2crmt6ggoa1nqqjgxfgvxcv; acw_tc=AQAAAASNFwqwVgsAN1+ttIHpntN4Nni4; ECOACLJSESSIONID=GNIX5XuloasLR1uuGxXE13VhJIacl; ctoken=vLpqUgRR4LYjtAwmPadA6hyMGRfAWq; SERVERID=b51a1587275c07bb8777ba7db96a6aaf|1506569341|1506563644"));
            httppost.setConfig(requestConfig);

            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity, decodecharset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        httppost.releaseConnection();

        return content;
    }
}