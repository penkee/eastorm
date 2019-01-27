/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.common.dao.plus;

import com.eastorm.api.common.domain.SysCity;

import java.util.List;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface SysCityPlus {
	/**
	 * 搜索县城
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<SysCity> queryAreaList(String typeValue);
	/**
	 * 查询所有县城
	 * added by Buke at 2015年12月27日
	 */
	public List<SysCity> queryAreas();
}
