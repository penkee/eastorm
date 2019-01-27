package com.eastorm.api.common.service;

import com.eastorm.api.common.domain.DictItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public interface DictItemService {
	/**
	 * 新增，修改
	 * @param item
	 * @return
	 * add by penkee@163.com
	 */
	DictItem save(DictItem item);
	
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
	DictItem findOne(Integer id);
	
	/**
	 * 查询列表
	 * @return
	 * add by penkee@163.com
	 */
	Page<DictItem> queryPage(Pageable page, Map<String,String[]> searchMap);
	
	List<DictItem> queryListAll();
	/**
	 * 字典表放全局变量里
	 * 
	 * add by penkee@163.com
	 */
	void refreshCache(ServletContext context);
	/**
	 * 查询值
	 * add by penkee@163.com
	 * @return typeValue
	 */
	public String queryValue(String name, String typeKey,ServletContext context);
	/**
	 * 查询列表
	 * add by penkee@163.com
	 * @return typeValue
	 */
	public List<Map<String,String>> queryDict(String name,ServletContext context);
}
