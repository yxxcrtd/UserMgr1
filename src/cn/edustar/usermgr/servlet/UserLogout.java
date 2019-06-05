package cn.edustar.usermgr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注销
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Aug 16, 2010 13:52:25 PM
 */
public class UserLogout extends HttpServlet {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6491107448235302229L;

	/**
	 * 构造方法
	 */
	public UserLogout() {
		super();
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// 
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String sUniqueId = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cCookie = cookies[i];
				if (cCookie.getName().equalsIgnoreCase("UserTicket")) {
					sUniqueId = cCookie.getValue();
					break;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

}
