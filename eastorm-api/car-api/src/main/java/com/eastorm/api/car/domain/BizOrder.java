package com.eastorm.api.car.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;
   /**
    * biz_order 实体类
    */ 


public class BizOrder extends DefaultEntity {
	private String orderNo;
	private int carId;
	private String carNo;
	private int driverId;
	private int passengerId;
	private double unitPrice;
	private byte person;
	private double discount;
	private double insuranceAmount;
	private double orderTotal;
	private double paidAmount;
	private byte payStatus;
	private byte payType;
	private byte status;
	private String cancelReason;
	private Date cancelTime;
	private int trainTimeId;
	private int startCityId;
	private String startCityName;
	private int endCityId;
	private String endCityName;
	private Date createTime;
	private Date modifyTime;
	private int modifiedById;
	private byte valid;
	public void setOrderNo(String orderNo){
	this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setCarId(int carId){
	this.carId=carId;
	}
	public int getCarId(){
		return carId;
	}
	public void setCarNo(String carNo){
	this.carNo=carNo;
	}
	public String getCarNo(){
		return carNo;
	}
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
	public void setUnitPrice(double unitPrice){
	this.unitPrice=unitPrice;
	}
	public double getUnitPrice(){
		return unitPrice;
	}
	public void setPerson(byte person){
	this.person=person;
	}
	public byte getPerson(){
		return person;
	}
	public void setDiscount(double discount){
	this.discount=discount;
	}
	public double getDiscount(){
		return discount;
	}
	public void setInsuranceAmount(double insuranceAmount){
	this.insuranceAmount=insuranceAmount;
	}
	public double getInsuranceAmount(){
		return insuranceAmount;
	}
	public void setOrderTotal(double orderTotal){
	this.orderTotal=orderTotal;
	}
	public double getOrderTotal(){
		return orderTotal;
	}
	public void setPaidAmount(double paidAmount){
	this.paidAmount=paidAmount;
	}
	public double getPaidAmount(){
		return paidAmount;
	}
	public void setPayStatus(byte payStatus){
	this.payStatus=payStatus;
	}
	public byte getPayStatus(){
		return payStatus;
	}
	public void setPayType(byte payType){
	this.payType=payType;
	}
	public byte getPayType(){
		return payType;
	}
	public void setStatus(byte status){
	this.status=status;
	}
	public byte getStatus(){
		return status;
	}
	public void setCancelReason(String cancelReason){
	this.cancelReason=cancelReason;
	}
	public String getCancelReason(){
		return cancelReason;
	}
	public void setCancelTime(Date cancelTime){
	this.cancelTime=cancelTime;
	}
	public Date getCancelTime(){
		return cancelTime;
	}
	public void setTrainTimeId(int trainTimeId){
	this.trainTimeId=trainTimeId;
	}
	public int getTrainTimeId(){
		return trainTimeId;
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
	public void setModifiedById(int modifiedById){
	this.modifiedById=modifiedById;
	}
	public int getModifiedById(){
		return modifiedById;
	}
	public void setValid(byte valid){
	this.valid=valid;
	}
	public byte getValid(){
		return valid;
	}
}

