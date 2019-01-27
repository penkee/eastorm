<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>核电活动后台系统</title>

    <link rel="stylesheet" type="text/css" href="../js/ext-4.2.1.883/examples/portal/portal.css" />
    <!-- 包含extjs基本库文件-->
   <jsp:include page="/back/comm/_include"/>
    <!-- shared example code -->
    <script type="text/javascript" src="../js/ext-4.2.1.883/examples/shared/examples.js"></script>

    <script type="text/javascript">
    	//设置Ext.app对应的url
        Ext.Loader.setPath('Ext.app', '../js/ext-4.2.1.883/examples/portal/classes');
    </script>
    <script type="text/javascript" src="../js/ext-4.2.1.883/examples/portal/portal.js"></script>
    <script type="text/javascript">
	function showsub(){
	    ${submenu}
	}

    Ext.define('Ext.app.Portal', {

        extend: 'Ext.container.Viewport',
        requires: ['Ext.app.PortalPanel', 'Ext.app.PortalColumn', 'Ext.app.GridPortlet', 'Ext.app.ChartPortlet','Ext.tab.Panel'],

        initComponent: function(){
        	//存放在head中的html
            var headHTML = '<iframe class="ifrmwork" scrolling="no" frameborder="0" src="comm/_head"></iframe>';
          //存放在左边菜单中的html
            var leftmenu = '<div class="portlet-content" id="leftmenu"></div>';
        	//存放在panel中的html
            var content = '<iframe class="ifrmwork" id="mainContentfrm" name="mainContentfrm" scrolling="no" frameborder="0" src="comm/_index"></iframe>';
            var currentItem;
            Ext.apply(this, {
                id: 'app-viewport',
                layout: {
                    type: 'border',
                    padding: '0 5 5 5' // pad the layout from the window edges
                },
                items: [
                {//【头部标题】
                    id: 'app-header',
                    xtype: 'box',
                    region: 'north',
                    height: 40,
                    html: headHTML
                },
                {
                    xtype: 'container',
                    region: 'center',
                    layout: 'border',
                    items: [
                    {//【左边树形菜单】
                        id: 'app-options',
                        title: '选项',
                        region: 'west',
                        animCollapse: true,//动画切换
                        width: 200,
                        minWidth: 150,
                        maxWidth: 400,
                        height:909,
                        split: true,
                        collapsible: true,//可切换
                        layout:{
                            type: 'accordion',
                            animate: true
                        },
                        items: [
                         ${firstmenu}
                         ]
                    },
                    {//主板块内容
                        id: 'app-portal',
                        xtype: 'tabpanel',
                        region: 'center',
                        plugins: Ext.create('Ext.ux.TabCloseMenu', {
                            closeTabText: '关闭当前',
                            closeOthersTabsText: '关闭其他',
                            closeAllTabsText: '关闭所有',
                            extraItemsTail: [
                                        '-',
                                        {
                                            text: '可关闭',
                                            checked: true,
                                            hideOnClick: true,
                                            handler: function (item) {
                                            	var tabs = Ext.getCmp('app-portal');
                                            	tabs.remove(item);
                                            }
                                        }
                                    ],
                            listeners: {
                                aftermenu: function () {
                                   //currentItem = null;
                                },
                                beforemenu: function (menu, item) {
                                    var menuitem = menu.child('*[text="可关闭"]');
                                    currentItem = item;
                                    menuitem.setChecked(item.closable);
                                }
                            }
                        }),
                        items: [{
                            title: '欢迎页',
                            closable:false,
                            html: content,
                           height: getAutoHeight(),
                            listeners: {//定义关闭事件
                                'close': Ext.bind(this.onPortletClose, this)
                            }
                        }]
                    }]
                }, {//【头部标题】
                    xtype: 'box',
                    region: 'south',
                    height: 16,
                    html:'<div style="text-align: center;vertical-align: middle;color:#fff;font-weight: bold;font-family: 微软雅黑">上海达云科技&copy; 版权所有</div>'
                }]
            });
            this.callParent(arguments);
        },

        onPortletClose: function(portlet) {
            this.showMsg('"' + portlet.title + '" was removed');
            //console.log( portlet.title)
        },

        showMsg: function(msg) {
            var el = Ext.get('app-msg'),
                msgId = Ext.id();

            this.msgId = msgId;
            el.update(msg).show();

            Ext.defer(this.clearMsg, 3000, this, [msgId]);
        },

        clearMsg: function(msgId) {
            if (msgId === this.msgId) {
                Ext.get('app-msg').hide();
            }
        }
    });
    
    function getAutoHeight(){
    	//console.log(document.documentElement.clientHeight)
    	return document.documentElement.clientHeight-10;  	
    }
    Ext.onReady(function(){
            Ext.create('Ext.app.Portal'); 
            showsub();
        });
    //增加tab
    function addTab(url,title){
    	if(url=='')return;
    	 var tabs = Ext.getCmp('app-portal');
    	//判断是否已存在
    	var tmpHtml='<iframe class="ifrmwork" name="mainContentfrm" scrolling="true" frameborder="0" src="'+url+'"></iframe>';
    	var items=tabs.items.items;
    	if(url.indexOf('orderDetail')<0){
    		for(var t=0;t<items.length;t++){
        		if(items[t].initialConfig.html==tmpHtml){
        			  tabs.setActiveTab(t);
        			  return;
        		}
        	}
    	}
    	 var tab = tabs.add({
             title: title,
             closable: true,
             html : tmpHtml
         });

         tabs.setActiveTab(tab); 
    }
    </script>
    <!-- </x-compile> -->
    <style>
   .ifrmwork{width:100%;border: 0px #fff solid;height:100%;}
   .x-grid-body{border-top-width: 0px}
   .x-tree-node-text  a{text-decoration: none;color:#666666;}
    </style>
</head>
<body>
    <span id="app-msg" style="display:none;"></span>
</body>
</html>
