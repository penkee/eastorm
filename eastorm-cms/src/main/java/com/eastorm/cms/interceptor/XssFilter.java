package com.eastorm.cms.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器说明： 这个是防止页面提交参数出现XSS攻击的
 * 过滤器，主要针对请求的参数进行检查，如发现有非法字符串
 * 将过滤一遍。
 *
 * @author charles.feng
 * @ClassName: XssFilter
 * @Description:
 * @date 2015年7月28日 下午2:41:21
 */
public class XssFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(XssFilter.class);

    public FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp= (HttpServletResponse)response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, content-type, Accept");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.config = null;
    }

}