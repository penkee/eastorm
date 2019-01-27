package com.eastorm.api.car.vo;

import java.util.Date;

/**
 * biz_train_time 实体类
 */


public class BizTrainTimeAddVo {
 private int trainTimeId;
 private int driverId;
 private int stock;
 private int startCityId;
 private String startCityName;
 private int endCityId;
 private String endCityName;
 private Date departureTime;

 public int getTrainTimeId() {
  return trainTimeId;
 }

 public void setTrainTimeId(int trainTimeId) {
  this.trainTimeId = trainTimeId;
 }

 public int getDriverId() {
  return driverId;
 }

 public void setDriverId(int driverId) {
  this.driverId = driverId;
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

 public Date getDepartureTime() {
  return departureTime;
 }

 public void setDepartureTime(Date departureTime) {
  this.departureTime = departureTime;
 }
}

