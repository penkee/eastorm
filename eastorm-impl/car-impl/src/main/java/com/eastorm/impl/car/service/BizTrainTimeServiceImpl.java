package com.eastorm.impl.car.service;

import com.eastorm.api.car.domain.BizTrainTime;
import com.eastorm.api.car.service.BizTrainTimeService;
import com.eastorm.api.car.vo.BizTrainTimeAddVo;
import com.eastorm.api.car.vo.BizTrainTimeVo;
import com.eastorm.impl.car.dao.BizTrainTimeDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BizTrainTimeServiceImpl implements BizTrainTimeService {
	private static Logger  logger=Logger.getLogger(BizTrainTimeServiceImpl.class);
	@Autowired
	private BizTrainTimeDao dao;


	public BizTrainTime save(BizTrainTime item) {
		return dao.save(item);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public BizTrainTime findOne(Integer id) {
		return dao.findOne(id);
	}

	public Page<BizTrainTime> queryPage(Pageable page, Map<String, String[]> searchMap, Map<String, Object> bizMap) {
		return null;
	}

	public Integer addTrainTime(BizTrainTimeAddVo trainTimeAddVo) {
		return null;
	}

	public Integer editTrainTime(BizTrainTimeAddVo trainTimeAddVo) {
		return null;
	}

	public Page<BizTrainTimeAddVo> queryMyTainTimePage(Pageable page, Integer driverId) {
		return null;
	}

	public Page<BizTrainTimeVo> searchTrainTimeList(Pageable page, Integer driverId) {
		return null;
	}
}
