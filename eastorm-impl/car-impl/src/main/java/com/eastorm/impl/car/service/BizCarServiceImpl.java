package com.eastorm.impl.car.service;

import com.eastorm.api.car.domain.BizCar;
import com.eastorm.api.car.service.BizCarService;
import com.eastorm.api.car.vo.BizCarVo;
import com.eastorm.api.member.domain.BizDriver;
import com.eastorm.api.member.service.BizDriverService;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.impl.car.dao.BizCarDao;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BizCarServiceImpl implements BizCarService {
	private static Logger  logger=Logger.getLogger(BizCarServiceImpl.class);
	@Autowired
	private BizCarDao bizCarDao;
	@Autowired
	private BizDriverService bizDriverService;

	public BizCar save(BizCar item) {
		return bizCarDao.save(item);
	}

	public void delete(Integer id) {
		bizCarDao.delete(id);
	}

	public BizCar findOne(Integer id) {
		return bizCarDao.findOne(id);
	}

	public Page<BizCar> queryPage(Pageable page, Map<String, String[]> searchMap, Map<String, Object> bizMap) {
		return null;
	}
	/**
	 * 司机录入汽车信息
	 *
	 * 如果不存在则新增，存在修改即可
	 * @retrun carId
	 */
	public Integer editCarInfo(Integer driverId, BizCarVo carVo) {
		StringFunc.checkNull(driverId,"driverId");
		StringFunc.checkNull(carVo,"driverId");

        BizDriver driver=bizDriverService.findOne(driverId);

        if(driver==null){
            throw new EastormException(Const.FR_FAILTURE,"该司机不存在");
        }

        BizCar car=new BizCar();
        BeanUtils.copyProperties(carVo,car);

        if(driver.getCarId()==null){
            //新增
            car.setId(null);
            car.setValid((byte)1);
        }else{
            //修改
            car.setId(carVo.getCarId());
        }

        bizCarDao.save(car);
		return car.getId();
	}

    /**
     * 司机查询汽车信息
     *
     * @throws EastormException
     * @retrun carId
     */
	public BizCarVo queryCarInfo(Integer driverId) {
        BizDriver driver=bizDriverService.findOne(driverId);

        if(driver==null){
            throw new EastormException(Const.FR_FAILTURE,"该司机不存在");
        }
        BizCar car= bizCarDao.findOne(driver.getCarId());

        BizCarVo carVo=new BizCarVo();
        BeanUtils.copyProperties(car,carVo);
        carVo.setCarId(car.getId());

	    return carVo;
	}
}
