package com.eastorm.api.common.service;


import com.eastorm.api.common.domain.MenuItem;

public interface MenuItemService {
	/**
	 * 新增，修改
	 * @param item
	 * @return
	 * add by penkee@163.com
	 */
	MenuItem save(MenuItem item);
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 * add by penkee@163.com
	 */
	void delete(Integer id);

	/**
	 * @param id
	 * add by penkee@163.com
	 * @return 
	 */
	MenuItem findOne(Integer id);
	/**
	 * 根据authUrls精确查询记录
	 * @param authUrls
	 * @return
	 * add by penkee@163.com
	 */
	MenuItem queryByAuthUrls(String authUrls);
}
