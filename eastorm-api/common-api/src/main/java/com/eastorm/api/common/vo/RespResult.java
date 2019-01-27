package com.eastorm.api.common.vo;

import com.eastorm.core.base.utils.Const;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("返回对象")
public class RespResult<T> {
	@ApiModelProperty("0000-成功")
	private String errCode;
	@ApiModelProperty("错误信息")
	private String errMsg;
	@ApiModelProperty("业务数据")
	private T data;
	
	public static <T> RespResult<T> returnSucc(T data){
		RespResult<T> resp=new RespResult<T>();
		resp.setData(data);
		resp.setErrCode(Const.FR_SUCCESS);
		return resp;
	}
	
	public static <T> RespResult<T> returnFail(String errCode,String errMsg){
		RespResult<T> resp=new RespResult<T>();
		resp.setErrCode(errCode);
		resp.setErrMsg(errMsg);
		return resp;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
