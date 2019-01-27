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


import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
public class HttpConnectionUtil {
	private static Logger log = Logger.getLogger(HttpConnectionUtil.class);
    /**
     * map转成url字符串
     * @throws UnsupportedEncodingException
     */
    protected static String mapToString(Map<String, String> params,String encodeCharset) throws UnsupportedEncodingException{
        StringBuilder sendData = new StringBuilder();
		for(Map.Entry<String, String> entry : params.entrySet()){
		    sendData.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),encodeCharset)).append("&");
		}
        if(sendData.length() > 0){
            sendData.setLength(sendData.length() - 1); //删除最后一个&符号
        }
        return sendData.toString();
    }

    /**
     * 发送HTTP_GET请求(原生)
     * @param reqURL 请求地址
     * @param params 默认编码解码UTF-8
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sendGetRequest(String reqURL, Map<String, String> params) throws UnsupportedEncodingException{
        return sendGetRequest(reqURL+"?"+mapToString(params,"UTF-8"),"UTF-8");
    }
    /**
     * 发送HTTP_GET请求(原生)
     * @param reqURL 请求地址
     * @param params 默认编码解码UTF-8
     * @param decodeCharset 指定解码
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String sendGetRequest(String reqURL, Map<String, String> params,String encodeCharset,String decodeCharset) throws UnsupportedEncodingException{
        return sendGetRequest(reqURL+"?"+mapToString(params,encodeCharset),decodeCharset);
    }
    /**
     * 原生发送HTTP_GET请求
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param url 请求地址
     * @param decodeCharset 解码，不能为空
     * @return 非200返回空
     * @throws UnsupportedEncodingException
     */
    public static String sendGetRequest(String url,String decodeCharset){
    	String result = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(5000); //10秒连接超时
            connection.setReadTimeout(10000);    //10秒读取超时
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),decodeCharset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HTTP_POST请求
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL 请求地址
     * @param params 文本编码，解码都是UTF-8
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String sendPostRequest(String reqURL, Map<String, String> params) throws UnsupportedEncodingException{
        return sendPostRequest(reqURL, mapToString(params,"UTF-8"));
    }
     
    /**
     * 发送HTTP_POST请求，字符流
     * @param reqURL   请求地址
     * @param sendData 指定解码
     * @return
     */
    public static String sendPostRequest(String reqURL, String sendData){
        return sendPostRequest(reqURL,sendData,"UTF-8",null);
    }
    
    /**
     * 发送HTTP_POST请求
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL 请求地址
     * @param params 文本不需要编码
     * @param encodecharset 编码
     * @param decodecharset 解码
     * @return
     */
    public static String sendPostRequest(String reqURL, Map<String, String> params,String encodecharset,String decodecharset) throws UnsupportedEncodingException{
        return sendPostRequest(reqURL,  mapToString(params,encodecharset),decodecharset,null);
    }
     
     
    /**
     * 发送HTTP_POST请求
     * @param reqURL   请求地址
     * @param sendData 指定解码
     * @param decodecharset 指定解码
     * @param timeout 超时时间，null默认10s
     * @return
     */
    public static String sendPostRequest(String reqURL, String sendData,String decodecharset,Integer timeout){
        HttpURLConnection httpURLConnection = null;
        OutputStream out = null; //写
        InputStream in = null;   //读
        String content=null;
        try{
            URL sendUrl = new URL(reqURL);
            httpURLConnection = (HttpURLConnection)sendUrl.openConnection();
            
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);        //指示应用程序要将数据写入HttpBody,其值默认为false
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(5000); //5秒连接超时
            httpURLConnection.setReadTimeout(timeout==null?10000:timeout);    //10秒读取超时

            out = httpURLConnection.getOutputStream();
            out.write(sendData.toString().getBytes());

            //清空缓冲区,发送数据
            out.flush();

            if(httpURLConnection.getResponseCode()>=200&&httpURLConnection.getResponseCode()<300){
                in = httpURLConnection.getInputStream();
                byte[] byteDatas = new byte[in.available()];
                in.read(byteDatas);
                content= new String(byteDatas,decodecharset);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(out != null){
                try{
                    out.close();
                }catch (Exception e){
                    log.error("关闭输出流时发生异常,堆栈信息如下", e);
                }
            }
            if(in != null){
                try{
                    in.close();
                }catch(Exception e){
                    log.error("关闭输入流时发生异常,堆栈信息如下", e);
                }
            }
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
        }

        return content;
    }
}