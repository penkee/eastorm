/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.wap.web;

import com.eastorm.api.hedian.service.BizActCommentService;
import com.eastorm.api.hedian.service.BizActivitysumService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/activityjoin")
public class ActivityJoinController extends BaseController {
	private static Logger  logger=Logger.getLogger(ActivityJoinController.class);
	
	@Autowired
	private BizActivitysumService activitysumService;
	@Autowired
	private BizActCommentService actCommentService;

}
