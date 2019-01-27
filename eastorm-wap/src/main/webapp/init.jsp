<%@page import="com.fastcloud.utils.WechatProperties"%>
<%@page import="com.fastcloud.utils.SysProperties"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		SysProperties.reload();
		WechatProperties.reload();
		out.print(WechatProperties.getValue("wechat_partnerkey"));
	%>
</body>
</html>