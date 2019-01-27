package com.eastorm.impl.common.service;

import com.eastorm.api.common.domain.MenuItem;
import com.eastorm.api.common.service.MenuItemService;
import com.eastorm.api.common.service.NodeLinkService;
import com.eastorm.impl.common.dao.MenuItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuItemServiceImpl   implements MenuItemService {

	@Autowired
	private MenuItemDao menuItemDao;
	@Autowired
	private NodeLinkService nodeLinkDao;

	/* 新增，修改
	 * add by penkee@163.com
	 */
	@Override
	public MenuItem save(MenuItem item) {
		return menuItemDao.save(item);
	}


	/*  删除一条记录
	 * add by penkee@163.com
	 */
	@Override
	public void delete(Integer id) {
		//先删除menu外键关联
		//TODO 改成service
		//nodeLinkDao.deleteParentAndNode(id);
		menuItemDao.delete(id);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public MenuItem findOne(Integer id) {
		return menuItemDao.findOne(id);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public MenuItem queryByAuthUrls(String authUrls) {
		return menuItemDao.queryByAuthUrls(authUrls);
	}
}
