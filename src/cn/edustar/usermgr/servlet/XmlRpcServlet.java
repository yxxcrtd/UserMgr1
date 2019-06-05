package cn.edustar.usermgr.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

import cn.edustar.usermgr.service.impl.XmlRpcUserService;

/**
 * XmlRpcServlet
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 16, 2008 12:49:31 PM
 */
@SuppressWarnings("serial")
public class XmlRpcServlet extends HttpServlet {
	private XmlRpcServletServer server;

	public void init(ServletConfig pConfig) throws ServletException {
		super.init(pConfig);
		try {
			server = new XmlRpcServletServer();
			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			phm.addHandler("UserService", XmlRpcUserService.class);
			server.setHandlerMapping(phm);
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) server.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(false);
		} catch (XmlRpcException e) {
			throw new ServletException(e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		server.execute(request, response);
	}
	
	public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException, ServletException {
		server.execute(pRequest, pResponse);
	}

	public void destroy() {
		super.destroy();
	}
	
}
