package com.jTableWWH.tableRowHead;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 描述：行头 表格模型
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-9-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class HeadTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7141273193092453620L;

	private List<RowHeadEntity> dataList = new ArrayList<RowHeadEntity>();

	public HeadTableModel() {

	}

	public HeadTableModel(int rowCount) {
		RowHeadEntity entity;
		for (int i = 0; i < rowCount; i++) {
			entity = new RowHeadEntity(true, 30);
			dataList.add(entity);
		}
	}

	@Override
	public int getRowCount() {
		return dataList.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return dataList.get(rowIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		dataList.set(rowIndex, (RowHeadEntity) aValue);

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	/**
	 * 获取数据列表
	 * 
	 * @return
	 */
	public List<RowHeadEntity> getDataList() {
		return dataList;
	}

	/**
	 * 
	 * @param rowIndex
	 * @return
	 */
	public RowHeadEntity getRowHeadEntity(int rowIndex) {
		return dataList.get(rowIndex);
	}

	/**
	 * 重新设置新的数据列表
	 * 
	 * @param dataList
	 */
	public void setDataList(List<RowHeadEntity> dataList) {
		if (dataList == null) {
			throw new IllegalArgumentException("Cannot set a null dataList");
		}
		this.dataList = dataList;
		fireTableDataChanged();
	}

	/**
	 * 在指定的位置添加一行
	 * 
	 * @param rowId
	 * @param entity
	 */
	public void addRow(int index, RowHeadEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"Cannot set a null RowHeadEntity");
		}
		dataList.add(index, entity);
		fireTableRowsInserted(index, dataList.size());
	}

	/**
	 * 在末尾添加一行
	 * 
	 * @param entity
	 */
	public void addRow(RowHeadEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"Cannot set a null RowHeadEntity");
		}
		dataList.add(entity);
		fireTableRowsInserted(dataList.size(), dataList.size());
	}

	/**
	 * 删除一行
	 * 
	 * @param rowId
	 */
	public RowHeadEntity removeRow(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= dataList.size()) {
			throw new IllegalArgumentException(
					"rowIndex must be greater than 0 and less than dataList.size()");
		}

		RowHeadEntity e = dataList.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, dataList.size());
		return e;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return RowHeadEntity.class;
	}
}
