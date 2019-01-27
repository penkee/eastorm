<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <!-- 包含extjs基本库文件-->
	<jsp:include page="/back/comm/_include"/>
	<style>
	#topic-grid .x-grid-row-editor-buttons-default-top{bottom: -35px}
	</style>
	<script type="text/javascript">

	Ext.require([
	    'Ext.grid.*',
	    'Ext.data.*',
	    'Ext.util.*',
	    'Ext.ux.form.SearchField',
	    'Ext.toolbar.Paging',
	    'Ext.ModelManager',
	    'Ext.tip.QuickTipManager'
	]);

	Ext.onReady(function(){
	    Ext.tip.QuickTipManager.init();
		//定义数据模型，可以设置数据格式，验证方式
	    Ext.define('SysCacheModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	             'id',
	            'keyname', 
	            'jpql',
	            'resultType'
	        ],
	        idProperty: 'id',
	        validations: [{//这里验证作用是当点击添加按钮时，如果数据符合条件，js也会向服务器提交
	            type: 'length',
	            field: 'keyname',
	            min: 1
	        },{
	            type: 'length',
	            field: 'jpql',
	            min: 1
	        }]
	    });

	    // create the Data Store
	    var store = Ext.create('Ext.data.Store', {
	        autoSync: true,//修改后，立刻以json格式发回到服务器
	        pageSize: 12,  //一页显示多少行
	        model: 'SysCacheModel',//对应上边的model
	        remoteSort: true,
	        proxy: {
	            type: 'ajax',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            api:{
	                read:'cache/read',
	                update:'cache/update',//store中的某条记录发生更改，会将更改反馈给该url
	                create:'cache/update'
	            },
	            reader: {
	            	type:'json',
	                root: 'list',
	                totalProperty: 'totalCount',
	                successProperty : 'success',
	                messageProperty : 'message'
	            },
	            writer: {
	                type: 'json'
	            },
	            // 可以传递排序参数到后台
	            simpleSortMode: true
	        },
	        listeners: {//调用服务器端后台代码后执行的js
	            write: function(store, operation,success){

	            }
	        }
	    });
		//行编辑插件
	    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
	        listeners: {
	            cancelEdit: function(rowEditing, context) {//点击取消，会取消新增的记录
	                // Canceling editing of a locally added, unsaved record: remove it               
	                if (context.record.phantom) {
	                    store.remove(context.record);
	                }
	            },
	            edit:function(editor, context, eOpts) {
	            	if(context.record.data.name==''){
	            		context.cancel=true;
	            		store.remove(context.record);
	            	}
	            }
	        }
	    });
	    
	    var grid = Ext.create('Ext.grid.Panel', {
	        width: 'auto',
	        height: 500,
	        store: store,
	        //disableSelection: true,//设置是否可选定行
	        loadMask: true,
	        columns:[{
	            id: 'topic',
	            text: "编号",
	            dataIndex: 'id',//对应data数据的列索引
	            width:90,
	            sortable: false
	        },{
	            text: "键名称",
	            dataIndex: 'keyname',
	            width:120,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "jpql语句",
	            dataIndex: 'jpql',
	            width:450,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "结果类型(0-single,1-list,2-Map)",
	            dataIndex: 'resultType',
	            width:180,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        }],
	        // 分页插件
	        bbar: Ext.create('Ext.PagingToolbar', {
	            store: store,
	            displayInfo: true,
	            displayMsg: '显示 {0} - {1}条，共 {2}条',
	            emptyMsg: "暂无记录"
	        }),
	        renderTo: 'topic-grid',
	        plugins: [rowEditing],//可编辑数据插件
	        dockedItems: [{//菜单插件，可定位的项
	            xtype: 'toolbar',
	            items: [{
	                text: '添加',
	                iconCls: 'icon-add',
	                handler: function(){
	                    // empty record
	                    store.insert(0, new SysCacheModel());
	                    rowEditing.startEdit(0, 0);
	                }
	            }, '-', {
	                itemId: 'delete',
	                text: '删除',
	                iconCls: 'icon-delete',
	                disabled: true,
	                handler: function(){
	                    var selection = grid.getView().getSelectionModel().getSelection()[0];
	                    if (selection) {
	                    	Ext.Msg.confirm('信息', '确定要删除？', function(btn){
	                            if (btn == 'yes') {
	                            	//js发起请求，发往服务器端
	   	                    	 Ext.Ajax.request({ 
	                   				url: "cache/delete", 
	                   				params: selection.data, 
	                   				method: "POST", 
	                   				callback: function(options,success,response) { 
	                   					var jsonResult = Ext.JSON.decode(response.responseText);    
	                   					switch(jsonResult.status){
	                   						case 0:store.remove(selection);break;
	                   						default:Ext.Msg.alert('消息',jsonResult.message);break;
	                   					}     
	                   				}, 
	                   				failure: function(response) { 
	                       				Ext.Msg.alert("警告", "数据操作失败，请稍后再试！"); 
	                   				} 
	               				}); 
	                            }
	                    	})
	                    
	                    }
	                }
	            },'-',{
	                text: '刷新字典',
	                iconCls: 'icon-refresh',
	                handler: function(){
	                	 Ext.Ajax.request({ 
                				url: "cache/refresh", 
                				method: "POST", 
                				callback: function(options,success,response) { 
                					var jsonResult = Ext.JSON.decode(response.responseText);   
                					switch(jsonResult.status){
                						default:Ext.Msg.alert('消息',jsonResult.message);break;
                					}     
                				}, 
                				failure: function(response) { 
                    				Ext.Msg.alert("警告", "数据操作失败，请稍后再试！"); 
                				} 
            				}); 
	                }
	            }, '-', {
	            	 width: 400,
	                    fieldLabel: '搜索',
	                    labelWidth: 50,
	                    xtype: 'searchfield',
	                    store: store,
	                    paramName:'sh_like_keyname_string'
	            }]
	        }]
	    });
	    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#delete').setDisabled(selections.length === 0);
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