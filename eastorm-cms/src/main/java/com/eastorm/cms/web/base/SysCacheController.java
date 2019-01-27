/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.SysCache;
import com.eastorm.api.common.service.SysCacheService;
import com.eastorm.core.base.utils.Const;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/sys/cache")
@ApiIgnore
public class SysCacheController extends BaseController {
	private static Logger   logger=Logger.getLogger(SysCacheController.class);
	@Autowired
	private SysCacheService sysCacheService;
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
		Page<SysCache>  list=sysCacheService.queryPage(pageable,searchMap,null);
        return resultMap(list);
	}
	
	/**
	 * 新增，修改记录操作
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(HttpEntity<SysCache> sysCache){
		//页面传来的数据
		SysCache formitem=sysCache.getBody();	
		boolean res = true;
		String message="";
		try {			
			if (formitem.getId() == null) {
				//排重
				List<SysCache> list=sysCacheService.findByKeyname(formitem.getKeyname());
				if(CollectionUtils.isNotEmpty(list)){
					res=false;
					message="键值已存在";
				}else{
					//新增
					sysCacheService.save(formitem);
				}
			} else {
				//修改
				SysCache item= sysCacheService.findOne(formitem.getId());
				item.setJpql(formitem.getJpql());
				item.setKeyname(formitem.getKeyname());
				item.setResultType(formitem.getResultType());		
				sysCacheService.save(item);
			}
		} catch (Exception e) {
			res=false;
			e.printStackTrace();
			logger.debug("更新缓存失败："+e);
		}
		Map<String,Object> map = new HashMap<String,Object>();  
        map.put("success",res);  
        map.put("message",message);  
        map.put("list", formitem);
        return map;
	}
	/**
	 *删除操作
	 *100-存在子节点，无法删除
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(SysCache sysCache){
		Map<String,Object> map = new HashMap<String,Object>();  
		try {
			sysCacheService.delete(sysCache.getId());
			map.put("status", Const.Result.SUCCESS.ordinal());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("status",Const.Result.FAILTURE.ordinal());
			map.put("message","系统异常");
			logger.debug(e);
		}
        return map;
	}
	/**
	 *刷新操作
	 */
	@RequestMapping(value="/refresh",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> refresh(){
		Map<String,Object> map = new HashMap<String,Object>();  
		try {
			sysCacheService.refreshCache(super.getServletContext());
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
