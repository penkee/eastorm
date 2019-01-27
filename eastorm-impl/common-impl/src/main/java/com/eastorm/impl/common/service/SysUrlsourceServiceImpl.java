package com.eastorm.impl.common.service;

import com.eastorm.api.common.domain.SysUrlsource;
import com.eastorm.api.common.service.SysUrlsourceService;
import com.eastorm.impl.common.dao.SysUrlsourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysUrlsourceServiceImpl   implements SysUrlsourceService {
	@Autowired
	private SysUrlsourceDao sysurlsourceDao;
	/* (non-Javadoc)
	 * @see com.eastorm.service.MemberService#save(com.eastorm.service.MemberService)
	 */
	@Override
	public SysUrlsource save(SysUrlsource urlsource) {
		// TODO Auto-generated method stub
		return sysurlsourceDao.save(urlsource);
	}

}
