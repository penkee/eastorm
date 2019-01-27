/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.common.dao.impl;

import com.eastorm.api.common.domain.DictItem;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.common.dao.plus.DictItemPlus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class DictItemDaoImpl extends DefaultDao implements DictItemPlus {

	@Override
	public Page<DictItem> queryPage(Pageable page,
									Map<String, String[]> searchMap) {
		return super.queryPagebase(page, "from DictItem m where 1=1 ","order by name desc", searchMap);
	}

	@Override
	public List<DictItem> queryListAll() {
		return defaultEntityManager.queryNamed("DictItem.queryListAll");
	}
}
