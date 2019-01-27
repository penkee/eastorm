/**
 * DyMember.java
	Description:会员表,DyMember是因为Member是mysql的一个关键字
 */
package com.eastorm.api.member.domain;


import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**用户表
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name="lk_member_role")
public class LkMemberRole extends DefaultEntity {
	private static final Long serialVersionUID = 1L;

	/*****************fr-key*******************************/
	private Integer sysMemberId;
	private Integer roleId;
	@Transient
	private SysMember sysMember;
	@Transient
	private SysRole role;

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public Integer getSysMemberId() {
		return sysMemberId;
	}
	public void setSysMemberId(Integer sysMemberId) {
		this.sysMemberId = sysMemberId;
	}
	public SysMember getSysMember() {
		return sysMember;
	}
	public void setSysMember(SysMember sysMember) {
		this.sysMember = sysMember;
	}
}
