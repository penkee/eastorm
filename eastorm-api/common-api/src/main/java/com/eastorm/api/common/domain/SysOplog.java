/**
 *MenuItem.java
	Description:栏目菜单单项表
 */
package com.eastorm.api.common.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/** typeValue，  操作日志  ,name-操作人，typeKey-类型 p-产品，o-订单，tKey=对应id,tValue-操作时间
 * @author Pengkun (penkee@163.com)
 *	2013-10-20
 */
@Entity
@Table(name="sys_oplog")
public class SysOplog extends DefaultEntity {
	private static final long serialVersionUID = 1L;
	@Column(length=100)
	private String content;//日志
	@Column(length=20)
	private String createName;//创建人
	private Date createDate;
	private char type;//p-产品，o-订单
	private Integer objId;//对象id
	
	public Integer getObjId() {
		return objId;
	}
	public void setObjId(Integer objId) {
		this.objId = objId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
}
