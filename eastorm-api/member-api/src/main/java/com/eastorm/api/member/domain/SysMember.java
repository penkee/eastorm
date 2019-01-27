/**
 * DyMember.java
	Description:会员表,DyMember是因为Member是mysql的一个关键字
 */
package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.AuditDefaultEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**用户表
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name="sys_member")
public class SysMember extends AuditDefaultEntity {
	private static final long serialVersionUID = 1L;
	
	private byte isValid;//是否有效 ，0-无效， 1-有效
	@Column(length=20)
	private String username;//手机号用户名											//用户名,宽表冗余
	private Integer grade;//0-超级管理员，3-公司管理层，6-内部员工，9-商户  为了扩展
	/*****************fr-key*******************************/
	
	public byte getIsValid() {
		return isValid;
	}
	public void setIsValid(byte isValid) {
		this.isValid = isValid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
