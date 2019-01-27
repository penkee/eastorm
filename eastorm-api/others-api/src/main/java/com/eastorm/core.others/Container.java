package com.eastorm.core.others;

import com.eastorm.core.others.wechat.WXUserInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:江东大人
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Container {
    /**
     * 机器当前扫码用户
     */
    public static Map<String,WXUserInfo> SAOMAO_USERS=new ConcurrentHashMap<String, WXUserInfo>();
}
