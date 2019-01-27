/**
 * description:首页control
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.member;

import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.api.member.service.SysLoginService;
import com.eastorm.api.member.service.SysRoleService;
import com.eastorm.cms.web.base.BaseController;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.SecurityUtils;
import com.eastorm.core.base.utils.StringFunc;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pengkun (penkee@163.com) 2013-10-13
 */
@Controller
@RequestMapping("/back")
public class LoginController extends BaseController {
	private Logger logger = Logger.getLogger(LoginController.class.getName());
	@Autowired
	private SysLoginService sysLoginService;
	@Autowired
	private SysRoleService sysRoleService;
	/**
	 * 登录submit
	 * 
	 * @param m
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/_login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> _login(SysLogin m, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//验证码
			String imgcode=(String)session.getAttribute(Const.BACKImgCode);
			if(imgcode==null||!imgcode.toLowerCase().equals(m.getCheckcode().toLowerCase())){
				resultMap.put("status",  Const.Result.FAILTURE.ordinal());
				resultMap.put("message",  "验证码错误。");
				return resultMap;	
			}
			SysLogin login =sysLoginService.queryOneByUsername(StringFunc.URLDecoder(m.getUsername()));
			if(login!=null
					&&login.getMember()!=null
					&&1==login.getMember().getIsValid()
					&& SecurityUtils.decipherMD5(login.getPassword()).equals(SecurityUtils.MD5(
							StringFunc.URLDecoder(m.getPassword())+login.getCheckcode()
									)
					)){
				//查找角色
				String roles=sysRoleService.queryAuthUrls(login.getMember().getId());
				if(StringFunc.isNull(roles)){//没有角色代表是前台会员，不能登录后台系统
					resultMap.put("status",  Const.Result.FAILTURE.ordinal());
					resultMap.put("message",  "用户名或密码错误。");
					logger.info("非法登录："+login.getUsername());
				}else{
					login.setMemberId(login.getMember().getId());
					session.setAttribute(Const.SysLogin, login);
					//更新登录时间
					Date lastLoginTime=login.getLastLoginTime();
					login.setLastLoginTime(new Date());
					sysLoginService.save(login);
					login.setLastLoginTime(lastLoginTime);
					session.setAttribute(Const.SysMember, login.getMember());
					session.setAttribute(Const.AuthUrls, roles);
					resultMap.put("status",  Const.Result.SUCCESS.ordinal());		
					resultMap.put("message",  "登录成功。");
					
					session.removeAttribute(Const.BACKImgCode);
				}
			}else{
				if(login!=null&&login.getMember()!=null&&0==login.getMember().getIsValid()){
					resultMap.put("message",  "该用户已被锁定，请联系公司客服。");
				}else{
					resultMap.put("message",  "用户名或密码错误。");
				}
				resultMap.put("status",  Const.Result.FAILTURE.ordinal());
			}
		} catch (Exception e) {
			logger.error("登录失败：",e);
			resultMap.put("status",  Const.Result.FAILTURE.ordinal());
			resultMap.put("message",  "未知错误："+e.getMessage());
		}
		return resultMap;	
	}
	/**
	 *退出
	 * 
	 * @param session
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/loginout")
	public String _loginout(HttpSession session) {	
		session.removeAttribute(Const.SysMember);		
		session.removeAttribute(Const.AuthUrls);	
		session.invalidate();
		return "back/loginout";	
	}
}
