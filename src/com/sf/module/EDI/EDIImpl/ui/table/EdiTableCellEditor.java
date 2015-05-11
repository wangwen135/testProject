package com.sf.module.EDI.EDIImpl.ui.table;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.ui.table.editDialog.CellEditDialog;
import com.sf.module.EDI.EDIImpl.ui.table.functionEditDialog.FunctionEditDialog;

/**
 * 描述：表格单元格编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTableCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1103573008673340262L;

	private int clickCountToStart = 2;

	/**
	 * entity编辑器
	 */
	private CellEditDialog editDialog;

	/**
	 * function编辑器
	 */
	private FunctionEditDialog functionEdit;

	private ICell cell;

	/**
	 * 下一次编辑函数
	 */
	private boolean editFunctionNext = false;

	private Window windows;

	private JLabel lableEntity = new JLabel("正在编辑...");

	private JLabel lableFunction = new JLabel("正在编辑函数...");

	public EdiTableCellEditor(Window windows) {
		this.windows = windows;
		editDialog = new CellEditDialog(windows, this);
		functionEdit = new FunctionEditDialog(windows, this);
	}

	/**
	 * <pre>
	 * 下次编辑将使用编辑函数模式
	 * </pre>
	 * 
	 * @param editFunctionNext
	 */
	public void setEditFunctionNext(boolean editFunctionNext) {
		this.editFunctionNext = editFunctionNext;
	}

	@Override
	public Object getCellEditorValue() {
		return cell;
	}

	@Override
	public Component getTableCellEditorComponent(final JTable table,
			final Object value, boolean isSelected, final int row,
			final int column) {

		if (value == null) {
			// 如果为空是不能编辑的
			return null;
		}

		cell = (ICell) value;
		cell.setColumn(column);
		if (editFunctionNext) {
			setEditFunctionNext(false);
			return editFunction(table, row, column, cell);
		}

		if (cell.getEntitySize() <= 0) {// 只有在entity列表为空时编辑
			if (cell.getFunctionSize() <= 0) {
				return null;
			}
			return editFunction(table, row, column, cell);

		} else {

			return editEntity(table, row, column, cell);

		}
	}

	/**
	 * <pre>
	 * 编辑实体
	 * </pre>
	 * 
	 * @param table
	 * @param row
	 * @param column
	 * @param cell
	 * @return
	 */
	public Component editEntity(final JTable table, final int row,
			final int column, final ICell cell) {
		if (cell.getEntitySize() == 1) {
			// 对于单个常量直接弹出常量编辑器
			IEntity e = cell.getEntity(0);
			if (e.canEdit()) {
				e = e.edit(windows);
				cell.setEntity(0, e);
			}
			return null;

		} else {

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					editDialog
							.setTableModelAndVisible(cell, table, row, column);
				}
			});
		}

		return lableEntity;
	}

	/**
	 * <pre>
	 * 编辑函数
	 * </pre>
	 * 
	 * @param table
	 * @param row
	 * @param column
	 * @param cell
	 * @return
	 */
	public Component editFunction(final JTable table, final int row,
			final int column, final ICell cell) {

		if (cell.getFunctionSize() == 1) {
			if (cell.getFunction(0).canEdit()) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						cell.getFunction(0).showEditor(windows);
						cancelCellEditing();
					}
				});
			} else {
				return null;
			}
		} else {
			// 多个函数
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					functionEdit.setTableModelAndVisible(cell, table, row,
							column);
				}
			});
		}

		return lableFunction;
	}

	public boolean isCellEditable(EventObject e) {
		if (e == null) {
			return true;
		}
		if (e instanceof MouseEvent) {
			return ((MouseEvent) e).getClickCount() >= clickCountToStart;
		}
		/*
		 * if (e instanceof KeyEvent) { if (((KeyEvent) e).getKeyCode() == 32) {
		 * return true; } else { return false; } }
		 */
		if (e instanceof ActionEvent) {
			return true;
		}
		return false;
	}

}
