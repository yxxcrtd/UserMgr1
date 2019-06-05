package cn.edustar.usermgr.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import cn.edustar.usermgr.common.DataTable;
import cn.edustar.usermgr.common.Pager;
import cn.edustar.usermgr.dao.UserDao;
import cn.edustar.usermgr.pojos.TicketInfo;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.CacheService;
import cn.edustar.usermgr.service.UserQueryParam;
import cn.edustar.usermgr.service.UserService;

/**
 * @author Yang XinXin
 * @version 1.0.0, 2008-04-05 10:41:46
 */
public class UserServiceImpl implements UserService {
	private ConcurrentHashMap<String, TicketInfo> TICKET_INFO_MAP = new ConcurrentHashMap<String, TicketInfo>();
	private UserDao userDao;
	private CacheService cacheService;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	public User getUserByLoginNameFromCache(String loginName, boolean ifFromCache) {
		User user = null;
		user = (User) this.cacheService.get(keyForCache(loginName));
		if (user == null) {
			user = getUserByLoginName(loginName);
			if (user != null) {
				putUserToCache(user);
			}
		} else {
			return user;
		}
		return user;
	}

	public User getUserByUserTicket(String sUserTicket) {
		if (null == sUserTicket || sUserTicket.length() == 0) {
			return null;
		}
		
		TicketInfo ticket = TICKET_INFO_MAP.get(sUserTicket);

		if (null == ticket) {
			return null;
		}
		
		ticket.setLastAccessed(new Date());
		return getUserByLoginNameFromCache(ticket.getUserName(), true);
	}

	private void putUserToCache(User user) {
		this.cacheService.put(keyForCache(user.getUserId()), user);
		this.cacheService.put(keyForCache(user.getLoginName()), user);
	}

	private static final String keyForCache(int userId) {
		return "u.id." + userId + ".user";
	}

	private static final String keyForCache(String userLoginName) {
		return "u.name." + userLoginName + ".user";
	}

	public DataTable getUserDataTable(UserQueryParam param, Pager pager) {
		return userDao.getUserDataTable(param, pager);
	}

	public TicketInfo createUserTicket(User user, String ip) {
		TicketInfo ticketInfo = new TicketInfo(user.getLoginName(), ip);
		TICKET_INFO_MAP.put(ticketInfo.getTicket(), ticketInfo);
		return ticketInfo;
	}

	public void timeoutClear(long TIMEOUT) {
		int i = 0;
		try {
			Date dCurDate = new Date();
			String sUserKey = "";
			Iterator<String> oUserinfoList = TICKET_INFO_MAP.keySet().iterator();
			while (oUserinfoList.hasNext()) {
				sUserKey = oUserinfoList.next();
				TicketInfo oTicketInfo = TICKET_INFO_MAP.get(sUserKey);
				if ((dCurDate.getTime() - oTicketInfo.getCreateDate().getTime()) > TIMEOUT) {
					TICKET_INFO_MAP.remove(sUserKey);
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUserPassword(User user, String newPassword) {
		userDao.updateUserPassword(user, newPassword);
	}

	public void delUserByLoginName(String loginName) {
		userDao.delUserByLoginName(loginName);
	}

}
