/**
 *MenuItem.java
	Description:栏目菜单单项表
 */
package com.eastorm.api.common.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/** typeKey-typeValue，  代号=名称  ,name-经度，attr1-纬度
 * tKey--类别：0-省，1-市-2，县\区
 * attr2---是否有效1-有效，0-无效
 * @author Pengkun (penkee@163.com)
 *	2013-10-20
 */
@Entity
@DiscriminatorValue("1")
public class SysCity extends CommType   {
	public SysCity(){
		super.setType(1);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
