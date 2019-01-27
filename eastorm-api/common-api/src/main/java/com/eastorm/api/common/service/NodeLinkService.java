package com.eastorm.api.common.service;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.NodeLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface NodeLinkService {
	NodeLink save(NodeLink nodeLink);
	/**
	 * 根据type获得list
	 * @param type
	 * @return
	 * add by penkee@163.com
	 */
	List<NodeLink> getListByType(int type);
    /**
     * 根据父节点，查询一级孩子的列表
     * @param page
     * @param orderBy
     * @param parentId
     * @param searchMap
     * @return
     * add by penkee@163.com
     */
	Page<CommType> queryPageByParentId(Pageable page, String orderBy, Integer parentId, Map<String,Object> searchMap);

	/**插入nodeLink和node事务处理
	 * @param nodeLink
	 * @param node
	 * add by penkee@163.com
	 */
	void saveAndNode(NodeLink nodeLink, CommType node);
	/**
	 * 根据父节点查询孩子数量
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	Integer qryListCountByParentId(Integer parentId);
	/**
	 * 根据父节点查询孩子数量
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<CommType> qryListByParentId(Integer parentId);

	Integer  findParentNodeId(Integer nodeId);
	
	/**
	 *  所有parentId的区，防止外部遍历
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String, Object>> qryAreaListByParentId(Integer parentId);

	/**
	 * 为了删除menuitem数据，有外键约束
	 * @param nodeId
	 * add by penkee@163.com
	 */
	void deleteParentAndNode(Integer nodeId);
	/**
	 * wap的菜单
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	List<Map<String,Object>> qryWapMenuListByParentId(Integer parentId);
}
