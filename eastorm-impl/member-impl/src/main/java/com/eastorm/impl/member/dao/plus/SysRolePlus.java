/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.member.dao.plus;

import com.eastorm.api.member.domain.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface SysRolePlus {
	/**
	 * 查询列表
	 * @param memberId
	 * @return
	 * add by penkee@163.com
	 */
	List<SysRole> queryListByMemberId(Integer memberId);
	
	/**
	 * 查询角色列表
	 * @return
	 * add by penkee@163.com
	 */
	Page<SysRole> queryPage(Pageable page, Map<String,Object> searchMap);
}
