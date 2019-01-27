package com.eastorm.core.others.wechat;

import com.alibaba.fastjson.JSONObject;
import com.eastorm.core.base.utils.StringFunc;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description
 * 读取公众号在微信平台的认证信息
 * 
 * @author 郑波
 * @date 2014-03-07 09:50
 */
public final class WechatConfig {
	public static String jsapi_ticket;
	public static int jsapi_ticket_expires_in;
	public static long jsapi_ticket_updatetime;
	
	public static Map<String,String> getConfig(String url,String platform){
		Map<String,String> res=new HashMap<String,String>();
		
		String noncestr="Wm3WZYTPz0wzc"+new Random().nextInt(20);
		if(StringFunc.isNull(jsapi_ticket)||(System.currentTimeMillis()-jsapi_ticket_updatetime)/1000>=jsapi_ticket_expires_in){
			JSONObject jsapi_ticketJson=getJsToken(platform);
			
			jsapi_ticket=StringFunc.toString(jsapi_ticketJson.get("ticket"));
			String expires_in=StringFunc.toString(jsapi_ticketJson.get("expires_in"));
			jsapi_ticket_expires_in=Integer.parseInt(expires_in);
			jsapi_ticket_updatetime=System.currentTimeMillis();
		}
		
		String timestamp=""+System.currentTimeMillis()/1000;//时间戳
		
		 HttpParam[] list=new HttpParam[4];
		 list[0]=new HttpParam("jsapi_ticket",jsapi_ticket);
		 list[1]=new HttpParam("timestamp",timestamp);
		 list[2]=new HttpParam("url",url);
		 list[3]=new HttpParam("noncestr",noncestr);
		 sortByname(list);
		String str= getStr(list);
		 //System.out.println(str);
    	String signature = WechatTools.SHA1(str.toString());
    	//System.out.println(signature);
    	res.put("noncestr", noncestr);
    	res.put("jsapi_ticket", jsapi_ticket);
    	res.put("timestamp", timestamp);
    	res.put("url", url);
    	res.put("signature", signature);
		return res;
	}
	
	/**
	 * 获取jstoken
	 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
	 * @return
	 */
	private static JSONObject getJsToken(String platform) {
		String accesstoken=MyTokenService.getInstance().getToken(platform);
		
		Map<String, String> tokenPramas = new HashMap<String, String>(2);
		/* +"?grant_type=client_credential&appid="+appID+"&secret="+appSecret */
		tokenPramas.put("type", "jsapi");
		tokenPramas.put("access_token", accesstoken);
		JSONObject token = TokenService.getInstance().parseJson("https://api.weixin.qq.com/cgi-bin/ticket/getticket",
				tokenPramas);
		return token;
	}
	
	public static void sortByname(HttpParam[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if (list[i].getName().compareTo(list[j].getName()) > 0) {
					HttpParam tmp = list[i];
					list[i] = list[j];
					list[j] = tmp;
				}
			}
		}
	}
	public static String getStr(HttpParam[] list){
		StringBuilder str=new StringBuilder();
		 for(HttpParam p:list){
			 str.append(p.getName()+"="+p.getValue()+"&");
		 }
		 str.deleteCharAt(str.length()-1);
		 return str.toString();
	}
	public static String getStrEnCode(HttpParam[] list){
		StringBuilder str=new StringBuilder();
		 for(HttpParam p:list){
			 str.append(p.getName()+"="+
					 URLEncoder.encode(p.getValue()).replace("+", "%20")+"&");
		 }
		 str.deleteCharAt(str.length()-1);
		 return str.toString();
	}
}
