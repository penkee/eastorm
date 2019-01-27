<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <!-- 包含extjs基本库文件-->
	<jsp:include page="/back/comm/_include"/>	
	<script type="text/javascript">
	Ext.require([
	    'Ext.grid.*',
	    'Ext.data.*',
	    'Ext.util.*',
	    'Ext.form.*',
	    'Ext.ux.form.SearchField',
	    'Ext.toolbar.Paging',
	    'Ext.ModelManager',
	    'Ext.tip.QuickTipManager',
	    'Ext.ux.statusbar.StatusBar',//状态bar
	]);

	Ext.onReady(function(){
		var  searchWin,updateWin;
	    Ext.tip.QuickTipManager.init(); 
   		var list;//menuList
	    
	    Ext.Ajax.request({ 
			url: "../sys/menu/readAll",
			method: "GET", 
			async: false,//异步false
			callback: function(options,success,response) { 
				var jsonResult = Ext.JSON.decode(response.responseText);    
				list=jsonResult.list;
			}, 
			failure: function(response) { 
				Ext.Msg.alert("警告", "数据加载失败，请稍后再试！"); 
			} 
		}); 
	    //生成后的符合extjs的json数据
        var treeData={};
        var rootNode={id:8,name:'管理'};  //[test-发布修改]         
        
        
        var initTreeData=function(node,treeData,list,authUrls){
         	//先node自己属性
         	treeData.text= node.name;
         	if(authUrls!=null&&authUrls.indexOf('A'+node.id+',')>=0){
         		treeData.checked=true;
         		treeData.expanded= true;
         	}else{
        		treeData.checked=false;
         	}
         	treeData.itemId=node.id;
          	//然后所有孩子
          	var childCount=0;
         	 for(var i in list){
          		if(list[i].parent.id==node.id){
         			//生成孩子tree
         			var subTree={};
     				initTreeData(list[i].node,subTree,list,authUrls);
     
         			if(childCount==0){                                  
         				treeData.children=[];
         				treeData.children.push(subTree);
         			}else{
         				treeData.children.push(subTree);
         			} 
         			childCount++;
          		}
          }
          if(childCount==0){
          	//没孩子
         	treeData.leaf= true;
          }
       }
        
        
	    function showSearchForm() {
	        if (!searchWin) {
	            var form = Ext.widget('form', {
	            	layout: {
	                    type: 'vbox',
	                    align: 'stretch'
	                },
	                border: false,
	                bodyPadding: 10,
	                width: 250,
	                fieldDefaults: {
	                	msgTarget: 'side',
	                    labelAlign: 'left',//标签居左
	                    labelWidth: 50
	                },
	                items: [{
						xtype: 'numberfield',
						minValue: 0,
						fieldLabel: '编号',
						name:'id'
					}, {
						xtype: 'textfield',
						fieldLabel: '名称',
						name:'name'
					}],

	                buttons: [{
	                    text: '确定',
	                    handler: function() {
	                    	if (this.up('form').getForm().isValid()) {
	                        	store.load();
	                            this.up('window').hide();
	                        }
	                    }
	                },{
	                    text: '清空搜索条件',
	                    handler: function() {
	                        this.up('form').getForm().reset();
	                    }
	                }]
	            });
	         
	            //为了弹出window
	            searchWin = Ext.widget('window', {
	                title: '输入搜索条件',
	                closeAction: 'hide',
	                width: 260,
	                height: 200,
	                minWidth: 150,
	                minHeight: 100,
	                layout: 'fit',
	                resizable: true,
	                modal: true,
	                items: form
	            });
	        }
	        searchWin.show();
	    }
	    function showDetailForm(authUrls) {
	    	var selection = grid.getView().getSelectionModel().getSelection()[0];	    
	    	authUrls=selection.data.authUrls;
	    	
	    	if(authUrls==null)authUrls='';
	    	
	    	initTreeData(rootNode,treeData,list,authUrls);
	    	var treeStore = Ext.create('Ext.data.TreeStore', {
           	 	root:treeData
           	});
            
            var tree=Ext.create('Ext.tree.Panel', {
        	    width: '90%',
        	    height: 290,
        	    minHeight: 200,
        	    store: treeStore,
        	    rootVisible: true,
        	    listeners: {       	    	
        	    	checkchange: function(node, checked, options) {
        	        	node.cascadeBy(function(n) { n.set('checked', checked); });
        	        	if (checked) {           
        	            	node.bubble(function(n) { n.set('checked', checked); });
        	        	}
        	        	else {
        	            	node.bubble(function(n) {
        	                    var childNodes = this.childNodes;
        	                    var length = childNodes.length;
        	                    var allUnchecked = true;

        	                    for (var i = 0; i < length; i++) {
        	                        if (childNodes[i].get('checked')) {
        	                            allUnchecked = false;
        	                            break;
        	                        }
        	                    }

        	                    if (allUnchecked) {
        	                        n.set('checked', false);
        	                    }
        	            });
        	        }   
        	    }
        	}
        	
        	});


            var form = Ext.widget('form', {
            	layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                border: false,
                bodyPadding: 10,
                width: '100%',
                height:'100%',
                fieldDefaults: {
                	msgTarget: 'side',
                    labelAlign: 'left',//标签居左
                    labelWidth: 35
                },
                items: [{
                    xtype: 'displayfield',
                    fieldLabel: '名称',
                    name:'name'
                },{
                    xtype: 'displayfield',
                    fieldLabel: '权限',
                    height:400,
                    id:'treeId'
                }]
            });
            form.on("render",function(){
            	tree.render("treeId-inputEl");
            })
	            //为了弹出window
	            var updateWin = Ext.widget('window', {
	                title: '角色详情',
	                closeAction: 'destroy',
	                width: 350,
	                height:450,
	                minWidth: 150,
	                minHeight: 100,
	                layout: 'fit',
	                resizable: true,
	                modal: true,
	                items: form
	            });	      		
            	updateWin.items.items[0].loadRecord(selection);	  
            	updateWin.show();
	    }
	    function showUpdateForm(/*0-add,1-modify*/type,roleId,authUrls) {
	    	if(authUrls==null)authUrls='';
	    	
	    	initTreeData(rootNode,treeData,list,authUrls);
	    	var treeStore = Ext.create('Ext.data.TreeStore', {
           	 	root:treeData
           	});
            
            var tree=Ext.create('Ext.tree.Panel', {
        	    width: '90%',
        	    height: 290,
        	    minHeight: 200,
        	    store: treeStore,
        	    rootVisible: true,
        	    listeners: {       	    	
        	    	checkchange: function(node, checked, options) {
        	        	node.cascadeBy(function(n) { n.set('checked', checked); });
        	        	if (checked) {           
        	            	node.bubble(function(n) { n.set('checked', checked); });
        	        	}
        	        	else {
        	            	node.bubble(function(n) {
        	                    var childNodes = this.childNodes;
        	                    var length = childNodes.length;
        	                    var allUnchecked = true;

        	                    for (var i = 0; i < length; i++) {
        	                        if (childNodes[i].get('checked')) {
        	                            allUnchecked = false;
        	                            break;
        	                        }
        	                    }

        	                    if (allUnchecked) {
        	                        n.set('checked', false);
        	                    }
        	            });
        	        }   
        	    }
        	}
        	
        	});


            var form = Ext.widget('form', {
            	layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                border: false,
                bodyPadding: 10,
                width: '100%',
                height:'100%',
                fieldDefaults: {
                	msgTarget: 'side',
                    labelAlign: 'left',//标签居左
                    labelWidth: 35
                },
                items: [{
                    xtype: 'textfield',
                    fieldLabel: '名称',
                    afterLabelTextTpl: required,
                    allowBlank: false,
                    name:'name'
                },{
                    xtype: 'displayfield',
                    fieldLabel: '权限',
                    height:400,
                    id:'treeId'
                }],
                buttons: [{
                    text: '确定',
                    id:'updateOkBtn',
                    handler: function() {
                        if (this.up('form').getForm().isValid()) {
                            var formJson=this.up('form').getForm().getValues();   
                          //发回到服务器
                        	dyStatusBarFun.loadStatusBusy('win-statusbar','updateOkBtn');
                        	var checkList =tree.getChecked();
                    		var authStr='';
                    		for(var i=0;i<checkList.length;i++){
                    	  		authStr+='A'+checkList[i].raw.itemId+',';             		    
                    		}
                    		
                    		Ext.Ajax.request({ 
                    			url: "../sysrole/update",
                    			params:{
                    				id:roleId,
                    				name:formJson.name,
                    				authUrls:authStr
                    			},
                    			method: "POST", 
                    			callback: function(options,success,response) { 
                    				var jsonResult = Ext.JSON.decode(response.responseText);    
                    				switch(jsonResult.status){
                            			case 0:
                            				if(type==0){
                            					dyStatusBarFun.loadStatusSucc('win-statusbar','添加成功','updateOkBtn');   
                            				}else{
                            					dyStatusBarFun.loadStatusSucc('win-statusbar','修改成功','updateOkBtn');   
                            				}          
                            				grid.getView().getSelectionModel().deselectAll();
                            				store.reload();
                            				break;
           								case 2:
           									dyStatusBarFun.loadStatusFail('win-statusbar','系统异常','updateOkBtn');
           									break;
                            		}                    				
                    			}, 
                    			failure: function(response) { 
                    				dyStatusBarFun.loadStatusFail('win-statusbar','服务器端异常');
                    			} 
                    		});
                        }
                    }
                }],
                bbar: Ext.create('Ext.ux.StatusBar', {
                    id: 'win-statusbar',
                    busyText:'等待中。。。',
                    defaultText: ''
                })
            });
            form.on("render",function(){
            	tree.render("treeId-inputEl");
            })
	            //为了弹出window
	            var updateWin = Ext.widget('window', {
	                title: (type==0?'添加':'修改')+'角色',
	                closeAction: 'destroy',
	                width: 350,
	                height:450,
	                minWidth: 150,
	                minHeight: 200,
	                layout: 'fit',
	                resizable: true,
	                modal: true,
	                items: form
	            });
	        	//每次打开都要清除状态条
	        	dyStatusBarFun.loadStatusClear('win-statusbar');
	      		updateWin.show();
            	
	      		 //加载要修改的数据
		      	if(type==1){
		        	var selection = grid.getView().getSelectionModel().getSelection()[0];	 
		        	updateWin.items.items[0].loadRecord(selection);	        	
		      	}
	    }

	    // create the Data Store
	    var store =  Ext.create('Ext.data.Store', {
        	fields: [
                     'id',
                     'name',
                     'authUrls'
                 ],
            proxy: {
	            type: 'ajax',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            url:'../sysrole/read',
	            reader: {
	            	type:'json',
	                root: 'list'
	            }
	        }
        });
	    
	    var grid = Ext.create('Ext.grid.Panel', {
	        width: '100%',
	        height: 500,
	        store: store,
	        //disableSelection: true,//设置是否可选定行
	        loadMask: true,
	        columns:[{
	            text: "编号",
	            dataIndex: 'id',//对应data数据的列索引
	            width:90
	        },{
	            text: "名称",
	            dataIndex: 'name',
	            width:300
	        }],
	        // 分页插件
	        bbar: Ext.create('Ext.PagingToolbar', {
	            store: store,
	            displayInfo: true,
	            displayMsg: '显示 {0} - {1}条，共 {2}条',
	            emptyMsg: "暂无记录"
	        }),
	        renderTo: 'topic-grid',
	        dockedItems: [{//菜单插件，可定位的项
	            xtype: 'toolbar',
	            items: [ 
	            	{
	                text: '详情',
	                iconCls: 'icon-detail',
	                itemId: 'detail',
	                disabled: true,
	                handler: function(){
	                	showDetailForm();
	                }
	            },'-',{
	                text: '添加',
	                iconCls: 'icon-add',
	                handler: function(){
	                	showUpdateForm(0);
	                }
	            },'-',{
	                text: '修改',
	                iconCls: 'icon-update',
	                itemId: 'modify',
	                disabled: true,
	                handler: function(){
	                	var selection = grid.getView().getSelectionModel().getSelection()[0];	 
	                	showUpdateForm(1,selection.data.id,selection.data.authUrls);
	                }
	            },'-', {
	                text: '输入搜索条件',
	                iconCls: 'icon-search',
	                handler: showSearchForm
	            }]
	        }]
	    });
	    
	    store.on('beforeload', function(){
	    	if(searchWin){
	    		var sForm=searchWin.items.items[0].form;
	    		var formJson=sForm.getValues(); 
	    		var new_params = { 
	    				id: formJson.id,
	             		name:  encodeURI(formJson.name)	
	    		};  
		    	Ext.apply(store.proxy.extraParams, new_params); 
	    	}	
		});
	    
	    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#modify').setDisabled(selections.length === 0);
	        grid.down('#detail').setDisabled(selections.length === 0);
	    });
	    // trigger the data store load
	    store.loadPage(1);
	    
	});
	
	
 
  
</script>
</head>
<body>
	<div id="topic-grid"></div>
</body>
</html>