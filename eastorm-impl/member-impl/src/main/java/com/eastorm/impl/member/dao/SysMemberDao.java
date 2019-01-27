/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.member.dao;

import com.eastorm.api.member.domain.SysMember;
import com.eastorm.impl.member.dao.plus.SysMemberPlus;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysMemberDao extends JpaRepository <SysMember, Integer>,SysMemberPlus {

}
