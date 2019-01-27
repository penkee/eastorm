/**
 *NodeLink.java
	Description:节点关联表
 */
package com.eastorm.api.common.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-10-20
 */
@Entity
@Table(name="sys_nodelink")
public class NodeLink extends DefaultEntity {
	private static final long serialVersionUID = 1L;

	private Integer parentId;
	private Integer nodeId;
	@Transient
	private CommType parent;
	@Transient
	private CommType node;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public CommType getParent() {
		return parent;
	}

	public void setParent(CommType parent) {
		this.parent = parent;
	}

	public CommType getNode() {
		return node;
	}

	public void setNode(CommType node) {
		this.node = node;
	}
}
