package com.eastorm.api.car.service;


import com.eastorm.api.car.vo.BizCarVo;
import com.eastorm.api.common.service.BaseService;
import com.eastorm.api.car.domain.BizCar;
import com.eastorm.core.base.sys.EastormException;

public interface BizCarService extends BaseService<BizCar> {
    /**
     * 司机录入汽车信息
     *
     * 如果不存在则新增，存在修改即可
     * @retrun carId
     */
    Integer editCarInfo(Integer driverId, BizCarVo carVo);
    /**
     * 司机查询汽车信息
     *
     * @throws EastormException
     * @retrun carId
     */
    BizCarVo queryCarInfo(Integer driverId);
}