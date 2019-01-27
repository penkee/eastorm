/**
 *MenuItem.java
	Description:栏目菜单单项表
 */
package com.eastorm.api.common.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**type-类型，typeValue-url,name-名称,tKey-顺序，tValue=1-隐藏型
 * @author Pengkun (penkee@163.com)
 *	2013-10-20
 */
@Entity
@DiscriminatorValue("2")
public class MenuItem extends CommType   {
	public MenuItem(){
		super.setType(2);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
