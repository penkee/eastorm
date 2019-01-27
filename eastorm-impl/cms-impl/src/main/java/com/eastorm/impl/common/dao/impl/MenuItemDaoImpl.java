/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.common.dao.impl;

import com.eastorm.api.common.domain.MenuItem;
import com.eastorm.impl.common.dao.plus.MenuItemPlus;
import com.eastorm.core.database.sys.dao.DefaultDao;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class MenuItemDaoImpl extends DefaultDao implements MenuItemPlus {

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public MenuItem queryByAuthUrls(String authUrls) {
		// TODO Auto-generated method stub
		return (MenuItem)defaultEntityManager.queryNamedOne("menuitem.queryByAuthUrls", authUrls);
	}
	
}
