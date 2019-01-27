package com.eastorm.core.others.wechat;


/***
 * 本类用于公众号的基本功能服务
 * @author 郑波
 *
 */
public class WechatService {

	/* 接收微信服务器发过来的XML数据并处理 */
	public static String responseMsg(String wechatXml,String server) {
		return MessageService.getInstance().receMessage(wechatXml, server);
	}
}
