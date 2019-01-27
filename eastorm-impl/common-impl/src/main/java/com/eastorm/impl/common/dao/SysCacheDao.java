/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.common.dao;


import com.eastorm.api.common.domain.SysCache;
import com.eastorm.impl.common.dao.plus.SysCachePlus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface SysCacheDao extends JpaRepository<SysCache, Integer>,SysCachePlus {
	List<SysCache> findByKeyname(String keyname);
}
