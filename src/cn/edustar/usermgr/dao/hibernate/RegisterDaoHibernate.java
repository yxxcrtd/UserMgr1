package cn.edustar.usermgr.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edustar.usermgr.dao.RegisterDao;
import cn.edustar.usermgr.pojos.User;

/**
 * @author Yang XinXin
 */
@SuppressWarnings("rawtypes")
public class RegisterDaoHibernate extends HibernateDaoSupport implements RegisterDao {
	private static final String LOAD_IS_DUPLICATE_LOGINNAME = "FROM User U WHERE (U.loginName = ?)";
	private static final String LOAD_IS_DUPLICATE_EMAIL = "FROM User U WHERE (U.email = ?)";

	public void saveRegister(User user) {
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	public boolean isDuplicateLoginName(String strLoginName) {
		List list = this.getHibernateTemplate().find(LOAD_IS_DUPLICATE_LOGINNAME, strLoginName);
		if (list != null && list.size() >= 1) {
			return true;
		} else
			return false;
	}

	public boolean isDuplicateEmail(String strEmail) {
		List list = this.getHibernateTemplate().find(LOAD_IS_DUPLICATE_EMAIL,
				strEmail);
		if (list != null && list.size() >= 1) {
			return true;
		} else
			return false;
	}

}
