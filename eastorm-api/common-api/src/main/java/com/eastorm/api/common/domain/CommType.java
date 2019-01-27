/**
 * CommType.java
	Description:基础类别表
 */
package com.eastorm.api.common.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.*;

/**
 * 1-SysCity,
 * 3-DictItem ,
 * 8-WapMenuItem
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name = "sys_commtype")
@DiscriminatorColumn(name="comm_type",discriminatorType=DiscriminatorType.INTEGER)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("0")
public class CommType extends DefaultEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length=10)
	private String typeKey;
	@Column(length=50)
	private String typeValue;
	@Column(length=50)
	private String name;
	
	private Integer tKey;
	private Integer tValue;
	
	private Integer type;//类型，对应@DiscriminatorValue
	@Column(length=50)
	private String attr1;
	@Column(length=50)
	private String attr2;
	
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public CommType(){}
	public CommType(Integer _tKey,String _name){
		tKey=_tKey;
		name=_name;
	}
	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public Integer gettKey() {
		return tKey;
	}

	public void settKey(Integer tKey) {
		this.tKey = tKey;
	}

	public Integer gettValue() {
		return tValue;
	}

	public void settValue(Integer tValue) {
		this.tValue = tValue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
}
