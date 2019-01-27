/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.member.dao;

import com.eastorm.api.member.domain.LkMemberRole;
import com.eastorm.impl.member.dao.plus.LkMemberRolePlus;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface LkMemberRoleDao extends JpaRepository <LkMemberRole, Integer>,LkMemberRolePlus {
}
