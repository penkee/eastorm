
package com.eastorm.api.member.domain;

import com.eastorm.core.database.sys.DefaultEntity;
import com.eastorm.api.common.domain.SysCity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**用户表
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
@Entity
@Table(name="sys_member_info")
public class SysMemberInfo extends DefaultEntity {
	private static final long serialVersionUID = 1L;
	@Column(length=8)
	private String nickname;//昵称
	private Byte sex;//值为1时是男性，值为2时是女性，值为0时是未知
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;//生日
	private int stature;//身高
	private int weight;//体重
	@Column(length=11)
	private String sanwei;//三围 12,12,54
	@Column(length=50)
	private String tag;//个人标签
	@Column(length=50)
	private String intro;//个人介绍
	@Column(length=500)
	private String headPic;//头像
	@Column(length=100)
	private byte showPrivateClass;//私密信息：0-不显示，1-关注的人显示，2-所有人都显示
	@Column(length=50)
	private String email;//邮箱
	@Column(length=12)
	private String mobile;//手机号
	
	private Integer score;//积分
	private BigDecimal balance;//余额
	private Integer candiscount;//折扣剩余次数
	private Double discount;//折扣
	/*******************fr-key**********************************/
	private Integer cityId;//所属省份
	private Integer companydisctId;
	private Integer sysMemberId;
	@Transient
	private SysCity city;
	
	public Integer getSysMemberId() {
		return sysMemberId;
	}
	public void setSysMemberId(Integer sysMemberId) {
		this.sysMemberId = sysMemberId;
	}
	public Integer getCompanydisctId() {
		return companydisctId;
	}
	public void setCompanydisctId(Integer companydisctId) {
		this.companydisctId = companydisctId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getStature() {
		return stature;
	}
	public void setStature(int stature) {
		this.stature = stature;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getSanwei() {
		return sanwei;
	}
	public void setSanwei(String sanwei) {
		this.sanwei = sanwei;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public byte getShowPrivateClass() {
		return showPrivateClass;
	}
	public void setShowPrivateClass(byte showPrivateClass) {
		this.showPrivateClass = showPrivateClass;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public SysCity getCity() {
		return city;
	}
	public void setCity(SysCity city) {
		this.city = city;
	}
	public Integer getCandiscount() {
		return candiscount;
	}
	public void setCandiscount(Integer candiscount) {
		this.candiscount = candiscount;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
}
