package cn.edustar.usermgr.common;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Mar 17, 2008 4:42:06 PM
 */
@SuppressWarnings("rawtypes")
public class QueryHelper extends HqlHelperBase {
	public String selectClause = "";
	public String fromClause = "";
	public String whereClause = "";	public String orderClause = "";
	public void addAndWhere(String condition) {
		if (this.whereClause == "")
			this.whereClause = " WHERE (" + condition + ")";
		else
			this.whereClause += " AND (" + condition + ")";
	}
	
	public void addOrder(String order) {
		if (this.orderClause == "")
			this.orderClause = " ORDER BY " + order;
		else
			this.orderClause += ", " + order;
	}
	
	public int queryTotalCount(HibernateTemplate hiber) {
		final String hql = "SELECT COUNT(*) " + this.fromClause + " " + this.whereClause;
		Object result = hiber.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				initQuery(query);
				return query.uniqueResult();
			}});
		return safeGetIntResult(result);
	}
	
	public List queryData(HibernateTemplate hiber) {
		return queryData(hiber, -1, -1);
	}
	
	public Object querySingleData(HibernateTemplate hiber) {
		List list = queryData(hiber, 0, 1);
		if (list == null || list.size() == 0) return null;
		return list.get(0);
	}
	
	public int queryIntValue(HibernateTemplate hiber) {
		List list = queryData(hiber, 0, 1);
		if (list == null || list.size() == 0) return 0;
		Object v = list.get(0);
		if (v == null) return 0;
		if (v instanceof Number) return ((Number)v).intValue();
		return 0;
	}
	
	public List queryData(HibernateTemplate hiber, final int first_result, final int max_results) {
		final String hql = this.selectClause + " " + this.fromClause + " " + this.whereClause + " " + this.orderClause; 
		return hiber.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				initQuery(query);
				if (first_result != -1)
					query.setFirstResult(first_result);
				if (max_results != -1)
					query.setMaxResults(max_results);
				return query.list();
			}
		});
	}
	
	public List queryData(HibernateTemplate hiber, final Pager page_info) {
		return queryData(hiber, (page_info.getCurrentPage() - 1) * page_info.getPageSize(), page_info.getPageSize());
	}

	private static final int safeGetIntResult(Object v) {
		if (v == null) return 0;
		if (v instanceof Integer) return (Integer)v;
		if (v instanceof Number) return ((Number)v).intValue();
		return 0;
	}

}
