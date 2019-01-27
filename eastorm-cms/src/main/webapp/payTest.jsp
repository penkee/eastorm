<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="../../css/style.css">
<script src="js/jquery-1.7.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title></title>
<meta name="keywords" content="" /> 
<meta name="description" content="" /> 
<script>
	
	wx.ready(function(){
		alert('ok');
	});

	wx.error(function(res){
		document.write(strobj(res));
	});
</script>
</head>

<body>
	
</body>
</html>