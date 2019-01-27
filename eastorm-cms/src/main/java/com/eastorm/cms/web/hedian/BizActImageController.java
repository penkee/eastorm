/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.hedian;

import com.eastorm.api.hedian.domain.BizActImage;
import com.eastorm.api.hedian.service.BizActImageService;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.cms.web.base.BaseController;
import com.eastorm.core.base.sys.EastormException;
import com.eastorm.core.base.utils.Const;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/bizActImage")
@ApiIgnore
public class BizActImageController extends BaseController {
	private static Logger   logger=Logger.getLogger(BizActImageController.class);
	@Autowired
	private BizActImageService actImageService;
	
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

		Page<BizActImage> list = actImageService.queryPage(pageable, request.getParameterMap(),null);
		return resultMap(list);
	}
	
	/**
	 * 更新
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String,Object> update(BizActImage form_actimage,
			@RequestParam(value="showPic",required=false)MultipartFile showPic) {
		Map<String,Object> resultMap = new HashMap<String,Object>();  
		resultMap.put("success", true);	
		try {		
			//项目基本路径
			String baseUrl=super.getServletContext().getRealPath("/");
		    if (!baseUrl.endsWith(File.separator)) {
				  baseUrl = baseUrl + File.separator;
			}
			logger.info("baseUrl:"+baseUrl);

			actImageService.saveOne(form_actimage,showPic,baseUrl);
			resultMap.put("status", Const.Result.SUCCESS.ordinal());
		} catch (EastormException e) {
			resultMap.put("status", e.getErrMsg());
		}catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", Const.Result.EXCEPTION.ordinal());
		}
        return resultMap;      
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") Integer id,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();  
		SysMember curMember=super.getCurMember(session);
		try {
			actImageService.delete(id);
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
	 * ftp生成
	 */
	@RequestMapping(value="/ftpAdd")
	@ResponseBody
	public Map<String,Object> ftpAdd(){
		Map<String,Object> map = new HashMap<String,Object>();  
		try {
			//项目基本路径
			String baseUrl=super.getServletContext().getRealPath("/");
		    if (!baseUrl.endsWith(File.separator)) {
				  baseUrl = baseUrl + File.separator;
			}
			logger.info("baseUrl:"+baseUrl);
			
			List<String> errList=actImageService.ftpAdd(baseUrl);
			map.put("errList",errList);
			map.put("status",Const.Result.SUCCESS.ordinal());
			map.put("message","删除成功");
		} catch (Exception e) {
			map.put("status",Const.Result.FAILTURE.ordinal());
			map.put("message",e.getMessage());
			logger.error(e.getMessage(),e);
		}
        return map;
	}
}
