package com.eastorm.api.member.service;

import com.eastorm.api.common.service.BaseService;
import com.eastorm.api.member.domain.BizLogin;

public interface BizLoginService extends BaseService<BizLogin> {
    /**
     * 根据用户名查找一条记录
     * 没有则插入一条记录
     * @param openId
     * @param platform
     * @return
     * add by penkee@163.com
     */
    BizLogin findOneByLoginOpenId(String openId,Byte platform);
}
