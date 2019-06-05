package cn.edustar.usermgr;

import javax.servlet.ServletContext;

import cn.edustar.usermgr.service.UserService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 15, 2008 1:45:12 PM
 */
public abstract class UserContext {
	public static final String USER_CONTEXT_KEY_NAME = "cn.edustar.usermgr.common.UserContext";
	private static final ThreadLocal<UserContext> user_ctxt = new ThreadLocal<UserContext>();

	public static final UserContext getCurrentUserContext() {
		return user_ctxt.get();
	}

	public static final UserContext setCurrentUserContext(UserContext ctxt) {
		UserContext old = user_ctxt.get();
		user_ctxt.set(ctxt);
		return old;
	}

	public abstract String getVersion();

	public abstract ServletContext getServletContext();

	public abstract Object getAttribute(String key);

	public abstract void setAttribute(String key, Object value);

	public abstract UserService getUserService();
	
}
