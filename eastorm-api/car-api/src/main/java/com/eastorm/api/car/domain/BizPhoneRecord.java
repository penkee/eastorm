package com.eastorm.api.car.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;
   /**
    * biz_phone_record 实体类
    */ 


public class BizPhoneRecord extends DefaultEntity {
	private int driverId;
	private String driverMobile;
	private int passengerId;
	private Date createTime;
	public void setDriverId(int driverId){
	this.driverId=driverId;
	}
	public int getDriverId(){
		return driverId;
	}
	public void setDriverMobile(String driverMobile){
	this.driverMobile=driverMobile;
	}
	public String getDriverMobile(){
		return driverMobile;
	}
	public void setPassengerId(int passengerId){
	this.passengerId=passengerId;
	}
	public int getPassengerId(){
		return passengerId;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
}

