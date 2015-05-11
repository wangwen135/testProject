package com.swing.edi.ui.table;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.swing.edi.reportModel.area.cell.ICell;

/**
 * 描述： 表格工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-1-30      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TableUtils {

	/**
	 * <pre>
	 * 在表格的末尾添加列
	 * </pre>
	 * 
	 * @param table
	 */
	public static void addColumn(JTable table) {
		synchModelAndView(table);
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.addColumn(table.getColumnCount());
	}

	/**
	 * <pre>
	 * 在当前选择的位置添加列
	 * </pre>
	 * 
	 * @param table
	 */
	public static void addColumnOnCurrent(JTable table) {
		int column = table.getSelectedColumn();
		if (column >= 0) {
			synchModelAndView(table);
			DefaultTableModel dftm = (DefaultTableModel) table.getModel();
			dftm.addColumn(table.getColumnCount());
			table.getColumnModel().moveColumn(table.getColumnCount() - 1,
					column);
		}
	}

	/**
	 * <pre>
	 * 删除当前选择的列
	 * </pre>
	 * 
	 * @param table
	 */
	public static void delColumnOnCurrent(JTable table) {
		int selectedColumn = table.getSelectedColumn();
		if (selectedColumn >= 0) {
			if (JOptionPane.showConfirmDialog(table, "是否删除该列？", "请确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				table.getColumnModel().removeColumn(
						table.getColumnModel().getColumn(selectedColumn));
				synchModelAndView(table);
			}
		}
	}

	/**
	 * <pre>
	 * 在表格的末尾添加行
	 * </pre>
	 * 
	 * @param table
	 */
	@SuppressWarnings("rawtypes")
	public static void addRow(JTable table) {
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.addRow(new Vector());
	}

	/**
	 * <pre>
	 * 删除表格中选择的行
	 * </pre>
	 * 
	 * @param table
	 */
	public static void delRow(JTable table) {
		if (table.getSelectedRow() >= 0) {
			if (JOptionPane.showConfirmDialog(table, "是否删除该行？", "请确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				DefaultTableModel dftm = (DefaultTableModel) table.getModel();
				dftm.removeRow(table.getSelectedRow());
			}
		}
	}

	/**
	 * <pre>
	 * 使表格的模型和试图保持同步
	 * </pre>
	 * 
	 * @param table
	 */
	@SuppressWarnings("unchecked")
	public static void synchModelAndView(JTable table) {

		int columnCount = table.getColumnCount();
		int rowCount = table.getRowCount();

		EdiTableModel oldModel = (EdiTableModel) table.getModel();

		Vector<Vector<ICell>> newData = new Vector<Vector<ICell>>(rowCount);

		// 只需要同步列
		Vector<Vector<Object>> oldData = oldModel.getDataVector();

		for (int i = 0; i < rowCount; i++) {
			// 添加所有的行
			Vector<ICell> row = new Vector<ICell>(columnCount);
			newData.add(row);
		}

		for (int i = 0; i < columnCount; i++) {
			int modelColumn = table.convertColumnIndexToModel(i);
			for (int j = 0; j < rowCount; j++) {
				ICell o = (ICell) oldData.elementAt(j).elementAt(modelColumn);
				// 重新设置cell所在的行和列
				if (o != null) {
					o.setColumn(i);
					// 表格中的行是不会移动的
					// o.setRow(j);
				}
				newData.elementAt(j).add(o);
			}
		}

		Vector<Integer> columnNames = new Vector<Integer>();
		for (int i = 0; i < columnCount; i++) {
			columnNames.add(i);
		}

		EdiTableModel newModel = new EdiTableModel(newData, columnNames);

		table.setModel(newModel);
	}

	/**
	 * <pre>
	 * 转换数字到字符
	 * 如果 1转换成A ，如果结果到Z以后,则用AA、AB 这种形式
	 * </pre>
	 * 
	 * @param column
	 * @return
	 */
	public static String convertIntegerToString(int column) {
		// 如果结果到Z,则用AA、AB 这种形式
		if (column < 26) {// 小于Z
			char c = (char) (65 + column);
			return String.valueOf(c);
		} else {
			StringBuffer sb = new StringBuffer();
			int a = column / 26;
			int b = column % 26;

			char prefix = (char) (64 + a);
			sb.append(prefix);
			char c = (char) (65 + b);
			sb.append(c);
			return sb.toString();
		}
	}

}
