package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;

   /**
    * biz_driver 实体类
    */ 


public class BizDriver  extends DefaultEntity {
	private String name;
	private byte sex;
	private String cardNo;
	private String avatarUrl;
	private String mobile;
	private Integer carId;
	private byte serviceScore;
	private int serviceOrders;
	private byte status;
	private byte valid;
	private Date createTime;
	private Date modifyTime;
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setSex(byte sex){
	this.sex=sex;
	}
	public byte getSex(){
		return sex;
	}
	public void setCardNo(String cardNo){
	this.cardNo=cardNo;
	}
	public String getCardNo(){
		return cardNo;
	}
	public void setAvatarUrl(String avatarUrl){
	this.avatarUrl=avatarUrl;
	}
	public String getAvatarUrl(){
		return avatarUrl;
	}
	public void setMobile(String mobile){
	this.mobile=mobile;
	}
	public String getMobile(){
		return mobile;
	}
	public void setCarId(Integer carId){
	this.carId=carId;
	}
	public Integer getCarId(){
		return carId;
	}
	public void setServiceScore(byte serviceScore){
	this.serviceScore=serviceScore;
	}
	public byte getServiceScore(){
		return serviceScore;
	}
	public void setServiceOrders(int serviceOrders){
	this.serviceOrders=serviceOrders;
	}
	public int getServiceOrders(){
		return serviceOrders;
	}
	public void setStatus(byte status){
	this.status=status;
	}
	public byte getStatus(){
		return status;
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

