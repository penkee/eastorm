/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.common.dao.plus;


import com.eastorm.api.common.domain.MenuItem;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface MenuItemPlus {
	/**
	 * 根据authUrls精确查询记录
	 * @param authUrls
	 * @return
	 * add by penkee@163.com
	 */
	MenuItem queryByAuthUrls(String authUrls);
}
