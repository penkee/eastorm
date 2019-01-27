/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.member.dao;

import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.impl.member.dao.plus.SysLoginPlus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysLoginDao extends JpaRepository <SysLogin, Integer>,SysLoginPlus {
	List<SysLogin> findByUsername(String username);
	List<SysLogin> findByMemberId(Integer id);
}
