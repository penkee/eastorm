/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.member.dao.plus;

import com.eastorm.api.member.domain.SysMemberInfo;
import com.eastorm.core.base.sys.EastormException;

import java.util.List;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface SysMemberInfoPlus {
	/**
	 * 折扣次数设置
	 * added by Buke at 2016年4月30日
	 * @throws EastormException
	 */
	void updateCanDiscount(Integer sysMemberId, int num) throws EastormException;
	/**
	 * updateJF(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param sysMemberId
	 * @param score
	 * @return void
	*/
	public void updateScore(Integer sysMemberId, int score) throws EastormException;
	/**
	 * 查找
	 * @param userid
	 * @return 
	 * added by Buke at 2016年7月3日
	 */
	List<SysMemberInfo> queryBySysMemberId(Integer userid);
}
