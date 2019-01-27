/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.member.dao.plus;


import com.eastorm.api.member.domain.SysLogin;

import java.util.List;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface SysLoginPlus {
	/**
	 * 根据用户名查找
	 * @param username
	 * @return
	 * add by penkee@163.com
	 */
	List<SysLogin> queryListByUsername(String username);
}
