
package com.eastorm.api.common.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**缓存记录表
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name="sys_cache")
public class SysCache extends DefaultEntity {
	private static final long serialVersionUID = 1L;
	@Column(length=20)
	private String keyname;//键名称
	@Column(length=200)
	private String jpql;//jpql语句
	private Byte resultType;//0-single,1-list,2-map
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public Byte getResultType() {
		return resultType;
	}
	public void setResultType(Byte resultType) {
		this.resultType = resultType;
	}
	public String getJpql() {
		return jpql;
	}
	public void setJpql(String jpql) {
		this.jpql = jpql;
	}
	
}