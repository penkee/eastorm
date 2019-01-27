/**
 * 增加天数
 * @param date
 * @param num
 * @returns {String}
 */
function addDays(date, num) {
        var targetday_milliseconds = date.getTime() + 1000 * 60 * 60 * 24 * num;
 
        date.setTime(targetday_milliseconds);  
 
        var tYear = date.getFullYear();
        var tMonth = date.getMonth();
        var tDate = date.getDate();
        tMonth = doHandleMonth(tMonth + 1);
        tDate = doHandleMonth(tDate);
        tHour = date.getHours();
        tMinute = date.getMinutes();
        tSecond = date.getSeconds();
        tHour = doHandleMonth(date.getHours());
        tMinute = doHandleMonth(date.getMinutes());
        tSecond = doHandleMonth(date.getSeconds());
 
        return tYear + "/" + tMonth + "/" + tDate;
    }
	function doHandleMonth(month){ 
        var m = month; 
        if(month.toString().length == 1){ 
            m = "0" + month; 
        } 
        return m; 
    }
	/*end*/

function all_Click(checkname,checkstate){
	var chk=document.getElementsByName(checkname);
    for(var i=0;i<chk.length;i++)   
    {   
    	chk[i].checked=checkstate;   
    }   
 }  
/**
 * document.getElementById简写
 * @param {Object} id
 * @return {TypeName} 
 */
function g(id){
	return document.getElementById(id);
}
/**
 * 是否为空
 * @param {Object} v
 * @return {TypeName} 
 */
function isNull(v){
	if(v.value == null || v.value.replace(/\s/g, "") == ''){
		return true;
	}else{
		return false;
	}
}
/**
 * 是否为空
 * @param {Object} v
 * @return {TypeName} 
 */
function isStringNull(v){
	if(v == null || v.replace(/\s/g, "") == ''){
		return true;
	}else{
		return false;
	}
}
function showTip(e,t){
	//clear
	var p=$(e).parent();
	$(e).parent().empty();
	p.append(e);
	
	$(e).after('&nbsp;<span style="color:#f00">'+t+'</span>');
	
	$(e).focus();
}
function checkStrNull(e,tip){
	if (isNull(e)) {
			showTip(e,tip);
			return false;
	} else {
			showTip(e,'');
			return true;
	}
}
function checkParm(){
	var res = true;
	$(".vertify_null").each(function(i,e) {
		if (res) {
			res = checkStrNull(e,$(e).attr('tip'));
		}
	});
	return res;
}
function all_Click(checkname, checkstate) {
		var chk = document.getElementsByName(checkname);
		for ( var i = 0; i < chk.length; i++) {
			chk[i].checked = checkstate;
		}
	}
String.prototype.toDate = function(format) {
        pattern = format.replace("yyyy", "(\\~1{4})").replace("yy", "(\\~1{2})")
.replace("MM", "(\\~1{2})").replace("M", "(\\~1{1,2})")
.replace("dd", "(\\~1{2})").replace("d", "(\\~1{1,2})").replace(/~1/g, "d");
 
        var returnDate;
        if (new RegExp(pattern).test(this)) {
            var yPos = format.indexOf("yyyy");
            var mPos = format.indexOf("MM");
            var dPos = format.indexOf("dd");
            if (mPos == -1) mPos = format.indexOf("M");
            if (yPos == -1) yPos = format.indexOf("yy");
            if (dPos == -1) dPos = format.indexOf("d");
            var pos = new Array(yPos + "y", mPos + "m", dPos + "d").sort();
            var data = { y: 0, m: 0, d: 0 };
            var m = this.match(pattern);
            for (var i = 1; i < m.length; i++) {
 
                if (i == 0) return;
                var flag = pos[i - 1].split('')[1];
                data[flag] = m[i];
            };
 
            if (data.y.toString().length == 2) {
                data.y = parseInt("20" + data.y);
            }
            data.m = data.m - 1;
            returnDate = new Date(data.y, data.m, data.d);
        }
        if (returnDate == null || isNaN(returnDate)) returnDate = new Date();
        return returnDate;
    }
/**
 * 获取字典的某个值
 */
function getDictItemValue(data,value){
	var s='';
	for(var i=0;i<data.length;i++){
		if(data[i].typeKey==value){
			s=data[i].typeValue;
		}
	}
	return s;
}
/**
 * 验证邮箱
 * @param email
 * @returns
 */
function verifyEmail(email){
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	return myreg.test(email);
}

function getRootPath () {
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp?id=3436
    var curWwwPath = window.document.location.href;
    var wIndex=curWwwPath.indexOf('?');
    if(wIndex>0){
    	curWwwPath=curWwwPath.substring(0, wIndex);
    }
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    
    var pos = curWwwPath.indexOf(pathName);
    if('/'==pathName){
    	pos=curWwwPath.length-1;
    }
    //获取主机地址，如： http://localhost:8080
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    
    if(projectName.indexOf('mycx')<0)projectName='';
    return(localhostPaht + projectName);
}

function addCookie(name,value,expiresHours){ 
	var cookieString=name+"="+escape(value); 
	//判断是否设置过期时间 
	if(expiresHours>0){
		var date=new Date(); 
		date.setTime(date.getTime+expiresHours*3600*1000); 
		cookieString=cookieString+"; expires="+date.toGMTString(); 
	} 
	document.cookie=cookieString; 
}
function getCookie(name){ 
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; "); 
	for(var i=0;i<arrCookie.length;i++){
		var arr=arrCookie[i].split("="); 
		if(arr[0]==name)return arr[1]; 
	} 
	return ""; 
}
function deleteCookie(name){
	var date=new Date(); 
	date.setTime(date.getTime()-10000); 
	document.cookie=name+"=v; expires="+date.toGMTString(); 
}

function toString(v){
	if(v==null)return "";
	return v;
}

function changeTip(tip,e){
	if(tip==e.value){
		e.value='';
	}else{
		if(isStringNull(e.value)){
			e.value=tip;
		}
	}
}


/**      
 * 对Date的扩展，将 Date 转化为指定格式的String      
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
 * eg:      
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}       

function isChinese(temp){ 
	if(/.*[\u4e00-\u9fa5]+.*$/.test(temp)) { 
		return true; 
	}
	return false;
}
function setValue(id,v){
	$('#'+id).val(v);
}

function timer(ts)  
{  
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数  
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数  
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数  
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数  
    dd = checkTime(dd);  
    hh = checkTime(hh);  
    mm = checkTime(mm);  
    ss = checkTime(ss);  
    return dd + "天" + hh + "时" + mm + "分" + ss + "秒";  
}  
function checkTime(i)    
{    
   if (i < 10) {    
       i = "0" + i;    
    }    
   return i;    
} 

function checkMail(mail) {
	 var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	 if (filter.test(mail)) 
		 return true;
	 else 
		 return false;
}
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}