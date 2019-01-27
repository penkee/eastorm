package com.eastorm.impl.others.wechat;

import com.alibaba.fastjson.JSONObject;
import com.eastorm.core.base.utils.HttpClientUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class SendMedia {
	private static Logger logger=Logger.getLogger(SendMedia.class);
	public Media mediaUploadGraphic(String access_token, MediaType mediaType, File media) {
		Media weixinMedia = new Media();
		String result = null;
		try {
			// 拼装请求地址
			String uploadMediaUrl = String.format(
					"http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%1s&type=%2s",
					access_token,
					mediaType.name());
			URL url = new URL(uploadMediaUrl);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			
			// 请求正文信息
			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			String filename = media.getName();
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + filename + "\" \r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] head = sb.toString().getBytes("utf-8");

			// 获得输出流
			OutputStream out = new DataOutputStream(con.getOutputStream());
			// 输出表头
			out.write(head);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			DataInputStream in = new DataInputStream(new FileInputStream(media));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);
			out.flush();
			out.close();
			
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
			
			System.out.println(result);

			// 解析返回结果
			Map jsonObject = JSONObject.parseObject(result, Map.class);
			
			if (jsonObject.get("errcode") != null) {
				weixinMedia.setErrcode(jsonObject.get("errcode").toString());
				weixinMedia.setErrmsg(jsonObject.get("errmsg").toString());
				
				return weixinMedia;
			}
			
			weixinMedia.setType(mediaType.name());
			weixinMedia.setUrl(jsonObject.get("url").toString());
			
			// type等于thumb时的返回结果和其它类型不一样
			if ("thumb".equals(mediaType.name())) {
				weixinMedia.setMedia_id(jsonObject.get("thumb_media_id").toString());
			} else {
				weixinMedia.setMedia_id(jsonObject.get("media_id").toString());
			}
			
			
		} catch (IOException e) {
			logger.error("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally {
		}
		
		return weixinMedia;
	}
	public static String tmpErweima(String access_token,Integer mid){
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token;
		
		JSONObject params=new JSONObject();
		
		JSONObject scene=new JSONObject();
		scene.put("scene_id", mid);
		JSONObject action_info=new JSONObject();
		action_info.put("scene", scene);
		
		params.put("expire_seconds", 7*24*60*60);
		params.put("action_name", "QR_SCENE");
		params.put("action_info", action_info);
		
			String res= HttpClientUtil.sendPostRequest(url, params.toJSONString());
			System.out.println("-------------------"+res);
		return null;
	}
	
	public static boolean sendImage(String access_token,String openId,String mediaId){
		String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;
		
		JSONObject params=new JSONObject();
		
		params.put("touser", openId);
		params.put("msgtype", "image");
		
		JSONObject image=new JSONObject();
		image.put("media_id", mediaId);
		
		params.put("image", image);
		
		String res=HttpClientUtil.sendPostRequest(url, params.toJSONString());
		
		// 解析返回结果
		Map jsonObject = JSONObject.parseObject(res, Map.class);
		
		if (jsonObject.get("errcode") != null) {
			
		}
		logger.error("-------------------"+res);
		return true;
	}
	
}
