/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.common.dao;

import com.eastorm.api.common.domain.SysCity;
import com.eastorm.impl.common.dao.plus.SysCityPlus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysCityDao extends JpaRepository<SysCity, Integer>,SysCityPlus {
}
