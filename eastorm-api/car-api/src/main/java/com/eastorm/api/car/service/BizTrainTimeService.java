package com.eastorm.api.car.service;


import com.eastorm.api.car.vo.BizTrainTimeAddVo;
import com.eastorm.api.car.vo.BizTrainTimeVo;
import com.eastorm.api.common.service.BaseService;
import com.eastorm.api.car.domain.BizTrainTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BizTrainTimeService extends BaseService<BizTrainTime> {

    /**
     * 添加车次表
     * 判断重复，时间交叉不能有2个车次
     * @param trainTimeAddVo
     * @return
     */
    Integer addTrainTime(BizTrainTimeAddVo trainTimeAddVo);
    /**
     * 编辑车次表
     * 判断重复，时间交叉不能有2个车次
     * @param trainTimeAddVo
     * @return
     */
    Integer editTrainTime(BizTrainTimeAddVo trainTimeAddVo);

    /**
     * 查询我的车次表
     * @param driverId
     * @return
     */
    Page<BizTrainTimeAddVo> queryMyTainTimePage(Pageable page, Integer driverId);
    /**
     * 乘客搜索车次表
     * @param driverId
     * @return
     */
    Page<BizTrainTimeVo> searchTrainTimeList(Pageable page, Integer driverId);
}
