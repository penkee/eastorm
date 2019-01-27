/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.common.dao.impl;

import com.eastorm.api.common.domain.SysCache;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.common.dao.plus.SysCachePlus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class SysCacheDaoImpl extends DefaultDao implements SysCachePlus {
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Page<SysCache> queryPage(Pageable page, String orderBy,
									Map<String, String[]> searchMap) {
		if(StringFunc.isNull(orderBy)){
			orderBy="order by keyname";
		}
		return super.queryPagebase(page, "from SysCache m where 1=1 ",orderBy, searchMap);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysCache> queryListAll() {
		// TODO Auto-generated method stub
		return defaultEntityManager.queryNamed("SysCache.queryListAll");
	}

}
