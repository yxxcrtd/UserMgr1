package cn.edustar.usermgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.UserService;

/**
 * 统一用户验证
 * 
 * @author Yang XinXin
 * @version 1.0.0 Apr 16, 2008 12:49:31 PM
 */
public class VerUserBean extends ServletBeanBase {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ticket = request.getParameter("UserTicket");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("用户票证：" + ticket); 
		User user = userService.getUserByUserTicket(ticket);
		
		// 输出格式：用户Id, 登录名, 密码, GUID, 真实姓名, 角色Id, usn
		if (null != user) {
			System.out.println("验证成功！");
			out.println(user.getUserId() + "," 
					+ user.getLoginName() + ","
					+ user.getUserPassword() + "," 
					+ user.getUserGuid() + ","
					+ URLEncoder.encode(user.getTrueName(), "UTF-8") + ","
					+ user.getRole() + ","
					+ user.getUsn());
			System.out.println("统一用户输出的信息：" 
					+ user.getUserId() + "," 
					+ user.getLoginName() + "," 
					+ user.getUserPassword() + "," 
					+ user.getUserGuid() + "," 
					+ URLEncoder.encode(user.getTrueName(), "UTF-8") + "," 
					+ user.getRole() + "," 
					+ user.getUsn());
		} else {
			out.println("<script>alert('error');</script>");
			System.out.println("验证出错！");
		}
		out.flush();
		out.close();
	}
	
}
