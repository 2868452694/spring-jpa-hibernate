package cn.agent.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 服务年限
 */
@Entity
@Table(name = "SERVICETIME")
public class Servicetime  implements Serializable {
	/**
	 * 服务年限id
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long stid;
	/**
	 * 服务年限名称
	 */
	@Column(name = "STNAME")
	private String stname;
	/**
	 * 服务年限值
	 */
	@Column(name = "STVALUE")
	private Long stvalue;

	public
	Long getStid() {
		return stid;
	}

	public
	void setStid(Long stid) {
		this.stid = stid;
	}

	public
	String getStname() {
		return stname;
	}

	public
	void setStname(String stname) {
		this.stname = stname;
	}

	public
	Long getStvalue() {
		return stvalue;
	}

	public
	void setStvalue(Long stvalue) {
		this.stvalue = stvalue;
	}
}
