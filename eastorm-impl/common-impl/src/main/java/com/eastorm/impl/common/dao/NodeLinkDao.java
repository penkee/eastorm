/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.common.dao;


import com.eastorm.api.common.domain.NodeLink;
import com.eastorm.impl.common.dao.plus.NodeLinkPlus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface NodeLinkDao extends JpaRepository<NodeLink, Integer>,NodeLinkPlus {
	List<NodeLink> findByNodeId(Integer nodeId);
	Integer countByParentId(Integer parentId);
}
