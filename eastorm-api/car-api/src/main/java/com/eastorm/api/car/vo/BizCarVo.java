package com.eastorm.api.car.vo;


/**
 * biz_car 实体类
 */
public class BizCarVo {
    private Integer carId;
    private String carImageUrl;
    private int brandId;
    private String brandName;
    private String model;
    private String color;
    private byte fullLoad;
    private String carNo;


    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setFullLoad(byte fullLoad) {
        this.fullLoad = fullLoad;
    }

    public byte getFullLoad() {
        return fullLoad;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarNo() {
        return carNo;
    }
}

