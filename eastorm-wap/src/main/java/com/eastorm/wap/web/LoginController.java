/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.wap.web;

import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import com.eastorm.api.common.vo.RespResult;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController{
	private static Logger  logger=Logger.getLogger(LoginController.class);
	
	/**
	 * 图片拼图验证码
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "登录", notes = "图片滑动")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token",dataType = "String",required = true,value = "图片滑动后的token",paramType = "query")
	})
	public RespResult<String> login(String token){


/**构造入参为appId和appSecret
 * appId和前端验证码的appId保持一致，appId可公开
 * appSecret为秘钥，请勿公开
 * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟**/
		try {
			String appId = "05e7ae43da213a470173fc47f7774d5e";
			String appSecret = "f830bb0622f626cd1ed850e51fdc43e1";
			CaptchaClient captchaClient = new CaptchaClient(appId,appSecret);
//captchaClient.setCaptchaUrl(captchaUrl);
//特殊情况需要额外指定服务器,可以在这个指定，默认情况下不需要设置
			CaptchaResponse response = captchaClient.verifyToken(token);
			System.out.println(response.getCaptchaStatus());
//确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
			if (response.getResult()) {
                /**token验证通过，继续其他流程**/
				return RespResult.returnSucc("验证成功");
            } else {
                /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
				return RespResult.returnSucc("验证失败");
            }
		} catch (Exception e) {

		}
		return RespResult.returnSucc("验证失败");
	}
}
