package cn.edustar.usermgr.action;

import java.util.Map;
import javax.servlet.http.Cookie;
import cn.edustar.usermgr.pojos.User;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Yang Xinxin
 * @version 1.0.0
 */
@SuppressWarnings("unchecked")
public class LogoutAction extends BaseAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5591434975269911891L;
	
	private String redUrl;

	public String execute() throws Exception {
		if (null == redUrl || "null".equals(redUrl) || "".equals(redUrl) || redUrl.length() == 0) {
			redUrl = "logout.jsp";
		} else {
		}
		Cookie[] cookies = request.getCookies();
		try {
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equalsIgnoreCase("UserTicket")) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						String dynamicDomain = request.getSession().getServletContext().getInitParameter("dynamicDomain");
						if ("".equals(dynamicDomain) || null == dynamicDomain || dynamicDomain.length() < 0) {
							String sDomain = getDomain(request.getServerName());
							if (!sDomain.equals("") && !Character.isDigit(sDomain.charAt(0))) {
								cookie.setDomain(sDomain);
							}
						} else {
							cookie.setDomain(dynamicDomain);			
						}
						response.addCookie(cookie);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(User.SESSION_LOGIN_USERID_KEY, null);
		session.put(User.SESSION_LOGIN_NAME_KEY, null);
		request.setAttribute("username", null);
		request.setAttribute("user", null);
		return SUCCESS;
	}

	public String getRedUrl() {
		return redUrl;
	}

	public void setRedUrl(String redUrl) {
		this.redUrl = redUrl;
	}

}
