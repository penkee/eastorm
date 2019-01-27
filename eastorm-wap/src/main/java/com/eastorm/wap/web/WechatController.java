/**
 * description:
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.wap.web;

import com.eastorm.api.member.service.SysLoginService;
import com.eastorm.api.member.service.SysMemberService;
import com.eastorm.core.others.wechat.WechatService;
import com.eastorm.core.others.wechat.WechatTools;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseController {
	private static Logger  logger=Logger.getLogger(WechatController.class);
	
	@Autowired
	private SysLoginService sysLoginService;
	@Autowired
	private SysMemberService sysMemberService;
	/**
	 * 获取扫描用户
	 */
	@RequestMapping(value="/gateway")
	@ResponseBody
	@ApiIgnore
	public String start(
			String signature,//签名
			String timestamp,//时间
			String nonce,//随机数  
			String echostr,//随机字符串
			HttpServletRequest request
			){
		String token = "spider123";
		/** 第一次申请成为开发者 **/
		if (StringUtils.isNotEmpty(signature)) {
			//将token、timestamp、nonce三个参数进行字典序排序
			String[] strArray = new String[] { token, timestamp, nonce };
			java.util.Arrays.sort(strArray);
			String str = "";
			for (String s : strArray) {
				str += s;
				System.out.println(s);
			}
			String sign = WechatTools.SHA1(str);

			if (sign.equals(signature)) {
				if (StringUtils.isNotEmpty(echostr)) {
					logger.info("echostr -- " + echostr);
					return echostr;
				} else {
					try {
						StringBuffer receivedData = new StringBuffer();
						InputStreamReader inReader = new InputStreamReader(request.getInputStream(), "UTF-8");
						BufferedReader aReader = new BufferedReader(inReader);
						String aLine = null;
						while ((aLine = aReader.readLine()) != null) {
							receivedData.append(aLine.trim());
						}
						String xmldata = receivedData.toString();
						// 将字符串转为XML
						if (xmldata.length() > 0) {
							// 处理XMl数据并得到回复的XML数据  f代表服务号
						    String replyXmlData = WechatService.responseMsg(xmldata,"f");
							return replyXmlData;
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return "no info";
	}
}
