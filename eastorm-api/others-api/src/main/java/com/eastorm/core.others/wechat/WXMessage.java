package com.eastorm.core.others.wechat;
/**
 * 微信消息拼接模板
 * @author pengk
 *
 */
public class WXMessage {
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String content;

	public WXMessage() {
		super();
	}

	public WXMessage(String toUserName, String fromUserName,
			String createTime, String content) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.content = content;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString(){
		String xml = "<xml>"
			           +"<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>"
			           +"<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>"
			           +"<CreateTime>"+this.getCreateTime()+"</CreateTime>"
			           +this.getContent()
		           +"</xml>";
		return xml;
	}
}
