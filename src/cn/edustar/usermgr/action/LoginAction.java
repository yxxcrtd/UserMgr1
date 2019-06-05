package cn.edustar.usermgr.action;

import java.util.Map;
import java.util.Calendar;
import javax.servlet.http.Cookie;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.pojos.TicketInfo;
import cn.edustar.usermgr.common.ParamUtil;
import com.opensymphony.xwork2.ActionContext;
import cn.edustar.usermgr.service.UserService;
import cn.edustar.usermgr.service.RegisterService;

/**
 * 登录
 * 
 * @author Yang Xinxin
 * @version 1.0.0 May 29, 2008 5:18:01 PM
 */
@SuppressWarnings("unchecked")
public class LoginAction extends BaseAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7459098754066645198L;
	
	private static int INT_SESSION_ABATE_TIME = -1;
	private ParamUtil param_util;
	private String username;
	private String password;
	private String vercode;
	private String redUrl;
	private UserService userService;
	private RegisterService registerService;
	private int userStatus;

	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String ver = (String) session.get("rand");
		session.put("rand", null);
		this.param_util = new ParamUtil(getActionContext().getParameters());
		redUrl = param_util.safeGetStringParam("redUrl");
		if(vercode==null) vercode="";
		if(!(vercode.equals("v")))
		{
			if (!vercode.equalsIgnoreCase(ver)) {
				this.addActionError(this.getText("usermgr.validation.vercode.invalid"));
				return INPUT;
			}
		}
		User user = userService.getUserByLoginName(username);
		if (user == null) {
			this.addActionError(this.getText("usermgr.validation.error"));
			return INPUT;
		}
		userStatus = user.getUserStatus();
		switch (userStatus) {
		case 0:
			break;
		case 1:
			this.addActionError(this.getText("usermgr.user.status.audit"));
			return INPUT;
		case 2:
			this.addActionError(this.getText("usermgr.user.status.deleted"));
			return INPUT;
		default:
			this.addActionError(this.getText("usermgr.user.status.unknown"));
			return INPUT;
		}
		if (equalPassword(user.getUserPassword(), password) == false) {
			this.addActionError(this.getText("usermgr.validation.error"));
			return INPUT;
		}
		TicketInfo ticket = userService.createUserTicket(user, request.getRemoteAddr());
		writeClientCookie(user, ticket);
		updateLoginInfo(user);
		session.put(User.SESSION_LOGIN_USERID_KEY, user.getUserId());
		session.put(User.SESSION_LOGIN_NAME_KEY, username);
		request.setAttribute("username", username);
		request.setAttribute("user", user);
		return LOGIN_SUCCESS;
	}

	private void writeClientCookie(User user, TicketInfo ticket) {
		Cookie cookie = new Cookie("UserTicket", ticket.getTicket());
		cookie.setMaxAge(INT_SESSION_ABATE_TIME);
		String dynamicDomain = request.getSession().getServletContext().getInitParameter("dynamicDomain");
		if ("".equals(dynamicDomain) || null == dynamicDomain || dynamicDomain.length() < 0) {
			String sDomain = getDomain(request.getServerName());
			if (!sDomain.equals("") && !Character.isDigit(sDomain.charAt(0))) {
				cookie.setDomain(sDomain);
			}
		} else {
			cookie.setDomain(dynamicDomain);			
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	private void updateLoginInfo(User user) {
		user.setLastLoginIp(user.getCurrentLoginIp());
		user.setLastLoginTime(user.getCurrentLoginTime());
		user.setCurrentLoginIp(request.getRemoteAddr());
		Calendar c = Calendar.getInstance();
		user.setCurrentLoginTime(c.getTime());
		int oldLoginTime = user.getLoginTimes();
		int newLoginTime = oldLoginTime + 1;
		user.setLoginTimes(newLoginTime);
		registerService.saveRegister(user);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public String getRedUrl() {
		return redUrl;
	}

	public void setRedUrl(String redUrl) {
		this.redUrl = redUrl;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	
}
