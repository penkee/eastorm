package com.eastorm.api.common.service;


import com.eastorm.api.common.domain.SysCity;
import com.eastorm.api.common.vo.CityItem;

import java.util.List;

public interface SysCityService {
	/**
	 * 新增，修改
	 * @param item
	 * @return
	 * add by penkee@163.com
	 */
	SysCity save(SysCity item);
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 * add by penkee@163.com
	 */
	void delete(Integer id);

	/**查找，并查找对于城市的图片
	 * @param id
	 * add by penkee@163.com
	 * @return 
	 */
	SysCity findOne(Integer id);
	/**
	 * 根据关键字开头，查询
	 * added by Buke at 2015年9月6日
	 */
	List<SysCity> queryAreaList(String typeValue);
	/**
	 * 查询所有县城
	 * added by Buke at 2015年12月27日
	 */
	public List<SysCity> queryAreas();
	/**
	 * 查询所有城市，树形，给前端调用
	 * added by Buke at 2015年12月27日
	 */
	public List<CityItem> queryAll(Integer rootId);
}
