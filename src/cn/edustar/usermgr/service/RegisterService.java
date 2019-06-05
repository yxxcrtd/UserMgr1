package cn.edustar.usermgr.service;

import cn.edustar.usermgr.pojos.User;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 9, 2008 12:39:22 PM
 */
public interface RegisterService {
	public void saveRegister(User user);

	public boolean isDuplicateLoginName(String strLoginName);

	public boolean isDuplicateEmail(String strEmail);
}
