package cn.edustar.usermgr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edustar.usermgr.common.MD5;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 7, 2008 5:16:04 PM
 */
public abstract class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2267879469387092561L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private String action = "index";
	public static final String LIST_SUCCESS = "List_Success";
	public static final String LOGIN_SUCCESS = "Login_Success";
	public static final String UPDATE_SUCCESS = "Update_Success";
	public static final String TWO_SUCCESS = "Two_Success";
	public static final String THREE_SUCCESS = "Three_Success";

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDomain(String sDomain) {
		String sResStr = "";
		if (sDomain.contains(".")) {
			String[] sTempVar = sDomain.split("\\.");
			boolean bIpNum = false;
			if (bIpNum) {
				sResStr = "";
			} else {
				switch (sTempVar.length) {
				case 2:
					sResStr = sDomain;
					break;
				default:
					sResStr = sDomain.substring(sDomain.indexOf(".") + 1);
					break;
				}
			}
		} else {
			sResStr = "";
		}
		return sResStr;
	}

	public boolean equalPassword(String dbUserPassword, String upwd) {
		String md5_pwd = MD5.toMD5(upwd);
		if (md5_pwd.equalsIgnoreCase(dbUserPassword))
			return true;
		if (dbUserPassword.length() == 16) {
			if (md5_pwd.indexOf(dbUserPassword) > -1)
				return true;
		}
		return false;
	}

}
