/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.member.dao.impl;

import com.eastorm.api.member.domain.SysLogin;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.member.dao.plus.SysLoginPlus;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class SysLoginDaoImpl extends DefaultDao implements SysLoginPlus {
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysLogin> queryListByUsername(String username) {
		return defaultEntityManager.queryNamed("sysLogin.queryListByUsername", username);
	}
}
