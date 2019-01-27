/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.SysOplog;
import com.eastorm.api.common.service.SysOplogService;
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
import java.util.Map;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/sys/oplog")
@ApiIgnore
public class SysOplogController extends BaseController {
	private static Logger logger=Logger.getLogger(SysOplogController.class);
	@Autowired
	private SysOplogService oplogService;
	/**
	 * 返回菜单列表的json格式
	 * @param page
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/read")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam("page") int page,
			@RequestParam("limit")int limit,		
			HttpServletRequest request){
		Map<String,String[]> searchMap = request.getParameterMap();
		Pageable pageable=new PageRequest(page-1, limit);
		Page<SysOplog>  list=oplogService.queryPage(pageable, searchMap, null);
        return resultMap(list);
	}
}
