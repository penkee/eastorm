package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.MenuItem;
import com.eastorm.api.common.service.MenuItemService;
import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.core.base.utils.Const;
import org.springframework.data.domain.Page;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器基类 session 放backDyMember,backRole
 * 
 * @author Pengkun (penkee@163.com) 2013-10-13
 */
public class BaseController {
	/**
	 * 获得后台登录存入session的对象-
	 * 
	 * @param session
	 * @return add by penkee@163.com
	 */
	protected SysMember getCurMember(HttpSession session) {
		return (SysMember)session.getAttribute(Const.SysMember);
	}
	/**
	 * 获得后台登录存入session的对象-
	 * 
	 * @param session
	 * @return add by penkee@163.com
	 */
	protected SysLogin getCurLogin(HttpSession session) {
		return (SysLogin)session.getAttribute(Const.SysLogin);
	}
	/**
	 * 获得后台登录role的urls
	 * 
	 * @param session
	 * @return add by penkee@163.com
	 */
	protected String getCurAuthUrls(HttpSession session) {
		return (String)session.getAttribute(Const.AuthUrls);
	}

	/**
	 * 返回时map定义
	 * 
	 * @param list
	 * @return add by penkee@163.com
	 */
	protected Map<String, Object> resultMap(Page list) {
		Map<String, Object> resMap = new HashMap<String, Object>(2);
		resMap.put("totalCount", list.getTotalElements());
		resMap.put("list", list.getContent());
		return resMap;
	}
	/**
	 * 返回时map定义
	 * 
	 * @param list
	 * @return add by penkee@163.com
	 */
	protected Map<String, Object> resultMap(List list) {
		Map<String, Object> resMap = new HashMap<String, Object>(2);
		resMap.put("totalCount", list.size());
		resMap.put("list", list);
		return resMap;
	}
	/**
	 * 检测url是否有权限获得
	 * @param session
	 * @param url,类似back/oa/roleList.html
	 * @return
	 * add by penkee@163.com
	 */
	protected boolean isAccess(HttpSession session,String url,MenuItemService menuItemService){
		if(url.indexOf("WEB-INF")>0){
			return true;
		}
		String authUrls=getCurAuthUrls(session);
		int bindex=url.indexOf("back/");
		String checkUrl=url.substring(bindex+5);
		MenuItem item=menuItemService.queryByAuthUrls(checkUrl);
		if(item!=null&&authUrls.indexOf("A"+item.getId()+",")>=0){
			return true;
		}else{
			return false;
		}
	}
	
	protected ServletContext getServletContext() {
		 WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
         ServletContext servletContext = webApplicationContext.getServletContext();
		return servletContext;
	}
}
