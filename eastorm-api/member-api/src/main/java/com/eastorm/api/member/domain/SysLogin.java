/**
 * DyMember.java
	Description:会员表,DyMember是因为Member是mysql的一个关键字
 */
package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name="sys_login")
public class SysLogin extends DefaultEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(length=100)
	private String username;//用户名：wechat_{openid}或者手机号，邮箱
	@Column(length=200)
	private String email;//邮箱用户名
	@Column(length=11)
	private String mobile;//手机号用户名
	@Column(length=50)
	private String password;//密码，加密
	private Date lastLoginTime;//上次登录时间
	@Column(length=10)
	private String checkcode;
	private Integer memberId;
	/*****************fr-key*******************************/
	@Transient
	private SysMember member;

	public SysMember getMember() {
		return member;
	}
	public void setMember(SysMember member) {
		this.member = member;
	}
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
