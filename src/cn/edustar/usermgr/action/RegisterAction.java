package cn.edustar.usermgr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import cn.edustar.usermgr.common.MD5;
import cn.edustar.usermgr.common.ParamUtil;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.RegisterService;

/**
 * 注册
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Mar 10, 2008 9:03:31 AM
 */
public class RegisterAction extends BaseAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2206683769072462466L;
	
	private ParamUtil param_util;
	private RegisterService registerService;

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	private String loginName;
	private String userPassword;
	private String rePassword;
	private String email;
	private Date createDate = new Date();
	private String lastLoginIp;
	private Date lastLoginTime;
	private int loginTimes;
	private String question;
	private String answer;
	private int usn;

	public String execute() throws Exception {
		this.param_util = new ParamUtil(getActionContext().getParameters());
		String cmd = param_util.safeGetStringParam("cmd");
		if ("register".equalsIgnoreCase(cmd)) {
			return Register();
		} else
			return Input();
	}

	private String Input() {
		return INPUT;
	}
	
	public String remoteRegister() {
		this.param_util = new ParamUtil(getActionContext().getParameters());
		try {
			PrintWriter out = response.getWriter();
			if (!isDuplicateLoginName(param_util.safeGetStringParam("loginName"))) {
				if (!isDuplicateEmail(param_util.safeGetStringParam("email"))) {
					save();
					//request.setAttribute("username", loginName);
					out.print("ok");
				} else {
					out.print(this.getText("usermgr.validation.email.exist"));
				}
			} else {				
				out.print(this.getText("usermgr.validation.loginName.exist"));
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String Register() {
		if (!isDuplicateLoginName(param_util.safeGetStringParam("loginName"))) {
			if (!isDuplicateEmail(param_util.safeGetStringParam("email"))) {
				save();
				request.setAttribute("username", loginName);
				return SUCCESS;
			} else {
				this.addFieldError("email", this.getText("usermgr.validation.email.exist"));
			}
		} else {
			this.addFieldError("loginName", this.getText("usermgr.validation.loginName.exist"));
		}
		return INPUT;
	}

	private void save() {
		User user = new User();
		user.setLoginName(param_util.safeGetStringParam("loginName"));
		user.setUserPassword(MD5.toMD5(param_util.safeGetStringParam("userPassword")));
		user.setEmail(param_util.safeGetStringParam("email"));
		user.setQuestion(param_util.safeGetStringParam("question"));
		user.setAnswer(MD5.toMD5(param_util.safeGetStringParam("answer")));
		Calendar c = Calendar.getInstance();
		user.setCreateDate(c.getTime());
		user.setLastLoginTime(c.getTime());
		user.setCurrentLoginTime(c.getTime());
		user.setLastLoginIp(request.getRemoteAddr());
		user.setCurrentLoginIp(request.getRemoteAddr());
		user.setLoginTimes(0);
		user.setUsn(1);
		user.setRole(0);
		registerService.saveRegister(user);
	}

	private boolean isDuplicateLoginName(String strLoginName) {
		if (this.registerService.isDuplicateLoginName(strLoginName)) {
			return true;
		} else
			return false;
	}

	private boolean isDuplicateEmail(String strEmail) {
		if (this.registerService.isDuplicateEmail(strEmail)) {
			return true;
		} else
			return false;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public int getUsn() {
		return usn;
	}

	public void setUsn(int usn) {
		this.usn = usn;
	}

}
