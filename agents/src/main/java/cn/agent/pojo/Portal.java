package cn.agent.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 门户信息
 */
@Entity
@Table(name = "PORTAL")
public class Portal  implements Serializable {
	/**
	 * 门户编号
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long portalid;
	/**
	 * 客户编号
	 */
	@Column(name = "CLIENTID")
	private Long clientid; //客户编号
	/**
	 * 法人代表
	 */
	@Column(name = "CORPORATE")
	private String corporate;
	/**
	 * 证件类型
	 */
	@Column(name = "PAPERSTYPE",insertable = false,updatable = false)
	private Long paperstype; //证件类型

	/**
	 * 证件类型
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PAPERSTYPE")
	private Types types;
	/**
	 * 证件值
	 */
	@Column(name = "PAPERSVALUE")
	private String papersvalue;
	/**
	 * 公司电话
	 */
	@Column(name = "TELEPHONE")
	private String telephone;
	/**
	 * 公司传真
	 */
	@Column(name = "FAX")
	private String fax;
	/**
	 * 注册日期
	 */
	@Column(name = "CREATETIME")
	private Date createtime;
	/**
	 * 国家
	 */
	@Column(name = "COUNTRY")
	private String country; //国家
	/**
	 * 省
	 */
	@Column(name = "PROVINCE")
	private String province;
	/**
	 *城市
	 */
	@Column(name = "CRTY")
	private String crty;
	/**
	 * 区县
	 */
	@Column(name = "DISTRICT")
	private String district;
	/**
	 * 公司地址
	 */
	@Column(name = "ADDRESS")//公司地址
	private String address;
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	public Long getPortalid() {
		return portalid;
	}

	public void setPortalid(Long portalid) {
		this.portalid = portalid;
	}

	public Long getClientid() {
		return clientid;
	}

	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	public Types getTypes() {
		return types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public String getPapersvalue() {
		return papersvalue;
	}

	public void setPapersvalue(String papersvalue) {
		this.papersvalue = papersvalue;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCrty() {
		return crty;
	}

	public void setCrty(String crty) {
		this.crty = crty;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public
	Long getPaperstype() {
		return paperstype;
	}

	public
	void setPaperstype(Long paperstype) {
		this.paperstype = paperstype;
	}
}
