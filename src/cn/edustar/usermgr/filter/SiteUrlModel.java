package cn.edustar.usermgr.filter;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 15, 2008 3:45:29 PM
 */
public class SiteUrlModel {
	private static ThreadLocal<String> thread_site_url = new ThreadLocal<String>();
	private String default_site_url = "http://localhost/UserMgr/";

	public static final String setCurrentSiteUrl(String site_url) {
		String old_site_url = thread_site_url.get();
		thread_site_url.set(site_url);
		return old_site_url;
	}

	public String getAsString() {
		String url = thread_site_url.get();
		if (url == null || url.length() == 0)
			return default_site_url;
		return url;
	}

	public String getVariableName() {
		return "SiteUrl";
	}

}
