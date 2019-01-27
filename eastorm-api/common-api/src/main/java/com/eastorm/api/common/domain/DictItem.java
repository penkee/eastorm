/**
 *MenuItem.java
	Description:栏目菜单单项表
 */
package com.eastorm.api.common.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**typeKey-代号,typeValue-名称,tKey-顺序，name-大类名称
 * 字典类
 * @author Pengkun (penkee@163.com)
 *	2013-10-20
 */
@Entity
@DiscriminatorValue("3")
public class DictItem extends CommType   {
	public DictItem(){
		super.setType(3);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
