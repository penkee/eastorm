//状态bar的设置方法
var dyStatusBarFun={
		loadStatusBusy:function(statusBar,btn){
	        statusBar = Ext.getCmp(statusBar);
	        statusBar.showBusy();
	        //btn disabled
	        if(btn){
	        	 btn = Ext.getCmp(btn);
	        	 btn.disable();
	        }
	    },
		loadStatusSucc:function(statusBar,msg,btn){
	        statusBar = Ext.getCmp(statusBar);
	        statusBar.setStatus({
                text: msg?msg:'操作成功!',
                iconCls: 'x-status-valid',
                clear: true // auto-clear after a set interval
            });
	        //btn enabled
	        if(btn){
	        	 btn = Ext.getCmp(btn);
	        	 btn.enable();
	        }
	    },
	    loadStatusFail:function(statusBar,msg,btn){
	        statusBar = Ext.getCmp(statusBar);
	        statusBar.setStatus({
                text: msg?msg:'操作失败!',
                iconCls: 'x-status-error',
                clear: true // auto-clear after a set interval
            });	 
	        //btn enabled
	        if(btn){
	        	 btn = Ext.getCmp(btn);
	        	 btn.enable();
	        }
	    },
	    loadStatusClear:function(statusBar){
	        statusBar = Ext.getCmp(statusBar);
	        statusBar.clearStatus({useDefaults:true});   
	    },
	    loadStatus:function(statusBar,msg,btn,status){
	        statusBar = Ext.getCmp(statusBar);
	        statusBar.setStatus({
                text: msg?msg:(status==0?'操作成功!':'操作失败!'),
                iconCls: status==0?'x-status-valid':'x-status-error',
                clear: true // auto-clear after a set interval
            });
	        //btn enabled
	        if(btn){
	        	 btn = Ext.getCmp(btn);
	        	 btn.enable();
	        }
	    }
}
	    