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
	    Ext.define('MenuModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	             'id',
	            'name', 
	            'typeValue',
	            'tKey',
	            'tValue'
	        ],
	        idProperty: 'id',
	        validations: [{//这里验证作用是当点击添加按钮时，如果数据符合条件，js也会向服务器提交
	            type: 'length',
	            field: 'name',
	            min: 1
	        }]
	    });

	    // create the Data Store
	    var store = Ext.create('Ext.data.Store', {
	         autoSync: true,//修改后，立刻以json格式发回到服务器
	        pageSize: 12,  //一页显示多少行
	        model: 'MenuModel',//对应上边的model
	        remoteSort: true,
	        proxy: {
	            type: 'ajax',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            api:{
	                read:'read/${parentId}',
	                update:'update/${parentId}',//store中的某条记录发生更改，会将更改反馈给该url
	                create:'update/${parentId}'
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
	            text: "名称",
	            dataIndex: 'name',
	            width:120,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "顺序",
	            dataIndex: 'tKey',
	            width:120,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "链接",
	            dataIndex: 'typeValue',
	            flex:1,//自适应宽度
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "类型(0-显示，1-隐藏型)",
	            dataIndex: 'tValue',
	            flex:1,//自适应宽度
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "子节点",
	            dataIndex: 'id',
	            width:100,
	            renderer:enterSubnode
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
	                    store.insert(0, new MenuModel());
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
	                   				url: "delete", 
	                   				params: selection.data, 
	                   				method: "POST", 
	                   				callback: function(options,success,response) { 
	                   					var jsonResult = Ext.JSON.decode(response.responseText);    
	                   					switch(jsonResult.status){
	                   						case 0:store.remove(selection);break;
	                   						case 1:Ext.Msg.alert("警告", "未知错误！");break;
	                   						case 100:Ext.Msg.alert("警告", "由于该记录存在子节点，无法删除！"); break;
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
	                text: '上一级',
	                iconCls: 'icon-up',
	                handler: function(){
	                	window.location='${upId}';
	                }
	            },'-',{
	            	 width: 400,
	                    fieldLabel: '搜索',
	                    labelWidth: 50,
	                    xtype: 'searchfield',
	                    store: store,
	                    paramName:'name'
	            }]
	        }]
	    });
	    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#delete').setDisabled(selections.length === 0);
	    });
	    // pluggable renders
	    function enterSubnode(value, p, record) {
	        return Ext.String.format(
	            '<a href="{0}">进入</a>',
	            record.getId()
	        );
	    }
	    // trigger the data store load
	    store.loadPage(1);
	});
</script>
</head>
<body>
	<div id="topic-grid"></div>
</body>
</html>