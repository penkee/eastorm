package com.eastorm.impl.member.service;

import com.eastorm.api.member.domain.LkMemberRole;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.api.member.domain.SysRole;
import com.eastorm.api.member.service.SysRoleService;
import com.eastorm.impl.member.dao.LkMemberRoleDao;
import com.eastorm.impl.member.dao.SysMemberDao;
import com.eastorm.impl.member.dao.SysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysRoleServiceImpl   implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private LkMemberRoleDao lkMemberRoleDao;
	@Autowired
	private SysMemberDao sysMemberDao;

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public String queryAuthUrls(Integer memberId) {
		// TODO Auto-generated method stub
		List<SysRole> roles=sysRoleDao.queryListByMemberId(memberId);
		StringBuilder strRoles=new StringBuilder();
		for(SysRole role :roles){
			strRoles.append(role.getAuthUrls());
		}
		return strRoles.toString();
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public Page<SysRole> queryPage(Pageable page,Map<String, Object> searchMap) {
		return sysRoleDao.queryPage(page, searchMap);
	}

	public List<SysRole> findAll() {
		// TODO Auto-generated method stub
		return sysRoleDao.findAll();
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public SysRole findOne(Integer id) {
		// TODO Auto-generated method stub
		return sysRoleDao.findOne(id);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public SysRole save(SysRole role) {
		// TODO Auto-generated method stub
		return sysRoleDao.save(role);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	public void saveLkMemberRole(List<LkMemberRole> roles, Integer memberId, SysMember sysMember) {
		// TODO Auto-generated method stub
		lkMemberRoleDao.deleteByMemberId(memberId);
		lkMemberRoleDao.save(roles);
		sysMemberDao.save(sysMember);
	}

	@Override
	public List<SysRole> queryList(Integer memberId) {
		// TODO Auto-generated method stub
		return sysRoleDao.queryListByMemberId(memberId);
	}

}
