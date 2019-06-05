package cn.edustar.user;

/**
 * 用户在教研系统/统一用户中的常量定义
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 16, 2008 12:55:01 PM
 */
public interface UserConst {

	/** 用户状态，0 - 正常 */
	public static final int USER_STATUS_NORMAL = 0;
	
	/** 用户状态，1 - 待审核 */
	public static final int USER_STATUS_WAIT_AUTID = 1;
	
	/** 用户状态，2 - 已删除 */
	public static final int USER_STATUS_DELETED = 2;
	
	/** 用户状态，3 - 已锁定 */
	public static final int USER_STATUS_LOCKED = 3;

	/** 用户性别：0 - 女 */
	public static final short USER_GENDER_FEMALE = 0;
	
	/** 用户性别：1 - 男 */
	public static final short USER_GENDER_MALE = 1;
	
	/** 用户性别：3 - 未知或未填 */
	public static final short USER_GENDER_UNKNOWN = 3;
	
}
