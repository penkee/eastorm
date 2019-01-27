package com.eastorm.impl.common.service;

import com.eastorm.api.common.domain.SysCity;
import com.eastorm.api.common.service.SysCityService;
import com.eastorm.api.common.vo.CityItem;
import com.eastorm.core.base.utils.StringFunc;
import com.eastorm.core.base.utils.SysProperties;
import com.eastorm.impl.common.dao.NodeLinkDao;
import com.eastorm.impl.common.dao.SysCityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service("cityService")
public class SysCityServiceImpl   implements SysCityService {

	@Autowired
	private SysCityDao sysCityDao;
	@Autowired
	private NodeLinkDao nodeLinkDao;

	/* 新增，修改
	 * add by penkee@163.com
	 */
	@Override
	public SysCity save(SysCity item) {
		return sysCityDao.save(item);
	}

	/*  删除一条记录
	 * add by penkee@163.com
	 */
	@Override
	public void delete(Integer id) {
			//先删除menu外键关联
			nodeLinkDao.deleteParentAndNode(id);
			sysCityDao.delete(id);
	}

	/* (non-Javadoc)
	 * add by penkee@163.com
	 */
	@Override
	public SysCity findOne(Integer id) {
		// TODO Auto-generated method stub
		SysCity city= sysCityDao.findOne(id);
		if(city!=null){
				String areaPic=SysProperties.getValue("areaimage_"+id);
				
				if(StringFunc.isNull(areaPic)){
					areaPic=SysProperties.getValue("areaimage_default");
				}
				city.setName(areaPic);
		}
		return city;
	}

	@Override
	public List<SysCity> queryAreaList(String typeValue) {
		// TODO Auto-generated method stub
		return sysCityDao.queryAreaList(typeValue);
	}
	
	@Override
	public List<SysCity> queryAreas() {
		return sysCityDao.queryAreas();
	}

	@Override
	public List<CityItem> queryAll(Integer rootId) {
		List<Map<String, Object>> nodeList=nodeLinkDao.qryAreaListByParentId(rootId);
		
		List<CityItem> resList=null;
		if(nodeList!=null&&nodeList.size()>0){
			resList=new ArrayList<CityItem>(nodeList.size());
			
			for (Iterator<Map<String, Object>> iterator = nodeList.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				
				CityItem item=new CityItem();
				item.setId(Integer.parseInt(map.get("id").toString()));
				item.setName(map.get("name").toString());
				
				List<CityItem> subCity=queryAll(item.getId());
				item.setSubCity(subCity);
				
				resList.add(item);
			}
		}

		return resList;
	}


}
