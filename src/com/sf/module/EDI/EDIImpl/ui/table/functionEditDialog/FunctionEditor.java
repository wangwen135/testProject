package com.sf.module.EDI.EDIImpl.ui.table.functionEditDialog;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：函数单元格编辑器，负责调起函数编辑框
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
public class FunctionEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = -2651610385495961217L;

	private Window window;

	public FunctionEditor(Window window) {
		this.window = window;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		if (value != null) {
			IFunction func = (IFunction) value;
			func.showEditor(window);
		}

		return null;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		if (e == null) {
			return true;
		}
		if (e instanceof MouseEvent) {
			return ((MouseEvent) e).getClickCount() >= 2;
		}
		if (e instanceof ActionEvent) {
			return true;
		}
		return false;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}
}
