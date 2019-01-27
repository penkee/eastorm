package com.eastorm.api.common.service;


import com.eastorm.api.common.domain.SysCache;

import javax.servlet.ServletContext;
import java.util.List;

public interface SysCacheService extends BaseService<SysCache>{
	List<SysCache> queryListAll();
	void refreshCache(ServletContext context);
	List<SysCache> findByKeyname(String keyname);
}
