/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.common.dao.plus;

import com.eastorm.api.common.domain.SysCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface SysCachePlus {
	/**
	 * 查询列表
	 * @return
	 * add by penkee@163.com
	 */
	Page<SysCache> queryPage(Pageable page, String orderBy, Map<String,String[]> searchMap);
	List<SysCache> queryListAll();
	/**
	 * 根据jpql查询list的通用方法
	 * @param jpql
	 * @return
	 * add by penkee@163.com
	 */
	List queryList(String jpql);
}
