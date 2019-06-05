package cn.edustar.usermgr.dao;

import cn.edustar.usermgr.common.DataTable;
import cn.edustar.usermgr.common.Pager;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.UserQueryParam;

/**
 * @author Yang XinXin
 * @version 1.0.0 Mar 26, 2008 1:57:23 PM
 */
public interface UserDao {

	public User getUserByUserId(int userId);

	public User getUserByLoginName(String loginName);

	public DataTable getUserDataTable(UserQueryParam param, Pager pager);

	public void updateUserPassword(User user, String newPassword);

	public void delUserByLoginName(String loginName);

}
