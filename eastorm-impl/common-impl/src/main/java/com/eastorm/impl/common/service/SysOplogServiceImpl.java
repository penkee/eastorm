package com.eastorm.impl.common.service;

import com.eastorm.api.common.domain.SysOplog;
import com.eastorm.api.common.service.SysOplogService;
import com.eastorm.impl.common.dao.SysOplogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service
public class SysOplogServiceImpl   implements SysOplogService {

	@Autowired
	private SysOplogDao oplogDao;

	/* 新增，修改
	 * add by penkee@163.com
	 */
	@Override
	public SysOplog save(SysOplog item) {
		return oplogDao.save(item);
	}

	@Override
	public void addLog(String content, String username, String type,Integer objId) {
		SysOplog log=new SysOplog();
		log.setCreateName(username);
		log.setType(type.charAt(0));
		log.setContent(content);
		log.setCreateDate(new Date());
		log.setObjId(objId);
		save(log);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysOplog findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysOplog> queryPage(Pageable page,
			Map<String, String[]> searchMap, Map<String, Object> bizMap) {
		String extWhere="";
		String orderBy="order by id desc";
		return oplogDao.queryPage(page, extWhere,orderBy,searchMap,"SysOplog");
	}

}
