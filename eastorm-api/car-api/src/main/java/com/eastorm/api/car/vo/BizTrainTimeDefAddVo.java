package com.eastorm.api.car.vo;

import com.eastorm.core.database.sys.DefaultEntity;

import java.util.Date;

/**
 * biz_train_time_def 实体类
 */


public class BizTrainTimeDefAddVo extends DefaultEntity {
 private int driverId;
 private int startCityId;
 private int endCityId;
 private Date departureTime;
 private int dayInWeek;
 private int daysEvery;
 public void setDriverId(int driverId){
 this.driverId=driverId;
 }
 public int getDriverId(){
     return driverId;
 }
 public void setStartCityId(int startCityId){
 this.startCityId=startCityId;
 }
 public int getStartCityId(){
     return startCityId;
 }
 public void setEndCityId(int endCityId){
 this.endCityId=endCityId;
 }
 public int getEndCityId(){
     return endCityId;
 }
 public void setDepartureTime(Date departureTime){
 this.departureTime=departureTime;
 }
 public Date getDepartureTime(){
     return departureTime;
 }
 public void setDayInWeek(int dayInWeek){
 this.dayInWeek=dayInWeek;
 }
 public int getDayInWeek(){
     return dayInWeek;
 }
 public void setDaysEvery(int daysEvery){
 this.daysEvery=daysEvery;
 }
 public int getDaysEvery(){
     return daysEvery;
 }
}

