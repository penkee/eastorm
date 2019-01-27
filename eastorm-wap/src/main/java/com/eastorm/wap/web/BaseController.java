package com.eastorm.wap.web;

import com.eastorm.api.member.domain.SysMember;
import com.eastorm.core.base.utils.Const;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * 控制器基类 session 放backDyMember,backRole
 * 
 * @author Pengkun (penkee@163.com) 2013-10-13
 */
public class BaseController {
	/**
	 * 获得登录存入session的对象-
	 * 
	 * @param session
	 * @return add by penkee@163.com
	 */
	protected SysMember getCurMember(HttpSession session) {
		return (SysMember)session.getAttribute(Const.FrontSysMember);
	}

	protected ServletContext getServletContext() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
        ServletContext servletContext = webApplicationContext.getServletContext();
		return servletContext;
	}
}
