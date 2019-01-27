package com.eastorm.api.member.service;

import com.eastorm.api.common.service.BaseService;
import com.eastorm.api.member.domain.BizPassenger;
import com.eastorm.api.member.vo.BizPassengerAddVo;

public interface BizPassengerService extends BaseService<BizPassenger> {
    /**
     * 插入记录
     * @param passengerAddVo
     * @return
     * add by penkee@163.com
     */
    void save(BizPassengerAddVo passengerAddVo);
}