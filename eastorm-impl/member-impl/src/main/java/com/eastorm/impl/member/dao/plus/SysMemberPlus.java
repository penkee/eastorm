/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.member.dao.plus;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.member.domain.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface SysMemberPlus {
	/**
	 * 查询role
	 * @return
	 * add by penkee@163.com
	 */
	List<SysRole> queryRole(Integer memberId);
	
	/**
	 * 查询列表
	 * @return
	 * add by penkee@163.com
	 */
	Page<Map<String,Object>> queryPage(Pageable page, Map<String,String[]> searchMap);
	
	List<CommType> queryListByUsername(String username) ;
	/**
	 * 导出
	 * @param pageRequest
	 * @param parameterMap
	 * @return 
	 * added by Buke at 2016年6月28日
	 */
	Page<Map<String, Object>> queryBackExport(PageRequest pageRequest,
			Map<String, String[]> parameterMap);
}
