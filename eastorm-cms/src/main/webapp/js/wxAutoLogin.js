//得到地址栏参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 	
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return decodeURI(r[2]); return null; 
}
/*
1.cookie里边判断没有登陆，且来源是weixin的，就跳转授权
2.授权返回有code，调用联合登陆，保存cookie后，即登陆成功

 8_app_id=wx83798fdeb71f7a8a
 9_app_id=wxc68fdf265e81a885

*/

function wxlogin(){
	var code=GetQueryString("code");
	if(code){
		var url= "wap.dycloud.com";
		$('#code').html(code);
		$.ajax({
			type: "get",
			url:'http://'+ url+"/wechat/wxlogin",
			data: {
				"code":code
			},
			dataType: "json",
			success: function(data){
				if(data.errCode!='0000'){
					alert("显示："+data.errMsg);
				}else{
					alert(data.wxInfo.openid)
					alert(data.wxInfo.nickname)
					
					localStorage.setItem("openid",data.wxInfo.openid);//设置b为"isaac"
				}
			},
			error:function(data){
				alert('请求失败，请稍后再试');
			}
		});

	}else{
		var openid = localStorage.getItem("openid");//获取b的值
		if(openid==null){
			var url = "" + window.location + "";//不知道那个的redirect_uri开头有个空格，可以不用
			url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2af5f37afeacfd3d&redirect_uri=' + url + '&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect';
			window.location.replace(url);
		}
	}

}

