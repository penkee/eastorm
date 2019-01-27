package com.eastorm.api.member.vo;

public class BizPassengerAddVo {
    protected Integer id;
    private String mobile;
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    public String getMobile(){
        return mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
