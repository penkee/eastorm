/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.member;

import com.eastorm.api.member.domain.SysRole;
import com.eastorm.api.member.service.SysRoleService;
import com.eastorm.cms.web.base.BaseController;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/sysrole")
@ApiIgnore
public class SysRoleController extends BaseController {
	private static Logger   logger=Logger.getLogger(SysRoleController.class);
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 返回list
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/read")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("page") int page,
			@RequestParam("limit") int limit,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name) {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("id", id);
		searchMap.put("name", StringFunc.URLDecoder(name));
		Pageable pageable = new PageRequest(page - 1, limit);

		Page<SysRole> list = sysRoleService.queryPage(pageable, searchMap);
		return resultMap(list);
	}
	
	/**
	 * 更新角色
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String,Object> update(SysRole form_role) {
		Map<String,Object> resultMap = new HashMap<String,Object>();  
		resultMap.put("success", true);	
		try {			
			if (form_role.getId() == null) {
				//新增
				sysRoleService.save(form_role);
			}else{
				//修改
				SysRole db_role=sysRoleService.findOne(form_role.getId());
				db_role.setName(form_role.getName());
				db_role.setAuthUrls(form_role.getAuthUrls());
				sysRoleService.save(db_role);
			}			
			resultMap.put("status", Const.Result.SUCCESS.ordinal());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", Const.Result.EXCEPTION.ordinal());
		}
		
		resultMap.put("list", form_role);
        return resultMap;      
	}
	/**
	 * 返回list
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/readAll")
	@ResponseBody
	public Map<String, Object> getListAll() {
		List<SysRole> list = sysRoleService.findAll();
		return resultMap(list);
	}
}
