function logout(){
	$.ajax({
		url: getRootPath()+"/logout",
		dataType:"json",
		success: function(data){
			location.reload();
      	}
	});
}
/**
 * 自动登录
 */
function autologin(){
	//如果cookie中loginsign存在，则提交
	var loginsign=getCookie("loginsign");
	var username=getCookie("username");
	var hasLogin=getCookie("hasLogin");
	if(!isStringNull(loginsign)&&!isStringNull(username)&&isStringNull(hasLogin)){
		$.ajax({
			type: 'POST',
			url: getRootPath()+"/loginauto",
			data:{
				"loginsign":loginsign,
				"username":username
			},
			dataType:"json",
			async:false,
			success: function(data){
				if("0000"==data.errCode){
					addCookie('hasLogin','1',0);
					location.reload();
				}
	      	}
		});
	}
}
autologin();

/**
 * 展示下一级城市列表
 * @param sid
 * @param e
 */
function showNextAddr(sid,e,showStyle,selected){
	if(!isNull(e)){
		$.ajax({
			type: 'GET',
			url: getRootPath()+"/sys/city/readAll",
			async:false,
			data:{
				"parentId":e.value
			},
			dataType:"json",
			success: function(data){
				showStyle(sid,data,selected);
	      	}
		});
	}
}
function showStyle1(sid,data,selected){
	var list=data.list;
	var str='<option value="">'+$('#'+sid).children(0).html()+'</option>';
	for(var i=0;i<list.length;i++){
		str+='<option value="'+list[i].id+'" '+(list[i].id==selected?'selected="selected"':'')+'>'+list[i].typeValue+'</option>'
	}
	$('#'+sid).html(str);
}

function showTrafficSite(sid,e,showStyle,tool,selected){
	if(!isNull(e)&&!isStringNull(tool)){
		$.ajax({
			type: 'GET',
			url: getRootPath()+"/sys/trafficSite/readAll",
			data:{
				"areaId":e.value,
				"tool":tool
			},
			dataType:"json",
			success: function(data){
				showStyle(sid,data,selected);
	      	}
		});
	}
}

function showTrafficSiteStyle1(sid,data,selected){
	var list=data.list;
	var str='<option value="">'+$('#'+sid).children(0).html()+'</option>';
	for(var i=0;i<list.length;i++){
		str+='<option value="'+list[i].id+'" '+(list[i].id==selected?'selected="selected"':'')+'>'+toString(list[i].name)+'-'+toString(list[i].trafficName)+'</option>'
	}
	$('#'+sid).html(str);
}

/**
 * 根据字母分组获取城市
 */
function queryCitys(letter,selPutid){
	if(!isStringNull(letter.value)&&isChinese(letter.value)){
		$.ajax({
			type: 'GET',
			url: getRootPath()+"/sys/area/readAll?firstChar="+encodeURI(encodeURI(letter.value)),
			dataType:"json",
			success: function(data){
				var html='';
				
				$(data.list).each(function (i,e) {
					html+='<span><a title="'+e.typeValue+'" href="javascript:getSelectedCity(\''+e.id+'\',\''+letter.id+'\',\''+e.typeValue+'\',\''+selPutid+'\')">'+e.typeValue+'</a></span>';
				});
				
				if(html==''){
					$('#con_one_1').html("对不起，没有该城市");
				}else{
					$('#con_one_1').html(html);
				}
	      	}
		});
	}
}

