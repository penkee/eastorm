/**
 *SystemInit.java
	Description:
 */
package com.eastorm.cms.init;

import com.eastorm.api.common.service.DictItemService;
import com.eastorm.api.common.service.SysCacheService;
import com.eastorm.core.base.utils.SysProperties;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Pengkun (penkee@163.com)
 *	2014-8-11
 */
public class SystemInit extends HttpServlet{
	private Logger logger = Logger.getLogger(SystemInit.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    //初始化菜单根和城市根,产品分类根
		String menuRootId = SysProperties.getValue("menuRootId");
		String cityRootId= SysProperties.getValue("cityRootId");
		String categoryId=SysProperties.getValue("categoryId");
		String wapmenuRootId=SysProperties.getValue("wapmenuRootId");
		
		ServletContext context=getServletContext();
		context.setAttribute("menuRootId", Long.parseLong(menuRootId));
		context.setAttribute("cityRootId", Long.parseLong(cityRootId));
		context.setAttribute("categoryId", Long.parseLong(categoryId));
		context.setAttribute("wapmenuRootId", Long.parseLong(wapmenuRootId));
		
		logger.info("初始化menuRootId："+menuRootId);
		logger.info("初始化cityRootId："+cityRootId);
		logger.info("初始化categoryId："+categoryId);
		logger.info("初始化wapmenuRootId："+wapmenuRootId);
		
		//加载字典-自定义key_value
	    WebApplicationContext wac = WebApplicationContextUtils .getWebApplicationContext (context);
	    DictItemService dictItemService= (DictItemService) wac.getBean("dictItemService");
	    dictItemService.refreshCache(context);
	    
		//加载缓存，是从数据库读取sql
	    SysCacheService sysCacheService= (SysCacheService) wac.getBean("sysCacheService");
	    sysCacheService.refreshCache(context);
	}
}
