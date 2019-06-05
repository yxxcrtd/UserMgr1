package cn.edustar.usermgr.action;

import cn.edustar.usermgr.common.MD5;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.common.ParamUtil;
import cn.edustar.usermgr.service.UserService;
import cn.edustar.usermgr.service.RegisterService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 May 28, 2008 5:08:00 PM
 */
public class FindAction extends BaseAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7100040046660850866L;
	
	private static final int MINIMUM_LENGTH = 5;
	private static final int MAXIMUM_LENGTH = 21;
	private UserService userService;
	private RegisterService registerService;
	private ParamUtil param_util;
	private String username;
	private String question;
	private String answer;
	private String password;
	private String repassword;

	public void validate() {
		if (null == username || username.length() == 0) {
			this.addFieldError("username", this.getText("usermgr.validation.loginName"));
		}
	}

	public String execute() throws Exception {
		this.param_util = new ParamUtil(getActionContext().getParameters());
		User user = userService.getUserByLoginNameFromCache(username, true);
		String cmd = param_util.safeGetStringParam("cmd");
		if ("find".equalsIgnoreCase(cmd))
			return Find();
		else if ("two".equalsIgnoreCase(cmd))
			return Two(user);
		else if ("three".equalsIgnoreCase(cmd))
			return Three(user);
		else if ("four".equalsIgnoreCase(cmd))
			return Four(user);
		return INPUT;
	}

	private String Find() {
		username = param_util.safeGetStringParam("username");
		return INPUT;
	}

	private String Two(User user) {
		if (user == null) {
			this.addActionError(this.getText("usermgr.find.username.error"));
			return INPUT;
		} else {
			question = user.getQuestion();
			if ("".equals(question) || null == question) {
				this.addActionError(this.getText("usermgr.find.question.error"));
				return INPUT;
			}
			return TWO_SUCCESS;
		}
	}

	private String Three(User user) {
		if (user.getAnswer().equals(MD5.toMD5(answer))) {
			return THREE_SUCCESS;
		} else {
			this.addActionError(this.getText("usermgr.find.answer.error"));
			return Two(user);
		}
	}

	private String Four(User user) {
		if (isInvalid(this.getPassword())) {
			this.addFieldError("password", this.getText("usermgr.find.password.input"));
			return Three(user);
		}
		if (isInvalid(this.getRepassword())) {
			this.addFieldError("repassword", this.getText("usermgr.find.repassword.input"));
			return Three(user);
		}
		if (!isInvalidLong(this.getPassword()) && !isInvalidLong(this.getRepassword())) {
			this.addActionError(this.getText("usermgr.find.password.length"));
			return Three(user);
		}
		if (password.equals(repassword)) {
			user.setUserPassword(MD5.toMD5(password));
			registerService.saveRegister(user);
			return SUCCESS;
		} else {
			this.addActionError(this.getText("usermgr.validation.error.password"));
			return Three(user);
		}
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	private boolean isInvalidLong(String value) {
		return (value.length() > MINIMUM_LENGTH && value.length() < MAXIMUM_LENGTH);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
