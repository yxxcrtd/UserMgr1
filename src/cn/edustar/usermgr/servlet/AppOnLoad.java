package cn.edustar.usermgr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edustar.usermgr.TimeOutClearEngine;
import cn.edustar.usermgr.service.UserService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 16, 2008 12:50:25 PM
 */
public class AppOnLoad extends HttpServlet {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5455737052582948067L;

	public AppOnLoad() {
		super();
	}

	public void init() throws ServletException {
		super.init();
		String sTimeOut = this.getServletConfig().getInitParameter("TimeOutSec");
		if (sTimeOut.equals(""))
			sTimeOut = "1800";
		WebApplicationContext app_ctxt = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		UserService user_svc = (UserService)app_ctxt.getBean("userService");
		TimeOutClearEngine.InitEngine(user_svc, Long.valueOf(sTimeOut));
	}

	public void destroy() {
		TimeOutClearEngine.CloseEngine();
		super.destroy();
	}
	
}
