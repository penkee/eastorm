/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.NodeLink;
import com.eastorm.api.common.service.NodeLinkService;
import com.eastorm.api.hedian.domain.WapMenuItem;
import com.eastorm.api.hedian.service.WapMenuItemService;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.core.base.utils.SysProperties;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/sys/wapmenu")
@ApiIgnore
public class WapMenuController extends BaseController {
	private static Logger   logger=Logger.getLogger(WapMenuController.class);
	@Autowired
	private NodeLinkService nodeLinkService;
	@Autowired
	private WapMenuItemService wapMenuItemService;
	/**
	 * 返回菜单列表的json格式
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/read/{parentId}")
	@ResponseBody
	public Map<String,Object> getListByParentId(@PathVariable("parentId") Integer parentId,
			@RequestParam("page") int page,
			@RequestParam("limit")int limit,		
			@RequestParam(value="name",required=false) String name){
		Map<String,Object> searchMap = new HashMap<String,Object>();  
		searchMap.put("typeValue", StringFunc.URLDecoder(name));
        Page<CommType> list=nodeLinkService.queryPageByParentId(new PageRequest(page-1, limit), "order by m.node.typeKey ", parentId,searchMap);
        
        Map<String,Object> map = new HashMap<String,Object>();  
        map.put("totalCount", list.getTotalElements());  
        map.put("list",list.getContent() );  
        return map;  
	}
	
	/**
	 * 获取菜单页面
	 * 
	 * @param parentId
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/{parentId}")
	public ModelAndView getMenuList(@PathVariable("parentId") Integer parentId,
			HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("back/sys/wapmenu");
		 ServletContext servletContext = super.getServletContext();
		 //-1标记为根
		if(-1==parentId){
			parentId=(Integer)servletContext.getAttribute("wapmenuRootId");
		}
		mav.addObject("parentId", parentId);

		Integer pId=null;
		if(-1!=parentId&&parentId!=(Integer)servletContext.getAttribute("wapmenuRootId")){
			//查找父节点,根没有父节点
			pId=nodeLinkService.findParentNodeId(parentId);
		}
		mav.addObject("upId", pId==null?-1:pId);
		return mav;
	}
	/**
	 * 新增，修改记录操作
	 */
	@RequestMapping(value="/update/{parentId}")
	@ResponseBody
	public Map<String,Object> update(@PathVariable("parentId") Integer parentId,
			HttpEntity<WapMenuItem> wapMenuItem){
		//页面传来的数据
		WapMenuItem formitem=wapMenuItem.getBody();	
		boolean res = true;
		try {			
			if (formitem.getId() == null) {
				//新增
				NodeLink menu = new NodeLink();
				menu.setParentId(parentId);
				nodeLinkService.saveAndNode(menu, formitem);
			} else {
				//修改
				WapMenuItem item= wapMenuItemService.findOne(formitem.getId());
				item.setTypeKey(formitem.getTypeKey());
				item.setTypeValue(formitem.getTypeValue());		
				item.settKey(formitem.gettKey());
				item.setAttr1(formitem.getAttr1());
				item.setAttr2(formitem.getAttr2());
				item.setName(formitem.getName());
				item.settValue(formitem.gettValue());
				wapMenuItemService.save(item);
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
			logger.error("插入菜单失败："+e);
		}
		Map<String,Object> map = new HashMap<String,Object>();  
        map.put("success",res);  
        map.put("message","" );  
        map.put("list", formitem);
        return map;
	}
	/**
	 *删除操作
	 *100-存在子节点，无法删除
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(WapMenuItem wapMenuItem){
		//删除之前判断是否有孩子
		Map<String,Object> map = new HashMap<String,Object>();  
		if(nodeLinkService.qryListCountByParentId(wapMenuItem.getId())>0){
			 map.put("status",100);  
		}else{
			try {
				wapMenuItemService.delete(wapMenuItem.getId());
				map.put("status", Const.Result.SUCCESS.ordinal());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				map.put("status",Const.Result.FAILTURE.ordinal());
			}
		}
        return map;
	}
	
	/**
	 *刷新操作
	 */
	@RequestMapping(value="/refresh",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> refresh(){
		Map<String,Object> map = new HashMap<String,Object>(2);  
		try {
			String wapmenuRootId= SysProperties.getValue("wapmenuRootId");

			map.put("status",Const.Result.SUCCESS.ordinal());
			map.put("message","刷新成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status",Const.Result.FAILTURE.ordinal());
			map.put("message","系统异常");
			logger.debug(e);
		}
        return map;
	}

}
