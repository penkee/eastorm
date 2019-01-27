/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.service.MenuItemService;
import com.eastorm.core.base.utils.*;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back")
@ApiIgnore
public class CmmnController extends BaseController {
	private static Logger   logger=Logger.getLogger(CmmnController.class);
	@Autowired
	private MenuItemService menuItemService;
	/**
	 * 返回后台/back/目录下的通用页
	 * @param page
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/{page}", method = RequestMethod.GET)
	public String getPage(@PathVariable("page") String page){
		return "back/"+page;
	}
	/**
	 *返回后台/back/comm/_head
	 * @param session
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/comm/_head")
	public ModelAndView getPageHead(HttpSession session){
		ModelAndView mav = new ModelAndView("back/comm/_head");
		mav.addObject("backDyMember",super.getCurMember(session));
		
		if(super.getCurLogin(session).getLastLoginTime()!=null)
			mav.addObject("backLoginDate", DateFunc.format(super.getCurLogin(session).getLastLoginTime()));
		return mav;
	}
	/**
	 *返回后台/back/comm/_head
	 * @param session
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/comm/_index")
	public ModelAndView getPageIndex(HttpSession session){
		ModelAndView mav = new ModelAndView("back/comm/_index");
		mav.addObject("backDyMember",super.getCurMember(session));
		mav.addObject("backLoginDate", DateFunc.format(new Date()));
		return mav;
	}
	/**
	 *返回后台/back/comm/目录下的通用页
	 * @param page1
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/{page1}/{page2}")
	public String getPagecomm(@PathVariable("page1") String page1,
			@PathVariable("page2") String page2,
			HttpServletRequest request,
			HttpSession session){
		//验证权限
		if(super.isAccess(session,request.getRequestURL().toString(),menuItemService)){
			return "back/"+page1+"/"+page2;
		}else{
			return "back/noAccess";
		}
	}
	/**
	 *返回后台/back/comm/目录下的通用页
	 * @param page1
	 * @return
	 * add by penkee@163.com
	 */
	@RequestMapping(value="/{page1}/{page2}/{page3}")
	public String getPagecomm(@PathVariable("page1") String page1,
			@PathVariable("page2") String page2,
			@PathVariable("page3") String page3,
			HttpServletRequest request,
			HttpSession session){
		//验证权限
		if(isAccess(session,request.getRequestURL().toString(),menuItemService)){
			return "back/"+page1+"/"+page2+"/"+page3;
		}else{
			return "back/noAccess";
		}
		
	}
	/**
	 * 产品图片上传
	 * @param uploadImg
	 * @return
	 * add by penkee@163.com
	 * 2014-9-11
	 */
	@RequestMapping(value="/imageUpload")
	@ResponseBody
	public Map<String,Object> imageUpload(
			@RequestParam("uploadImg")MultipartFile uploadImg,
			@RequestParam(value="width",required=false)Long width,
			@RequestParam(value="height",required=false)Long height,
			@RequestParam(value="content",required=false)String content
			){
        Map<String,Object> map = new HashMap<String,Object>();  
        
        //bizImages/editor
    	//项目基本路径
		String baseUrl=super.getServletContext().getRealPath("/");
	    if (!baseUrl.endsWith(File.separator)) {
			  baseUrl = baseUrl + File.separator;
		}
        Random rn=new Random();
        String url="";
        boolean success=true;
        if(uploadImg!=null){
	    	String uploadFilename=DateFunc.format(new Date(), 6)+rn.nextInt(100);
			final String saveUrl=baseUrl+"bizImages"+File.separator+"editor"+File.separator+uploadFilename+".jpg";
	    	try {
				uploadImg.transferTo(new File(saveUrl));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success=false;
				logger.debug(e);
			}
					try {
						logger.info("正在压缩："+saveUrl);
						ImgCompress imgCom = new ImgCompress(saveUrl);
						imgCom.resizeFix();
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
					}
	    	url="bizImages/editor/"+uploadFilename+".jpg";
        }
        
        Map<String,Object> data = new HashMap<String,Object>();  
        data.put("url", url);
        data.put("content", content);
        data.put("width", width==null?"":width);
        data.put("height", height==null?"":height);
        
        map.put("success", success);
        map.put("message", "上传失败");
        map.put("data", data);
        return map;  
	}
	
	/**
	 * 获取验证码图片
	 * added by Buke at 2015年6月7日
	 */
	@RequestMapping(value="/getCodeImg")
	public void getCodeImg(HttpServletResponse response,
			HttpSession session) throws IOException{
		GenerateImg geneimg=new GenerateImg(75,30);
		
		String txt= StringFunc.getRandomStr(4);
		geneimg.drawText(txt, "png",response.getOutputStream() );
		
		logger.info("图片验证码："+txt);
		session.setAttribute(Const.BACKImgCode, txt);
	}
}
