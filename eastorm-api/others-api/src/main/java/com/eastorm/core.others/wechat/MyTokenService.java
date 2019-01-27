package com.eastorm.core.others.wechat;

import com.alibaba.fastjson.JSONObject;
import com.eastorm.core.base.utils.StringFunc;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 本类用于需要网页授权的令牌管理，和公众号的令牌，分平台改造，定时刷新
 * modified by pengk
 * @author 郑波
 */
public class MyTokenService {
	private static MyTokenService instance = new MyTokenService();
	public static Map<String,Token> appAccessTokenMap = new HashMap<String,Token>();//不同平台有不同的token

	public static MyTokenService getInstance() {
		return instance;
	}
	/**
	 * 获取公众号token
	 * @return
	 */
	private JSONObject getToken(String appid,String secret) {
		Map<String, String> tokenPramas = new HashMap<String, String>();
		/* +"?grant_type=client_credential&appid="+appID+"&secret="+appSecret */
		tokenPramas.put("appid", appid);
		tokenPramas.put("secret", secret);
		tokenPramas.put("grant_type", "client_credential");
		JSONObject token = TokenService.getInstance().parseJson(WechatProperties.getValue("access_token_url"),tokenPramas);
		return token;
	}
	/**
	 * 获取公众号token
	 * added by pengkun at 2014-12-8
	 * @return
	 */
	public String getToken(String platform) {
		Token tk=appAccessTokenMap.get(platform);
		if(tk!=null&&!StringFunc.isNull(tk.getValue())){
			//判断时间是否超过规定过期时间
			long curtime=(System.currentTimeMillis()-tk.getRefreshTime().getTime())/1000;//转成秒
			if(curtime<tk.getTimeLength()-60){//10s缓冲
				return tk.getValue();
			}
		}
		String appid=WechatProperties.getValue(platform+"_app_id");
		String secret=WechatProperties.getValue(platform+"_private_key");
		JSONObject tokenJSON=getToken(appid,secret);
		
		if (tokenJSON == null||StringFunc.isNull(tokenJSON.get("access_token"))) {
			return "";
		}

		Token newToken=new Token();
		newToken.setTimeLength(Integer.parseInt(tokenJSON.get("expires_in").toString()));
		newToken.setValue((String)tokenJSON.get("access_token"));
		newToken.setRefreshTime(new Date());
		
		appAccessTokenMap.put(platform, newToken);
		return newToken.getValue();
	}
}
