<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	body{;background-color: #3892D3;}
	.contHead{height: 40px;color:#fff;position: absolute;top:10px;left:0px;width:100%}
	.contHead #topTitle{font-size: 22px;font-weight: 200;display: inline-block;padding: 0px 15px 0px 15px;width: 200px;float: left;}
	.contHead #topLoginInfo{display: inline-block;float: right;height:30px;color:#DFEAF2;font-size: 12px;margin-right: 80px;margin-top: 8px;}
	.contHead #topLoginInfo .topName{font-size: 18px;display: inline-block;margin-right: 10px;color:#fff;font-weight: bold;font-family: 楷体}
</style>
</head>
<body>
		<div class="contHead">
			<div id="topTitle">后台系统DEMO</div>
			<div id="topLoginInfo">
				欢迎你，<span class="topName">${backDyMember.username }</span>
				[<a href="../loginout" style="color:#fff">退出系统</a>]
				&nbsp;&nbsp;&nbsp;上次登录时间：${backLoginDate }</div>
		</div>
</body>
</html>