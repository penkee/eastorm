package com.eastorm.impl.others.wechat;

import com.eastorm.core.others.Container;
import com.eastorm.core.others.wechat.WXMessage;
import com.eastorm.core.others.wechat.WXUserInfo;
import com.eastorm.core.others.wechat.WechatProperties;
import com.eastorm.core.others.wechat.WechatTools;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 微信消息处理
 * @author 郑波
 *
 */
public class MessageService{
	private static Logger  logger=Logger.getLogger(MessageService.class);
	
	/* 文本 */
	public final static String MSG_TYPE_TEXT = "text";
	/* 位置 */
	public final static String MSG_TYPE_LOCATION = "location"; 
	/* 图片 */
	public final static String MSG_TYPE_IMAGE = "image"; 
	/* 链接 */
	public final static String MSG_TYPE_LINK = "link"; 
	/* 语音 */
	public final static String MSG_TYPE_VOICE = "voice"; 
	/* 事件 */
	public final static String MSG_TYPE_EVENT = "event"; 
	/* 视频 */
	public final static String MSG_TYPE_VIDEO = "video"; 
	/* 文本+图片 */
	public final static String MSG_TYPE_NEWS = "news"; 
	/* 音乐 */
	public final static String MSG_TYPE_MUSIC = "music";
	/* 投诉消息 */
	public final static String MSG_TYPE_COMPLAINTS = "request_confirm_reject"; 
	
	private static MessageService instance = new MessageService();
	
	public static MessageService getInstance(){
		return instance;
	}
	
	/**
	 * 接收微信用户发过来的消息并处理
	 * @param wechatXML 微信消息体
	 * @param platform 平台 f:服务号 d:订阅号
	 * @return
	 */
	public String receMessage(String wechatXML,String platform) {
		try {
			if(!WechatTools.isNotEmpty(wechatXML)){
				return "";
			}
			Map<String, String> map = WechatTools.parseWechatXML(wechatXML);
			return handle(map,platform);
		} catch (Exception e) {
			logger.error(MessageService.class.getName()+":消息处理失败，原因是"+e.getMessage());
			return "";
		}
	}
	private String handle(Map<String, String> map,String platform){
		if(map == null || map.size() <= 0) return "";
		String msgType = map.get("MsgType");
		if (MSG_TYPE_TEXT.equals(msgType)) {
			 return "";
		} else if (MSG_TYPE_IMAGE.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_LOCATION.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_LINK.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_VOICE.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_VIDEO.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_MUSIC.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_EVENT.equals(msgType)) {
			return eventHandle(map,platform);
		} else if (MSG_TYPE_NEWS.equals(msgType)) {
			return "";
		} else if (MSG_TYPE_COMPLAINTS.contains(msgType)) {
			return "";
		}else {
			return "";
		}
	}
	/**
	 * 事件消息处理
	 */
	public String eventHandle(Map<String, String> map, String platform) {
		String event = map.get("Event");
		String key = map.get("EventKey");
		
		logger.info(map);
		/* 订阅关注事件 */
		if ("subscribe".equalsIgnoreCase(event)) {
			String openId=map.get("FromUserName");
			if(!StringUtils.isEmpty(openId)){
				if(key!=null&&key.length()>5){
					String mid=key.substring(6);
					WXUserInfo wxInfo= Container.SAOMAO_USERS.get(mid);
					if(wxInfo==null){
						wxInfo=new WXUserInfo();
					}
					wxInfo.setOpenId(openId);
					
					
				}
			}
			
			String content = map.get("Content");
			String toUserName = map.get("FromUserName");
			String fromUserName = map.get("ToUserName");
			String answer= WechatProperties.getValue("welcome");
			return createMsgTemplate(answer,fromUserName,toUserName);
		}
		return  "";
	}
	
	/**
	 * 创建微信消息模板
	 * added by pengkun at 2014-12-12
	 * 修改成通用模板，具体不同之处从自动回复设置中读取 modified by pengkun at 2015-1-9
	 * @param content
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public String createMsgTemplate(String content,String fromUserName,String toUserName){
		if(StringUtils.isEmpty(content))return "";
		
		WXMessage message = new WXMessage();
		if(content.indexOf("<MsgType>")<0){
			//纯文本
			content="<MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+content+"]]></Content>";
		}
		message.setContent(content);
		message.setCreateTime(String.valueOf(System.currentTimeMillis()));
		message.setFromUserName(fromUserName);
		message.setToUserName(toUserName);
		return message.toString();
	}
}
