/**
 * UserDaoPlus.java
	Description:
 */
package com.eastorm.impl.common.dao.plus;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.NodeLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**增加额外的方法
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
public interface NodeLinkPlus {
	List<NodeLink> getListByType(int type);
	/**
	 * 分页
	 * @param page
	 * @param orderBy
	 * @param map
	 * @return
	 * add by penkee@163.com
	 */
	Page<CommType> queryPageByParentId(Pageable page,
									   String orderBy, Integer parentId, Map<String,Object> searchMap) ;
	/**
	 *  所有孩子
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<CommType> qryListByParentId(Integer parentId);
	/**
	 * 为了删除menuitem数据，有外键约束
	 * @param menuItemId
	 * add by penkee@163.com
	 */
	void deleteParentAndNode(Integer nodeId);
	
	/**
	 * 查询所有兄弟，包括自己
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> qryBrotherIds(Integer nodeId);
	
	/**
	 *  所有parentId的区，防止外部遍历
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> qryAreaListByParentId(Integer parentId);
	/**
	 * wap的菜单
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> qryWapMenuListByParentId(Integer parentId);
}
