package cn.agent.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 联系人
 */
@Entity
@Table(name = "LINKMAN")
public class Linkman  implements Serializable {
	/**
	 * 联系人编号
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long linkmanid;
   /**
	 * 客户编号
	*/
	@Column(name = "CLIENTID",insertable = false,updatable = false)
	private Long clientid;
	/**
	 * 客户
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientid")
	private Client client;
	/**
	 * 联系人姓名
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 电话
	 */
	@Column(name = "TELEPHONE")
	private String telephone;
	/**
	 * 传真
	 */
	@Column(name = "FAX")
	private String fax;
	/**
	 * 邮箱
	 */
	@Column(name = "EMAIL")
	private String email;
	/**
	 * 职务
	 */
	@Column(name = "DUTY")
	private String duty;

	public Long getLinkmanid() {
		return linkmanid;
	}

	public void setLinkmanid(Long linkmanid) {
		this.linkmanid = linkmanid;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public
	Long getClientid() {
		return clientid;
	}

	public
	void setClientid(Long clientid) {
		this.clientid = clientid;
	}
}
