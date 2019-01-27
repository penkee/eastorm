/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.member.dao.impl;

import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.member.dao.plus.LkMemberRolePlus;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class LkMemberRoleDaoImpl extends DefaultDao implements LkMemberRolePlus {

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public void deleteByMemberId(Integer memberId) {
		// TODO Auto-generated method stub
		defaultEntityManager.updateNamed("lkMemberRole.deleteByMemberId", memberId);
	}

}
