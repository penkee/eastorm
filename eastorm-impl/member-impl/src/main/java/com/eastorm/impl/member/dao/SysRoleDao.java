/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.member.dao;

import com.eastorm.api.member.domain.SysRole;
import com.eastorm.impl.member.dao.plus.SysRolePlus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysRoleDao extends JpaRepository <SysRole, Integer>,SysRolePlus {
	
}
