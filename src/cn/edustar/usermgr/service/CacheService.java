package cn.edustar.usermgr.service;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Mar 13, 2008 8:16:55 AM
 */
public interface CacheService {
	public Object get(String key);

	public void put(String key, Object value);

	public void remove(String key);

	public void clear();
}
