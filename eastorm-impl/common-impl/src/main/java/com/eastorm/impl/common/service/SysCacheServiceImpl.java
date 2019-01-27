package com.eastorm.impl.common.service;

import com.alibaba.fastjson.JSONObject;
import com.eastorm.core.database.sys.DefaultEntity;
import com.eastorm.api.common.domain.SysCache;
import com.eastorm.api.common.service.SysCacheService;
import com.eastorm.impl.common.dao.SysCacheDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysCacheService")
public class SysCacheServiceImpl   implements SysCacheService {
	private Logger logger = Logger.getLogger(SysCacheServiceImpl.class);
	@Autowired
	private SysCacheDao sysCacheDao;

	/* 新增，修改
	 * add by penkee@163.com
	 */
	@Override
	public SysCache save(SysCache item) {
		return sysCacheDao.save(item);
	}


	/*  删除一条记录
	 * add by penkee@163.com
	 */
	@Override
	public void delete(Integer id) {
			sysCacheDao.delete(id);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public SysCache findOne(Integer id) {
		// TODO Auto-generated method stub
		return sysCacheDao.findOne(id);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysCache> queryListAll() {
		// TODO Auto-generated method stub
		return sysCacheDao.queryListAll();
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public void refreshCache(ServletContext context) {
	    List<SysCache> sysCaches=queryListAll();
	    for(SysCache item:sysCaches){
	    	List list=sysCacheDao.queryList(item.getJpql());
	    	if(0==item.getResultType()){
	    		//json格式
	    		if(list!=null&&list.size()>0){
	    			context.setAttribute(item.getKeyname(), list.get(0));
	    			logger.info("加载String缓存："+item.getKeyname());
	    		    try {
	                	String v=JSONObject.toJSONString(list.get(0));
	                	context.setAttribute(item.getKeyname()+"_JSON", v);
	    			} catch (Exception e) {
	    				logger.error(e);
	    			}
	    		}
	    	}else if(1==item.getResultType()){
	    		//list
	    		context.setAttribute(item.getKeyname(), list);
    			logger.info("加载List缓存："+item.getKeyname());
    		    try {
					String v=JSONObject.toJSONString(list);
                	context.setAttribute(item.getKeyname()+"_JSON", v);
    			} catch (Exception e) {
    				logger.debug(e);
    			}
	    	}else if(2==item.getResultType()){
	    		//map
	    		if(list!=null&&list.size()>0){
	    			Map<Integer,Object> map=new HashMap<Integer,Object>();
	    			for(Object o:list){
	    				DefaultEntity de=(DefaultEntity)o;
	    				map.put(de.getId(), o);
	    			}
	    			context.setAttribute(item.getKeyname(), map);
	    		}
    			logger.info("加载Map缓存："+item.getKeyname());
    		    try {
					String v=JSONObject.toJSONString(list);
                	context.setAttribute(item.getKeyname()+"_JSON", v);
    			} catch (Exception e) {
    				logger.error(e.getMessage(),e);
    			}
	    	}
	    }
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public List<SysCache> findByKeyname(String keyname) {
		// TODO Auto-generated method stub
		return sysCacheDao.findByKeyname(keyname);
	}


	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public Page<SysCache> queryPage(Pageable page,
			Map<String, String[]> searchMap, Map<String, Object> bizMap) {
		// TODO Auto-generated method stub
		return sysCacheDao.queryPage(page,"", searchMap);
	}
}
