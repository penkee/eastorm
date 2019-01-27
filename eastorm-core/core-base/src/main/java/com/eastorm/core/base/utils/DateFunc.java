/**
 *DateUtils.java
	Description:
 */
package com.eastorm.core.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-12-15
 */
public class DateFunc {
	private static String[] pattern = new String[]{
		"yyyy-MM",//---------------------------------------------0
		"yyyyMM",//---------------------------------------------1
		"yyyy/MM",   //---------------------------------------------2
        "yyyyMMdd",//---------------------------------------------3
        "yyyy-MM-dd",//---------------------------------------------4
        "yyyy/MM/dd",   //---------------------------------------------5
         "yyyyMMddHHmmss",   //---------------------------------------------6
         "yyyy-MM-dd HH:mm:ss",   //---------------------------------------------7
         "yyyy/MM/dd HH:mm:ss"//---------------------------------------------8
         };  
	/**
	 * 字符串转date
	 * @param s
	 * @return
	 * add by penkee@163.com
	 */
	public static Date parse(String s){
		Date res=null;
		try {
			res= DateUtils.parseDate(s,pattern);
		} catch (Exception e) {}
		return res;
	}
	/**
	 * 格式化date
	 * @param date
	 * @return
	 * add by penkee@163.com
	 */
	public static String format(Date date){
		SimpleDateFormat sm=new SimpleDateFormat(pattern[7]);
		return sm.format(date);
	}
	/**
	 * 格式化date
	 * @param date
	 * @return
	 * add by penkee@163.com
	 */
	public static String format(Date date,int index){
		SimpleDateFormat sm=new SimpleDateFormat(pattern[index]);
		return sm.format(date);
	}
}
