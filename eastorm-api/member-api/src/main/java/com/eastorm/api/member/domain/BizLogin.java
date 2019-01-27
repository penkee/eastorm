package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;
   /**
    * biz_login 实体类
    */ 


public class BizLogin  extends DefaultEntity {
	private int driverId;
	private int passengerId;
	private String loginOpenId;
	private Byte platform;
	private String password;
	private String avatarUrl;
	private String nickName;
	private Date createTime;
	private Date modifyTime;
	public void setDriverId(int driverId){
	this.driverId=driverId;
	}
	public int getDriverId(){
		return driverId;
	}
	public void setPassengerId(int passengerId){
	this.passengerId=passengerId;
	}
	public int getPassengerId(){
		return passengerId;
	}
	public void setLoginOpenId(String loginOpenId){
	this.loginOpenId=loginOpenId;
	}
	public String getLoginOpenId(){
		return loginOpenId;
	}
	public void setPlatform(Byte platform){
	this.platform=platform;
	}
	public Byte getPlatform(){
		return platform;
	}
	public void setPassword(String password){
	this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setAvatarUrl(String avatarUrl){
	this.avatarUrl=avatarUrl;
	}
	public String getAvatarUrl(){
		return avatarUrl;
	}
	public void setNickName(String nickName){
	this.nickName=nickName;
	}
	public String getNickName(){
		return nickName;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setModifyTime(Date modifyTime){
	this.modifyTime=modifyTime;
	}
	public Date getModifyTime(){
		return modifyTime;
	}
}

