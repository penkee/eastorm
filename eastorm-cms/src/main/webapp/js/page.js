var SplitPage=function(allcount,onePagecount,currPage,styleShowNum){
				/**
				 * 记录总数
				 */
				this.allcount=allcount;
				/**
				 * 每一页记录数
				 */
				this.onePagecount=onePagecount;
				/**
				 * 目前页码
				 */
				this.currPage=currPage;
				/**
				 * 一行显示几个
				 */
				this.styleShowNum=styleShowNum;

				/**
				 * 分成页数
				 */
				this.pageNum=0;
				
				this.left=0;
				this.right=0;				
				this.showHTML=function(pstyle) {
					// <a href='a.do?p=1' class='curr'>1</a>,2,3,4,5,6,7,8
					this.getlimit();
					return pstyle.genHtml(allcount,left, right, currPage,pageNum,onePagecount);
				}

				this.getlimit=function(){

					//计算分成多少页
					pageNum= Math.ceil(allcount / onePagecount) ;
					var half=Math.round(styleShowNum / 2);
					 //左右边界固定
			        if (pageNum <= styleShowNum)
			        {
			            left = 1;
			            right = pageNum;
			        }//右边界固定
			        else if (currPage >= pageNum - half)
			        {
			            left = pageNum - styleShowNum + 1;
			            right = pageNum;
			        }//左边界固定
			        else if (currPage <= half)
			        {
			            left = 1;
			            right = styleShowNum;
			        }//左右边界都不固定
			        else
			        {
			            left = currPage - half+1;
			            right = currPage + Math.floor(styleShowNum/2);    
			        }
				}				
		}

var PageStyleA =function() {

	this.genHtml=function(allcount,start,end,cur,pageNum,onePagecount) {
		
		var sb = "";
		if (cur > start)
			sb+='<a class="font_st" target="_self" href="#" onclick="pagego(' + (cur - 1) + ')">&lt;</a>';
		for ( i = start; i <= end; i++) {
			if (i == cur)
				sb+='<span class="active_link">' + i + "</span>";
			else
				sb+='<a class="font_st" target="_self" href="#" onclick="pagego(' + i + ')">' + i + '</a>';
		}

		if (cur < end)
			sb+='<a class="font_st" target="_self" href="#" onclick="pagego(' + (cur + 1) + ')">&gt;</a>';		
		return sb;
	}
}
