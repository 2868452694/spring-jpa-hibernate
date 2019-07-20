package cn.agent.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 类型
 */
@Entity
@Table(name = "TYPES")
public class Types  implements Serializable {
	/**
	 * 类型编号
	 */
	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sql_type")
	@SequenceGenerator(name = "sql_type",sequenceName ="sqltype")*/
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long typeid;
	/**
	 * 父类编号
	 */
	@Column(name = "PARENTID")
	private Long parentid;
	/**
	 * 类型名称
	 */
	@Column(name = "TYPENAME")
	private String typename;
	/**
	 * 状态，是否启用
	 */
	@Column(name = "STATUS")
	private Long status;
	/**
	 * 类型数值
	 */
	@Column(name = "TYPEVALUE")
	private Long typevalue;
	/**
	 * 实际数值
	 */
	@Column(name = "ACTUALVALUE")
	private Long actualvalue;
	/**
	 * 存在表示，是否存在
	 */
	@Column(name = "EXIST")
	private Long exist;

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTypevalue() {
		return typevalue;
	}

	public void setTypevalue(Long typevalue) {
		this.typevalue = typevalue;
	}

	public Long getActualvalue() {
		return actualvalue;
	}

	public void setActualvalue(Long actualvalue) {
		this.actualvalue = actualvalue;
	}

	public Long getExist() {
		return exist;
	}

	public void setExist(Long exist) {
		this.exist = exist;
	}

}
