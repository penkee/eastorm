package com.eastorm.api.common.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {
	/**
	 * 新增，修改
	 * @param item
	 * @return
	 * add by penkee@163.com
	 */
	T save(T item);
	
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
	T findOne(Integer id);
	
	/**
	 * 
	 * @param page
	 * @param searchMap   数据筛选参数
	 * @param bizMap  业务逻辑参数
	 * @return
	 * add by penkee@163.com
	 * 2014-8-30
	 */
	Page<T> queryPage(Pageable page,Map<String,String[]> searchMap,Map<String,Object> bizMap);
	
}
