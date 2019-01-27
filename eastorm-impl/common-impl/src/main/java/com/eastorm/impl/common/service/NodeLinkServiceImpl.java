package com.eastorm.impl.common.service;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.NodeLink;
import com.eastorm.api.common.service.NodeLinkService;
import com.eastorm.impl.common.dao.CommTypeDao;
import com.eastorm.impl.common.dao.NodeLinkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NodeLinkServiceImpl   implements NodeLinkService {
	@Autowired
	private NodeLinkDao nodeLinkDao;
	@Autowired
	private CommTypeDao commTypeDao;
	/* (non-Javadoc)
	 * @see com.eastorm.service.MemberService#save(com.eastorm.service.MemberService)
	 */
	@Override
	public NodeLink save(NodeLink nodeLink) {
		// TODO Auto-generated method stub
		return nodeLinkDao.save(nodeLink);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<NodeLink> getListByType(int type) {
		// TODO Auto-generated method stub
		return nodeLinkDao.getListByType(type);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Page<CommType> queryPageByParentId(Pageable page,
											  String orderBy, Integer parentId, Map<String,Object> searchMap) {
		return nodeLinkDao.queryPageByParentId(page, orderBy,parentId, searchMap);
	}
	@Override
	public void saveAndNode(NodeLink nodeLink, CommType node) {
			commTypeDao.save(node);
			nodeLink.setNodeId(node.getId());
			nodeLinkDao.save(nodeLink);
	}

	/* 根据父节点查询孩子数量
	 * add by penkee@163.com
	 */
	@Override
	public Integer qryListCountByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return nodeLinkDao.countByParentId(parentId);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<CommType> qryListByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return nodeLinkDao.qryListByParentId(parentId);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Integer findParentNodeId(Integer nodeId) {
		// TODO Auto-generated method stub
		List<NodeLink> list=nodeLinkDao.findByNodeId(nodeId);
		return list.size()>0?list.get(0).getParentId():null;
	}

	@Override
	public List<Map<String, Object>> qryAreaListByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return nodeLinkDao.qryAreaListByParentId(parentId);
	}

	/**
	 * 为了删除menuitem数据，有外键约束
	 *
	 * @param nodeId add by penkee@163.com
	 */
	@Override
	public void deleteParentAndNode(Integer nodeId) {
		nodeLinkDao.deleteParentAndNode(nodeId);
	}

	/**
	 * wap的菜单
	 * @param parentId
	 * @return
	 * add by penkee@163.com
	 */
	@Override
	public List<Map<String, Object>> qryWapMenuListByParentId(Integer parentId) {
		return nodeLinkDao.qryWapMenuListByParentId(parentId);
	}

}
