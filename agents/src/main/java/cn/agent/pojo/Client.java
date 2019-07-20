package cn.agent.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户
 */
@Entity
@Table(name = "CLIENT")
public class Client  implements Serializable {
	/**
	 * 客户编号
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long clientid;
	/**
	 * 用户编号
	 */
	@Column(name="USERSID",insertable = false,updatable = false)
	private Long usersid; //用户编号
	/**
	 * 用户
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USERSID")
	private Users users;
	/**
	 * 企业名称
	 */
	@Column(name = "ENTERPRISENAME")
	private String enterprisename; //企业名称
	/**
	 * 企业类型
	 */
	@Column(name = "ENTERPRISETYPE",insertable = false,updatable = false)
	private Long enterprisetype;  //企业类型
	/**
	 * 	客户类型
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTERPRISETYPE")
	private Types types;
	/**
	 * 企业主页
	 */
	@Column(name = "ENTERPRISEURL")
	private String enterpriseurl;
	/**
	 * 使用状态 0 启用 1禁用
	 */
	@Column(name = "STATUS")
	private Long status;


	public Long getClientid() {
		return clientid;
	}

	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}

	public Types getTypes() {
		return types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public String getEnterpriseurl() {
		return enterpriseurl;
	}

	public void setEnterpriseurl(String enterpriseurl) {
		this.enterpriseurl = enterpriseurl;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public
	Long getUsersid() {
		return usersid;
	}

	public
	void setUsersid(Long usersid) {
		this.usersid = usersid;
	}

	public
	Long getEnterprisetype() {
		return enterprisetype;
	}

	public
	void setEnterprisetype(Long enterprisetype) {
		this.enterprisetype = enterprisetype;
	}



}
