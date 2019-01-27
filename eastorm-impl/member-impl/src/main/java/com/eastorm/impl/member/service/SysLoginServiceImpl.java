package com.eastorm.impl.member.service;

import com.eastorm.api.common.service.SysOplogService;
import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.api.member.domain.SysMemberInfo;
import com.eastorm.api.member.service.SysLoginService;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.SecurityUtils;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.impl.member.dao.SysLoginDao;
import com.eastorm.impl.member.dao.SysMemberDao;
import com.eastorm.impl.member.dao.SysMemberInfoDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class SysLoginServiceImpl   implements SysLoginService {
	private static Logger  logger=Logger.getLogger(SysLoginServiceImpl.class);
	@Autowired
	private SysLoginDao sysLoginDao;
	@Autowired
	private SysMemberDao sysMemberDao;
	@Autowired
	private SysMemberInfoDao memberInfoDao;
	@Autowired
	private SysOplogService oplogService;
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public SysLogin queryOneByUsername(String username) {
		// TODO Auto-generated method stub
		List<SysLogin> list=sysLoginDao.queryListByUsername(username);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public SysLogin findOneByUsername(String username) {
		// TODO Auto-generated method stub
		List<SysLogin> list=sysLoginDao.findByUsername(username);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public SysLogin findOne(Integer id) {
		// TODO Auto-generated method stub
		return sysLoginDao.findOne(id);
	}
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public void save(SysLogin login) {
		// TODO Auto-generated method stub
		sysLoginDao.save(login);
	}
	@Override
	public int queryCountByUsername(String username) {
		// TODO Auto-generated method stub
		List<SysLogin> list=sysLoginDao.queryListByUsername(username);
		if(CollectionUtils.isNotEmpty(list)){
			return list.size();
		}
		return 0;
	}
	@Override
	public SysMember insertFrontUser(String username, String password, String openid,
									 String alias, String headurl, String verifyCode, Byte sex) throws EastormException {
		
		//验证用户名是否重复
		List<SysLogin> loginlist=sysLoginDao.findByUsername(username);
		if(loginlist!=null&&loginlist.size()>0){
			throw new EastormException(Const.FR_FAILTURE, "该用户已存在");
		}
		
		SysMember form_member=new SysMember();
		SysLogin loginWechat=new SysLogin();
		SysMemberInfo memberInfo=new SysMemberInfo();
		
		form_member.setUsername(username);
		form_member.setIsValid((byte) 1);
		form_member.setCreateDate(new Date());
		form_member.setCreateName("系统");
		
		memberInfo.setShowPrivateClass((byte) 0);
		if(StringFunc.isNull(headurl))headurl=Const.HeadPic;
		memberInfo.setHeadPic(headurl);
		memberInfo.setNickname(alias);
		memberInfo.setBalance(new BigDecimal(0));
		memberInfo.setScore(0);
		memberInfo.setSex(sex==null?0:sex);
		
		sysMemberDao.save(form_member);
		
		loginWechat.setUsername("wechat_"+openid);
		loginWechat.setMemberId(form_member.getId());
		//插入前判断是否重复 modified by pengk at 2016-7-4
		List<SysLogin> loginWeixinlist=sysLoginDao.findByUsername(loginWechat.getUsername());
		if(loginWeixinlist!=null&&loginWeixinlist.size()>0){
			//throw new EastormException(Const.FR_FAILTURE, "手机号已存在");
		}else{
			sysLoginDao.save(loginWechat);
		}
		memberInfo.setSysMemberId(form_member.getId());
		//memberInfoDao.save(memberInfo);
		return form_member;
	}
	@Override
	public List<SysLogin> findByMemberId(Integer id) {
		// TODO Auto-generated method stub
		return sysLoginDao.findByMemberId(id);
	}
	@Override
	public void resetPwd(String username, String password) throws EastormException {
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{6,12}$");
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches()){
			throw new EastormException(Const.FR_FAILTURE, "密码必须输入6-12个字母数字符号的组合");
		}
		
		SysLogin login=findOneByUsername(username);
		StringFunc.checkObjectNull(login, "账户不存在");
		
		String enPwd="";
		try {
			enPwd = SecurityUtils.encipherMD5(SecurityUtils.MD5(password
					+login.getCheckcode()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.setPassword(enPwd);
		sysLoginDao.save(login);
		oplogService.addLog("用户"+username+"重置密码", username, "m", login.getMemberId());
	}
}
