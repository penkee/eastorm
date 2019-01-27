/**
 * description:首页control
 * create by penkee
 * date:2013-8-5
 */
package com.eastorm.cms.web.base;

import com.eastorm.api.common.domain.CommType;
import com.eastorm.api.common.domain.NodeLink;
import com.eastorm.api.common.service.NodeLinkService;
import com.eastorm.api.member.service.SysRoleService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 
 * @author Pengkun (penkee@163.com) 2013-10-13
 */
@Controller
@RequestMapping("/back")
@ApiIgnore
public class IndexBKController extends BaseController {
	private Logger logger = Logger.getLogger(IndexBKController.class);
	@Autowired
	private NodeLinkService menuService;
	
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 首页
	 * 
	 * @return add by penkee@163.com
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpSession session) {
		List<NodeLink> menus = menuService.getListByType(2);
		/*
		 * { html: leftmenu, title:'产品管理', autoScroll: true, border: false,
		 * iconCls: 'nav' },{ title:'系统管理', html: '', border: false, autoScroll:
		 * true, iconCls: 'settings' }
		 */
		StringBuilder firstmenu = new StringBuilder();// 一级菜单
		StringBuilder submenu = new StringBuilder();// 二级以及以下菜单
		String authUrls=getCurAuthUrls(session);
		
		 WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
         ServletContext servletContext = webApplicationContext.getServletContext();
         Integer rootId=Integer.parseInt(servletContext.getAttribute("menuRootId").toString());
		for(NodeLink n:menus){
			//一级菜单
			if(n.getParent().getId()== rootId
					&&authUrls.indexOf("A"+n.getNode().getId()+",")>=0
					&&(n.getNode().gettValue()==null||n.getNode().gettValue()!=1)){
				
				firstmenu.append("{");
				firstmenu.append("title:'" + n.getNode().getName() + "',");
				firstmenu.append("html:'<div class=\"portlet-content\" id=\"leftmenu"+ n.getNode().getId() + "\"></div>',");
				firstmenu.append("autoScroll:true,");
				firstmenu.append("border: false,");
				firstmenu.append("iconCls: 'settings'");
				firstmenu.append("},");
				
				submenu.append(" Ext.create(\"Ext.tree.Panel\", {");
				submenu.append("      width: 150,");
				submenu.append("      renderTo: Ext.getDom(\"leftmenu"+ n.getNode().getId() + "\") ,");
				submenu.append("   	rootVisible:false,");// 设为false将隐藏根节点
				submenu.append("   	useArrows: true,");
				submenu.append("    	colspan: 2,");
				submenu.append("     root: {");
				submenu.append("text: '根',");
				submenu.append("     		expanded: false,");
				submenu.append("         children: [");

				for(NodeLink t:menus){
					if(t.getParent().getId()==n.getNode().getId()
							&&authUrls.indexOf("A"+n.getNode().getId()+",")>=0
							&&(n.getNode().gettValue()==null||n.getNode().gettValue()!=1)){
						boolean isHas=genTree( t.getNode(), firstmenu, submenu, menus.get(0).getParent().getId(), menus,authUrls);
//						if(submenu.charAt(submenu.length()-1)==','){
//							submenu.deleteCharAt(submenu.length()-1);
//						}
//						if(isHas)
//							submenu.append(",");
					}
				}
				
				if(submenu.charAt(submenu.length()-1)==','){
					submenu.deleteCharAt(submenu.length()-1);
				}
				
				submenu.append("                       ]");
				submenu.append("     }");
				submenu.append("     });");
			}
		}
		
		ModelAndView mav = new ModelAndView("back/index");
		firstmenu.deleteCharAt(firstmenu.length()-1);
		mav.addObject("firstmenu", firstmenu.toString());
		mav.addObject("submenu", submenu.toString());
		return mav;
	}

	/**
	 * 形成菜单
	 * 
	 * @param parent
	 * @param firstmenu
	 * @param submenu
	 * @param root 真正的根 add by penkee@163.com
	 * @return 有数据返回true，没有则返回false
	 */
	private boolean genTree(CommType parent, StringBuilder firstmenu,
							StringBuilder submenu, final Integer root, List<NodeLink> menus,
							String authUrls) {
		//首先判断是否有权限  modified by cruze at 2014-8-13
		if(authUrls.indexOf("A"+parent.getId()+",")<0){
			return false;
		}
		if(parent.gettValue()!=null&&parent.gettValue()==1){
			return false;
		}
		
		//首先将自己加到字符串
		submenu.append("      {");
		//submenu.append("                 text: \"<a href='"+parent.getTypeValue()+"' target='mainContentfrm'>"+parent.getName()+"</a>\",");
		submenu.append("                 text: \"<a href=javascript:addTab('"+parent.getTypeValue()+"','"+
				parent.getName()+"')>"+parent.getName()+"</a>\",");
		//判断是否有孩子
		int child=0;
		for(NodeLink n:menus){
			if(n.getParent().getId()==parent.getId()){
				if(child==0){
					submenu.append("      leaf: false,");
					submenu.append("      children:[");
				}
				boolean tmpres=genTree(n.getNode(),firstmenu,submenu,root,menus,authUrls);
				if(tmpres){
					
				}
				child++;
			}
		}
		if(child>0){
			//去掉最后一个,
			submenu.deleteCharAt(submenu.toString().length()-1);
			submenu.append("         ]},");
		}else{
			submenu.append("      leaf: true},");
		}
        return true;
	}
	
	
}
