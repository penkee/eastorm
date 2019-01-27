package com.eastorm.impl.others.wechat;
public class HttpParam {
	private String name;
	private String value;
	
	public HttpParam(String _name,String _value){
		this.name=_name;
		this.value=_value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}