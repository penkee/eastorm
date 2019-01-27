/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.member;

import com.eastorm.api.hedian.domain.BizActivitysum;
import com.eastorm.api.hedian.service.BizActivitysumService;
import com.eastorm.cms.web.base.BaseController;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.base.utils.Const;
import com.eastorm.core.base.utils.StringFunc;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/bizActivitysum")
@ApiIgnore
public class BizActivitysumController extends BaseController {
	private static Logger   logger=Logger.getLogger(BizActivitysumController.class);
	@Autowired
	private BizActivitysumService activitySumService;

	/**
	 * 返回list
	 *
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/read")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("page") int page,
			@RequestParam("limit") int limit,HttpServletRequest request) {

		Pageable pageable = new PageRequest(page - 1, limit);

		Page<BizActivitysum> list = activitySumService.queryPage(pageable, request.getParameterMap(),null);
		return resultMap(list);
	}

	/**
	 * 更新
	 *
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String,Object> update(HttpEntity<BizActivitysum> bizActivitysum) {
		BizActivitysum form_actimage=bizActivitysum.getBody();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", true);
		try {
			StringFunc.checkNull(form_actimage.getActivityid(),"activityid");

			activitySumService.saveOne(form_actimage);
			resultMap.put("status", Const.Result.SUCCESS.ordinal());
			resultMap.put("list", form_actimage);
		} catch (EastormException e) {
			resultMap.put("status", e.getErrMsg());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			resultMap.put("status", Const.Result.EXCEPTION.ordinal());
		}
        return resultMap;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			activitySumService.delete(id);
			map.put("status",Const.Result.SUCCESS.ordinal());
			map.put("message","删除成功");
		} catch (Exception e) {
			map.put("status",Const.Result.FAILTURE.ordinal());
			map.put("message",e.getMessage());
			logger.error(e.getMessage(),e);
		}
        return map;
	}

	/**
	 * 清理记录
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/clear")
	@ResponseBody
	public Map<String,Object> clear(String beginTime,String endTime,String activityid) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", true);
		try {
			StringFunc.checkNull(activityid,"activityid");

			Date bTime=null;
			Date eTime=null;
			if(!StringFunc.isNull(beginTime)){
				bTime=DateUtils.parseDate(beginTime, "yyyy-MM-dd HH:mm");
			}
			if(!StringFunc.isNull(endTime)){
				eTime=DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm");
			}

			activitySumService.clear(bTime,eTime,activityid);
			resultMap.put("status", Const.Result.SUCCESS.ordinal());
		} catch (EastormException e) {
			resultMap.put("status", e.getErrMsg());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			resultMap.put("status", Const.Result.EXCEPTION.ordinal());
		}
        return resultMap;
	}

}
