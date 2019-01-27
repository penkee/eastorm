package com.eastorm.api.member.service;

import com.eastorm.api.member.domain.SysMemberInfo;
import com.eastorm.core.base.sys.EastormException;

/**
 * @brief     会员信息
 * @details   详细描述
 * @param
 * @retval    返回值
 * @author    慕容恪
 * @date      2018/8/12
 * @note      变更信息
 * @return
*/
public interface SysMemberInfoService {
	SysMemberInfo findBySysMemberId(Integer sysMemberId);
	/**
	 * 如果有折扣，则更新折扣部分
	 * added by Buke at 2016年4月30日
	 * @throws EastormException
	 */
	void updateUserInfo(Integer sysMemberId, SysMemberInfo memberInfo) throws EastormException;
	/**
	 * 更新积分
	 * added by Buke at 2016年4月30日
	 * @throws EastormException
	 */
	void updateJF(Integer sysMemberId, int score) throws EastormException;
	SysMemberInfo findOne(Integer id);
	SysMemberInfo queryBySysMemberId(Integer userid);
	void save(SysMemberInfo memberInfo);
}
