package cn.edustar.usermgr.pojos;

import java.util.Date;
import java.util.UUID;

/**
 * 票证对象
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 11, 2008 9:27:21 AM
 */
public class TicketInfo {
	private final String ticket = UUID.randomUUID().toString().toLowerCase();
	private String userName;
	private Date createDate = new Date();
	private Date lastAccessed = new Date();
	private String ip;

	public TicketInfo(String userName, String ip) {
		this.userName = userName;
		this.ip = ip;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getTicket() {
		return this.ticket;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public String getIp() {
		return this.ip;
	}

	public Date getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

}
