package cn.edustar.usermgr.pojos;

import java.io.Serializable;
import cn.edustar.user.BaseUserInfo;

/**
 * 统一用户对象
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 5, 2008 9:37:01 PM
 */
public class User extends BaseUserInfo implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6056246015995021236L;
	
	public static final String SESSION_LOGIN_USERID_KEY = "jitar.login.userId";
	
	public static final String SESSION_LOGIN_NAME_KEY = "jitar.login.loginName";
	
}
