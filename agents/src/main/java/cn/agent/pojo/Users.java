package cn.agent.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 */
@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "role" })
public class Users  implements Serializable {
	/**
	 * 用户编号
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long userid;
	/**
	 * 用户名称
	 */
	@Column(name = "USERNAME")
	private String username;
	/**
	 * 密码
	 */
	@Column(name = "PASSWORD")
	private String password;
	/**
	 * 姓名
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 账户余额
	 */
	@Column(name = "BALANCE")
	private Double balance;
	/**
	 * 电话
	 */
	@Column(name = "TELEPHONE")
	private String telephone;
	/**
	 * 证件类型id
	 */
	@Column(name = "TYPEID")
	private Long typeid;
	/**
	 * 证件编号
	 */
	@Column(name = "CERTIFICATEVALUE")
	private String certificatevalue;
	/**
	 * 最后一次登录时间
	 */
	@Column(name = "LASTLOGINTIME")
	private Date lastlogintime; //最后一次登录时间

	/**
	 * 是否启用
	 */
	private Integer enable;

	/**
	 * 角色
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ROLEID")
	private Role role;


	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public String getCertificatevalue() {
		return certificatevalue;
	}

	public void setCertificatevalue(String certificatevalue) {
		this.certificatevalue = certificatevalue;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
}
