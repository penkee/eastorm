/**
 * Role.java
	Description:角色表
 */
package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name="sys_role")
public class SysRole extends DefaultEntity {
	private static final long serialVersionUID = 1L;
	@Column(length=10)
	private String name;																//角色名称		
	@Column(length=2000)
	private String authUrls;															//可通过的url集合，存menuItemid

	public String getAuthUrls() {
		return authUrls;
	}
	public void setAuthUrls(String authUrls) {
		this.authUrls = authUrls;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
