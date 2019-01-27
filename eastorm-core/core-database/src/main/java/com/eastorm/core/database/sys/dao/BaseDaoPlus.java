/**
 * 2014-9-27
	Description:
 */
package com.eastorm.core.database.sys.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2014-9-27
 */
public interface BaseDaoPlus<T> {
	 Page<T> queryPage(Pageable page, String extWhere, String orderBy, Map<String, String[]> searchMap, String model);
}
