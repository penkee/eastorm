package com.eastorm.impl.common.service;

import com.alibaba.fastjson.JSONObject;
import com.eastorm.api.common.domain.DictItem;
import com.eastorm.api.common.service.DictItemService;
import com.eastorm.impl.common.dao.DictItemDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.*;


@Service("dictItemService")
public class DictItemServiceImpl   implements DictItemService {
	private Logger logger = Logger.getLogger(DictItemServiceImpl.class);
	@Autowired
	private DictItemDao dictItemDao;

	/* 新增，修改
	 * add by penkee@163.com
	 */
	@Override
	public DictItem save(DictItem item) {
		return dictItemDao.save(item);
	}


	/*  删除一条记录
	 * add by penkee@163.com
	 */
	@Override
	public void delete(Integer id) {
			dictItemDao.delete(id);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public DictItem findOne(Integer id) {
		// TODO Auto-generated method stub
		return dictItemDao.findOne(id);
	}
	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Page<DictItem> queryPage(Pageable page,
			Map<String, String[]> searchMap) {
		// TODO Auto-generated method stub
		return dictItemDao.queryPage(page, searchMap);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<DictItem> queryListAll() {
		// TODO Auto-generated method stub
		return dictItemDao.queryListAll();
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public void refreshCache(ServletContext context) {
	    List<DictItem> dictItems=queryListAll();
	    String tmpKey="";
	    List<DictItem> tmpDictItems=null;
	    for(DictItem item:dictItems){
	    	/************foot*********************/
	    	if(!item.getName().equals(tmpKey)&&!"".equals(tmpKey)){
	    		context.setAttribute(tmpKey, tmpDictItems);
	    		logger.info("加载字典："+tmpKey+",数量："+tmpDictItems.size());
	            try {
	            	String v=JSONObject.toJSONString(tmpDictItems);
	            	context.setAttribute(tmpKey+"_JSON", v);
	            	//logger.info("加载字典："+tmpKey+"_JSON"+",value："+context.getAttribute(tmpKey+"_JSON"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
	    		tmpDictItems=null;
	    	}
	    	/************foot end*********************/
	    	/************head*********************/
	    	if(!item.getName().equals(tmpKey)){
	    		tmpDictItems=new LinkedList<DictItem>();
	    		tmpKey=item.getName();
	    	}
	    	/************head end*********************/
	    	tmpDictItems.add(item);
	    }
	    /************last one*********************/
	    if(dictItems.size()>0){
	    	context.setAttribute(tmpKey, tmpDictItems);
	    	logger.info("加载字典："+tmpKey+",数量："+tmpDictItems.size());
            try {
				String v=JSONObject.toJSONString(tmpDictItems);
				context.setAttribute(tmpKey+"_JSON", v);
            	//logger.info("加载字典："+tmpKey+"_JSON"+",value："+context.getAttribute(tmpKey+"_JSON"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		tmpDictItems=null;
	    }
	}


	@Override
	public String queryValue(String name, String typeKey,ServletContext context) {
		List<DictItem> tmpDictItems= (List<DictItem>)context.getAttribute(name);
		
		if(tmpDictItems!=null){
			for(DictItem dc:tmpDictItems){
				if(dc.getTypeKey().equals(typeKey)){
					return dc.getTypeValue();
				}
			}
		}
		return "";
	}


	@Override
	public List<Map<String, String>> queryDict(String name,
			ServletContext context) {
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		List<DictItem> tmpDictItems= (List<DictItem>)context.getAttribute(name);
		
		if(tmpDictItems!=null){
			for(DictItem dc:tmpDictItems){
				Map<String, String> item=new HashMap<String, String>(2);
				item.put("key", dc.getTypeKey());
				item.put("value", dc.getTypeValue());
				list.add(item);
			}
		}
		return list;
	}
}
