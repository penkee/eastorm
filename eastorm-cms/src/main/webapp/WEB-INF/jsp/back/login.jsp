<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd html 4.01 Transitional//EN" "http://www.w3c.org/tr/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>核电活动管理登录</TITLE>
<script src="../js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/back/login.css" />
<mata name=GENERATOR content="MShtml 8.00.6001.23486">
</head>
<body>
		<div></div>
		<table style="margin: auto; width: 100%; HEIGHT: 100%" border=0 cellSpacing=0 cellPadding=0>
			<tbody>
				<tr>
					<td height=150>&nbsp;</td>
				</tr>
				<tr style="HEIGHT: 254px">
					<td>
						<div style="margin: 0px auto; width: 936px">
							<img style="DISPLAY: block" src="../image/back/body_03.jpg">
						</div>
						<div style="BACKGROUND-COLOR: #278296">
							<div style="margin: 0px auto; width: 936px">
								<div
									style="BACKGROUND: url(../image/back/body_05.jpg) no-repeat; HEIGHT: 155px">
									<div
										style="TEXT-ALIGN: left; width: 265px; FLOAT: right; HEIGHT: 125px; _height: 95px">
										<table border=0 cellSpacing=0 cellPadding=0 width="100%">
											<tbody>										
												<tr>
													<td style="HEIGHT: 43px"><input id=username
														class=input type=text name=username></td>
												</tr>
												<tr>
													<td><input id=password class=input type=password
														name=password></td>
												</tr>
												<tr>
													<td style="HEIGHT: 50px">
														<input id=checkcode class=yzm size=8 type=text name=checkcode>
														 <img style="CURSOR: pointer"
														onclick="this.src='getCodeImg?id='+Math.random()*5;"
														alt=验证码,看不清楚?请点击刷新验证码 align=baseline
														src="getCodeImg" width=65 height=25>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div style="HEIGHT: 1px; CLEAR: both"></div>
									<div style="width: 380px; FLOAT: right; CLEAR: both">
										<table border=0 cellSpacing=0 cellPadding=0 width=300>
											<tbody>
												<tr>
													<td width=100 align=right>
														<input class="inputSet" id=btnLogin src="../image/back/btn1.jpg" type=image>
													</td>
													<td width=100 align=middle>
														<input class="inputSet" id=btnReset src="../image/back/btn2.jpg" type="image" >
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div style="margin: 0px auto; width: 936px">
							<img src="../image/back/body_06.jpg">
							<span id="tip" class="formTip"></span>
						</div>
					</td>
				</tr>
				<tr style="HEIGHT: 30%">
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<script language=javascript type=text/javascript>
	$(document).ready(function() {
		$('#username').focus();
		 var checkVar=function(){
			if ($.trim($('#username').val()) == '') {
				$('#username').css("border-color", "#ff9900");
				$('#username').focus();
				return false;
			} else {
				$('#username').css("border-color", "");
			}

			if ($.trim($('#password').val()) == '') {
				$('#password').css("border-color", "#ff9900");
				$('#password').focus();
				return false;
			} else {
				$('#password').css("border-color", "");
			}

			/*if ($.trim($('#checkcode').val()).length != 4) {
				$('#checkcode').css("border-color", "#ff9900");
				$('#checkcode').focus();
				return false;
			} else {
				$('#checkcode').css("border-color", "");
			}*/
			return true;
		};
		
		
		
		$("#btnLogin").click(function(){
			$.ajax({ 
				url: "_login",
				type:"POST",
				async:false,//同步
				dataType:"json",
				data:{
					username:encodeURI($('#username').val()),
					password:encodeURI($('#password').val()),
					checkcode:$('#checkcode').val()
				},
				beforeSend:function(){
					showTip("正在提交。。。");
					return checkVar();
				},
				success: function(data){
		        	switch(data.status){
		        	case 0:
		    			window.location='index';
		    			break;
		        	case 1:
		        		showTip(data.message);
		        		break;
		        	}
		      	},
		      	error:function(jqXHR,  textStatus,  errorThrown){
		      		showTip("系统异常");
		      	}
			});
		});
		$("#btnReset").click(function(){
			formReset();
			});
		
	});
	function showTip(msg,res){
		$('#tip').html(msg);
	}
	function formReset(){
		$('#username').val('');
		$('#password').val('');
		$('#checkcode').val('');
		$('#tip').html('');
	}
	
	document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];        
         if(e && e.keyCode==13){ // enter 键
             $('#btnLogin').click();
        }
    }; 
</script>
</body>
</html>
