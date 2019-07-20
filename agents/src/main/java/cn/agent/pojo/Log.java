package cn.agent.pojo;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * log日志表
 */
@Entity
@Table(name = "LOG")
public class Log  implements Serializable {
	public
	Log(Users users, String loginfo, Date logtime) {
		this.users = users;
		this.loginfo = loginfo;
		this.logtime = logtime;
	}

	public
	Log() {
	}

	/**
	 * 日志id
	 */
	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sql_Log")
	@SequenceGenerator(name = "sql_Log",sequenceName ="seqLog")*/
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment",strategy = "increment")
	private Long logid;
/*	*//**
	 * 用户id
	 *//*
	@Column(name = "USERID")
	private Long userid;*/
	/**
	 * 用户
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="USERID")
	private Users users;

	/**
	 * 日志信息
	 */
	@Column(name = "LOGINFO")
	private String loginfo;
	/**
	 * 操作时间
	 */
	@Column(name = "LOGTIME")
	private Date logtime;

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

/*	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}*/

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}

	public Date getLogtime() {
		return logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

}

