/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.MenuItem;
import com.eastorm.api.common.domain.NodeLink;
import com.eastorm.api.common.service.MenuItemService;
import com.eastorm.api.common.service.NodeLinkService;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
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
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/sys/menu")
@ApiIgnore
public class MenuController extends BaseController {
	private static Logger   logger=Logger.getLogger(MenuController.class);
	@Autowired
	private NodeLinkService nodeLinkService;
	@Autowired
	private MenuItemService menuItemService;
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
		searchMap.put("name", StringFunc.URLDecoder(name));
        Page<CommType> list=nodeLinkService.queryPageByParentId(new PageRequest(page-1, limit), "order by m.node.id desc ", parentId,searchMap);
        
        Map<String,Object> map = new HashMap<String,Object>();  
        map.put("totalCount", list.getTotalElements());  
        map.put("list",list.getContent() );  
        return map;  
	}
	
	/**
	 * 获取菜单页面
	 * @param parentId
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/{parentId}")
	public ModelAndView getMenuList(@PathVariable("parentId") Integer parentId,
			HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("back/sys/menu");
		
		 ServletContext servletContext = super.getServletContext();
		 //-1标记为根
		if(-1==parentId){
			parentId=(Integer)servletContext.getAttribute("menuRootId");
		}
		Integer pId=null;
		if(-1!=parentId&&parentId!=((Long)servletContext.getAttribute("categoryId")).intValue()){
			//查找父节点,根没有父节点
			pId=nodeLinkService.findParentNodeId(parentId);
		}
		mav.addObject("upId", pId==null?-1:pId);
		mav.addObject("parentId", parentId);
		return mav;
	}
	/**
	 * 新增，修改记录操作
	 */
	@RequestMapping(value="/update/{parentId}")
	@ResponseBody
	public Map<String,Object> update(@PathVariable("parentId") Integer parentId,
			HttpEntity<MenuItem> menuItem){
		//页面传来的数据
		MenuItem formitem=menuItem.getBody();	
		boolean res = true;
		try {			
			if (formitem.getId() == null) {
				//新增
				NodeLink menu = new NodeLink();
				menu.setParentId(parentId);
				nodeLinkService.saveAndNode(menu, formitem);
			} else {
				//修改
				MenuItem item= menuItemService.findOne(formitem.getId());
				item.setName(formitem.getName());
				item.setTypeValue(formitem.getTypeValue());		
				item.settKey(formitem.gettKey());
				item.settValue(formitem.gettValue());
				menuItemService.save(item);
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
			logger.debug("插入菜单失败："+e);
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
	public Map<String,Object> delete(MenuItem menuItem){
		//删除之前判断是否有孩子
		Map<String,Object> map = new HashMap<String,Object>();  
		if(nodeLinkService.qryListCountByParentId(menuItem.getId())>0){
			 map.put("status",100);  
		}else{
			try {
				menuItemService.delete(menuItem.getId());
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
	 * 获取所有菜单list
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/readAll")
	@ResponseBody
	public Map<String,Object> getAll() {
		List<NodeLink> menus = nodeLinkService.getListByType(2);
		Map<String,Object> resultMap = new HashMap<String,Object>();  
        resultMap.put("list",menus);  
        return resultMap;  
	}
}
