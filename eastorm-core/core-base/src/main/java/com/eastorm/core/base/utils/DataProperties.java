/**
 *SysProperties.java
	Description:
 */
package com.eastorm.core.base.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * @author Pengkun (penkee@163.com) 2014-8-11
 */
public class DataProperties {
	public static Properties pro = null;
	public static String configFile = "data.properties";
	private static Logger logger= LoggerFactory.getLogger(DataProperties.class);
	static {
		pro = new Properties();
		ClassLoader loader = DataProperties.class.getClassLoader();
		
		String path=loader.getResource(configFile).getPath();
		path=path.replaceAll("%20", " ").replace("WEB-INF/classes", "upload");
		logger.info(path);
		try {
			FileInputStream fos=new FileInputStream(path);
			pro.load(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Key获取设置的值 added by penkee(penkee@163.com) at 2014-3-11
	 */
	public static String getValue(String key) {
		return pro.getProperty(key);
	}
	
	public static synchronized void setValue(String key,String value) {
		ClassLoader loader = DataProperties.class.getClassLoader();
		pro.setProperty(key,value);
		
		try {
			String path=loader.getResource(configFile).getPath();
			path=path.replaceAll("%20", " ").replace("WEB-INF/classes", "upload");
			FileOutputStream fos=new FileOutputStream(path);
			pro.store(fos, value);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeValue(String key) {
		pro.remove(key);
	}
	
	public static Set<Object> getKeyList() {
		return pro.keySet();
	}
	
	
	public static void main(String[] args){
		DataProperties.setValue("aaa", "西瓜");
	}
}
