package com.eastorm.core.others.wechat;

import com.alibaba.fastjson.JSONObject;
import com.eastorm.core.base.utils.HttpClientUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
/**
 * 本类用于需要网页授权的令牌管理
 * 
 * @author 郑波
 */
public class TokenService {
	private static Logger  logger=Logger.getLogger(TokenService.class);
	
	private static TokenService instance = new TokenService();
	private static Map<String, String> params = new HashMap<String, String>();
	public static Token appAccessToken = new Token();

	private TokenService() {}

	public static TokenService getInstance() {
		return instance;
	}

	/**
	 * 通过code换取网页授权access_token，注意该token与公众号token有区别
	 * 
	 * @param code
	 * 				微信授权码
	 * @return	正确时返回：{									<br>
						   "access_token":"ACCESS_TOKEN",	<br>
						   "expires_in":7200,				<br>
						   "refresh_token":"REFRESH_TOKEN",	<br>
						   "openid":"OPENID",				<br>
						   "scope":"SCOPE"					<br>
						  }									<br>
				错误时返回：例如{"errcode":40029,"errmsg":"invalid code"}
	 */
	public JSONObject getToken(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		Map<String, String> tokenPramas = new HashMap<String, String>();
		tokenPramas.put("appid", WechatProperties.getValue("1_app_id"));
		tokenPramas.put("secret", WechatProperties.getValue("1_private_key"));
		tokenPramas.put("code", code);
		tokenPramas.put("grant_type", "authorization_code");
		return parseJson(url,tokenPramas);
	}

	/**
	 * 当token过期时，可以通过上一次获取的refresh_token刷新access_token
	 * 通过access_token获取到的refresh_token参数
	 * @param refreshToken
	 * 						上一次获取access_token时获取的refresh_token
	 * @return	正确时返回：{									<br>
						   "access_token":"ACCESS_TOKEN",	<br>
						   "expires_in":7200,				<br>
						   "refresh_token":"REFRESH_TOKEN",	<br>
						   "openid":"OPENID",				<br>
						   "scope":"SCOPE"					<br>
						  }									<br>
				错误时返回：例如{"errcode":40029,"errmsg":"invalid code"}
	 */
	public JSONObject refreshToken(String refreshToken){
		String url = WechatProperties.getValue("oauth2_refresh_token_url");
		Map<String, String> tokenPramas = new HashMap<String, String>();
		tokenPramas.put("appid", WechatProperties.getValue("1_app_id"));
		tokenPramas.put("secret", WechatProperties.getValue("1_private_key"));
		tokenPramas.put("grant_type", "refresh_token");
		tokenPramas.put("refresh_token",refreshToken);
		return parseJson(url,tokenPramas);
	}
	/**
	 * 获取用户信息
	 * added by Buke at 2016年4月23日
	 */
	public JSONObject getUserInfo(String accessToken,String openId,String lang){
		String url = WechatProperties.getValue("oauth2_get_userinfo_url");
		Map<String,String> userParams = new HashMap<String, String>();
		userParams.put("access_token",accessToken);
		userParams.put("openid", openId);
		userParams.put("lang", lang);
		if(lang == null || "".equals(lang.trim())){
			userParams.put("lang", "zh_CN");
		}
        return parseJson(url,userParams);
	}
	
	/**
	 * 请求转json
	 * added by Buke at 2016年4月23日
	 */
	public JSONObject parseJson(String url,Map<String,String> userParams ){
        try {
        	String res= HttpClientUtil.sendPostRequest(url, userParams);
        	logger.info("授权结果:"+res);
            JSONObject parseObject = JSONObject.parseObject(res);  
            return parseObject;
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return null;
	}
}
