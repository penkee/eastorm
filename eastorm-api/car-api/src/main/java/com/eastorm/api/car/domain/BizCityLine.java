package com.eastorm.api.car.domain;

import com.eastorm.core.database.sys.DefaultEntity;

/**
    * biz_city_line 实体类
    */ 


public class BizCityLine extends DefaultEntity {
	private int firstCityId;
	private String firstCityName;
	private int secondCityId;
	private String secondCityName;
	private Byte status;
	private Byte valid;
	public void setFirstCityId(int firstCityId){
	this.firstCityId=firstCityId;
	}
	public int getFirstCityId(){
		return firstCityId;
	}
	public void setFirstCityName(String firstCityName){
	this.firstCityName=firstCityName;
	}
	public String getFirstCityName(){
		return firstCityName;
	}
	public void setSecondCityId(int secondCityId){
	this.secondCityId=secondCityId;
	}
	public int getSecondCityId(){
		return secondCityId;
	}
	public void setSecondCityName(String secondCityName){
	this.secondCityName=secondCityName;
	}
	public String getSecondCityName(){
		return secondCityName;
	}
	public void setStatus(Byte status){
	this.status=status;
	}
	public Byte getStatus(){
		return status;
	}
	public void setValid(Byte valid){
	this.valid=valid;
	}
	public Byte getValid(){
		return valid;
	}
}

