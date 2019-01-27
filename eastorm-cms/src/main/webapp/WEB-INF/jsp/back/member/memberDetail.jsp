<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <!-- 包含extjs基本库文件-->
	<jsp:include page="/back/comm/_include"/>	
	<script type="text/javascript" src="<%=basePath %>js/ext-4.2.1.883/plugins/extHtmlEditor.js"></script>
	<script type="text/javascript">
	Ext.require([
	    'Ext.data.*',
	    'Ext.util.*',
	    'Ext.form.*',
	    'Ext.tip.QuickTipManager',
	    'Ext.ux.statusbar.StatusBar',//状态bar
	]);

	Ext.onReady(function(){
	    Ext.tip.QuickTipManager.init();
	    var IS_VALID=${IS_VALID_JSON};
	    var MEMBER_GRADE=${MEMBER_GRADE_JSON};
	    var SEX=${SEX_JSON};
	    
	    var id=getUrlParam("id");
	    
	    var member=null;//用户
	    var memberInfo=null;//用户信息
	    var loginList=null;//登录列表
	    var roleList=null;//角色
	    
	    Ext.Ajax.request({
			url: "detail/"+id+"",
			method: "GET", 
			async: false,//异步false
			callback: function(options,success,response) {
				var data = Ext.JSON.decode(response.responseText);
				member=data.member;
				memberInfo=data.memberInfo==null?{}:data.memberInfo;
				loginList=data.loginList;
				roleList=data.roleList;
			},
			failure: function(response) {
				Ext.Msg.alert("警告", "数据加载失败，请稍后再试！"); 
			}
		});
	    
	    Ext.create('Ext.form.Panel', {
	        title: '用户详情',
	        renderTo: Ext.getBody(),
	        bodyPadding: 5,
	        width: 1000,
	        height:800,
	        autoScroll:true,
	        items: [{
        	    xtype: 'fieldcontainer',
        	    layout: 'hbox',
        	    items: [
        	    	{
				    	xtype: 'displayfield',
				    	fieldLabel: '会员编号',
				    	labelWidth: 60,
				    	margin:'0 0 0 30',
				    	value: member.id
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '手机号用户名',
				    	labelWidth: 90,
				    	margin:'0 0 0 30',
				    	value: member.username
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '状态',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: getDictItemValue(IS_VALID,member.isValid)
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '等级',
				    	labelWidth: 60,
				    	margin:'0 0 0 30',
				    	value: getDictItemValue(MEMBER_GRADE,member.grade)
			   		}
              	]},
              	{
             	    xtype: 'fieldset',
             	    layout: 'vbox',
             	    title:'用户基本信息', 
             	    collapsible:true, 
             	    autoHeight:true, 
             	    autoWidth:true, 
             	    items: [{
				    	xtype: 'displayfield',
				    	fieldLabel: '头像',
				    	labelWidth: 60,
				    	margin:'0 0 0 30',
				    	value: memberInfo.headPic
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '昵称',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: memberInfo.nickname
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '性别',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: getDictItemValue(SEX,memberInfo.sex)
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '生日',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: memberInfo.birthday==null?'':Ext.util.Format.date(new Date(memberInfo.birthday),'Y-m-d H:i:s')
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '身高',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: memberInfo.stature
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '体重',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: memberInfo.weight
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '三围',
				    	labelWidth: 30,
				    	margin:'0 0 0 30',
				    	value: memberInfo.sanwei
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '个人标签',
				    	labelWidth: 60,
				    	margin:'0 0 0 30',
				    	value: memberInfo.tag
			   		},{
				    	xtype: 'displayfield',
				    	fieldLabel: '个人介绍',
				    	labelWidth: 60,
				    	margin:'0 0 0 30',
				    	value: memberInfo.intro
			   		}]
               	},
              	{
            	    xtype: 'fieldcontainer',
            	    layout: 'hbox',
            	    items: [
            	    	{
    				    	xtype: 'displayfield',
    				    	fieldLabel: '积分',
    				    	labelWidth: 30,
    				    	margin:'0 0 0 30',
    				    	value: '<font color="#f00">'+memberInfo.score+'</front>'
    			   		},{
    				    	xtype: 'displayfield',
    				    	fieldLabel: '余额',
    				    	labelWidth: 30,
    				    	margin:'0 0 0 30',
    				    	value: '<font color="#f00">'+memberInfo.balance+'元</front>'
    			   		},{
    				    	xtype: 'displayfield',
    				    	fieldLabel: '折扣剩余次数',
    				    	labelWidth: 90,
    				    	margin:'0 0 0 30',
    				    	value: '<font color="#f00">'+memberInfo.candiscount+'</front>'
    			   		},{
    				    	xtype: 'displayfield',
    				    	fieldLabel: '折扣',
    				    	labelWidth: 30,
    				    	margin:'0 0 0 30',
    				    	value: '<font color="#f00">'+memberInfo.discount+'</front>'
    			   		}
                ]},
               	{
            	    xtype: 'displayfield',
            	    margin:'0 0 0 30',
            	    id:'loginList',
            	    items: []
               	},
               	{
            	    xtype: 'fieldcontainer',
            	    layout: 'hbox',
            	    width:800,
            	    items: [
							{
							    xtype: 'fieldcontainer',
							    layout: 'vbox',
							    witdh:390,
							    items: [
										{
										    xtype: 'button',
										    text : '设置用户状态',
										    margin:'0 0 10 20',
										    listeners:{
										    	click:function(){
										    		
										    	}
											}
										},
										{
										    xtype: 'button',
										    text : '修改积分',
										    margin:'0 0 10 20',
										    listeners:{
										    	click:function(){
										    		
										    	}
											}
										}
							 	]
							},
							{
							    xtype: 'fieldcontainer',
							    layout: 'hbox',
							    items: [
										{
										    xtype: 'button',
										    text : '查询操作日志',
										    margin:'0 0 0 20',
										    listeners:{
										    	click:function(){
										    		
										    	}
											}
										}
							 	]
							}
                 	]
               	}
              ]
	    });
	    
	    var loginStore = Ext.create('Ext.data.Store', {
	        fields: ['id','username', 'email', 'mobile', 'lastLoginTime'],
	        data : loginList
	    });
	 
	    Ext.create('Ext.ListView', {
	        renderTo: "loginList",
	        store: loginStore,
	        multiSelect: true,
	        width:800,
	        emptyText: '无数据',
	        reserveScrollOffset: true,
	        hideHeaders: false,        //是否隐藏标题
	        columns: [{
	            header: "登录ID",
	            width:70,
	            dataIndex: 'id'
	        }, {
	            header: "用户名",
	            width:250,
	            dataIndex: 'username'
	        },{
	            header: "邮箱",
	            width:100,
	            dataIndex: 'email'
	        },{
	            header: "手机号",
	            width:120,
	            dataIndex: 'mobile'
	        },{
	            header: "上次登录时间",
	            width:150,
	            dataIndex: 'lastLoginTime',
	            renderer:function(value){
	            	if(value==null)return '';
	            	return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s')
	            }
	        }]
	    });
	});

</script>
</head>
<body>
	
</body>
</html>