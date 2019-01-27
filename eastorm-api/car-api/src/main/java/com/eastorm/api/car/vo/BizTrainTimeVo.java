package com.eastorm.api.car.vo;

import com.eastorm.api.car.domain.BizCar;
import com.eastorm.api.member.domain.BizDriver;

import java.util.Date;

/**
 * biz_train_time 实体类
 */


public class BizTrainTimeVo {
 private int trainTimeId;
 private int defId;
 private BizDriver driver;
 private BizCar car;
 private int stock;
 private int startCityId;
 private String startCityName;
 private int endCityId;
 private String endCityName;
 private String departureTime;
 private String createTime;

 public int getTrainTimeId() {
  return trainTimeId;
 }

 public void setTrainTimeId(int trainTimeId) {
  this.trainTimeId = trainTimeId;
 }

 public int getDefId() {
  return defId;
 }

 public void setDefId(int defId) {
  this.defId = defId;
 }

 public BizDriver getDriver() {
  return driver;
 }

 public void setDriver(BizDriver driver) {
  this.driver = driver;
 }

 public BizCar getCar() {
  return car;
 }

 public void setCar(BizCar car) {
  this.car = car;
 }

 public int getStock() {
  return stock;
 }

 public void setStock(int stock) {
  this.stock = stock;
 }

 public int getStartCityId() {
  return startCityId;
 }

 public void setStartCityId(int startCityId) {
  this.startCityId = startCityId;
 }

 public String getStartCityName() {
  return startCityName;
 }

 public void setStartCityName(String startCityName) {
  this.startCityName = startCityName;
 }

 public int getEndCityId() {
  return endCityId;
 }

 public void setEndCityId(int endCityId) {
  this.endCityId = endCityId;
 }

 public String getEndCityName() {
  return endCityName;
 }

 public void setEndCityName(String endCityName) {
  this.endCityName = endCityName;
 }

 public String getDepartureTime() {
  return departureTime;
 }

 public void setDepartureTime(String departureTime) {
  this.departureTime = departureTime;
 }

 public String getCreateTime() {
  return createTime;
 }

 public void setCreateTime(String createTime) {
  this.createTime = createTime;
 }
}

