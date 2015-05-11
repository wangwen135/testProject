package com.swing.component.table;

import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

/**
 * 描述：表格行标题tableModel<br>
 * 只有一列，返回True Or False
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-7-28      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class RowHeadTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921300996508936323L;

	List<Map<String, Object>> datalist;

	private String name = "是否选择";

	private String key = "ROWSELECTED";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Map<String, Object>> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<Map<String, Object>> datalist) {
		this.datalist = datalist;
		fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (datalist != null) {
			Map<String, Object> rowData = datalist.get(row);

			Object value = rowData.get(key);
			if (value != null) {
				return value;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (datalist != null) {
			Map<String, Object> rowData = datalist.get(row);
			if (aValue instanceof Boolean) {
				rowData.put(key, aValue);
			} else {
				rowData.put(key, false);
			}
		}
		fireTableCellUpdated(row, column);
	}

	@Override
	public int getRowCount() {
		if (datalist != null) {
			return datalist.size();
		} else {
			return 0;
		}
	}

	@Override
	public String getColumnName(int column) {

		return name;
	}

	@Override
	public int getColumnCount() {

		return 1;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return Boolean.class;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}
}
