/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.member.dao.impl;

import com.eastorm.api.member.domain.SysRole;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.member.dao.plus.SysRolePlus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class SysRoleDaoImpl extends DefaultDao implements SysRolePlus {

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysRole> queryListByMemberId(Integer memberId) {
		// TODO Auto-generated method stub
		return defaultEntityManager.queryNamed("sysRole.queryListByMemberId", memberId);
	}
	

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Page<SysRole> queryPage(Pageable page, Map<String, Object> searchMap) {
		StringBuilder  jpql=new StringBuilder("from SysRole m where 1=1 ");
		Map<String, Object> param=new HashMap<String, Object>();
		
		if(searchMap.get("name")!=null){
			jpql.append("and m.name like :name ");
				param.put("name","%"+searchMap.get("name")+"%");
		}
		if(searchMap.get("id")!=null){
			jpql.append("and m.id = :id ");
			param.put("id", searchMap.get("id"));
		}
		return defaultEntityManager.queryPageByMap(page, jpql.toString(), param);
	}
}
