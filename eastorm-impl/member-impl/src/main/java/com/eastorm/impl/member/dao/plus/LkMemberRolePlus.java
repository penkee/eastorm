/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.member.dao.plus;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface LkMemberRolePlus {
	/**
	 * 根据memberId删除记录
	 * @param memberId
	 * add by penkee@163.com
	 */
	void deleteByMemberId(Integer memberId);
}
