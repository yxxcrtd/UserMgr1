package cn.edustar.usermgr.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edustar.usermgr.common.DataSchema;
import cn.edustar.usermgr.common.DataTable;
import cn.edustar.usermgr.common.MD5;
import cn.edustar.usermgr.common.Pager;
import cn.edustar.usermgr.common.QueryHelper;
import cn.edustar.usermgr.dao.UserDao;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.UserQueryParam;

/**
 * @author Yang XinXin
 * @version 1.0.0 Mar 26, 2008 1:57:23 PM
 */
@SuppressWarnings("rawtypes")
public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {
	private static final String LOAD_GET_USER_BY_USERID = "FROM User U WHERE (U.userId = ?)";
	private static final String LOAD_GET_USER_BY_LOGINNAME = "FROM User U WHERE (U.loginName = ?)";

	public User getUserByUserId(int userId) {
		List list = this.getHibernateTemplate().find(LOAD_GET_USER_BY_USERID, userId);
		if (list != null && list.size() >= 1) {
			return (User) list.get(0);
		}
		return null;
	}

	public User getUserByLoginName(String loginName) {
		List list = this.getHibernateTemplate().find(LOAD_GET_USER_BY_LOGINNAME, new String[] { loginName });
		if (list != null && list.size() >= 1) {
			return (User) list.get(0);
		}
		System.out.println("UserDaoHibernate中的用户对象为空！");
		return null;
	}

	public DataTable getUserDataTable(UserQueryParam param, Pager pager) {
		QueryHelper queryHelp = new QueryHelper();
		queryHelp.selectClause = "SELECT U.userId, U.loginName, U.nickName, U.email, U.userIcon, U.userStatus, U.province, U.district, U.company";
		queryHelp.fromClause = "FROM User U ";
		queryHelp.orderClause = "ORDER BY userId DESC";
		pager.setTotalRows(queryHelp.queryTotalCount(getHibernateTemplate()));
		List list = queryHelp.queryData(getHibernateTemplate(), pager);
		String schema_str = "userId, loginName, nickName, email, userIcon, userStatus, province, district, company";
		DataTable dt = new DataTable(new DataSchema(schema_str), list);
		return dt;
	}

	public void updateUserPassword(User user, String newPassword) {
		String pwd_md5 = MD5.toMD5(newPassword);
		String hql = "UPDATE User SET userPassword = ? WHERE userId = ?";
		getHibernateTemplate().bulkUpdate(hql, new Object[] { pwd_md5, user.getUserId() });
	}
	
	public void delUserByLoginName(String loginName) {
		User user=getUserByLoginName(loginName);
		getHibernateTemplate().delete(user);
	}
	
}
