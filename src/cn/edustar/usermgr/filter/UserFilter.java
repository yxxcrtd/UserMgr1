package cn.edustar.usermgr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.edustar.usermgr.UserContext;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 3, 2008 3:13:08 PM
 */
public class UserFilter implements Filter {
	private String encoding = "UTF-8";
	private FilterConfig filterConfig = null;
	private boolean ignore = false;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignoreEncoding");
		if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value))
			this.ignore = true;
		else
			this.ignore = false;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest http_req = (HttpServletRequest) request;
		UserContext.setCurrentUserContext((UserContext) filterConfig.getServletContext().getAttribute(UserContext.USER_CONTEXT_KEY_NAME));
		SiteUrlModel.setCurrentSiteUrl(getCurrentSiteUrl(http_req));
		if (!ignore && request.getCharacterEncoding() == null) {
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
				response.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);	
	}

	public void destroy() {
		this.encoding = null;
	}

	private static final String getCurrentSiteUrl(HttpServletRequest request) {
		String root = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath() + "/";
		return root;
	}

}
