/**
 *StringFunc.java
	Description:
 */
package com.eastorm.core.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.eastorm.core.base.sys.EastormException;

/**
 * @author Pengkun (penkee@163.com)
 *	2014-1-11
 */
public class StringFunc {
	public static String URLDecoder(String s,String enc){
		try {
			return URLDecoder.decode(s,enc);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
	public static String URLDecoder(String s){
		if(isNull(s))return s;
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
	public static String URLEncoder(String s){
		if(isNull(s))return s;
		try {
			return URLEncoder.encode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
	/**
	 * 判断字符串是否是空
	 * @param s
	 * @return
	 * add by penkee@163.com
	 */
	public static boolean isNull(Object s){
		if(s==null||toString(s).isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取随机字符串
	 * @param s
	 * @return
	 * add by penkee@163.com
	 */
	public static String getRandomStr(int num){
		char[] chars={'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y'};
		Random rn=new Random();
		String res="";
		for(int i=0;i<num;i++)
			res+=chars[rn.nextInt(chars.length)];
		return res;
	}
	/**
	 * 获取随机字符串
	 * @param s
	 * @return
	 * add by penkee@163.com
	 */
	public static String getRandomNumber(int num){
		char[] chars={'1','2','3','4','5','6','7','8','9'};
		Random rn=new Random();
		String res="";
		for(int i=0;i<num;i++)
			res+=chars[rn.nextInt(chars.length)];
		return res;
	}
	/**
	 * 不为空
	 * added by Buke at 2015年6月6日
	 */
	public static String toString(Object s){
		if(s==null)return "";
		
		return s.toString().replace("null", "").replace("undefined","").trim();
	}
	
	 public static String join(Object[] array, String separator) {
	        if (array == null) {
	            return null;
	        }
	        if (separator == null) {
	            separator = ",";
	        }

	        int bufSize = array.length;
	        bufSize *= ((array[0] == null ? 16 : array[0].toString().length())+ separator.length());

	        StringBuffer buf = new StringBuffer(bufSize);

	        for (int i = 0; i < array.length; i++) {
	        	if(isNull(array[i]))continue;
	        	
	        	//当不是第一个且确定有下一个时候加分隔符
	        	if(buf.length()>0&&i<array.length){
	        		buf.append(separator);
	        	}
	            buf.append(array[i]);
	        }
	        return buf.toString();
	}
	public static void checkNull(Object s,String errMsg) throws EastormException {
		if(isNull(s))throw new EastormException(Const.FR_FAILTURE,errMsg+"不能为空");
	}
	public static void checkObjectNull(Object s,String errMsg) throws EastormException {
		if(s==null)throw new EastormException(Const.FR_FAILTURE,errMsg);
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
					if (ip == null || ip.length() == 0
							|| "unknown".equalsIgnoreCase(ip)) {
						ip = request.getHeader("HTTP_CLIENT_IP");
						if (ip == null || ip.length() == 0
								|| "unknown".equalsIgnoreCase(ip)) {
							ip = request.getHeader("HTTP_X_FORWARDED_FOR");
							if (ip == null || ip.length() == 0
									|| "unknown".equalsIgnoreCase(ip)) {
								ip = request.getRemoteAddr();
							}
						}
					}
				}
			}
		}
		
		/** 如果ip来源是负载均衡设备 则 使用clientip获取值 */
		if(null == ip){
			ip = request.getHeader("client-ip");
			String cdnip = request.getHeader("Cdn-Src-Ip");
			if(null == ip && cdnip == null){
				ip = request.getRemoteAddr();
			}else if(cdnip != null){
				ip = cdnip;
			}
		}
		return ip;
	}

	 
	public static void main(String[] a){
		String[] formAttr={"bb","343","","undefined","gag",null};
		System.out.println(join(formAttr, ","));
	}
	/**
	 * strToDb(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param totalFee
	 * @return
	 * @return int
	*/
	public static double strToDb(String str) {
		if (isNull(str)) {
			return 0;
		} else {
			return Double.parseDouble(str);
		}
	}
	/**
	 * formatdb(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param d
	 * @return
	 * @return String
	*/
	public static String formatdb(double paramDouble) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(paramDouble);
	}
	/**
	 * 是否手机号
	 * @param mobile
	 * @return 
	 * added by Buke at 2016年5月31日
	 */
	public static boolean isMobile(String mobile){
		if(isNull(mobile))return false;
	
		String regx = "^1[0-9]{10}$";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	public static List<Integer> splitInteger(String str,String separator){
		if(StringFunc.isNull(str))return null;
		
		List<Integer> res=new ArrayList<Integer>();
		String[] arr=str.split(separator);
		for (String aid : arr) {
			res.add(Integer.parseInt(aid));
		}
		return res;
	}
}
