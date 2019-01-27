/**
 *BackLoginInterceptor.java
	Description:
 */
package com.eastorm.cms.interceptor;

import com.eastorm.core.base.utils.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**后台登录拦截器
 * @author Pengkun (penkee@163.com)
 *	2014-1-11
 */
public class BackLoginInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url=request.getRequestURI();
		if(request.getSession().getAttribute(Const.SysMember)==null&&url.indexOf("login")<0
				&&url.indexOf("getCodeImg")<0
				&&url.indexOf("loginout")<0){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			// 未登录
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			String loginPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/back/login";
			builder.append("<p style='display:block;height:400px;margin-top:180px'>会话已过期，请重新<a href='javascript:go()'>登录</a></p>");
			builder.append("<script>function go(){if(!window.parent){window.location='"+loginPath
					+"';}else{window.parent.location='"+loginPath+"';}}</script>");
			
			out.print(builder.toString());
			out.close();
			return false;
		}else{
			return super.preHandle(request, response, handler);
		}
	}
}
