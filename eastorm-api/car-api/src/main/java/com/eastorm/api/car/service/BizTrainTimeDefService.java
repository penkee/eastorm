package com.eastorm.api.car.service;


import com.eastorm.api.car.vo.BizTrainTimeDefAddVo;
import com.eastorm.api.car.vo.BizTrainTimeDefAddVo;
import com.eastorm.api.car.vo.BizTrainTimeVo;
import com.eastorm.api.common.service.BaseService;
import com.eastorm.api.car.domain.BizTrainTimeDef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BizTrainTimeDefService extends BaseService<BizTrainTimeDef> {
    /**
     * 添加车次定义表
     * @param trainTimeDefAddVo
     * @return
     */
    Integer addTrainTimeDef(BizTrainTimeDefAddVo trainTimeDefAddVo);
    /**
     * 编辑车次定义表
     * @param trainTimeAddDefVo
     * @return
     */
    Integer editTrainTimeDef(BizTrainTimeDefAddVo trainTimeAddDefVo);
    /**
     * 删除车次定义
     * @param trainTimeAddDefId
     * @return
     */
    Integer deleteTrainTimeDef(Integer trainTimeAddDefId);

    /**
     * 查询我的车次定义表
     * @param driverId
     * @return
     */
    Page<BizTrainTimeDefAddVo> queryMyTainTimeDefPage(Pageable page, Integer driverId);
    /**
     * 设置车次频率状态
     * @param trainTimeAddDefId
     * @param status
     * @return
     */
    void setTrainTimeDefStatus(Integer trainTimeAddDefId, Integer status);
}
