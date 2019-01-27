/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.member.dao.impl;

import com.eastorm.api.member.domain.SysMemberInfo;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.member.dao.plus.SysMemberInfoPlus;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class SysMemberInfoDaoImpl extends DefaultDao implements SysMemberInfoPlus {
	@Override
	public void updateCanDiscount(Integer sysMemberId, int num) throws EastormException {
		super.defaultEntityManager.updateNamed("sysMemberInfo.updateCanDiscount", num,sysMemberId);
	}

	/* (non-Javadoc)
	 * @see SysMemberInfoPlus#updateJF(java.lang.Long, int)
	 */
	@Override
	public void updateScore(Integer sysMemberId, int score) throws EastormException {
		// TODO Auto-generated method stub
		super.defaultEntityManager.updateNamed("sysMemberInfo.updateScore", score,sysMemberId);
	}

	@Override
	public List<SysMemberInfo> queryBySysMemberId(Integer userid) {
		return defaultEntityManager.queryNamed("sysMemberInfo.queryBySysMemberId", userid);
	}
}
