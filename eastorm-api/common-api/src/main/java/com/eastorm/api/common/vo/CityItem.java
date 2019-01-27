package com.eastorm.api.common.vo;

import java.util.List;

/**
 * @brief     城市
 * @details   详细描述
 * @param
 * @retval    返回值
 * @author    慕容恪
 * @date      2018/8/12
 * @note      变更信息
 * @return
*/
public class CityItem {
	private Integer id;
	private String name;
	private List<CityItem> subCity;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CityItem> getSubCity() {
		return subCity;
	}
	public void setSubCity(List<CityItem> subCity) {
		this.subCity = subCity;
	}
}