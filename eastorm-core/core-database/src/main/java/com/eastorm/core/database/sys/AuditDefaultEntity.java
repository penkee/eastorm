/**
 * DefaultEntity.java
	Description:基本实体类
 */
package com.eastorm.core.database.sys;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**审计基类
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
@MappedSuperclass
public class AuditDefaultEntity extends DefaultEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2372878905781007117L;
	
	protected Date createDate;//@Temporal(TemporalType.DATE) yyyy-MM-dd  
	
	protected Integer createById;
	
	protected Date modifiedDate;
	
	protected Integer modifiedById;
	@Column(length=20)
	protected String modifiedName;
	@Column(length=20)
	protected String createName;
	
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
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
	public Integer getCreateById() {
		return createById;
	}
	public void setCreateById(Integer createById) {
		this.createById = createById;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getModifiedById() {
		return modifiedById;
	}
	public void setModifiedById(Integer modifiedById) {
		this.modifiedById = modifiedById;
	}
	
}
