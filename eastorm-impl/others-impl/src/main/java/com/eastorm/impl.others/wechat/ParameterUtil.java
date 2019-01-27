package com.eastorm.impl.others.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jun.huyj
 * @version $Id: ParameterUtil.java, v 0.1 Nov 10, 2008 8:49:33 PM jun.huyj Exp $
 */
public class ParameterUtil {

    /**
     * @param params
     * @param isURLEncoder 
     * @return
     */
    public static String getSignData(Map<String, String> params, boolean isURLEncoder) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (value != null) {
            	/** encode **/
            	if(isURLEncoder){
                	value = URLEncoder.encode(value).replace("+", "%20");
                }
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }
        }
        return content.toString();
    }
    /**
     * value为空不参与签名
     * @param params
     * @param isURLEncoder
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getSignDatas(Map<String, String> params, boolean isURLEncoder) throws UnsupportedEncodingException {
        StringBuffer content = new StringBuffer();

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (value != null) {
            	/** encode **/
            	if(isURLEncoder){
                	value =URLEncoder.encode(value, "utf-8");
                }
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } 
        }
        return content.toString();
    }
}
