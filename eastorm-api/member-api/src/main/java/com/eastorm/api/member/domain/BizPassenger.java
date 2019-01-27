package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;
   /**
    * biz_passenger 实体类
    */ 


public class BizPassenger extends DefaultEntity {
	private String mobile;
	private byte valid;
	private Date createTime;
	private Date modifyTime;
	public void setMobile(String mobile){
	this.mobile=mobile;
	}
	public String getMobile(){
		return mobile;
	}
	public void setValid(byte valid){
	this.valid=valid;
	}
	public byte getValid(){
		return valid;
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

