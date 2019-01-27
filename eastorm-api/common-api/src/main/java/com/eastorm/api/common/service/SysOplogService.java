package com.eastorm.api.common.service;


import com.eastorm.api.common.domain.SysOplog;

public interface SysOplogService extends BaseService<SysOplog> {
    /**
     * 增加日志
     *
     * @param content  内容
     * @param username 操作人
     * @param type     类型 o-订单，p-产品 m-用户  j-积分
     * @param objId    对应type的实际id
     *                 added by Buke at 2016年5月21日
     */
    void addLog(String content, String username, String type, Integer objId);
}
