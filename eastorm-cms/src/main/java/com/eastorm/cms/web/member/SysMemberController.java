/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.member;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.member.domain.*;
import com.eastorm.api.member.service.SysLoginService;
import com.eastorm.api.member.service.SysMemberInfoService;
import com.eastorm.api.member.service.SysMemberService;
import com.eastorm.api.member.service.SysRoleService;
import com.eastorm.cms.web.base.BaseController;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.SecurityUtils;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/car")
@ApiIgnore
public class SysMemberController extends BaseController {
	private static Logger   logger=Logger.getLogger(SysMemberController.class);
	@Autowired
	private SysMemberService sysMemberService;
	@Autowired
	private SysLoginService sysLoginService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMemberInfoService sysMemberInfoService;
	/**
	 * 返回菜单列表的json格式
	 * @param page
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/read")
	@ResponseBody
	public Map<String,Object> read(
			@RequestParam("page") int page,
			@RequestParam("limit")int limit,
			HttpServletRequest request){
		Map<String,String[]> searchMap = request.getParameterMap();
		Pageable pageable=new PageRequest(page-1, limit);
		Page<Map<String,Object>>  list=sysMemberService.queryPage(pageable, searchMap);
        return resultMap(list);
	}
	
	
	/**
	 * 获取用户的权限
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/readRoles/{memberId}")
	@ResponseBody
	public Map<String,Object> queryAuth(@PathVariable("memberId") int memberId) {
		List<SysRole> roles=sysMemberService.queryRole(memberId);
        return resultMap(roles);  
	}
	/**
	 * 更新用户权限
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/updateRoles",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateRole(@RequestParam("id")  Integer id, @RequestParam("roleIds")  Integer[] roleIds
			, SysMember form_member, HttpSession session) {
		//SysLogin data_member=dymemberService.findOne(form_member.getId());
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", true);	
		try {
			List<LkMemberRole> lkroles=new ArrayList<LkMemberRole>();
			for(Integer roleId:roleIds){
				LkMemberRole lr=new LkMemberRole();
				lr.setSysMemberId(id);
				lr.setRoleId(roleId);
				lkroles.add(lr);
			}
			
			SysMember sysMember =sysMemberService.findOne(form_member.getId());
			
			sysMember.setIsValid(form_member.getIsValid());
			sysMember.setModifiedById(super.getCurMember(session).getId());
			sysMember.setModifiedDate(new Date());
			sysMember.setGrade(form_member.getGrade());
			
			sysRoleService.saveLkMemberRole(lkroles,id,sysMember);
			
			resultMap.put("status", Const.Result.SUCCESS.ordinal());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status",Const.Result.EXCEPTION.ordinal());
			resultMap.put("message",e.getMessage());
			logger.debug(e);
		}
        return resultMap;  
	}
	/**
	 *  新增公司内部用户
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insert(SysLogin form_login, HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", true);	
		if(form_login.getId()==null){
			//new
			//判断用户名是否重复
			SysLogin sysLogin =sysLoginService.findOneByUsername(form_login.getUsername());
			if(sysLogin!=null){
				resultMap.put("status", Const.Result.FAILTURE.ordinal());	
				resultMap.put("message", "用户名已存在");
			}else{
				try {
					SysMember form_member=new SysMember();
					
					form_member.setCreateById(super.getCurMember(session).getId());
					form_member.setCreateDate(new Date());
					form_member.setCreateName(super.getCurMember(session).getUsername());
					form_member.setUsername(form_login.getUsername());
					form_member.setIsValid(form_login.getMember().getIsValid());
					
					form_login.setCheckcode("1234");
					form_login.setMember(form_member);
					form_login.setPassword(SecurityUtils.encipherMD5(SecurityUtils.MD5(form_login.getPassword()
							+form_login.getCheckcode())));
					
					sysMemberService.insertLoginAndMember(form_login);
					resultMap.put("status", Const.Result.SUCCESS.ordinal());	
					resultMap.put("message", "新增成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultMap.put("status", Const.Result.FAILTURE.ordinal());	
					resultMap.put("message", "系统出错");
					logger.debug("新增公司内部用户出错："+e);
				}
			}
		}else{
			//reset password
			try {
				SysLogin sysLogin=sysLoginService.findOne(form_login.getId());
				
				sysLogin.setPassword(SecurityUtils.encipherMD5(SecurityUtils.MD5(form_login.getPassword()
						+sysLogin.getCheckcode())));
				sysLoginService.save(sysLogin);
				resultMap.put("status", Const.Result.SUCCESS.ordinal());	
				resultMap.put("message", "重置密码成功");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("status", Const.Result.FAILTURE.ordinal());	
				resultMap.put("message", "系统出错");
				logger.debug("修改公司内部用户出错："+e);
			}
		}
		
        return resultMap;  
	}
	
	/**
	 * 返回菜单列表的json格式，排重，下拉用
	 * @param username
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/readList")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="username") String username){
		List<CommType>  list=sysMemberService.queryListByUsername(username);
        return resultMap(list);
	}
	
	/**
	 *获取详情
	 */
	@RequestMapping(value="/detail/{id}")
	@ResponseBody
	public Map<String,Object> getDetail(@PathVariable("id") Integer id){
		Map<String,Object> map = new HashMap<String,Object>();  
		try {
			SysMember sysMember = sysMemberService.findOne(id);
			SysMemberInfo memberInfo= sysMemberInfoService.findBySysMemberId(id);
			List<SysLogin> loginList=sysLoginService.findByMemberId(id);
			List<SysRole> roleList=sysRoleService.queryList(id);
			
			map.put("car",sysMember);
			map.put("memberInfo",memberInfo);
			map.put("loginList",loginList);
			map.put("roleList",roleList);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message","系统异常");
			logger.error(e);
		}
        return map;
	}
	
	/**
	 * 下载
	 * @param request
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/memberListExcel")
	public String downOrderList(HttpServletRequest request,ModelMap mm){
		Page<Map<String, Object>> list=sysMemberService.queryBackExport(new PageRequest(0, 65534),request.getParameterMap());
        mm.addAttribute("list",list.getContent());
		return "memberListExcel";
	}
}
