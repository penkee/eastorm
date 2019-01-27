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
	    'Ext.ux.statusbar.StatusBar'//状态bar
	]);

	Ext.onReady(function(){
		var  searchWin,updateWin,setWin,resetWin,setDiscountWin;
	    Ext.tip.QuickTipManager.init();
	    var grades=${MEMBER_GRADE_JSON};
	    var gradeStore = Ext.create('Ext.data.Store', {
            fields: ['typeKey', 'typeValue'],
            data : grades
        });
        // Simple ComboBox using the data store
        var gradeCombo = Ext.create('Ext.form.field.ComboBox', {
            fieldLabel: '等级',
            valueField:'typeKey',
            store: gradeStore,
            name:'grade',
            queryMode: 'local',//local,remote
            typeAhead: true,
            allowBlank: false,
            forceSelection:true,
            tpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                        '<div class="x-boundlist-item">{typeKey} - {typeValue}</div>',
                    '</tpl>'
                ),
                // template for the content inside text field
                displayTpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                        '{typeKey} - {typeValue}',
                    '</tpl>'
                )
        });
        var gradeComboUpdate = Ext.create('Ext.form.field.ComboBox', {
            fieldLabel: '等级',
            valueField:'typeKey',
            store: gradeStore,
            name:'grade',
            queryMode: 'local',//local,remote
            typeAhead: true,
            allowBlank: false,
            forceSelection:true,
            tpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                        '<div class="x-boundlist-item">{typeKey} - {typeValue}</div>',
                    '</tpl>'
                ),
                // template for the content inside text field
                displayTpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                        '{typeKey} - {typeValue}',
                    '</tpl>'
                )
        });
	    function showSearchForm() {
	        if (!searchWin) {
	            var form = Ext.widget('form', {//此处form是widget名，代表form型的widget
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
	                    labelWidth: 85
	                },
	                items: [{
	                    xtype: 'numberfield',
	                    minValue: 0,
	                    fieldLabel: '会员编号',
	                    name:'id'
	                }, {
	                    xtype: 'textfield',
	                    fieldLabel: '用户名',
	                    name:'username'
	                }, {
	                    xtype: 'textfield',
	                    fieldLabel: '公司名',
	                    name:'compName'
	                }, {
	                    xtype: 'textfield',
	                    fieldLabel: '公司编号',
	                    name:'companyDesctId'
	                },{
	            	    xtype: 'fieldcontainer',
	            	    layout: 'hbox',
	            	    fieldLabel: '是否有效',
	            	    afterLabelTextTpl: required,
	            	    labelWidth:100,
	            	    defaultType: 'radiofield',
	            	    items: [  {
	        	            boxLabel  : '有效',
	        	            name      : 'isValid',
	        	            width:60,
	        	            inputValue: '1',
	        	            checked:true
	        	        }, {
	        	            boxLabel  : '无效',
	        	            width:60,
	        	            name      : 'isValid',
	        	            inputValue: '0'
	        	        }]
	            	},{
                	    xtype      : 'fieldcontainer',
                	    fieldLabel : '创建时间起止',
                	    layout: 'hbox',
                	    items: [
							{
								xtype: 'datefield',
								name:'createDate_s',
								format:'Y-m-d'
							},{
								xtype: 'datefield',
								name:'createDate_e',
								format:'Y-m-d'
							},{
								xtype: 'displayfield',
								value:'结束不包含'
							}
                	    ]
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
	                width: 560,
	                height: 400,
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
	 // The data store holding the states
        var roleStore = Ext.create('Ext.data.Store', {
        	fields: [
                     'id',
                     'name'
                 ],
            proxy: {
	            type: 'ajax',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            url:'../sysrole/readAll',
	            reader: {
	            	type:'json',
	                root: 'list'
	            }
	        },
	        autoLoad:true
        });

        //设置权限
        function showSetRoleForm(){
        	if (!setWin) {
	    		  var form = Ext.widget('form', {//此处form是widget名，代表form型的widget
	              	layout: {
	                      type: 'vbox',
	                      align: 'stretch'
	                  },
	                  border: false,
	                  bodyPadding: 10,
	                  width: '100%',
	                  height:'100%',	                  
	                  url:'updateRoles',
	                  fieldDefaults: {
	                  	msgTarget: 'side',
	                      labelAlign: 'left',//标签居左
	                      labelWidth: 75
	                  },
	                  defaultType: 'textfield',
	                  items: [{
	                	    xtype: 'hidden',
	                	    fieldLabel: '编号',
	                	    name:'id'
	                	},{
	                        xtype: 'checkboxgroup',
	                        fieldLabel: '角色列表',
	                        cls: 'x-check-group-alt',
	                        columns: 3,
	                        items: [
	                        ]
	                    }, {
                    	    xtype      : 'fieldcontainer',
                    	    fieldLabel : '有效',
                    	    defaultType: 'radiofield',
                    	    layout: 'hbox',
                    	    items: [
                    	        {
                    	            boxLabel  : '是',
                    	            name      : 'isValid',
                    	            width:40,
                    	            inputValue: '1',
                    	            checked:true
                    	        }, {
                    	            boxLabel  : '否',
                    	            name      : 'isValid',
                    	            inputValue: '0'
                    	        }
                    	    ]
                    	},gradeCombo
	                  ],
	                  buttons: [{
	                      text: '确定',
	                      id:'setOkBtn',
	                      handler: function() {
	                          if (this.up('form').getForm().isValid()) {
	                        	  dyStatusBarFun.loadStatusBusy('setwin-statusbar','setOkBtn');
	                        	  form.submit({
		                                success: function(form, action) {
		                                	switch(action.result.status){
		                                		case 0:
		                                			dyStatusBarFun.loadStatusSucc('setwin-statusbar',null,'setOkBtn');
		                                			grid.getView().getSelectionModel().deselectAll();
		                            				store.reload();
		                                			break;
	                   							default:
	                   								dyStatusBarFun.loadStatusFail('setwin-statusbar',action.result.message,'setOkBtn');
	                   								break;
		                                	}
		                                },
		                                failure: function(form, action) {
		                                	dyStatusBarFun.loadStatusFail('setwin-statusbar','服务器端异常');
		                                }
		                            });
	                          }
	                      }
	                  }, {
		                    text: '重置',
		                    handler: function() {
		                    	 var rolesAll=roleStore.data.items;
		                    	setWin.items.items[0].items.items[1].removeAll();
		                 	    for(var i=0;i<rolesAll.length;i++){
		                 	    	var isFind=false;
		                 	    	
		                 	    	var roleItem={boxLabel: rolesAll[i].data.name, name: "roleIds",inputValue: rolesAll[i].data.id,checked:isFind};
		                 	    	setWin.items.items[0].items.items[1].add(roleItem);
		                 	    }
		                 	   gradeCombo.clearValue();
		                    }
		                }
	                  ],
	                  bbar: Ext.create('Ext.ux.StatusBar', {
	                      id: 'setwin-statusbar',
	                      busyText:'等待中。。。',
	                      defaultText: ''
	                  })
	              });
	    		//为了弹出window
		            setWin = Ext.widget('window', {
		                closeAction: 'hide',
		                width: 460,
		                height: 280,
		                minWidth: 150,
		                minHeight: 100,
		                layout: 'fit',
		                resizable: true,
		                modal: true,
		                items: form
		            });
	    		  
	    	}else{
	    		//每次打开都要清除状态条
	        	dyStatusBarFun.loadStatusClear('setwin-statusbar');
	    	}  
        	//初始化角色列表
        	//查询这个人的角色列表
        	var selection = grid.getView().getSelectionModel().getSelection()[0];	        
        	var roles={};
    	    Ext.Ajax.request({ 
    			url: "readRoles/"+selection.data.id+"",
    			method: "GET", 
    			async: false,//异步false
    			callback: function(options,success,response) { 
    				var jsonResult = Ext.JSON.decode(response.responseText);    
    				roles=jsonResult.list;
    			}, 
    			failure: function(response) { 
    				Ext.Msg.alert("警告", "数据加载失败，请稍后再试！"); 
    			} 
    		}); 
    	    var rolesAll=roleStore.data.items;
    	    setWin.items.items[0].items.items[1].removeAll();
    	    setWin.items.items[0].items.items[0].setValue(selection.data.id);
    	    for(var i=0;i<rolesAll.length;i++){
    	    	var isFind=false;
    	    	for(var j=0;j<roles.length;j++){
    	    		if(rolesAll[i].data.id==roles[j].id){
    	    			isFind=true;
    	    			break;
    	    		}
    	    	}
    	    	var roleItem={boxLabel: rolesAll[i].data.name, name: "roleIds",inputValue: rolesAll[i].data.id,checked:isFind};
    	    	setWin.items.items[0].items.items[1].add(roleItem);
    	    }
    	    gradeCombo.setValue(''+selection.data['grade']);
	    	 var title='设置用户权限';
		      //加载要修改的数据
		     setWin.setTitle(title);
		     setWin.show();
        }
       
	    function showUpdateForm(/*0-add,1-modify*/type) {
	    	if (!updateWin) {
	    		  var form = Ext.widget('form', {//此处form是widget名，代表form型的widget
	              	layout: {
	                      type: 'vbox',
	                      align: 'stretch'
	                  },
	                  border: false,
	                  bodyPadding: 10,
	                  width: '100%',
	                  height:'100%',	                  
	                  url:'insert',
	                  fieldDefaults: {
	                  	msgTarget: 'side',
	                      labelAlign: 'left',//标签居左
	                      labelWidth: 75
	                  },
	                  defaultType: 'textfield',
	                  items: [{
	                	    xtype: 'hidden',
	                	    fieldLabel: '会员编号',
	                	    name:'id'
	                	},{
	                      fieldLabel: '用户名',
	                      allowBlank: false,
	                      minLength: 4,
	                      maxLength: 18,
	                      name:'username'},
	                      {
	                          fieldLabel: '密码',
	                          name: 'password',
	                          allowBlank: false,
	                          inputType: 'password',
	                          itemId:'pass',
	                          minLength: 6,
		                      maxLength: 12,
	                          listeners: {
	                              validitychange: function(field){
	                                  field.next().validate();
	                              },
	                              blur: function(field){
	                                  field.next().validate();
	                              }
	                          }
	                      }, {
	                          fieldLabel: '确认密码',
	                          name: 'pass-cfrm',
	                          allowBlank: false,
	                          inputType: 'password',
	                          vtype: 'password',
	                          initialPassField: 'pass' // id of the initial password field
	                      },{
	                    	    xtype      : 'fieldcontainer',
	                    	    fieldLabel : '有效',
	                    	    defaultType: 'radiofield',
	                    	    layout: 'hbox',
	                    	    items: [
	                    	        {
	                    	            boxLabel  : '是',
	                    	            name      : 'member.isValid',
	                    	            width:40,
	                    	            inputValue: '1',
	                    	            checked:true
	                    	        }, {
	                    	            boxLabel  : '否',
	                    	            name      : 'member.isValid',
	                    	            inputValue: '0'
	                    	        }
	                    	    ]
	                    	}
	                  ],
	                  buttons: [{
	                      text: '确定',
	                      id:'updateOkBtn',
	                      handler: function() {
	                          if (this.up('form').getForm().isValid()) {
	                        	  dyStatusBarFun.loadStatusBusy('win-statusbar','updateOkBtn');
	                        	  form.submit({
		                                success: function(form, action) {
		                                	switch(action.result.status){
		                                		case 0:
		                                			dyStatusBarFun.loadStatusSucc('win-statusbar',null,'updateOkBtn');
		                                			grid.getView().getSelectionModel().deselectAll();
		                            				store.reload();
		                                			break;
	                   							default:
	                   								dyStatusBarFun.loadStatusFail('win-statusbar',action.result.message,'updateOkBtn');
	                   								break;
		                                	}
		                                },
		                                failure: function(form, action) {
		                                	dyStatusBarFun.loadStatusFail('win-statusbar','服务器端异常');
		                                }
		                            });
	                          }
	                      }
	                  }
	                  ],
	                  bbar: Ext.create('Ext.ux.StatusBar', {
	                      id: 'win-statusbar',
	                      busyText:'等待中。。。',
	                      defaultText: ''
	                  })
	              });
	    		//为了弹出window
		            updateWin = Ext.widget('window', {
		                closeAction: 'hide',
		                width: 260,
		                height: 280,
		                minWidth: 150,
		                minHeight: 100,
		                layout: 'fit',
		                resizable: true,
		                modal: true,
		                items: form
		            });
	    		  
	    	}else{
	        	dyStatusBarFun.loadStatusClear('win-statusbar');
	    	}  
	    	 var title=((type==0?'添加':'修改')+'用户');
		      		//添加form要reset
		      updateWin.items.items[0].form.reset();
		      	
		      	updateWin.setTitle(title);
		      	 updateWin.show();
	    }

	    function showReSetForm() {
	    	if (!resetWin) {
	    		  var form = Ext.widget('form', {//此处form是widget名，代表form型的widget
	              	layout: {
	                      type: 'vbox',
	                      align: 'stretch'
	                  },
	                  border: false,
	                  bodyPadding: 10,
	                  width: '100%',
	                  height:'100%',	                  
	                  url:'insert',
	                  fieldDefaults: {
	                  	msgTarget: 'side',
	                      labelAlign: 'left',//标签居左
	                      labelWidth: 75
	                  },
	                  defaultType: 'textfield',
	                  items: [{
	                	    xtype: 'hidden',
	                	    fieldLabel: '编号',
	                	    name:'id'
	                	},{
	                      fieldLabel: '用户名',
	                      allowBlank: false,
	                      minLength: 4,
	                      maxLength: 18,
	                      name:'username'
	                    },{
	                          fieldLabel: '密码',
	                          name: 'password',
	                          allowBlank: false,
	                          inputType: 'password',
	                          itemId:'pass',
	                          minLength: 6,
		                      maxLength: 12,
	                          listeners: {
	                              validitychange: function(field){
	                                  field.next().validate();
	                              },
	                              blur: function(field){
	                                  field.next().validate();
	                              }
	                          }
	                      }, {
	                          fieldLabel: '确认密码',
	                          name: 'pass-cfrm',
	                          allowBlank: false,
	                          inputType: 'password',
	                          vtype: 'password',
	                          initialPassField: 'pass' // id of the initial password field
	                      }
	                  ],
	                  buttons: [{
	                      text: '确定',
	                      id:'resetOkBtn',
	                      handler: function() {
	                          if (this.up('form').getForm().isValid()) {
	                        	  dyStatusBarFun.loadStatusBusy('win-statusbar','resetOkBtn');
	                        	  form.submit({
		                                success: function(form, action) {
		                                	switch(action.result.status){
		                                		case 0:
		                                			dyStatusBarFun.loadStatusSucc('win-statusbar',null,'resetOkBtn');
		                                			break;
	                   							default:
	                   								dyStatusBarFun.loadStatusFail('win-statusbar',action.result.message,'resetOkBtn');
	                   								break;
		                                	}
		                                },
		                                failure: function(form, action) {
		                                	dyStatusBarFun.loadStatusFail('win-statusbar','服务器端异常');
		                                }
		                            });
	                          }
	                      }
	                  }
	                  ],
	                  bbar: Ext.create('Ext.ux.StatusBar', {
	                      id: 'win-statusbar',
	                      busyText:'等待中。。。',
	                      defaultText: ''
	                  })
	              });
	    		//为了弹出window
		            resetWin = Ext.widget('window', {
		                closeAction: 'hide',
		                width: 260,
		                height: 280,
		                minWidth: 150,
		                minHeight: 100,
		                layout: 'fit',
		                resizable: true,
		                modal: true,
		                items: form
		            });
	    		  
	    	}else{
	        	dyStatusBarFun.loadStatusClear('win-statusbar');
	    	}  
	    	 var title='重置密码';
		      //加载要修改的数据
        	var selection = grid.getView().getSelectionModel().getSelection()[0];	        	
        	resetWin.items.items[0].loadRecord(selection);	   
        	//设置用户名不可输入
        	resetWin.items.items[0].items.items[1].setDisabled(true);
	      	resetWin.setTitle(title);
	      	resetWin.show();
	    }
	    
	    function showSetDiscountForm() {
	    	if (!setDiscountWin) {
	    		  var form = Ext.widget('form', {//此处form是widget名，代表form型的widget
	              	layout: {
	                      type: 'vbox',
	                      align: 'stretch'
	                  },
	                  border: false,
	                  bodyPadding: 10,
	                  width: '100%',
	                  height:'100%',	                  
	                  url:'insert',
	                  fieldDefaults: {
	                  	msgTarget: 'side',
	                      labelAlign: 'left',//标签居左
	                      labelWidth: 75
	                  },
	                  defaultType: 'textfield',
	                  items: [{
	                	    xtype: 'hidden',
	                	    fieldLabel: '编号',
	                	    name:'ids'
	                	},{
	                      fieldLabel: '用户名',
	                      allowBlank: false,
	                      minLength: 4,
	                      maxLength: 18,
	                      name:'username'
	                    }
	                  ],
	                  buttons: [{
	                      text: '确定',
	                      id:'resetOkBtn',
	                      handler: function() {
	                          if (this.up('form').getForm().isValid()) {
	                        	  dyStatusBarFun.loadStatusBusy('win-statusbar','resetOkBtn');
	                        	  form.submit({
		                                success: function(form, action) {
		                                	switch(action.result.status){
		                                		case 0:
		                                			dyStatusBarFun.loadStatusSucc('win-statusbar',null,'resetOkBtn');
		                                			break;
	                   							default:
	                   								dyStatusBarFun.loadStatusFail('win-statusbar',action.result.message,'resetOkBtn');
	                   								break;
		                                	}
		                                },
		                                failure: function(form, action) {
		                                	dyStatusBarFun.loadStatusFail('win-statusbar','服务器端异常');
		                                }
		                            });
	                          }
	                      }
	                  }
	                  ],
	                  bbar: Ext.create('Ext.ux.StatusBar', {
	                      id: 'win-statusbar',
	                      busyText:'等待中。。。',
	                      defaultText: ''
	                  })
	              });
	    		//为了弹出window
		            setDiscountWin = Ext.widget('window', {
		                closeAction: 'hide',
		                width: 260,
		                height: 280,
		                minWidth: 150,
		                minHeight: 100,
		                layout: 'fit',
		                resizable: true,
		                modal: true,
		                items: form
		            });
	    	}else{
	        	dyStatusBarFun.loadStatusClear('win-statusbar');
	    	}  
	    	var title='设置企业优惠';
		    //加载要修改的数据
        	var selection = grid.getView().getSelectionModel().getSelection()[0];       	
        	setDiscountWin.items.items[0].loadRecord(selection);	   
        	//设置用户名不可输入
        	setDiscountWin.items.items[0].items.items[1].setDisabled(true);
        	setDiscountWin.setTitle(title);
        	setDiscountWin.show();
	    }
		//定义数据模型，可以设置数据格式，验证方式
	    Ext.define('SysMemberModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	             'id',
	            'username',
	            'grade',
	            'createDate',
	            'isValid',
	            'createName',
	            'candiscount',
	            'nickname',
	            'compId',
	            'compName',
	            'discountCode'
	        ],
	        idProperty: 'id',
	        validations: [{//这里验证作用是当点击添加按钮时，如果数据符合条件，js也会向服务器提交
	            type: 'length',
	            field: 'username',
	            min: 1
	        }]
	    });

	    // create the Data Store
	    var store = Ext.create('Ext.data.Store', {
	        autoSync: false,//修改后，立刻以json格式发回到服务器
	        pageSize: 20,  //一页显示多少行
	        model: 'SysMemberModel',//对应上边的model
	        remoteSort: true,
	        proxy: {
	            type: 'rest',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            url:'read',
	            reader: {
	            	type:'json',
	                root: 'list',
	                totalProperty: 'totalCount',
	                successProperty : 'success',
	                messageProperty : 'message'
	            },
	            // 可以传递排序参数到后台
	            simpleSortMode: true
	        }
	    });
	    
	    var sm = Ext.create('Ext.selection.CheckboxModel');
	    var grid = Ext.create('Ext.grid.Panel', {
	        width: '100%',
	        height: 800,
	        store: store,
	        //disableSelection: true,//设置是否可选定行
	        loadMask: true,
	        selModel: sm,
	        viewConfig:{  
                enableTextSelection:true  
            },
	        columns:[{
	            id: 'topic',
	            text: "会员编号",
	            dataIndex: 'id',//对应data数据的列索引
	            width:90,
	            sortable: false,
	            renderer:function(value){
	            	return '<a class="proName" onclick="parent.addTab(\'member/memberDetail?id='+value+'\',\'用户详情-'+value+'\')">'+value+'</a>';
	            }
	        },{
	            text: "用户名",
	            dataIndex: 'username',
	            width:120,
	            sortable: false
	        },{
	            text: "昵称",
	            dataIndex: 'nickname',
	            width:120,
	            sortable: false
	        },{
	            text: "公司名",
	            dataIndex: 'compName',
	            width:120,
	            sortable: false
	        },{
	            text: "公司优惠码",
	            dataIndex: 'discountCode',
	            width:120,
	            sortable: false
	        },{
	            text: "剩余折扣次数",
	            dataIndex: 'candiscount',
	            width:150,
	            sortable: false
	        },{
	            text: "等级",
	            dataIndex: 'grade',
	            width:120,
	            sortable: false,
	            renderer:function(value){
	            	var s='';
	            	
	            	for(var i=0;i<grades.length;i++){
	            		if(grades[i].typeKey==value){
	            			s=grades[i].typeValue;
	            		}
	            	}
	            	return s;
	            }
	        },{
	            text: "创建时间",
	            dataIndex: 'createDate',
	            width:200,
	            sortable: false,
	            renderer:function (value){
	            	return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
	            }
	        },{
	            text: "是否有效",
	            dataIndex: 'isValid',
	            width:100,
	            sortable: false,
	            renderer:function(value){
	            	var s='';
	            	switch(value){
		            	case 0:s='否';break;
		            	case 1:s='是';break;
		            	default:'未知'
	            	}
	            	return s;
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
	        dockedItems: [{//菜单插件，可定位的项
	            xtype: 'toolbar',
	            items: [{
	                text: '添加用户',
	                iconCls: 'icon-add',
	                itemId: 'add',
	                disabled: false,
	                handler: function(){
	                	showUpdateForm(0);
	                }
	            },'-', {
	                text: '重置密码',
	                iconCls: 'icon-update',
	                itemId: 'modify',
	                disabled: true,
	                handler: function(){
	                	showReSetForm();
	                }
	            },'-',  {
	                text: '设置权限',
	                iconCls: 'icon-set',
	                itemId: 'setRole',
	                disabled: true,
	                handler: function(){
	                	showSetRoleForm();
	                }
	            },'-',{
	                text: '输入搜索条件',
	                iconCls: 'icon-search',
	                handler: showSearchForm
	            },'-',{
	                text: '导出',
	                iconCls: 'icon-up',
	                handler: function(){
	                	var sForm=searchWin.items.items[0].form;
	    	    		var formJson=sForm.getValues(); 
	    	            g('id').value=formJson.id;
	    	            g('createDate_s').value=formJson.createDate_s;
	    	            g('createDate_e').value=formJson.createDate_e;
	    	            g('compName').value=formJson.compName;
	    	            g('compId').value=formJson.companyDesctId;
	    	            
	    	            g('isValid').value=formJson.isValid;
	                	g('formDown').submit();
	                }
	            },'-',  {
	                text: '设置企业优惠',
	                iconCls: 'icon-set',
	                itemId: 'setDiscount',
	                disabled: true,
	                handler: function(){
	                	showSetDiscountForm();
	                }
	            }]
	        }]
	    });
	    
	    store.on('beforeload', function(){
	    	if(searchWin){
	    		var sForm=searchWin.items.items[0].form;
	    		var formJson=sForm.getValues(); 
	    		var new_params = { 
	    				'sh_eq_m.id_long': formJson.id,
	             		'sh_like_m.username_string':  encodeURI(formJson.username),
	             		'sh_eq_cd.id_long':formJson.companyDesctId,
	             		'sh_greq_m.createDate_date':  encodeURI(formJson.createDate_s),
		             	'sh_leeq_m.createDate_date':  encodeURI(formJson.createDate_e),
		             	
		             	'sh_eq_m.isValid_byte':formJson.isValid,
		             	'sh_eq_cd.id_long':formJson.companyDesctId,
		             	'sh_like_cd.name':encodeURI(formJson.compName)
	    		};
		    	Ext.apply(store.proxy.extraParams, new_params); 
	    	}	
		});
	    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#modify').setDisabled(selections.length === 0);
	        grid.down('#setRole').setDisabled(selections.length === 0);
	        grid.down('#setDiscount').setDisabled(selections.length === 0);
	    });
	    // trigger the data store load
	    store.loadPage(1);
	    
	});
	
</script>
<style type="text/css">
.proName{text-decoration: none;color:#157FCC;display: block;height:14px;cursor: pointer;}
.proName:hover{text-decoration: underline;}
</style>
</head>
<body>
	<div id="topic-grid"></div>
	
	<form action="memberListExcel" id="formDown" method="post">
		<input type="hidden" name="sh_eq_m.id_long" id="id">
		<input type="hidden" name="sh_greq_m.createDate_date" id="createDate_s">
		<input type="hidden" name="sh_leeq_m.createDate_date" id="createDate_e">
		<input type="hidden" name="sh_like_cd.name" id="compName">
		<input type="hidden" name="sh_eq_cd.id_long" id="compId">
		<input type="hidden" name="sh_eq_m.isValid_byte" id="isValid">
	</form>
</body>
</html>