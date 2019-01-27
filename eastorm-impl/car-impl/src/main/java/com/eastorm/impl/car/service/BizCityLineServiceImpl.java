package com.eastorm.impl.car.service;

import com.eastorm.api.car.domain.BizCityLine;
import com.eastorm.api.car.service.BizCityLineService;
import com.eastorm.impl.car.dao.BizCityLineDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BizCityLineServiceImpl implements BizCityLineService {
	private static Logger  logger=Logger.getLogger(BizCityLineServiceImpl.class);
	@Autowired
	private BizCityLineDao dao;


	public BizCityLine save(BizCityLine item) {
		return dao.save(item);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public BizCityLine findOne(Integer id) {
		return dao.findOne(id);
	}

	/**
	 * 查询城市线路
	 * @param page
	 * @param searchMap   数据筛选参数
	 * @param bizMap  业务逻辑参数
	 * @return
	 */
	public Page<BizCityLine> queryPage(Pageable page, Map<String, String[]> searchMap, Map<String, Object> bizMap) {
		String extWhere="";
		String orderBy="order by id desc";

		return dao.queryPage(page, extWhere,orderBy,searchMap,"BizCityLine");
	}
}
