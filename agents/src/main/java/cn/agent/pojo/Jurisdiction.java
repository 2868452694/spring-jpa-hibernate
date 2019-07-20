package cn.agent.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 权限
 */
@Entity
@Table(name = "JURISDICTION")
public class Jurisdiction  implements Serializable {
	/**
	 * 权限id
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long jdid;
	/**
	 * 权限名称
	 */
	@Column(name = "JDNAME")
	private String jdname;
	/**
	 * 权限url
	 */
	@Column(name = "JDURL")
	private String jdurl;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createtime;
	/**
	 * 使用状态 0=启用 1=停用
	 */
	@Column(name = "STATUS")
	private Long status;
	/**
	 * 角色
	 */
/*
	@ManyToMany(targetEntity = Role.class)

	private Set<Role> roles;
*/

	public Long getJdid() {
		return jdid;
	}

	public void setJdid(Long jdid) {
		this.jdid = jdid;
	}

	public String getJdname() {
		return jdname;
	}

	public void setJdname(String jdname) {
		this.jdname = jdname;
	}

	public String getJdurl() {
		return jdurl;
	}

	public void setJdurl(String jdurl) {
		this.jdurl = jdurl;
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


}
