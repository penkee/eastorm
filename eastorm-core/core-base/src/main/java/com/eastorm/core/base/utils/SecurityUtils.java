/**
 *SecurityUtils.java
	Description:
 */
package com.eastorm.core.base.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Pengkun (penkee@163.com) 2014-7-30
 */
public class SecurityUtils {
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
	public final static String MD5(String s) throws Exception {
		
		byte[] strTemp = s.getBytes("UTF-8");
		// 使用MD5创建MessageDigest对象
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte b = md[i];
			// 将没个数(int)b进行双字节加密
			str[k++] = hexDigits[b >> 4 & 0xf];
			str[k++] = hexDigits[b & 0xf];
		}
		return new String(str);
	}
	/**
	 * 字符串加密
	 * @param s
	 * @return
	 * add by penkee@163.com
	 */
	public final static String encipherMD5(String s){
		StringBuilder sb=new  StringBuilder();
		
		Random rm=new Random();
		
		int p=rm.nextInt(31)+1;
		sb.append(p);
		sb.append(s);
		sb.insert(p, hexDigits[rm.nextInt(hexDigits.length)]);
		return sb.toString();
	}
	/**
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 * add by penkee@163.com
	 */
	public final static String decipherMD5(String s) throws Exception{
		int p=Integer.parseInt(s.substring(0, s.length()-33));
		return new StringBuilder(s).deleteCharAt(p).substring(s.length()-33);
	}
	public static void main(String[] args){
		long s=System.currentTimeMillis();
		send("15316365728","啊嘎额注册码是235，不许给人看","false","109458329","");
		System.out.println((System.currentTimeMillis()-s)+"毫秒");
	}
	
	public static void send(String mobile,String msg,String needstatus,String product,String extno){
		String reqURL="http://120.26.66.24/msg/HttpBatchSendSM";
    	Map<String, String> params=new HashMap<String,String>();
    	params.put("account", "shmygg_hy");
    	params.put("pswd", "shmygg_hy123");
    	params.put("mobile", mobile);
    	params.put("msg", msg);
    	params.put("needstatus", needstatus);
    	params.put("product", product);
    	params.put("extno", extno);
    	
    	
		try {
			String s = HttpClientUtil.getByHttpClient(reqURL, params);
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
