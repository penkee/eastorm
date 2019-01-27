/**
 * DefaultEntity.java
	Description:基本实体类
 */
package com.eastorm.core.database.sys;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-20
 */
@MappedSuperclass
public class DefaultEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	protected Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
