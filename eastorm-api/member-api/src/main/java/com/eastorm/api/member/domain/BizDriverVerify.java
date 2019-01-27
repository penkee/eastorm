package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;

/**
    * biz_driver_verify 实体类
    */ 


public class BizDriverVerify  extends DefaultEntity {
	private int driverId;
	private int zhimaScore;
	private int criminalCount;
	private byte education;
	private byte credit;
	private String driverCardUrl;
	public void setDriverId(int driverId){
	this.driverId=driverId;
	}
	public int getDriverId(){
		return driverId;
	}
	public void setZhimaScore(int zhimaScore){
	this.zhimaScore=zhimaScore;
	}
	public int getZhimaScore(){
		return zhimaScore;
	}
	public void setCriminalCount(int criminalCount){
	this.criminalCount=criminalCount;
	}
	public int getCriminalCount(){
		return criminalCount;
	}
	public void setEducation(byte education){
	this.education=education;
	}
	public byte getEducation(){
		return education;
	}
	public void setCredit(byte credit){
	this.credit=credit;
	}
	public byte getCredit(){
		return credit;
	}
	public void setDriverCardUrl(String driverCardUrl){
	this.driverCardUrl=driverCardUrl;
	}
	public String getDriverCardUrl(){
		return driverCardUrl;
	}
}

