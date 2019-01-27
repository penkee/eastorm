package com.eastorm.api.member.service;

import com.eastorm.api.member.domain.LkMemberRole;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.api.member.domain.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface SysRoleService {
	/**
	 * 查询列表,返回字符串
	 * @param memberId
	 * @return
	 * add by penkee@163.com
	 */
	String queryAuthUrls(Integer memberId);
	/**
	 * 查询角色列表
	 * @return
	 * add by penkee@163.com
	 */
	Page<SysRole> queryPage(Pageable page, Map<String,Object> searchMap);
	
	List<SysRole> findAll();
	/**
	 * @param id
	 * add by penkee@163.com
	 * @return 
	 */
	SysRole findOne(Integer id);

	SysRole save(SysRole role);
	
	void saveLkMemberRole(List<LkMemberRole> roles, Integer memberId, SysMember sysMember);
	
	/**
	 * 查询列表,返回字符串
	 * @param memberId
	 * @return
	 * add by penkee@163.com
	 */
	public List<SysRole> queryList(Integer memberId);
}
