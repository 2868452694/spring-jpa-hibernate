package cn.agent.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */
@Entity
@Table(name = "ROLE")
public class Role  implements Serializable {
	/**
	 * 角色id
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long roleid;
	/**
	 * 角色名
	 */
	@Column(name = "ROLENAME")
	private String rolename;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createtime;
	/**
	 * 是否启用
	 */
	@Column(name = "STATUS")
	private Long status;
	/**
	 * 是否存在 0已存在
	 */
	@Column(name="EXISTSTATUS")
	private Long existstatus;
	/**
	 * 权限
	 */
	@ManyToMany(targetEntity = Jurisdiction.class,cascade = CascadeType.MERGE)
	@JoinTable(
			name="REROLEJD",//中间表名
			joinColumns={@JoinColumn(name="roid")},//当前对象id在中间表的列名
			inverseJoinColumns={@JoinColumn(name="jdid")}//关联的另一个表在中间表的列名
	)
	private Set<Jurisdiction> jurisdictions;

	public
	Set<Jurisdiction> getJurisdictions() {
		return jurisdictions;
	}

	public
	void setJurisdictions(Set<Jurisdiction> jurisdictions) {
		this.jurisdictions = jurisdictions;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getExiststatus() {
		return existstatus;
	}

	public void setExiststatus(Long existstatus) {
		this.existstatus = existstatus;
	}
}
