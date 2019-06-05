package cn.edustar.usermgr.common;

import java.util.Map;
import java.util.List;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Mar 17, 2008 4:42:06 PM
 */
@SuppressWarnings("rawtypes")
public class ParamUtil {
	public static final Integer ZERO_INT = new Integer(0);
	public static final String EMPTY_STRING = "";
	private Map param_map;

	public ParamUtil(Map param_map) {
		this.param_map = param_map;
	}

	public String toString() {
		if (param_map == null)
			return "{}";
		StringBuffer strbuf = new StringBuffer(1024);
		strbuf.append("Parameter{");
		boolean first = true;
		for (Object key : param_map.keySet()) {
			if (first == false)
				strbuf.append(", ");
			strbuf.append(key).append("=");
			Object value = param_map.get(key);
			if (value == null || !(value instanceof Object[]))
				strbuf.append(value);
			else {
				Object[] a = (Object[]) value;
				strbuf.append("[");
				for (int j = 0; j < a.length; ++j) {
					strbuf.append(a[j]);
					if (j < a.length - 1)
						strbuf.append(",");
				}
				strbuf.append("]");
			}
			first = false;
		}
		strbuf.append("}");
		return strbuf.toString();
	}

	protected String getRequestParam(String key) {
		Object v = param_map.get(key);
		if (v == null)
			return null;
		if (v instanceof String)
			return (String) v;
		if (v instanceof String[]) {
			String[] sa = (String[]) v;
			if (sa.length == 0)
				return "";
			if (sa.length == 1)
				return sa[0];
			if (sa.length == 2)
				return sa[0] + "," + sa[1];
			StringBuilder strbuf = new StringBuilder();
			strbuf.append(sa[0]);
			for (int i = 1; i < sa.length; ++i)
				strbuf.append(",").append(sa[i]);
			return strbuf.toString();
		}
		return v.toString();
	}

	protected String[] getRequestParamValues(String key) {
		Object val = this.param_map.get(key);
		if (val == null)
			return null;
		if (val instanceof String)
			return new String[] { (String) val };
		return (String[]) val;
	}

	public static final boolean isInteger(String val) {
		if (val == null)
			return false;
		if (val.length() == 0)
			return false;
		val = val.trim();
		if (val.length() == 0)
			return false;
		for (int i = 0; i < val.length(); ++i) {
			char c = val.charAt(i);
			if (c == '+' || c == '-')
				continue;
			if (c > '9' || c < '0')
				return false;
		}
		try {
			Integer.parseInt(val);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	public static final boolean isEmptyString(String val) {
		if (val == null)
			return true;
		if (val.length() == 0)
			return true;
		if (val.trim().length() == 0)
			return true;
		return false;
	}

	public Integer safeGetIntParam(String key) {
		return safeGetIntParam(key, ZERO_INT);
	}

	public Integer safeGetIntParam(String key, Integer defval) {
		String val = getRequestParam(key);
		if ("null".equalsIgnoreCase(val))
			return null;
		if (isInteger(val) == false)
			return defval;
		return Integer.parseInt(val);
	}

	public Boolean safeGetBooleanParam(String key) {
		return safeGetBooleanParam(key, null);
	}

	public Boolean safeGetBooleanParam(String key, Boolean defval) {
		String val = getRequestParam(key);
		if (val == null || val.trim().length() == 0)
			return defval;
		val = val.trim();
		if (val.equals("0"))
			return Boolean.FALSE;
		else if (val.equals("1"))
			return Boolean.TRUE;
		else if (val.equalsIgnoreCase("yes"))
			return Boolean.TRUE;
		else if (val.equalsIgnoreCase("no"))
			return Boolean.FALSE;
		else if ("null".equalsIgnoreCase(val))
			return null;
		else {
			try {
				return Boolean.parseBoolean(val);
			} catch (Exception ex) {
				return defval;
			}
		}
	}

	public boolean isIntegerParam(String key) {
		String val = getRequestParam(key);
		return isInteger(val);
	}

	public static final boolean isBoolean(String val) {
		if (val == null) {
			return false;
		} else if (val.trim().length() < 1) {
			return false;
		} else {
			try {
				Boolean.parseBoolean(val);
			} catch (Exception ex) {
				return false;
			}
			return true;
		}
	}

	public static final boolean isEnglishName(String val) {
		if (val == null || val.length() == 0)
			return false;
		for (int i = 0; i < val.length(); i++) {
			char ch = val.charAt(i);
			if (ch < 'A' || (ch > 'Z' && ch < 'a') || ch > 'z') {
				return false;
			}
		}
		return true;
	}

	public static final boolean isBlankString(String val) {
		if (val == null || val.trim().length() < 1) {
			return true;
		} else {
			return false;
		}
	}

	public String safeGetStringParam(String key) {
		return safeGetStringParam(key, "");
	}

	public String safeGetStringParam(String key, String defval) {
		String val = getRequestParam(key);
		if (val == null)
			return defval;
		return val.trim();
	}

	public List<Integer> safeGetIntValues(String key) {
		String[] values = getRequestParamValues(key);
		java.util.ArrayList<Integer> id_array = new java.util.ArrayList<Integer>();
		if (values == null || values.length == 0)
			return id_array;
		for (int i = 0; i < values.length; ++i) {
			if (values[i] == null || values[i].length() == 0)
				continue;
			String[] val_split = values[i].split(",");
			for (int j = 0; j < val_split.length; ++j) {
				try {
					id_array.add(Integer.parseInt(val_split[j]));
				} catch (NumberFormatException ex) {
				}
			}
		}
		return id_array;
	}

	public java.util.Date safeGetDate(String key) {
		return safeGetDate(key, new java.util.Date());
	}

	@SuppressWarnings("deprecation")
	public java.util.Date safeGetDate(String key, java.util.Date defval) {
		String temp = safeGetStringParam(key);
		if (temp == null || temp.length() == 0)
			return defval;
		try {
			return new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(temp);
		} catch (Exception ex) {
		}
		try {
			return java.text.DateFormat.getInstance().parse(temp);
		} catch (Exception ex) {
		}
		try {
			return new java.util.Date(java.util.Date.parse(temp));
		} catch (Exception ex) {
		}
		return defval;
	}
	
}
