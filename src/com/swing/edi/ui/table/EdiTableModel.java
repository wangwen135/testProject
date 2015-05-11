package com.swing.edi.ui.table;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.swing.edi.reportModel.area.cell.ICell;

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
public class EdiTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1064575666122238439L;

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
		super(rowCount, columnCount);
	}

	public EdiTableModel(Vector<Vector<ICell>> data, Vector<Integer> columnNames) {
		super(data, columnNames);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return ICell.class;
		// return Object.class
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		Object o = getValueAt(row, column);
		if (o == null) {
			return false;
		}
//		ICell c = (ICell) o;
//		if (c.getEntitySize() <= 0) {
//			return false;
//		}

		return super.isCellEditable(row, column);
	}
}
