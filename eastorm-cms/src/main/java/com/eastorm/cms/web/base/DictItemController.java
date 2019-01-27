/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.DictItem;
import com.eastorm.api.common.service.DictItemService;
import com.eastorm.core.base.utils.Const;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/sys/dictItem")
@ApiIgnore
public class DictItemController extends BaseController {
	private static Logger   logger=Logger.getLogger(DictItemController.class);
	@Autowired
	private DictItemService dictItemService;
	/**
	 * 返回菜单列表的json格式
	 * @param page
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/read")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam("page") int page,
			@RequestParam("limit")int limit,		
			HttpServletRequest request){
		Map<String,String[]> searchMap = request.getParameterMap();
		Pageable pageable=new PageRequest(page-1, limit);
		Page<DictItem>  list=dictItemService.queryPage(pageable, searchMap);
        return resultMap(list);
	}
	
	/**
	 * 新增，修改记录操作
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(HttpEntity<DictItem> dictItem){
		//页面传来的数据
		DictItem formitem=dictItem.getBody();	
		boolean res = true;
		try {			
			if (formitem.getId() == null) {
				//新增
				dictItemService.save(formitem);
			} else {
				//修改
				DictItem item= dictItemService.findOne(formitem.getId());
				item.setName(formitem.getName());
				item.setTypeKey(formitem.getTypeKey());
				item.setTypeValue(formitem.getTypeValue());		
				item.settKey(formitem.gettKey());
				dictItemService.save(item);
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
			logger.debug("插入字典失败："+e);
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
	public Map<String,Object> delete(DictItem dictItem){
		Map<String,Object> map = new HashMap<String,Object>();  
		try {
			dictItemService.delete(dictItem.getId());
			map.put("status", Const.Result.SUCCESS.ordinal());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("status",Const.Result.FAILTURE.ordinal());
			map.put("message","系统异常："+e.getMessage());
			logger.debug(e);
		}
        return map;
	}
	/**
	 *删除操作
	 *100-存在子节点，无法删除
	 */
	@RequestMapping(value="/refresh",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> refresh(){
		Map<String,Object> map = new HashMap<String,Object>();  
		try {
			dictItemService.refreshCache(super.getServletContext());
			map.put("status",Const.Result.SUCCESS.ordinal());
			map.put("message","刷新成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("status",Const.Result.FAILTURE.ordinal());
			map.put("message","系统异常："+e.getMessage());
			logger.debug(e);
		}
        return map;
	}
}
