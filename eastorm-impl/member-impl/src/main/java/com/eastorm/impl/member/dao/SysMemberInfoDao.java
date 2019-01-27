/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.member.dao;

import com.eastorm.api.member.domain.SysMemberInfo;
import com.eastorm.impl.member.dao.plus.SysMemberInfoPlus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysMemberInfoDao extends JpaRepository <SysMemberInfo, Integer>,SysMemberInfoPlus {
	List<SysMemberInfo> findByEmail(String email);
	List<SysMemberInfo> findBySysMemberId(Integer sysMemberId);
	Long countBySysMemberId(Integer sysMemberId);
}
