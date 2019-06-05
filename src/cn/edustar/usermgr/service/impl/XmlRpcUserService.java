package cn.edustar.usermgr.service.impl;

import cn.edustar.user.BaseUserInfo;
import cn.edustar.usermgr.UserContext;
import cn.edustar.usermgr.pojos.User;

/**
 * @author Yang XinXin
 * @version 1.0.0 Apr 16, 2008 12:49:31 PM
 */
public class XmlRpcUserService {
	public BaseUserInfo getUserByTicket(String ticket, String ip) {
		UserContext userContext = UserContext.getCurrentUserContext();		
		User user = userContext.getUserService().getUserByUserTicket(ticket);
		if (user == null) {
			return null;
		}
		BaseUserInfo baseUserInfo = new BaseUserInfo();
		if (null != user) {
			copyBaseUserInfo(baseUserInfo, user);
			System.out.println("(验证成功)Ticket=" + ticket + "，IP=" + ip + "，userId=" + user.getUserId() + "，loginName=" + user.getLoginName());
		}
		return baseUserInfo;
	}

	private void copyBaseUserInfo(BaseUserInfo bu, User user) {
		bu.setUserId(user.getUserId());
		bu.setUserGuid(user.getUserGuid());
		bu.setLoginName(user.getLoginName());
		bu.setUserPassword("");
		bu.setEmail(user.getEmail());
		bu.setCreateDate(user.getCreateDate());
		bu.setLastLoginIp(user.getLastLoginIp());
		bu.setLastLoginTime(user.getLastLoginTime());
		bu.setLoginTimes(user.getLoginTimes());
		bu.setUsn(user.getUsn());
	}
	
}
