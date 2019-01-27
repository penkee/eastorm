package com.eastorm.api.car.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;

   /**
    * biz_car 实体类
    */ 


public class BizCar  extends DefaultEntity {
	private String carImageUrl;
	private int brandId;
	private String brandName;
	private String model;
	private String color;
	private byte fullLoad;
	private String carNo;
	private byte valid;
	private Date createTime;
	private Date modifyTime;
	public void setCarImageUrl(String carImageUrl){
	this.carImageUrl=carImageUrl;
	}
	public String getCarImageUrl(){
		return carImageUrl;
	}
	public void setBrandId(int brandId){
	this.brandId=brandId;
	}
	public int getBrandId(){
		return brandId;
	}
	public void setBrandName(String brandName){
	this.brandName=brandName;
	}
	public String getBrandName(){
		return brandName;
	}
	public void setModel(String model){
	this.model=model;
	}
	public String getModel(){
		return model;
	}
	public void setColor(String color){
	this.color=color;
	}
	public String getColor(){
		return color;
	}
	public void setFullLoad(byte fullLoad){
	this.fullLoad=fullLoad;
	}
	public byte getFullLoad(){
		return fullLoad;
	}
	public void setCarNo(String carNo){
	this.carNo=carNo;
	}
	public String getCarNo(){
		return carNo;
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

