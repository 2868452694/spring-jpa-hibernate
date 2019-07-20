package cn.agent.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 财务明细
 */
@Entity
@Table(name = "FINANCE")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "types" })
public class Finance  implements Serializable {
	/**
	 * 财务明细编号
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long finaid;  //财务明细编号

	/**
	 * 用户编号
	 */
	@Column(name = "USERID")
	private Long userid;  //用户编号

	@Column(name = "FINATYPE")
	private  Long finatype;


	/**
	 * 财务类型
	 */
	/*@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="FINATYPE")
	private Types types;*/
	/**
	 * 账户余额
	 */
	@Column(name = "BALANCE")
	private Double balance;

	/*public Types getTypes() {
		return types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}*/

	/**
	 * 操作资金
	 */
	@Column(name = "OPERATIONMONEY")
	private Double operationmoney;
	/**
	 * 操作描述
	 */
	@Column(name = "DESCRIPTION")
	private String description;
	/**
	 *操作时间
	 */
	@Column(name = "CREATETIME")
	private Date createtime;

	public Long getFinaid() {
		return finaid;
	}

	public void setFinaid(Long finaid) {
		this.finaid = finaid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getOperationmoney() {
		return operationmoney;
	}

	public void setOperationmoney(Double operationmoney) {
		this.operationmoney = operationmoney;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getFinatype() {
		return finatype;
	}

	public void setFinatype(Long finatype) {
		this.finatype = finatype;
	}
}
