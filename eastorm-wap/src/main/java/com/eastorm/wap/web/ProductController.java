/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.wap.web;

import com.eastorm.api.common.vo.RespResult;
import com.eastorm.api.hedian.service.BizActImageService;
import com.eastorm.api.hedian.service.BizActivitysumService;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.core.others.Container;
import com.eastorm.core.others.wechat.WXUserInfo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
	private static Logger  logger=Logger.getLogger(ProductController.class);
	
	@Autowired
	private BizActImageService actImageService;
	@Autowired
	private BizActivitysumService activitysumService;
	/**
	 * 获取扫描用户
	 */
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取扫描用户", notes = "获取设备扫描用户")
	public RespResult<WXUserInfo> getUser(
						@ApiParam(required=true,value="设备id")	
						@RequestParam(value="machineId",required=false) 
						String machineId){
		
		RespResult<WXUserInfo> map = new RespResult<WXUserInfo>();
		try {
			StringFunc.checkNull(machineId, "机器id");

			WXUserInfo info= Container.SAOMAO_USERS.get(machineId);

			map= RespResult.returnSucc(info);
		}  catch (EastormException e) {
			map.setErrCode(e.getErrCode());
			map.setErrMsg(e.getErrMsg());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			map.setErrCode(Const.FR_EXCEPTION);
			map.setErrMsg("未知错误");
		}
		return map;
	}

}
