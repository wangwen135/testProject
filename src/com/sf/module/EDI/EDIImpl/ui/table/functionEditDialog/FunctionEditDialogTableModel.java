package com.sf.module.EDI.EDIImpl.ui.table.functionEditDialog;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：函数编辑对话框中的表格模型<br>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-26      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FunctionEditDialogTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -987072172648911409L;

	private ICell cell;

	/**
	 * 函数列表
	 */
	private ArrayList<IFunction> functionList = new ArrayList<IFunction>();

	/**
	 * 表格列名
	 */
	private String[] columnName = new String[] { "函数  (运算顺序从上到下)", "可编辑",
			"可包含变量" };

	public FunctionEditDialogTableModel(ICell cell) {
		this.cell = cell;

		for (int i = 0; i < cell.getFunctionSize(); i++) {
			functionList.add(cell.getFunction(i).aClone());// 需要克隆对象
		}
	}

	@Override
	public int getRowCount() {
		return functionList.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		IFunction func = functionList.get(rowIndex);
		if (columnIndex == 0) {
			return func;
		} else if (columnIndex == 1) {
			return func.canEdit();
		} else if (columnIndex == 2) {
			return func.containEntity();
		}
		return func;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex != 0)
			return;// 忽略其他
		// 如果设置null需要删除该行
		if (aValue == null) {
			functionList.remove(rowIndex);
			// 通知表格改变
			fireTableDataChanged();
			return;

		}

		IFunction function = (IFunction) aValue;
		if (rowIndex < 0 || rowIndex >= getRowCount()) {
			functionList.add(function);
			fireTableDataChanged();
		} else {
			functionList.set(rowIndex, function);
			fireTableCellUpdated(rowIndex, 0);
			fireTableCellUpdated(rowIndex, 1);
			fireTableCellUpdated(rowIndex, 2);
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 0;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return IFunction.class;
		} else {
			return Boolean.class;
		}
	}

	@Override
	public String getColumnName(int column) {
		if (column < 3)
			return columnName[column];

		return super.getColumnName(column);
	}

	/**
	 * <pre>
	 * 同步数据，将修改的内容保存到cell中
	 * </pre>
	 */
	public void synchData() {
		cell.clearFunction();
		boolean clearentity = false;
		for (IFunction function : functionList) {
			cell.addFunction(function);
			clearentity = clearentity || !function.containEntity();
		}
		if (clearentity) {
			cell.clear();
		}
	}
}
