/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.common.dao.plus;

import com.eastorm.api.common.domain.DictItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface DictItemPlus {
	/**
	 * 查询列表
	 * @return
	 * add by penkee@163.com
	 */
	Page<DictItem> queryPage(Pageable page, Map<String,String[]> searchMap);
	List<DictItem> queryListAll();
}
