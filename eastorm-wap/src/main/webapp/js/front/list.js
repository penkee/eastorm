function showinputPanel(type){
		var e=$('#txt'+type+'Point');
		var span=$('#span'+type+'PointName');
		
		if(e.is(":hidden")){
			e.val(span.html());
			span.hide();
			e.show();
		}
	}
	
	function showCityPanel(t,type){
		var e=$('.sug4search');
		
		if('start'==type){
			e.css('left','30px');
		}else{
			e.css('left','41%');
		}
		
		var selPutid=type;
		queryCitys(t,selPutid);
		e.show();
	}
	
	function getSelectedCity(cityId,letterid,cityName,selPutid){
		$('#'+selPutid+'PointId').val(cityId);
		$('#'+selPutid+'PointName').val(cityName);
		var e=$('.sug4search');
		e.hide();
		
		var t=$('#txt'+selPutid+'Point');
		t.val(cityName);
		
		pagego(1);
	}
	
	function showMoreSearch(){
		var h=$('#searchPanel').css('height');
		if('180px'==h){
			$('#searchPanel').css('height','auto');
		}else{
			$('#searchPanel').css('height','180px');
		}
	}
	
	function updateSearchPanel(tip,value){
		var isfind=false;
		
		$('.selected_ul li').each(function(i,e){
			if(tip.indexOf('价格')>=0){
				var cxpriceStart=$('#cxpriceStart').val();
				var cxpriceEnd=$('#cxpriceEnd').val();
				if(cxpriceStart!=''&&cxpriceEnd!=''){
					tip='价格';
					value=cxpriceStart+'~'+cxpriceEnd;
				}
			}
			
			if(!isfind&&$(e).html().indexOf(tip)>=0){
				
				$(e).html(tip+value+'<a href="javascript:cancelOpt(\''+tip+'\')" class="icon_cancel"></a>');
				isfind=true;
			}
		});
		if(!isfind){
			var html='<li>'+tip+value+'<a href="javascript:cancelOpt(\''+tip+'\')" class="icon_cancel"></a></li>';
			$('.selected_ul').html($('.selected_ul').html()+html);
		}
	}
	function cancelOpt(tip){
		var name=tip.substring(0,2);
		if('出发'==name){
			$('#startdate').val('');
		}else if('到达'==name){
			$('#enddate').val('');
		}else if('关键'==name){
			$('#keyword').val('');
		}else if('交通'==name){
			//清除所有option
			$('.trafficTool li').each(function(i,e){
				$(e).removeClass('select_active');
			});
			$('#trafficTool').val('');
		}else if('价格'==name){
			$('#cxpriceStart').val('');
			$('#cxpriceEnd').val('');
		}else if('堵车'==name){
			//清除所有option
			$('.isJam li').each(function(i,e){
				$(e).removeClass('select_active');
			});
			$('#isJam').val('');
		}else if('超载'==name){
			//清除所有option
			$('.isOver li').each(function(i,e){
				$(e).removeClass('select_active');
			});
			$('#isOver').val('');
		}
		
		$('.selected_ul li').each(function(i,e){
			if($(e).html().indexOf(name)>=0){
				$(e).remove();
			}
		});
	}
	$('.trafficTool li').click(function(){
		$('.trafficTool li').each(function(i,e){
			$(e).removeClass('select_active');
		});
		
		$(this).addClass('select_active');
	});
	$('.isJam li').click(function(){
		$('.isJam li').each(function(i,e){
			$(e).removeClass('select_active');
		});
		
		$(this).addClass('select_active');
	});
	$('.isOver li').click(function(){
		$('.isOver li').each(function(i,e){
			$(e).removeClass('select_active');
		});
		
		$(this).addClass('select_active');
	});
	
	function initPage(total,size,page){
		var splitPage=new SplitPage(total,size,page,5);
		document.getElementById('pageBlock').innerHTML=splitPage.showHTML(new PageStyleA());
	}
	
	function pagego(i){
	    var startPointName=$('#startPointName').val();
	    var endPointName=$('#endPointName').val();
		//检查搜索条件
		if(isNull(g('startPointId'))){
			alert('请选择出发城市');
			showCityPanel('start');
			return;
		}
		if(isNull(g('endPointId'))){
			alert('请选择到达城市');
			showCityPanel('end');
			return;
		}
		
		$('#page').val(i);
		
		$.ajax({
			type: 'POST',
			url: getRootPath()+"/goutproject/read",
			dataType:"json",
			data:$('#form1').serialize(),// 要提交的表单
			success: function(data){
				var html='<ul class="result_con clearfix">';
				
				$(data.list).each(function (i,e) {
					if(e.endarea!=null){
						//<!-- 有中转 -->
						html+='<li>'
							+'<div class="result_btm_con lodgeunitname" style="cursor: pointer">'
								+'<div class="result_intro">'
									+'<a href="javascript:showDetail(\''+e.id+'\',\''+startPointName+'\',\''+e.midarea+'\')">'
									+'<span class="result_title_zz hiddenTxt">'+startPointName+'</span>'
									+'</a>'
									
									+'<span class="result_title_mid hiddenTxt"><span class="arrow">→</span>'+e.midarea+'<span class="arrow">→</span></span>'
									
									+'<a href="javascript:showDetail(\''+e.midid+'\',\''+e.midarea+'\',\''+e.endarea+'\')">'
									+'<span class="result_title_zz hiddenTxt">'+e.endarea+'</span>'
									+'</a> '
									
									+'<em class="hiddenTxt">乘坐'+e.strafficTool+' 中转 '+e.etrafficTool
									+'</em>'
								+'</div>'
							+'</div>'
						+'</li>';
					}else{
						html+='<li>'
							+'<div class="result_btm_con lodgeunitname" style="cursor: pointer">'
								+'<div class="result_intro">'
									+'<a href="javascript:showDetail(\''+e.id+'\',\''+startPointName+'\',\''+endPointName+'\')"><span class="result_title hiddenTxt">'+e.title+'</span></a>'
									+'<em class="hiddenTxt">'+e.username
										+'<span class="commenthref">'+(e.cmtcount==null?0:e.cmtcount)+'条评论</span> - '+e.price+'元'
									+'</em>'
								+'</div>'
							+'</div>'
						+'</li>';
					}
				});
				
				html+='</ul>';
				
				if(data.list.length<=0){
					$('#projectlist').html('<div class="result_no">'
					+'<em></em>'
					     	+'<p><span>没有符合条件的方案</span>建议您减少或更改一些查询条件重新搜索</p>'
					 +'</div>');
				}else{
					$('#projectlist').html(html);
				}
				$('#searchTotal').html(data.totalCount);
				initPage(data.totalCount,18,i);
	      	}
		});
	}
	//0-汽车，1-火车，2-飞机,3-轮船
	function showDetail(id,startPointName,endPointName){
		$.ajax({
			type: 'GET',
			url: getRootPath()+"/goutproject/detail?id="+id,
			dataType:"json",
			success: function(data){
				$('.ti_title').html('<span id=dStartName>'+startPointName+'</span><i class="tool'+data.goutproject.trafficTool+'"></i><span id=dEndName>'+endPointName+'</span>');
				$('#trafficName').html(data.goutproject.startTrafficSite.trafficName);
				$('#projectprice').html('￥'+(data.goutproject.price==null?'未知':data.goutproject.price));
				
				$('#startDate').html(new Date(data.goutproject.startDate).pattern('yyyy-MM-dd HH:mm:ss'));
				var time=timer(data.goutproject.endDate-data.goutproject.startDate);
				$('#projecttime').html(time);
				
				$('#strafficSiteName').html(startPointName+data.goutproject.startTrafficSite.name);
				$('#etrafficSiteName').html(endPointName+data.goutproject.endTrafficSite.name);
				$('#driverPhone').html(data.goutproject.driverPhone);
				$('#projectcontent').html(data.goutproject.content);
				
				if('1'==data.isfocus){
					$('#focusbtn').html('已收藏');
				}else{
					$('#focusbtn').html('点击收藏');
					$('#focusbtn').click(function(e,t){
						focus();
					});
				}
				if('1'==data.goutproject.isOwn){//是否自营
					$('#ziyingflag').show();
				}else{
					$('#ziyingflag').hide();
				}
				//评论树型图
				var index=0;//全部评论的索引
				var lastRootId=-1;//新的一段对话的根id
				var chtml='';//评论所有html
				$(data.commentlist.content).each(function(i,e){
					if(e.lastdialogId==-1&&lastRootId!=-1){
						//尾巴
									chtml+='</ul>'+
										'<div class="reply-form" id="inn_reply_form_'+lastRootId+'">'+
											'<input type="hidden" id="lastdialogId_'+lastRootId+'"/>'+
											'<input type="hidden" id="lastdialogResponseId_'+lastRootId+'"/>'+
				                        	'<div class="textarea">'+
				                            	'<textarea style="height: 70px; color: rgb(0, 0, 0);"  class="comment_reply focus" id="comment_reply_'+lastRootId+'"></textarea>'+
				                            	'<p class="limit">1~100个字</p>'+
				                        	'</div>'+
				                        	'<div class="act submit_reply" style="display: block;"><a class="btn-orange btn_inn_reply" href="javascript:new_btn_inn_reply('+lastRootId+')">回复</a></div>'+
				     					'</div>'+
					                '</div>'+
					            '</div>'+
					        '</div>'+
					    '</div>';
					}
					if(e.lastdialogId==-1){
						lastRootId=e.id;
						//一段新的对话的开始
		    chtml+='<div class="comment-item">'+
				        '<div class="user-bar">'+
				            '<span class="user-avatar">'+
				            	 '<a href="#" target="_blank" rel="nofollow" class="header">'+
				                    '<img src="'+getRootPath () +'/'+e.responsepic+'"/>'+
				                '</a>'+
				            '</span>'+
				            '<span class="user-level"><a style="color:#666; text-decoration:none; cursor:text"></a></span>'+
				        '</div>'+
				        '<div class="comment-info">'+
				            '<div class="info">'+
				                '<a class="user-name" href="#" target="_blank" rel="nofollow">'+e.responsename+'</a>'+
				                '<span> '+ new Date(e.createTime).pattern("yyyy-MM-dd hh:mm:ss")+'</span>'+
				            '</div>'+
				            '<div class="c-content">'+
				                '<p>'+e.content+'<a href="javascript:respShow('+lastRootId+','+e.id+',\''+e.responsename+'\','+e.responseId+')">  回复</a></p>'+
				            '</div>'+
				            '<div class="add-reply">'+
				                '<div class="ar-bd">'+
				                    '<span class="arrow"></span>'+
				                    '<ul class="comment_list">';
					}else{
						if(e.rootDialogId==lastRootId){
							chtml+='<li class="reply_item">'+
					                        '<a href="#" rel="nofollow" class="headersmall">'+
					                            '<img src="'+getRootPath () +'/'+e.responsepic+'"/>'+
					                        '</a>'+
					                        '<a href="#" rel="nofollow"> '+e.responsename+' </a>'+
					                        	'回复 '+e.lastdialogresponsename+'：'+e.content+
					                    '<a href="javascript:respShow('+lastRootId+','+e.id+',\''+e.responsename+'\','+e.responseId+')">  回复</a>'+
					                    '<span class="time"> '+new Date(e.createTime).pattern("yyyy-MM-dd hh:mm:ss")+'</span>'+
					                '</li>';
						}
					}
				});
				//最后尾巴
				if(''!=chtml){
							chtml+='</ul>'+
							
						'<div class="reply-form" id="inn_reply_form_'+lastRootId+'">'+
							'<input type="hidden" id="lastdialogId_'+lastRootId+'"/>'+
							'<input type="hidden" id="lastdialogResponseId_'+lastRootId+'"/>'+
                        	'<div class="textarea">'+
                            	'<textarea style="height: 70px; color: rgb(0, 0, 0);"  class="comment_reply focus" id="comment_reply_'+lastRootId+'"></textarea>'+
                            	'<p class="limit">1~100个字</p>'+
                        	'</div>'+
                        	'<div class="act submit_reply" style="display: block;"><a class="btn-orange btn_inn_reply" href="javascript:new_btn_inn_reply('+lastRootId+')">回复</a></div>'+
     					'</div>'+
     					
			                '</div>'+
			            '</div>'+
			        '</div>'+
			    '</div>';
				}
				$('#commentList').html(chtml);
				$('#goutproductid').val(id);
	      	}
		});
	}
	//显示回复框
	function respShow(showId,lastdialogId,lastdialogresponsename,lastdialogResponseId){
		$('#comment_reply_'+showId).val("回复 "+lastdialogresponsename+"：");
		$('#lastdialogId_'+showId).val(lastdialogId);
		$('#lastdialogResponseId_'+showId).val(lastdialogResponseId);
		
		$('#inn_reply_form_'+showId).show();
	}
	/**
	提交回复
    */
	function new_btn_inn_reply(showId){
		var goutproductid=$('#goutproductid').val();
		if(isStringNull(goutproductid)){
			return;
		}
		
		var content=$('#comment_reply_'+showId).val();
		if(isStringNull(content)){
			alert('评论内容不能为空');
			return;
		}
		var ignoreIdx=content.indexOf('：');
		if(ignoreIdx>0){
			content=content.substring(ignoreIdx+1);
		}
		$.ajax({
			type: 'POST',
			url:  getRootPath()+"/projectdialog/vt/insert",
			dataType:"json",
			async:false,
			data:{
				"content":content,
				"goutproductid":goutproductid,
				"lastdialogId":$('#lastdialogId_'+showId).val(),
				"lastdialogResponseId":$('#lastdialogResponseId_'+showId).val(),
				"rootDialogId":showId
			},
			success: function(data){
				if("0000"==data.errCode){
					showDetail(goutproductid,$('#dStartName').html(),$('#dEndName').html());
					$('#comment_reply_'+showId).val('');//clear
				}else{
					alert(data.errMsg);
				}
	      	}
		});
	}
	//点击收藏
	function focus(){
		var goutid=$('#goutproductid').val();
		$.ajax({
			type: 'POST',
			url:  getRootPath()+"/memberfocus/vt/insert",
			dataType:"json",
			async:false,
			data:{
				"ftype":3,
				"objectId":goutid
			},
			success: function(data){
				if("0000"==data.errCode){
					$('#focusbtn').html('已收藏');
					$('#focusbtn').click(function(e,t){
						
					});
				}else{
					alert(data.errMsg);
				}
	      	}
		});
	}