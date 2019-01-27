package com.eastorm.api.car.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;
   /**
    * biz_train_time 实体类
    */ 


public class BizTrainTime extends DefaultEntity {
	private int defId;
	private int driverId;
	private String driverName;
	private String driverMobile;
	private byte driverSex;
	private int stock;
	private int startCityId;
	private String startCityName;
	private int endCityId;
	private String endCityName;
	private Date departureTime;
	private Date createTime;
	private byte valid;

   public int getStock() {
	   return stock;
   }

   public void setStock(int stock) {
	   this.stock = stock;
   }

	public void setDefId(int defId){
	this.defId=defId;
	}
	public int getDefId(){
		return defId;
	}
	public void setDriverId(int driverId){
	this.driverId=driverId;
	}
	public int getDriverId(){
		return driverId;
	}
	public void setDriverName(String driverName){
	this.driverName=driverName;
	}
	public String getDriverName(){
		return driverName;
	}
	public void setDriverMobile(String driverMobile){
	this.driverMobile=driverMobile;
	}
	public String getDriverMobile(){
		return driverMobile;
	}
	public void setDriverSex(byte driverSex){
	this.driverSex=driverSex;
	}
	public byte getDriverSex(){
		return driverSex;
	}
	public void setStartCityId(int startCityId){
	this.startCityId=startCityId;
	}
	public int getStartCityId(){
		return startCityId;
	}
	public void setStartCityName(String startCityName){
	this.startCityName=startCityName;
	}
	public String getStartCityName(){
		return startCityName;
	}
	public void setEndCityId(int endCityId){
	this.endCityId=endCityId;
	}
	public int getEndCityId(){
		return endCityId;
	}
	public void setEndCityName(String endCityName){
	this.endCityName=endCityName;
	}
	public String getEndCityName(){
		return endCityName;
	}
	public void setDepartureTime(Date departureTime){
	this.departureTime=departureTime;
	}
	public Date getDepartureTime(){
		return departureTime;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setValid(byte valid){
	this.valid=valid;
	}
	public byte getValid(){
		return valid;
	}
}

