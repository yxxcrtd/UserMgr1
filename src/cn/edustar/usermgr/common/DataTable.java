package cn.edustar.usermgr.common;

import java.util.HashMap;
import java.util.List;

/**
 * @author Yang Xinxin
 * @version 1.0.0 Apr 10, 2008 11:10:37 AM
 */
@SuppressWarnings("rawtypes")
public class DataTable extends java.util.ArrayList<DataRow> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5629892660702860948L;

	public static final DataTable EMPTY_DATATABLE = new DataTable(DataSchema.EMPTY_SCHEMA);

	public static interface FieldCalculator {
		public Object calcValue(DataTable table, DataRow row, String name);
	}

	private DataSchema schema;
	private HashMap<String, FieldCalculator> calc_fields = new HashMap<String, FieldCalculator>();
	private String comment;

	public DataTable(DataSchema schema) {
		this.schema = schema;
	}

	public DataTable(DataSchema schema, List list) {
		this.schema = schema;
		this.addList(list);
	}

	public int getSize() {
		return this.size();
	}

	public boolean getIsEmpty() {
		return this.isEmpty();
	}

	public DataRow newRow() {
		return new DataRow(this);
	}

	public DataRow newRow(Object[] values) {
		return new DataRow(this, values);
	}

	public boolean addRow(DataRow dataRow) {
		return super.add(dataRow);
	}

	public void addRow(int index, DataRow dataRow) {
		if (dataRow.getDataTable() != this) {
			throw new IllegalArgumentException("addRow(int, DataRow)，增加的数据行与当前数据表的结构不一致。");
		} else {
			super.add(index, dataRow);
		}
	}

	public void addList(List list) {
		if (list == null || list.size() == 0)
			return;
		for (int i = 0; i < list.size(); ++i) {
			Object[] row = (Object[]) list.get(i);
			this.addRow(this.newRow(row));
		}
	}

	public DataSchema getSchema() {
		return this.schema;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public FieldCalculator findFieldCalcor(String name) {
		return this.calc_fields.get(name);
	}

	public void addCalcField(String name, FieldCalculator calc) {
		this.calc_fields.put(name, calc);
	}

	public void dump() {
		if (schema != null) {
			for (int index = 0; index < schema.size(); ++index) {
				String prop = schema.get(index);
				System.out.println("Schema[" + index + "] = " + prop);
			}
		}
		for (int index = 0; index < super.size(); ++index) {
			DataRow row = super.get(index);
			System.out.print("row[" + index + "]=");
			for (int j = 0; j < row.size(); j++) {
				System.out.print(((j > 0) ? ", " : "") + row.get(j));
			}
			System.out.println();
		}
	}

}
