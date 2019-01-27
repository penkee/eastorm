/**
 *MenuItem.java
	Description:栏目菜单单项表
 */
package com.eastorm.api.common.domain;

import com.eastorm.core.database.sys.DefaultEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Pengkun (penkee@163.com)
 *	2013-10-20
 */
@Entity
@Table(name="sys_urlsource")
public class SysUrlsource extends DefaultEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length=64)
	private String utm_source;
	@Column(length=64)
	private String utm_medium;
	@Column(length=1024)
	private String url;
	private Date createdate;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUtm_source() {
		return utm_source;
	}
	public void setUtm_source(String utm_source) {
		this.utm_source = utm_source;
	}
	public String getUtm_medium() {
		return utm_medium;
	}
	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}
	
	
}
