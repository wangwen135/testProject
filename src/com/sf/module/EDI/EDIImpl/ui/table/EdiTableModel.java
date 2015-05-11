package com.sf.module.EDI.EDIImpl.ui.table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

/**
 * 描述：EDI表格模型
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-7      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1064575666122238439L;

	/**
	 * 数据
	 */
	private Vector<Vector<ICell>> dataVector;

	public EdiTableModel() {
		this(0, 0);
	}

	/**
	 * 构造函数
	 * 
	 * @param rowCount
	 *            行数
	 * @param columnCount
	 *            列数
	 */
	public EdiTableModel(int rowCount, int columnCount) {
		dataVector = new Vector<Vector<ICell>>();
		Vector<ICell> row;
		for (int i = 0; i < rowCount; i++) {
			row = new Vector<ICell>();
			row.setSize(columnCount);
			dataVector.add(row);
		}

	}

	public EdiTableModel(Vector<Vector<ICell>> data) {
		this.dataVector = data;
	}

	public Vector<Vector<ICell>> getDataVector() {
		return dataVector;
	}

	public void setDataVector(Vector<Vector<ICell>> dataVector) {
		this.dataVector = dataVector;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return ICell.class;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		Object o = getValueAt(row, column);
		if (o == null) {
			return false;
		}
		return true;
	}

	@Override
	public int getRowCount() {
		if (dataVector != null)
			return dataVector.size();
		return 0;
	}

	@Override
	public int getColumnCount() {
		if (dataVector != null && dataVector.size() > 0) {
			Vector<ICell> v = dataVector.get(0);
			if (v != null)
				return v.size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || columnIndex < 0) {
			return null;
		}
		if (dataVector == null)
			return null;
		Vector<ICell> row = dataVector.get(rowIndex);
		if (row == null || row.size() <= 0 || row.size() <= columnIndex) {
			// 出现这种情况是有问题的

			return null;
		}
		return row.get(columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || columnIndex < 0) {
			return;
		}
		if (dataVector == null)
			return;

		Vector<ICell> row = dataVector.get(rowIndex);
		row.set(columnIndex, (ICell) aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	/**
	 * <pre>
	 * 添加列
	 * </pre>
	 */
	public void addColumn() {
		if (dataVector == null) {
			dataVector = new Vector<Vector<ICell>>();
		}
		Vector<ICell> row;
		for (int i = 0; i < dataVector.size(); i++) {
			row = dataVector.get(i);
			row.add(null);
		}
	}

	/**
	 * <pre>
	 * 在指定位置添加列
	 * </pre>
	 * 
	 * @param index
	 */
	public void addColumn(int index) {
		if (dataVector == null) {
			dataVector = new Vector<Vector<ICell>>();
		}
		Vector<ICell> row;
		for (int i = 0; i < dataVector.size(); i++) {
			row = dataVector.get(i);
			row.add(index, null);
		}
	}

	/**
	 * <pre>
	 * 删除指定列
	 * </pre>
	 * 
	 * @param index
	 */
	public void removeColumn(int index) {
		if (dataVector == null) {
			return;
		}
		Vector<ICell> row;
		for (int i = 0; i < dataVector.size(); i++) {
			row = dataVector.get(i);
			row.remove(index);
		}
	}

	/**
	 * <pre>
	 * 添加行
	 * </pre>
	 */
	public void addRow() {
		if (dataVector == null) {
			dataVector = new Vector<Vector<ICell>>();
		}

		Vector<ICell> row = new Vector<ICell>();
		row.setSize(getColumnCount());
		dataVector.add(row);
		fireTableDataChanged();

	}

	/**
	 * <pre>
	 * 移除行
	 * </pre>
	 * 
	 * @param row
	 */
	public void removeRow(int row) {
		if (dataVector == null) {
			return;
		}
		dataVector.remove(row);
		fireTableDataChanged();
	}

}
