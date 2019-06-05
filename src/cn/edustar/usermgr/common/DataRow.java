package cn.edustar.usermgr.common;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 10, 2008 11:02:20 AM
 */
public class DataRow extends java.util.ArrayList<Object> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2066498280345552009L;
	
	private DataTable table;

	public DataRow(DataTable table) {
		this.table = table;
	}

	public DataRow(DataTable table, Object[] values) {
		this(table);
		if (values == null)
			return;
		DataSchema schema = table.getSchema();
		int col_num = (schema.size() < values.length) ? schema.size() : values.length;
		for (int i = 0; i < col_num; ++i) {
			super.add(values[i]);
		}
	}

	public int size() {
		return this.table.getSchema().size();
	}

	public int getRowID() {
		return this.table.indexOf(this);
	}

	public DataSchema getSchema() {
		return this.table.getSchema();
	}

	public DataTable getDataTable() {
		return this.table;
	}

	@Override
	public Object get(int columnIndex) {
		if (columnIndex < super.size() && columnIndex >= 0)
			return super.get(columnIndex);
		else if (columnIndex < table.getSchema().size() && columnIndex >= 0)
			return null;
		else
			return null;
	}

	public Object get(String column_name) {
		int column_index = this.table.getSchema().getColumnIndex(column_name);

		if (column_index < 0) {
			DataTable.FieldCalculator calcor = this.table.findFieldCalcor(column_name);
			if (calcor != null)
				return calcor.calcValue(table, this, column_name);
			return null;
		}
		return get(column_index);
	}

	@Override
	public Object set(int columnIndex, Object value) {
		DataSchema schema = table.getSchema();
		if (super.size() < schema.size()) {
			super.ensureCapacity(schema.size());
			while (super.size() < schema.size()) {
				super.add(null);
			}
		}
		return super.set(columnIndex, value);
	}

	public Object set(String columnName, Object value) {
		int column_index = this.table.getSchema().getColumnIndex(columnName);
		if (column_index < 0)
			throw new IndexOutOfBoundsException("Column '" + columnName + "' not found");
		return this.set(column_index, value);
	}

}
