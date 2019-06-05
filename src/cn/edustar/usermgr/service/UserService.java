package cn.edustar.usermgr.service;

import cn.edustar.usermgr.common.DataTable;
import cn.edustar.usermgr.common.Pager;
import cn.edustar.usermgr.pojos.TicketInfo;
import cn.edustar.usermgr.pojos.User;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 5, 2008 9:25:07 PM
 */
public interface UserService {
	public User getUserByUserId(int userId);

	public User getUserByLoginName(String loginName);

	public User getUserByLoginNameFromCache(String loginName, boolean ifFromCache);

	public DataTable getUserDataTable(UserQueryParam param, Pager pager);

	public User getUserByUserTicket(String sUserTicket);

	public TicketInfo createUserTicket(User user, String ip);

	public void timeoutClear(long TIMEOUT);

	public void updateUserPassword(User user, String newPassword);
	
	public void delUserByLoginName(String loginName);
}
