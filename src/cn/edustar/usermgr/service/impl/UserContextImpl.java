package cn.edustar.usermgr.service.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import cn.edustar.usermgr.UserContext;
import cn.edustar.usermgr.service.UserService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 15, 2008 1:40:34 PM
 */
public class UserContextImpl extends UserContext implements ServletContextAware, ApplicationContextAware {
	private ServletContext servletContext;
	private ApplicationContext applicationContext;
	private UserService userService;
	private UserContext userContext;
	private Map<String, Object> attribute = new Hashtable<String, Object>();

	public UserContextImpl() {
	}

	public void init() {
		servletContext.setAttribute(USER_CONTEXT_KEY_NAME, this);
	}

	public void destroy() {
	}

	public String getVersion() {
		return "1.0.0";
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	public UserService getUserService() {
		if (this.userService == null) {
			this.userService = (UserService) this.applicationContext.getBean("userService");
		}
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserContext getUserContext() {
		if (userContext == null) {
			this.userContext = (UserContext) this.applicationContext.getBean("userContext");
		}
		return this.userContext;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public Object getAttribute(String key) {
		return this.attribute.get(key);
	}

	public void setAttribute(String key, Object value) {
		this.attribute.put(key, value);
	}
	
}
