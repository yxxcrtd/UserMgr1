package cn.edustar.usermgr.action;

import java.io.PrintWriter;

import cn.edustar.usermgr.common.PageContent;
import cn.edustar.usermgr.common.Pager;
import cn.edustar.usermgr.common.ParamUtil;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.UserQueryParam;
import cn.edustar.usermgr.service.UserService;

/**
 * 用户管理
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Mar 10, 2008 9:03:31 AM
 */
public class UserAction extends BaseAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1731437879109241825L;
	
	private ParamUtil param_util;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		this.param_util = new ParamUtil(getActionContext().getParameters());
		String cmd = param_util.safeGetStringParam("cmd");
		if ("list".equalsIgnoreCase(cmd) || null == cmd || "".equalsIgnoreCase(cmd))
			return List();
		else if ("update".equalsIgnoreCase(cmd))
			return Update();
		else if ("saveupdpwd".equalsIgnoreCase(cmd))
			return this.saveUpdatePassword();
		else if ("remoteupdpwd".equalsIgnoreCase(cmd))
			return this.remoteUpdatePassword();		
		else if ("remotedeluser".equalsIgnoreCase(cmd))
			return this.remotedelUser();
		return ERROR;
	}

	private String List() throws Exception {
		try {
			int page = param_util.safeGetIntParam("page", 1);
			UserQueryParam param = new UserQueryParam();
			param.uId = 1;
			Pager pager = new Pager();
			pager.setCurrentPage(page);
			pager.setPageSize(10);
			pager.setItemNameAndUnit("用户", "个");
			Object user_list = this.userService.getUserDataTable(param, pager);
			request.setAttribute("userList", user_list);
			request.setAttribute("pager", pager);
			return LIST_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500, e.getMessage());
			return NONE;
		}
	}

	private String Update() throws Exception {
		return UPDATE_SUCCESS;
	}

	private String remoteUpdatePassword() throws Exception {
		PrintWriter out = response.getWriter();
		String newpassword = param_util.safeGetStringParam("newpassword");
		String loginName = param_util.safeGetStringParam("loginName");
		User user = userService.getUserByLoginName(loginName);
		userService.updateUserPassword(user, newpassword);
		out.print("ok");
		return NONE;
	}
	
	private String remotedelUser() throws Exception{
		PrintWriter out = response.getWriter();
		String loginName = param_util.safeGetStringParam("loginName");
		if(userService.getUserByLoginName(loginName)!=null){
		userService.delUserByLoginName(loginName);
		}
		out.print("ok");
		return NONE;
	}

	private String saveUpdatePassword() throws Exception {
		PrintWriter out = response.getWriter();
		String oldpassword = param_util.safeGetStringParam("oldpassword");
		String newpassword = param_util.safeGetStringParam("newpassword");
		String loginName = param_util.safeGetStringParam("loginName");
		User user = userService.getUserByLoginName(loginName);
		if (equalPassword(user.getUserPassword(), oldpassword) == false) {
			out.append("<html><head>");
			out.append(PageContent.PAGE_UTF8);
			out.println("</head><body><script>alert('输入的旧密码不正确！');window.history.go(-1);</script></body></html>");
			out.flush();
			out.close();
			return NONE;
		}
		userService.updateUserPassword(user, newpassword);
		out.append("<html><head>");
		out.append(PageContent.PAGE_UTF8);
		out.println("</head><body><script>alert('    修改成功！');window.history.go(-1);</script></body></html>");
		out.flush();
		out.close();
		return NONE;
	}

}
