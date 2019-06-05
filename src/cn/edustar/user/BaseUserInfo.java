package cn.edustar.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一用户对象
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 16, 2008 12:49:31 PM
 */
public class BaseUserInfo implements Serializable, UserConst {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8892218994359754751L;
	
	private int userId;
	private String userGuid = java.util.UUID.randomUUID().toString().toUpperCase();
	private String loginName;
	private String trueName;
	private String userPassword;
	private String email;
	private Date createDate = new Date();
	private int userStatus;
	private String currentLoginIp;
	private Date currentLoginTime;
	private String lastLoginIp;
	private Date lastLoginTime;
	private int loginTimes;
	private String question;
	private String answer;
	private int usn;
	private int role;
	private String roleName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getUsn() {
		return usn;
	}

	public void setUsn(int usn) {
		this.usn = usn;
	}

	public String getCurrentLoginIp() {
		return currentLoginIp;
	}

	public void setCurrentLoginIp(String currentLoginIp) {
		this.currentLoginIp = currentLoginIp;
	}

	public Date getCurrentLoginTime() {
		return currentLoginTime;
	}

	public void setCurrentLoginTime(Date currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
