/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.common.dao;

import com.eastorm.api.common.domain.SysUrlsource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysUrlsourceDao extends JpaRepository<SysUrlsource, Integer> {
}
