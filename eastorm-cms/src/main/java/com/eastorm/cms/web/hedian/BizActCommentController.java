/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.hedian;

import com.eastorm.api.hedian.domain.BizActComment;
import com.eastorm.api.hedian.service.BizActCommentService;
import com.eastorm.api.member.domain.SysMember;
import com.eastorm.cms.web.base.BaseController;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/bizActComment")
@ApiIgnore
public class BizActCommentController extends BaseController {
	private static Logger   logger=Logger.getLogger(BizActCommentController.class);
	@Autowired
	private BizActCommentService actCommentService;
	
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

		Page<BizActComment> list = actCommentService.queryPage(pageable, request.getParameterMap(),null);
		return resultMap(list);
	}
	
	/**
	 * 审核成功
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/exam")
	@ResponseBody
	public Map<String,Object> exam(@RequestParam("ids") String ids,@RequestParam("status") Byte status,
			HttpSession session) {
		SysMember curMember=super.getCurMember(session);
		if(curMember.getGrade()==null||curMember.getGrade()>=9)return null;//非法入侵
		Map<String,Object> resultMap = new HashMap<String,Object>();  
		resultMap.put("success", true);	
	   try {
		   actCommentService.updateExam(ids,curMember,status);
		   	resultMap.put("message", "审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultMap.put("message", "审核失败："+e.getMessage());
		}
		return resultMap;     
	}
	
}
