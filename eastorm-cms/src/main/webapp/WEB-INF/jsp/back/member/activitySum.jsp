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
	    'Ext.tip.QuickTipManager',
	    'Ext.ux.statusbar.StatusBar'
	]);

	Ext.onReady(function(){
	    Ext.tip.QuickTipManager.init();
	    
	    function showClearForm() {
            var form = Ext.widget('form', {
            	layout: {
                    type: 'vbox'
                },
   		        url: '../bizActivitysum/clear',
                border: false,
                bodyPadding: 10,
                width: '100%',
                height:'100%',
                fieldDefaults: {
                	msgTarget: 'side',
                    labelAlign: 'left',//标签居左
                    labelWidth: 100
                },
                items: [{
   		            xtype: 'hiddenfield',
   		            name: 'id'
   		        },{
   		            xtype: 'hiddenfield',
   		            name: 'activityid'
   		        },{
                    xtype: 'datefield',
                    fieldLabel: '大于等于时间',
                    format:'Y-m-d H:i',
                    name:'beginTime'
                },{
                    xtype: 'datefield',
                    fieldLabel: '小于时间',
                    format:'Y-m-d H:i',
                    name:'endTime'
                }, {
				    xtype: 'displayfield',
				    value: '<font color="#f00">小时和分自己修改，24小时制</front>'
		 				}],
                buttons: [{
                    text: '确定',
                    id:'clearOkBtn',
                    handler: function() {
                        if (this.up('form').getForm().isValid()) {
                            var formJson=this.up('form').getForm().getValues();   
                            //发回到服务器
                        	dyStatusBarFun.loadStatusBusy('win-statusbar','clearOkBtn');
                    		
                        	form.submit({
                                success: function(form, action) {
                                	switch(action.result.status){
                                		case 0:
                                			dyStatusBarFun.loadStatusSucc('win-statusbar',null,'clearOkBtn');
                            				store.reload();
                                			break;
               							default:
               								dyStatusBarFun.loadStatusFail('win-statusbar',action.result.message,'clearOkBtn');
               								break;
                                	}
                                },
                                failure: function(form, action) {
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
            //为了弹出window
            var clearWin = Ext.widget('window', {
                title: '清理活动 ',
                closeAction: 'destroy',
                width: 700,
                height:350,
                minWidth: 150,
                minHeight: 200,
                layout: 'fit',
                resizable: true,
                modal: true,
                items: form
            });
		    //加载要修改的数据
        	var selection = grid.getView().getSelectionModel().getSelection()[0];	     
        	clearWin.items.items[0].loadRecord(selection);	     
	        //每次打开都要清除状态条
        	dyStatusBarFun.loadStatusClear('win-statusbar');
        	clearWin.show();
	    }
		//定义数据模型，可以设置数据格式，验证方式
	    Ext.define('ActivitysumModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	            'id',
	            'activityid', 
	            'activityname',
	            'numone',
	            'setAll',
	            'currAll'
	        ],
	        idProperty: 'id',
	        validations: [{//这里验证作用是当点击添加按钮时，如果数据符合条件，js也会向服务器提交
	            type: 'length',
	            field: 'activityid',
	            min: 1
	        }]
	    });

		
	    // create the Data Store
	    var store = Ext.create('Ext.data.Store', {
	         autoSync: true,//修改后，立刻以json格式发回到服务器
	        pageSize: 20,  //一页显示多少行
	        model: 'ActivitysumModel',//对应上边的model
	        remoteSort: true,
	        proxy: {
	            type: 'ajax',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            api:{
	                read:'../bizActivitysum/read',
	                update:'../bizActivitysum/update',//store中的某条记录发生更改，会将更改反馈给该url
	                create:'../bizActivitysum/update'
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
	                var 	record = operation.getRecords()[0],
	                    	name = Ext.String.capitalize(operation),
	                    	verb;
	                if (name == 'Destroy') {
	                    record = operation.records[0];
	                    verb = 'Destroyed';
	                } else {
	                    verb = name + 'd';
	                }
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
	        height: 700,
	        store: store,
	        //disableSelection: true,//设置是否可选定行
	        loadMask: true,
	        columns:[{
	            text: "编号",
	            dataIndex: 'id',//对应data数据的列索引
	            width:90,
	            sortable: false
	        },{
	            text: "活动标识(英文)",
	            dataIndex: 'activityid',
	            width:180,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "活动名(中文)",
	            dataIndex: 'activityname',
	            width:180,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "每人参与次数",
	            dataIndex: 'numone',
	            width:100,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "设置总数",
	            dataIndex: 'setAll',
	            width:160,
	            sortable: false,
	            field: {
	                xtype: 'textfield'
	            }
	        },{
	            text: "当前总数",
	            dataIndex: 'currAll',
	            width:160,
	            sortable: false,
	            field: {
	                xtype: 'displayfield'
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
	                    store.insert(0, new ActivitysumModel());
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
	                   				url: "bizActivitysum/delete/"+selection.data.id+"", 
	                   				params: {}, 
	                   				method: "get", 
	                   				callback: function(options,success,response) { 
	                   					var jsonResult = Ext.JSON.decode(response.responseText);    
	                   					switch(jsonResult.status){
	                   						case 0:store.remove(selection);break;
	                   						default:Ext.Msg.alert(jsonResult.message);break;
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
	                text: '清理',
	                itemId: 'clear',
	                disabled: true,
	                iconCls: 'icon-update',
	                handler: function(){
	                	showClearForm();
	                }
	            }, '-', {
	            	 width: 400,
	                    fieldLabel: '搜索',
	                    labelWidth: 50,
	                    xtype: 'searchfield',
	                    store: store,
	                    paramName:'sh_like_activityname_string'
	            }]
	        }]
	    });
	    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#delete').setDisabled(selections.length === 0);
	        grid.down('#clear').setDisabled(selections.length === 0);
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