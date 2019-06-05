package cn.edustar.usermgr.service.impl;

import cn.edustar.usermgr.dao.RegisterDao;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.RegisterService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 9, 2008 12:39:22 PM
 */
public class RegisterServiceImpl implements RegisterService {
	private RegisterDao registerDao;

	public void setRegisterDao(RegisterDao registerDao) {
		this.registerDao = registerDao;
	}

	public void saveRegister(User user) {
		this.registerDao.saveRegister(user);
	}

	public boolean isDuplicateLoginName(String strLoginName) {
		return this.registerDao.isDuplicateLoginName(strLoginName);
	}

	public boolean isDuplicateEmail(String strEmail) {
		return this.registerDao.isDuplicateEmail(strEmail);
	}

}
