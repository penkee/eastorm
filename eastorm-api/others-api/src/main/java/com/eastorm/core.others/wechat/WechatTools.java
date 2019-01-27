package com.eastorm.core.others.wechat;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 微信工具
 * @author 郑波
 *
 */
public class WechatTools {
	private static Logger log = Logger.getLogger(WechatTools.class);
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseWechatXML(String wechatXML) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(wechatXML);
			Element root = doc.getRootElement();
			List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			log.error(WechatTools.class.getName()+":parse xml err");
			return map;
		}
		return map;
	}

	public static String generatorXML(Map<String, String> reply) {
		if (null == reply) {
			return null;
		}
		Document document = DocumentHelper.createDocument();
		/* document.setXMLEncoding("UTF-8"); */
		Element root = document.addElement("xml");

		for (Entry<String, String> entry : reply.entrySet()) {
			Element e = root.addElement(entry.getKey());
			e.setText(entry.getValue());
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
        StringWriter sw = new StringWriter(); 
        XMLWriter xw = new XMLWriter(sw, format); 
        xw.setEscapeText(false); 
		
        try { 
            xw.write(document); 
            xw.flush(); 
            xw.close(); 
	    } catch (IOException e) { 
	        log.error(WechatTools.class.getName()+":format xml err");
	    } 
	       return sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""); 
		}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		/* 把密文转换成十六进制的字符串形式 */
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	/**
	 * SHA1加密
	 */
	public static final String SHA1(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public static String mapSha1(TreeMap<String, String> map) {
		if (map == null) {
			return "";
		}
		String str = "";
		for (String key : map.keySet()) {
			str += (key + "="
					+ URLEncoder.encode(map.get(key)).replace("+", "%20") + "&");
		}
		str = str.substring(0, str.lastIndexOf("&"));
		return SHA1(str);
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.trim().length() > 0);
	}

	/**
	 * 除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	public static String jsonMsg(boolean flag,String msg){
		return "{\"isok\":\""+flag+"\",\"msg\":\""+msg+"\"}";
	}
	/**
	   * MD5加密，解决汉字编码问题
	   * @param datastr
	   * @param key1
	   * @param key2
	   * @return
	   */
	public static String md5(String datastr,String key1,String key2){
		try{
			datastr = datastr + key1 + key2;
			MessageDigest md = MessageDigest.getInstance("MD5");
			/*
			 * 问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
			 */
			md.update(datastr.getBytes("utf-8"));  
			StringBuffer buf=new StringBuffer();
			for(byte b:md.digest()){
				buf.append(String.format("%02x", b&0xff));
			}
			return buf.toString();
		}catch( Exception e ){
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getMD5(String source) {
		String s = null;
		char hexDigits[] = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f' };
		try {
			MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte tmp[] = md.digest(); 
			char str[] = new char[16 * 2]; 
			int k = 0; 
			for (int i = 0; i < 16; i++) { 
				byte byte0 = tmp[i]; 
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				str[k++] = hexDigits[byte0 & 0xf]; 
			}
			s = new String(str); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
