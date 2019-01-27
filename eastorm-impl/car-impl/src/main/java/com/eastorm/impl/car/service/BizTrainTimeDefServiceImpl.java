package com.eastorm.impl.car.service;

import com.eastorm.api.car.domain.BizTrainTimeDef;
import com.eastorm.api.car.service.BizTrainTimeDefService;
import com.eastorm.api.car.vo.BizTrainTimeDefAddVo;
import com.eastorm.impl.car.dao.BizTrainTimeDefDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BizTrainTimeDefServiceImpl implements BizTrainTimeDefService {
	private static Logger  logger=Logger.getLogger(BizTrainTimeDefServiceImpl.class);
	@Autowired
	private BizTrainTimeDefDao dao;

	/**
	 * 添加车次定义表
	 * @param trainTimeDefAddVo
	 * @return
	 */
	public Integer addTrainTimeDef(BizTrainTimeDefAddVo trainTimeDefAddVo) {
		return null;
	}

	public Integer editTrainTimeDef(BizTrainTimeDefAddVo trainTimeAddDefVo) {
		return null;
	}

	public Integer deleteTrainTimeDef(Integer trainTimeAddDefId) {
		return null;
	}

	public Page<BizTrainTimeDefAddVo> queryMyTainTimeDefPage(Pageable page, Integer driverId) {
		return null;
	}

	public void setTrainTimeDefStatus(Integer trainTimeAddDefId, Integer status) {

	}

	public BizTrainTimeDef save(BizTrainTimeDef item) {
		return null;
	}

	public void delete(Integer id) {

	}

	public BizTrainTimeDef findOne(Integer id) {
		return null;
	}

	public Page<BizTrainTimeDef> queryPage(Pageable page, Map<String, String[]> searchMap, Map<String, Object> bizMap) {
		return null;
	}
}
