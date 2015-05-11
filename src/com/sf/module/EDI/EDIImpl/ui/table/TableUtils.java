package com.sf.module.EDI.EDIImpl.ui.table;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.sf.module.EDI.EDIImpl.TempletDragEditor;
import com.sf.module.EDI.EDIImpl.reportModel.area.TitleArea;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;

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
	public static void addColumn(TempletDragEditor editor, EdiTable table) {// 在最后增加都可以不同步
		TableColumn tc = new TableColumn(table.getColumnCount());
		tc.setWidth(TitleArea.DEFAULT_WIDTH);
		tc.setPreferredWidth(TitleArea.DEFAULT_WIDTH);
		table.getColumnModel().addColumn(tc);
		EdiTableModel editm = (EdiTableModel) table.getModel();
		editm.addColumn();
		adjustmentWidth(editor);

		// synchModelAndView(table);
		// EdiTableModel dftm = (EdiTableModel) table.getModel();
		// dftm.addColumn(table.getColumnCount());
	}

	/**
	 * <pre>
	 * 调整列宽
	 * </pre>
	 * 
	 * @param editor
	 */
	public static void adjustmentWidth(TempletDragEditor editor) {
		// 列宽调整
		List<Integer> widthList = editor.getColumnWidth();
		editor.setColumnWidth(widthList);
	}

	/**
	 * <pre>
	 * 在当前选择的位置添加列
	 * </pre>
	 * 
	 * @param table
	 */
	public static void addColumnOnCurrent(TempletDragEditor editor, JTable table) {
		int column = table.getSelectedColumn();
		if (column >= 0) {
			synchModelAndView(table);

			// table.getColumnModel().addColumn(
			// new TableColumn(table.getColumnCount()));
			// //需要同步列宽
			// EdiTableColumnModel etcm = (EdiTableColumnModel) table
			// .getColumnModel();
			// int width = 75;
			// int width2 = 0;
			// for (int i = column; i < etcm.getColumnCount(); i++) {
			// TableColumn tc = etcm.getColumn(i);
			// width2 = tc.getWidth();
			// tc.setWidth(width);
			// width = width2;
			// }
			// EdiTableModel editm = (EdiTableModel) table.getModel();
			// editm.addColumn(column);

			TableColumn tc = new TableColumn(table.getColumnCount());
			tc.setWidth(TitleArea.DEFAULT_WIDTH);
			tc.setPreferredWidth(TitleArea.DEFAULT_WIDTH);
			table.getColumnModel().addColumn(tc);
			EdiTableModel editm = (EdiTableModel) table.getModel();
			editm.addColumn();

			table.getColumnModel().moveColumn(table.getColumnCount() - 1,
					column);

			adjustmentWidth(editor);
		}
	}

	/**
	 * <pre>
	 * 删除当前选择的列
	 * </pre>
	 * 
	 * @param table
	 */
	public static void delColumnOnCurrent(TempletDragEditor editor, JTable table) {
		int selectedColumn = table.getSelectedColumn();
		if (selectedColumn >= 0) {
			if (JOptionPane.showConfirmDialog(table, "是否删除该列？", "请确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				table.getColumnModel().removeColumn(
						table.getColumnModel().getColumn(selectedColumn));
				synchModelAndView(table);
				adjustmentWidth(editor);
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
	public static void addRow(JTable table) {
		EdiTableModel dftm = (EdiTableModel) table.getModel();
		dftm.addRow();
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

				EdiTableModel dftm = (EdiTableModel) table.getModel();
				dftm.removeRow(table.getSelectedRow());
			}
		}
	}

	/**
	 * <pre>
	 * 设置表格默认宽度
	 * </pre>
	 * 
	 * @param table
	 */
	public static void setTableDefaultWidth(JTable table) {
		if (table == null)
			return;
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setWidth(TitleArea.DEFAULT_WIDTH);
			tc.setPreferredWidth(TitleArea.DEFAULT_WIDTH);
		}
	}

	/**
	 * <pre>
	 * 使表格的模型和试图保持同步
	 * </pre>
	 * 
	 * @param table
	 */
	public static void synchModelAndView(JTable table) {

		int columnCount = table.getColumnCount();
		int rowCount = table.getRowCount();

		EdiTableModel tableModel = (EdiTableModel) table.getModel();

		Vector<Vector<ICell>> newData = new Vector<Vector<ICell>>(rowCount);

		// 只需要同步列
		// Vector<Vector<ICell>> oldData = tableModel.getDataVector();

		for (int i = 0; i < rowCount; i++) {
			// 添加所有的行
			Vector<ICell> row = new Vector<ICell>(columnCount);
			newData.add(row);
		}

		for (int i = 0; i < columnCount; i++) {
			int modelColumn = table.convertColumnIndexToModel(i);
			for (int j = 0; j < rowCount; j++) {
				ICell o = (ICell) tableModel.getValueAt(j, modelColumn);
				// oldData.elementAt(j).elementAt(modelColumn);
				// 重新设置cell所在的行和列
				if (o != null) {
					o.setColumn(i);
					// 表格中的行会随着行删除变化
					o.setRow(j);
				}
				newData.elementAt(j).add(o);
			}
		}

		// 重新设置模型的数据
		tableModel.setDataVector(newData);
		// 将TableColumn的模型索引重置
		TableColumnModel tcmodel = table.getColumnModel();
		for (int i = 0; i < columnCount; i++) {
			TableColumn tc = tcmodel.getColumn(i);
			tc.setModelIndex(i);
		}

		// EdiTableModel newModel = new EdiTableModel(newData, columnNames);
		// table.setModel(newModel);
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
