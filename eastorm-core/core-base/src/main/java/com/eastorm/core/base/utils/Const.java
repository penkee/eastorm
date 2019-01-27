/**
 * Const.java
	Description:常量值
 */
package com.eastorm.core.base.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-10-13
 */
public class Const {
	/**
	 * json返回结果的状态定义
	 */
	public enum Result{
		/**
		 * 成功-0
		 */
		SUCCESS,
		/**
		 * 失败-1
		 */
		FAILTURE,
		/**
		 * 异常-2
		 */
		EXCEPTION,
		/**
		 * 没有权限-3
		 */
		NOACCESS
	}
	public static String FAILTURE="1";
	public static String EXCEPTION="2";
	public static String AuthUrls="authUrls";
	public static String SysMember="sysMember";
	public static String SysLogin="syslogin";
	public static String FrontSysMember="frontsysMember";
	public static String HeadPic="image/front/head.png";
	public static String ImgCode="imgcode";//前台，切记不可用于后台
	public static String BACKImgCode="backimgcode";//后台，切记不可用于前台
	public static String SMSCode="smscode";//前台，切记不可用于后台
	public static String Loginerrornum ="loginerrornum";
	public static String ResetPwderrornum ="resetPwderrornum";
	public static int GOUTLIMIT=12;
	/*前端用*/
	public static String FR_FAILTURE="0001";
	public static String FR_EXCEPTION="0002";
	public static String FR_SUCCESS="0000";
	public static String FR_OUTDATE="0003";//过期
	public static String FR_VERCODERROR="0004";//验证码错误
	public static String FR_NOAUTH="1000";//没授权成功
	public static Map<String,String> SMSCODE=new HashMap<String,String>(); 
	static{
		SMSCODE.put("0", "提交成功");
		SMSCODE.put("101", "无此用户");
		SMSCODE.put("102", "密码错");
		SMSCODE.put("103", "提交过快（提交速度超过流速限制）");
		SMSCODE.put("104", "系统忙（因平台侧原因，暂时无法处理提交的短信）");
		
		SMSCODE.put("105", "敏感短信（短信内容包含敏感词）");
		SMSCODE.put("106", "消息长度错（>536或<=0）");
		SMSCODE.put("107", "包含错误的手机号码");
		SMSCODE.put("108", "手机号码个数错（群发>50000或<=0;单发>200或<=0）");
		SMSCODE.put("109", "无发送额度（该用户可用短信数已使用完）");
		
		SMSCODE.put("110", "不在发送时间内");
		SMSCODE.put("111", "超出该账户当月发送额度限制");
		SMSCODE.put("112", "无发送额度（该用户可用短信数已使用完）");
		SMSCODE.put("113", "extno格式错（非数字或者长度不对）");
		SMSCODE.put("115", "自动审核驳回");
		
		SMSCODE.put("116", "签名不合法，未带签名（用户必须带签名的前提下）");
		SMSCODE.put("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
		SMSCODE.put("118", "用户没有相应的发送权限");
		SMSCODE.put("119", "用户已过期");
		SMSCODE.put("120", "内容不在白名单中");
	}
	public static final String CARDNEED = "卡片:需要";
	public static final String CARDNOTNEED = "卡片:不需要";
	public static final String ProductCache = "productCache";
	public static final String ActivitySumCache = "activitySumCache";
	public static final String StringCache = "stringCache";
	public static final String CategoryCache = "categoryCache";
	public static final String DISCOUNT = "discount";
	/**
	 * json返回结果的状态定义
	 */
	public enum PAYNAME{
		weixin// 微信
	}
	/**
	 * json返回结果的状态定义
	 */
	public enum DICTNAME{
		PAYTYPE,// 支付类型
		ORDER_STATUS,
		ORDER_PAYSTATUS,
		ORDER_SHIPSTATUS
	}
}
