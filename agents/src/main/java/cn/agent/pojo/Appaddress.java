package cn.agent.pojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * App地址
 */
@Entity
@Table(name = "APPADDRESS")
public class Appaddress  implements Serializable {
	/**
	 * App编号
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long appid;
	/**
	 * 配置名称
	 */
	@Column(name = "CONFIGNAME")
	private String configname;
	/**
	 * 配置数值
	 */
	@Column(name = "CONFIGVALUE")
	private String configvalue;

	public
	Long getAppid() {
		return appid;
	}

	public
	void setAppid(Long appid) {
		this.appid = appid;
	}

	public
	String getConfigname() {
		return configname;
	}

	public
	void setConfigname(String configname) {
		this.configname = configname;
	}

	public
	String getConfigvalue() {
		return configvalue;
	}

	public
	void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
	}

}
