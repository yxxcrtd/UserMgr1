package cn.edustar.usermgr.service.impl;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import cn.edustar.usermgr.service.CacheService;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Mar 13, 2008 8:16:55 AM
 */
public class EHCacheServiceImpl implements CacheService {
	private CacheManager manager = null;
	private Cache cache = null;

	public void init() {
		this.manager = CacheManager.create();
		this.cache = manager.getCache("usermgrCache");
	}

	public void destroy() {
		if (this.manager != null) {
			this.manager.shutdown();
			this.manager = null;
		}
	}

	public Object get(String key) {
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public void put(String key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	public void remove(String key) {
		cache.remove(key);
	}

	public void clear() {
		cache.removeAll();
	}

}