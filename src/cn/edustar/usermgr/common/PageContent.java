package cn.edustar.usermgr.common;

import org.apache.struts2.ServletActionContext;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Mar 17, 2008 4:42:06 PM
 */
public class PageContent {
	public static final String PAGE_UTF8 = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";

	public static String getAppPath() {
		String path = ServletActionContext.getRequest().getContextPath();
		try {
			path = ServletActionContext.getRequest().getContextPath();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		if (path.equals("/")) {
			path = "";
		}
		return path;
	}

}
