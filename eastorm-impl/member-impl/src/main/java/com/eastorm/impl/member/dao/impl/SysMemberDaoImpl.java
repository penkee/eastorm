/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.member.dao.impl;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.member.domain.SysRole;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.member.dao.plus.SysMemberPlus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class SysMemberDaoImpl extends DefaultDao implements SysMemberPlus {

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysRole> queryRole(Integer memberId) {
		// TODO Auto-generated method stub
		return defaultEntityManager.queryNamed("sysMember.queryRole", memberId);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Page<Map<String,Object>> queryPage(Pageable page,
			Map<String, String[]> searchMap) {
		Map<String, Object> param=new HashMap<String, Object>();
		
		StringBuilder sql=new StringBuilder("select m.id,m.createName,m.createDate,m.username,m.grade,m.isValid,minfo.candiscount,minfo.nickname,cd.id 'compId',cd.`name` 'compName',cd.discountCode from sys_member m ");
		sql.append(" left join sys_member_info minfo on m.id=minfo.sysMemberId");
		sql.append(" left join biz_companydisct cd on minfo.companydisctId=cd.id where 1=1");
		setSearchParam(searchMap, sql, param);//拼where条件
		sql.append("  order by m.id desc");
		return super.defaultEntityManager.queryPageMapNativeByMap(page, sql.toString(), param);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<CommType> queryListByUsername(String username) {
		// TODO Auto-generated method stub
		return defaultEntityManager.queryNamed("sysMember.queryListByUsername","%" +username+"%");
	}

	@Override
	public Page<Map<String, Object>> queryBackExport(PageRequest pageRequest,
			Map<String, String[]> parameterMap) {
		Map<String, Object> bizmap=new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder("select m.id,m.createName,m.createDate,m.username,m.grade,m.isValid,minfo.candiscount,minfo.nickname,cd.id 'compId',cd.`name` 'compName' from sys_member m ");
		sql.append(" left join sys_member_info minfo on m.id=minfo.sysMemberId");
		sql.append(" left join biz_companydisct cd on minfo.companydisctId=cd.id where 1=1");
		setSearchParam(parameterMap, sql, bizmap);//拼where条件
		sql.append("  order by m.id desc");
		return super.defaultEntityManager.queryPageMapNativeByMap(pageRequest, null,sql.toString(), bizmap);
	}

}
