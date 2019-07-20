package cn.agent.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 关键字
 */

@Entity
@Table(name = "KEYWORD")
public class Keyword  implements Serializable {
	/**
	 * 关键字id
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long kwid;
	/**
	 * 客户id
	 */
	@Column(name = "CLIENTID")
	private Long clientid;
	/**
	 * 用户id
	 */
	@Column(name = "USERID", insertable =false,updatable=false)
	private Long userid;
	/**
	 * 用户
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private Users users;
	/**
	 * 关键字名称
	 */
	@Column(name = "KWNAME")
	private String kwname;
	/**
	 * 服务类别
	 */
	@Column(name = "TYPESID" , insertable =false,updatable=false)
	private Long typesid;
	/**
	 *服务类型
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typesid")
	private Types types;
	/**
	 * 申请年限
	 */
	@Column(name = "APPLYLIMIT")
	private Long applylimit;
	/**
	 * 价格
	 */
	@Column(name = "PRICE")
	private Double price;
	/**
	 * 申请日期
	 */
	@Column(name = "APPLYTIME")
	private Date applytime;
	/**
	 * 到期日期
	 */
	@Column(name = "LASTTIME")
	private Date lasttime;
	/**
	 * 申请到到期状态(0:已过期，1:未过期)
	 */
	@Column(name = "STATUS")
	private Long status;
	/**
	 * 审核状态  0核审中 1已通过 2未通过
	 */
	@Column(name = "AUDITSTATUS")
	private Long auditstatus;
	/**
	 * 使用状态
	 */
	@Column(name = "USESTATUS")
	private Long usestatus;
	/**
	 * APP开通状态  1未开通  2开通
	 */
	@Column(name="APPSTATUS")
	private Long appStatus;

	public
	Long getAppStatus() {
		return appStatus;
	}

	public
	Long getUserid() {
		return userid;
	}

	public
	void setUserid(Long userid) {
		this.userid = userid;
	}

	public
	Long getTypesid() {
		return typesid;
	}

	public
	void setTypesid(Long typesid) {
		this.typesid = typesid;
	}

	public
	void setAppStatus(Long appStatus) {
		this.appStatus = appStatus;
	}

	public Long getKwid() {
		return kwid;
	}

	public void setKwid(Long kwid) {
		this.kwid = kwid;
	}

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

	public String getKwname() {
		return kwname;
	}

	public void setKwname(String kwname) {
		this.kwname = kwname;
	}

	public Types getTypes() {
		return types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public Long getApplylimit() {
		return applylimit;
	}

	public void setApplylimit(Long applylimit) {
		this.applylimit = applylimit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getApplytime() {
		return applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Long auditstatus) {
		this.auditstatus = auditstatus;
	}

	public Long getUsestatus() {
		return usestatus;
	}

	public void setUsestatus(Long usestatus) {
		this.usestatus = usestatus;
	}

}
