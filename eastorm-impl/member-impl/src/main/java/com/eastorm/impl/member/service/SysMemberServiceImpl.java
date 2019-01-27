package com.eastorm.impl.member.service;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.api.member.domain.SysRole;
import com.eastorm.api.member.service.SysMemberService;
import com.eastorm.core.base.utils.CryptUtil;
import com.eastorm.impl.member.dao.SysLoginDao;
import com.eastorm.impl.member.dao.SysMemberDao;
import com.eastorm.impl.member.dao.SysMemberInfoDao;
import com.eastorm.impl.member.dao.SysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysMemberServiceImpl   implements SysMemberService {
	@Autowired
	private SysMemberDao sysMemberDao;
	@Autowired
	private SysLoginDao sysLoginDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysMemberInfoDao memberInfoDao;
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysRole> queryRole(Integer id) {
		// TODO Auto-generated method stub
		return sysRoleDao.queryListByMemberId(id);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public Page<Map<String,Object>> queryPage(Pageable page,
			Map<String, String[]> searchMap) {
		// TODO Auto-generated method stub
		return sysMemberDao.queryPage(page, searchMap);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public void insertLoginAndMember(SysLogin login) {
		sysMemberDao.save(login.getMember());
		login.setMemberId(login.getMember().getId());
		sysLoginDao.save(login);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public SysMember findOne(Integer id) {
		// TODO Auto-generated method stub
		return sysMemberDao.findOne(id);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public List<CommType> queryListByUsername(String username) {
		// TODO Auto-generated method stub
		return sysMemberDao.queryListByUsername(username);
	}


	@Override
	public String encodeUserid(Integer id) {
		if(id==null)return null;
		return CryptUtil.getInstance().encryptAES(String.valueOf(id), "3464ghj5423*");
	}


	@Override
	public Integer decodeUserid(String id) {
		if(id==null)return null;
		return Integer.parseInt(CryptUtil.getInstance().decryptAES(String.valueOf(id), "3464ghj5423*"));
	}


	@Override
	public Page<Map<String, Object>> queryBackExport(PageRequest pageRequest,
			Map<String, String[]> parameterMap) {
		return sysMemberDao.queryBackExport(pageRequest,parameterMap);
	}

}
