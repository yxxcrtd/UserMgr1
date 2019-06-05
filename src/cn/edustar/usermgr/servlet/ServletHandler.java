package cn.edustar.usermgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletHandler
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 16, 2008 12:53:25 PM
 */
public interface ServletHandler {

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
