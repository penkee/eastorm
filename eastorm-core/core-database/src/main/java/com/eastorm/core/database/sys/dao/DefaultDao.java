/**
 *DefaultDao.java
	Description:
 */
package com.eastorm.core.database.sys.dao;


import com.eastorm.core.base.utils.DateFunc;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.core.database.sys.DefaultEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-12-8
 */
public class DefaultDao {
	 @Resource(name="defaultEntityManager")
	 protected DefaultEntityManager defaultEntityManager;
	 /* 分页
	 * add by penkee@163.com
	 */
	public Page queryPagebase(Pageable page, String jpql, String orderBy,
							  Map<String, String[]> searchMap) {
		StringBuilder  sbjpql=new StringBuilder(jpql);
		Map<String, Object> param=new HashMap<String, Object>();
		setSearchParam(searchMap, sbjpql, param);//拼where条件
		sbjpql.append(" "+orderBy);
		return defaultEntityManager.queryPageByMap(page, sbjpql.toString(), param);
	}
	 /* 分页
	 * add by penkee@163.com
	 */
	public Page queryPage(Pageable page, String extWhere, String orderBy,
						  Map<String, String[]> searchMap, String model) {
		StringBuilder  sbjpql=new StringBuilder("from "+model+" m where 1=1 "+extWhere);
		Map<String, Object> param=new HashMap<String, Object>();
		setSearchParam(searchMap, sbjpql, param);//拼where条件
		sbjpql.append(" "+orderBy);
		return defaultEntityManager.queryPageByMap(page, sbjpql.toString(), param);
	}
	 /**
	  * 根据页面传来约定的字符串解析形成search sql
	  * @param searchMap
	  * add by penkee@163.com
	  */
	 protected void setSearchParam(Map<String, String[]> searchMap,StringBuilder  jpql,Map<String, Object> param){
		//sh_eq_id_long,  sh_like_login.username,sh_gr_XXX,sh_le_aaa_long,sh_greq_XXX,sh_leeq_XXX,
		    int parmKeySeq=0;
			Object[] keys=searchMap.keySet().toArray();
			for(Object k:keys){
				//解析字符串
				String[] ops=k.toString().split("_");
				if(ops.length<3||!"sh".equals(ops[0]))continue;
				String op=ops[1];//符号
				String attr=ops[2];//要筛选的属性
				String clazz=ops.length==3?"string":ops[3];//类型，默认是string
				String parmKey=attr.replace(".", "_")+parmKeySeq++;//参数
				
				String changeOp=null;
				String[] changePara=searchMap.get(k);
				Object para=null;
				if(StringFunc.isNull(changePara[0]))continue;
				
				//解析类类型
				if("long".equals(clazz)){
					para=Integer.parseInt(changePara[0]);
				}else if("date".equals(clazz)){
					para= DateFunc.parse(changePara[0]);
				}else if("byte".equals(clazz)){
					para=Byte.parseByte(changePara[0]);
				}else if("bigdecimal".equals(clazz)){
					para=new BigDecimal(changePara[0]);
				}else if("char".equals(clazz)){
					para=changePara[0].charAt(0);
				}else if("int".equals(clazz)){
					para=changePara[0].charAt(0);
				}else{
					para=StringFunc.URLDecoder(changePara[0]);//解码
				}
				
				//解析符号
				if("eq".equals(op)){
					changeOp=" =";
				}else if("like".equals(op)){
					changeOp=" like";
					para="%"+para+"%";
				}else if("gr".equals(op)){
					changeOp=" >";
				}else if("le".equals(op)){
					changeOp=" <";
				}else if("greq".equals(op)){
					changeOp=" >=";
				}else if("leeq".equals(op)){
					changeOp=" <=";
				}
				
				if(!StringFunc.isNull(changePara)){
					jpql.append(" and "+attr+changeOp+"  :"+parmKey);
					param.put(parmKey, para);
				}
			}
	 }
	 public List queryList(String jpql){
		 return defaultEntityManager.query(jpql);
	 }
}
