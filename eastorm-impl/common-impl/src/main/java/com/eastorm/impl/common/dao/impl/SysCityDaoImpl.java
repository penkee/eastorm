/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.common.dao.impl;

import com.eastorm.api.common.domain.SysCity;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.common.dao.plus.SysCityPlus;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class SysCityDaoImpl extends DefaultDao implements SysCityPlus {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysCity> queryAreaList(String typeValue) {
		// TODO Auto-generated method stub
		return defaultEntityManager.queryNamed("city.queryAreaList",typeValue+"%");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysCity> queryAreas() {
		// TODO Auto-generated method stub
		return defaultEntityManager.queryNamed("city.queryAreas");
	}
}
