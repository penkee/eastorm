<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <!-- 包含extjs基本库文件-->
	<jsp:include page="/back/comm/_include"/>	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=EKDKvtkI2x0nfnmHPr9UKyXj"></script>
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
		var  searchWin,updateWin,detailWin;
	    Ext.tip.QuickTipManager.init();
        
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
	                    labelWidth: 65
	                },
	                items: [{
	                    xtype: 'numberfield',
	                    minValue: 0,
	                    fieldLabel: '编号',
	                    name:'id'
	                }, {
	                    xtype: 'textfield',
	                    fieldLabel: '店主名',
	                    name:'shopkeeper_username'
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
	
	    function locateShop(){
	    	var longitudeInput=Ext.getCmp('longitude');
    		var latitudeInput=Ext.getCmp('latitude');
    		if(!longitudeInput.validate()||!latitudeInput.validate()) return;
    		var longitude=longitudeInput.getValue();
    		var latitude=latitudeInput.getValue();
    		// 百度地图API功能
    		var map = new BMap.Map("allmap");                        // 创建Map实例
    		map.centerAndZoom(new BMap.Point(longitude, latitude-0.001), 18);     // 初始化地图,设置中心点坐标和地图级别
    		map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
    		map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
    		map.enableScrollWheelZoom();                            //启用滚轮放大缩小
    		map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
    		map.setCurrentCity("");          // 设置地图显示的城市 此项是必须设置的

    		var marker1 = new BMap.Marker(new BMap.Point(longitude, latitude));  // 创建标注
    		map.addOverlay(marker1);              // 将标注添加到地图中
	    }
	    
	  
	    
	    function showDetailForm() {
	    	if (!detailWin) {
	    		  var form = Ext.widget('form', {//此处form是widget名，代表form型的widget
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
	                      labelWidth: 75
	                  },
	                  defaultType: 'displayfield',
	                  items: [{
	    	            fieldLabel: '商户名',
	    	            name: 'merchName'
	    	        },{
	    	            fieldLabel: '店名',
	    	            name: 'shopName'
	    	        },{
	            	    xtype      : 'fieldcontainer',
	            	    fieldLabel : '城市',
	            	    layout: 'hbox',
	            	    defaultType: 'displayfield',
	            	    items: [{
		    	            fieldLabel: '省',
		    	            labelWidth: 30,
		    	            width:120,
		    	            name: 'province.typeValue'
		    	        },{
		    	            fieldLabel: '市',
		    	            labelWidth: 30,
		    	            width:120,
		    	            name: 'city.typeValue'
		    	        },{
		    	            fieldLabel: '区/县',
		    	            labelWidth: 60,
		    	            name: 'area.typeValue'
		    	        }]
	            	},{
	            	    xtype      : 'fieldcontainer',
	            	    layout: 'hbox',
	            	    defaultType: 'displayfield',
	            	    items: [{
	        	            fieldLabel: '地址',
	        	            width:200,
	        	            name: 'address'
	        	        }, {
	        	            fieldLabel: '电话',
	        	            labelWidth: 50,
	        	            name: 'tel'
	        	        }]
	            	} ,{
	            	    xtype      : 'fieldcontainer',
	            	    layout: 'hbox',
	            	    defaultType: 'displayfield',
	            	    items: [ {
		    	            fieldLabel: '店主',
		    	            width:200,
		    	            name: 'shopkeeper.username'
		    	        },{
		    	            fieldLabel: '跟进人',
		    	            labelWidth: 50,
		    	            name: 'follow.username'
		    	        }]
	            	},{
	    	        xtype:'tabpanel',
	    	        plain:true,
	    	        activeTab: 0,
	    	        defaults:{
	    	            bodyPadding: 10
	    	        },
	    	        items:[{
	    	            title:'位置',
	    	            height:280,
	    	            layout: 'anchor',
	    	            defaults: {
	    	                anchor: '100%'
	    	            },
	    	            items: [{
	    	                xtype: 'fieldcontainer',
	    	                layout: 'hbox',
	    	                items: [
	    					{
	    					    xtype: 'textfield',
	    					    fieldLabel: '经度',
	    					    afterLabelTextTpl: required,
	    					    name: 'longitude',
	    					    id:'longitude',
	    					    allowBlank: false
	    					},	{
	    					    xtype: 'textfield',
	    					    fieldLabel: '纬度',
	    					    afterLabelTextTpl: required,
	    					    name: 'latitude',
	    					    id:'latitude',
	    					    allowBlank: false
	    					},{
	    					    xtype: 'button',
	    					    text : '定位',
	    					    margin:'0 0 0 20',
	    					    listeners:{
	    					    	click:function(){
	    					    		locateShop();
	    					    	}	
	    					    }
	    					}]
	    	            },
	    	            {
	    	                xtype: 'box',
	    	                html:'<div id="allmap" style="width: 700px;height: 600px;margin:0;"></div>'
	    	            }
	    	            ]
	    	        },{
	    	            title:'照片',
	    	            height:280,
	    	            layout: 'anchor',
	    	            defaults: {
	    	                anchor: '100%'
	    	            },
	    	            items: [
	    							{
	    							    xtype: 'displayfield',
	    							    id:'doorPic',
	    							    fieldLabel: '门头照',
	    							    width:600,
	    							    height:30,
	    							    listeners: {
	    						            "dblclick": {
	    						            	element: 'el',
	    						                fn: function(){ 
	    						                	showBig('doorPic');
	    						                }
	    						            }
	    						        }
	    							},{
	    							    xtype: 'displayfield',
	    							    id:'shopLogo',
	    							    fieldLabel: '商店logo',
	    						        name: 'shopLogo',
	    							    width:600,
	    							    height:30
	    							}
	    	                    ]
	    	        }]
	    	    }
	    	        
	                  ]
	              });
	    		//为了弹出window
		            detailWin = Ext.widget('window', {
		                closeAction: 'hide',
		                width: 760,
		                height: 540,
		                minWidth: 150,
		                minHeight: 100,
		                layout: 'fit',
		                resizable: true,
		                modal: true,
		                items: form
		            });
	    		  
	    	}
	    	var title='商户进件详情';
        	var selection = grid.getView().getSelectionModel().getSelection()[0];	      
        	detailWin.items.items[0].loadRecord(selection);	       
        	Ext.getCmp('doorPic').setValue(
        			'<a href="#" onclick="window.open(\'../../'
        					+selection.data.doorPic
        					+'\', \'_blank\',\'\')" >'+selection.data.doorPic+'</a>');
        	Ext.getCmp('shopLogo').setValue(
        			'<a href="#" onclick="window.open(\'../../'
        					+selection.data.shopLogo
        					+'\', \'_blank\',\'\')" >'+selection.data.shopLogo+'</a>');
        	detailWin.setTitle(title);
        	detailWin.show();
        	locateShop();
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
	                  url:'checkCase',
	                  fieldDefaults: {
	                  	msgTarget: 'side',
	                      labelAlign: 'left',//标签居左
	                      labelWidth: 85
	                  },
	                  defaultType: 'textfield',
	                  items: [{
	                	    xtype: 'hidden',
	                	    fieldLabel: '编号',
	                	    name:'id'
	                	}, {
	                    	    xtype      : 'fieldcontainer',
	                    	    fieldLabel : '是否通过审核',
	                    	    defaultType: 'radiofield',
	                    	    layout: 'hbox',
	                    	    items: [
	                    	        {
	                    	            boxLabel  : '是',
	                    	            name      : 'verifyType',
	                    	            width:40,
	                    	            inputValue: '1',
	                    	            checked:true
	                    	        }, {
	                    	            boxLabel  : '否',
	                    	            name      : 'verifyType',
	                    	            inputValue: '3'
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
	    	 var title='审核商户进件';
	    	  //加载要修改的数据
		      	if(type==1){
		        	var selection = grid.getView().getSelectionModel().getSelection()[0];	        	
		        	updateWin.items.items[0].loadRecord(selection);	       
		      	}
	      	updateWin.setTitle(title);
	      	updateWin.show();
	    }

	    
		//定义数据模型，可以设置数据格式，验证方式
	    Ext.define('TmpMerchCaseModel', {
	        extend: 'Ext.data.Model',
	        fields: [
	             'id',
	            'merchName',
	            'shopName',
	            'province.typeValue',
	            'city.typeValue',
	            'area.typeValue',
	            'address',
	            'verifyType',
	            'longitude',
	            'latitude',
	            'shopkeeper.username',
	            'follow.username',
	            'doorPic',
	            'shopLogo',
	            'createDate',
	            'tel'
	        ],
	        idProperty: 'id'
	    });

	    // create the Data Store
	    var store = Ext.create('Ext.data.Store', {
	        autoSync: false,//修改后，立刻以json格式发回到服务器
	        pageSize: 12,  //一页显示多少行
	        model: 'TmpMerchCaseModel',//对应上边的model
	        remoteSort: true,
	        proxy: {
	            type: 'rest',//rest指的是method有get,post,put,delete;ajax指的是get,post
	            url:'readExam',
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
	    
	    var grid = Ext.create('Ext.grid.Panel', {
	        width: '100%',
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
	            text: "商户名",
	            dataIndex: 'merchName',
	            width:120,
	            sortable: false
	        },{
	            text: "店名",
	            dataIndex: 'shopName',
	            width:120,
	            sortable: false
	        },{
	            text: "省",
	            dataIndex: 'province.typeValue',
	            width:120,
	            sortable: false
	        },{
	            text: "市",
	            dataIndex: 'city.typeValue',
	            width:120,
	            sortable: false
	        },{
	            text: "区/县",
	            dataIndex: 'area.typeValue',
	            width:120,
	            sortable: false
	        },{
	            text: "店主名",
	            dataIndex: 'shopkeeper.username',
	            width:120,
	            sortable: false
	        },{
	            text: "跟进人",
	            dataIndex: 'follow.username',
	            width:120,
	            sortable: false
	        },{
	            text: "创建时间",
	            dataIndex: 'createDate',
	            width:200,
	            sortable: false,
	            renderer:function (value){
	            	return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
	            }
	        },{
	            text: "审核状态",
	            dataIndex: 'verifyType',
	            width:100,
	            sortable: false,
	            renderer:function(value){
	            	var s='';
	            	switch(value){
		            	case 0:s='待审';break;
		            	case 1:s='审核通过';break;
		            	case 2:s='二次待审';break;
		            	case 3:s='审核失败';break;
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
	            items: [ {
	                text: '审核',
	                iconCls: 'icon-update',
	                itemId: 'modify',
	                disabled: true,
	                handler: function(){
	                	showUpdateForm(1);
	                }
	            },'-', {
	                text: '输入搜索条件',
	                iconCls: 'icon-search',
	                handler: showSearchForm
	            }]
	        }],
	        listeners: {
	            "dblclick": {
	            	element: 'el',
	                fn: function(){ 
	                	showDetailForm();
	                }
	            }
	        }
	    });
	    
	    store.on('beforeload', function(){
	    	if(searchWin){
	    		var sForm=searchWin.items.items[0].form;
	    		var formJson=sForm.getValues(); 
	    		var new_params = { 
	    				'sh_eq_id_long': formJson.id,
	             		'sh_like_shopkeeper.username_string':  encodeURI(formJson.shopkeeper_username)	
	    		};  
		    	Ext.apply(store.proxy.extraParams, new_params); 
	    	}	
		});
	    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#modify').setDisabled(selections.length === 0);
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