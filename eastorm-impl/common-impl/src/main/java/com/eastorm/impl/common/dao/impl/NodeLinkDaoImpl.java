/**
 * UserDaoImpl.java
	Description:
 */
package com.eastorm.impl.common.dao.impl;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.NodeLink;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.core.database.sys.dao.DefaultDao;
import com.eastorm.impl.common.dao.plus.NodeLinkPlus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public class NodeLinkDaoImpl extends DefaultDao implements NodeLinkPlus {
	/* (non-Javadoc)
	 * @see com.eastorm.dao.UserDao#getCustomList()
	 */
	@SuppressWarnings("unchecked")
	public List<NodeLink> getCustomList() {
		return defaultEntityManager.queryNamed("menu.getList");
	}

	/* 查询分页
	 * add by penkee@163.com
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<CommType> queryPageByParentId(Pageable page,
											  String orderBy, Integer parentId, Map<String,Object> searchMap){
		StringBuilder  from=new StringBuilder("select m.node from NodeLink m where 1=1 ");
		Map<String, Object> param=new HashMap<String, Object>();
		
		from.append("and m.parent.id=:parentId ");
		param.put("parentId", parentId);
		
		if(!StringFunc.isNull(searchMap.get("name"))){
			from.append("and m.node.name like :name ");
			param.put("name", "%"+searchMap.get("name")+"%");
		}
		if(!StringFunc.isNull(searchMap.get("typeValue"))){
			from.append("and m.node.typeValue like :typeValue ");
			param.put("typeValue", "%"+searchMap.get("typeValue")+"%");
		}
		if(searchMap.containsKey("id")){
				from.append("and m.node.id=:id ");
				param.put("id", searchMap.get("id"));
		}
		return defaultEntityManager.queryPageByMap(page,  from.toString()+orderBy, param);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public void deleteParentAndNode(Integer nodeId) {
		// TODO Auto-generated method stub
		defaultEntityManager.updateNamed("menu.deleteParentAndChild", nodeId);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NodeLink> getListByType(int type) {
		// TODO Auto-generated method stub
		return  (List<NodeLink> ) defaultEntityManager.queryNamed("menu.getListByType",type);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommType> qryListByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return (List<CommType>) defaultEntityManager.queryNamed("menu.qryListByParentId",parentId);
	}

	@Override
	public List<Map<String, Object>> qryBrotherIds(Integer nodeId) {
		// TODO Auto-generated method stub
		String sql="select nodeid from sys_nodelink where parentId=(  select n.parentId from sys_commtype m"
				+" join sys_nodelink n on m.id=n.nodeId where m.comm_type=1 and m.id=?)";
		return defaultEntityManager.queryListNative(sql, nodeId);
	}

	@Override
	public List<Map<String, Object>> qryAreaListByParentId(Integer parentId) {
		String sql="select c.id,c.typeValue 'name' from sys_nodelink lk "
				+" join sys_commtype c on lk.nodeId=c.id where lk.parentId=? and c.attr2='1' and type=1";
		return defaultEntityManager.queryListNative(sql, parentId);
	}

	@Override
	public List<Map<String, Object>> qryWapMenuListByParentId(Integer parentId) {
		String sql="select c.id,c.name,c.typeValue 'url' from sys_nodelink lk"
				+" join sys_commtype c on lk.nodeId=c.id where lk.parentId=? and type=8 and tValue=0 order by c.tKey";
		return defaultEntityManager.queryListNative(sql, parentId);
	}
}
