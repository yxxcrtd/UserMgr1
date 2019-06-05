package cn.edustar.usermgr.common;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 10, 2008 11:07:00 AM
 */
public class DataSchema extends java.util.ArrayList<String> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6441826654594884995L;
	
	public static final DataSchema EMPTY_SCHEMA = new DataSchema();
	private java.util.HashMap<String, Integer> name_map = null;
	private int map_build_count = -1;

	public DataSchema() {
		super.modCount = 1;
	}

	public DataSchema(String columns) {
		this(parseSelectFields(columns));
	}

	public static final String[] parseSelectFields(String fields) {
		if (fields == null || fields.trim().length() == 0)
			return new String[] {};
		if (fields.toUpperCase().startsWith("SELECT "))
			fields = fields.substring("SELECT ".length());
		fields = fields.trim();

		String[] cols = fields.split(",");
		for (int i = 0; i < cols.length; ++i) {
			cols[i] = parseSingleField(cols[i]);
		}
		return cols;
	}

	private static final String parseSingleField(String field) {
		if (field == null || field.trim().length() == 0)
			return "";
		field = field.trim();
		String[] tokens = field.split("\\s");
		if (tokens.length == 0)
			return "";
		if (tokens.length == 1)
			return parseFieldFinal(tokens[0]);
		if (tokens.length == 2)
			return parseFieldFinal(tokens[1]); // 'title Title'
		return parseFieldFinal(tokens[tokens.length - 1]);
	}

	private static final String parseFieldFinal(String field) {
		if (field == null)
			return "";
		int index = field.lastIndexOf('.');
		if (index < 0)
			return field;
		return field.substring(index + 1);
	}

	public DataSchema(String[] columns) {
		for (int i = 0; i < columns.length; i++) {
			this.add(columns[i]);
		}
	}

	public int addColumn(String columnName) {
		super.add(columnName);
		return super.size() - 1;
	}

	public int getColumnCount() {
		return super.size();
	}

	public String getColumnName(int index) {
		return super.get(index);
	}

	public int getColumnIndex(String column_name) {
		if (super.modCount != this.map_build_count) {
			java.util.HashMap<String, Integer> map = new java.util.HashMap<String, Integer>();
			for (int index = 0; index < super.size(); ++index) {
				map.put(super.get(index), new Integer(index));
			}
			this.map_build_count = super.modCount;
			this.name_map = map;
		}
		Integer column_index = this.name_map.get(column_name);
		if (column_index == null)
			return -1;
		return column_index.intValue();
	}

}
