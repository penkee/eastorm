/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.common.dao;


import com.eastorm.api.common.domain.CommType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface CommTypeDao extends JpaRepository<CommType, Integer> {
	
}
