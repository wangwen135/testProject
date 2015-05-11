package com.swing.edi.ui.table;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

import com.swing.edi.reportModel.area.cell.ICell;
import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.ui.table.editDialog.CellEditDialog;

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

	private CellEditDialog editDialog;

	private Window windows;

	private JLabel lable = new JLabel("正在编辑...");

	public EdiTableCellEditor(Window windows) {
		this.windows = windows;
		editDialog = new CellEditDialog(windows, this);
	}

	@Override
	public Object getCellEditorValue() {
		return editDialog.getCell();
	}

	@Override
	public Component getTableCellEditorComponent(final JTable table,
			final Object value, boolean isSelected, final int row,
			final int column) {

		if (value == null) {
			// 如果为空是不能编辑的
			return null;
		}
		final ICell cell = (ICell) value;
		cell.setColumn(column);
		if (cell.getEntitySize() <= 0) {
			if (cell.getFunction() != null && cell.getFunction().canEdit()) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						cell.getFunction().showEditor(windows);
					}
				});
			}
			return null;
		} else if (cell.getEntitySize() == 1) {
			// 对于单个常量直接弹出常量编辑器
			IEntity e = cell.getEntity(0);
			if (IEntity.KEY_EDI_CONSTANT.equals(e.getKey())) {
				String str = JOptionPane.showInputDialog(table, "请输入常量值：",
						e.getValue());
				if (str != null) {
					e.setValue(str);
				}
				return null;
			}
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				editDialog.setTableModelAndVisible((ICell) value, table, row,
						column);
				// editDialog.setTableModelAndVisible((ICell) value);
			}
		});
		return lable;
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
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		return super.stopCellEditing();
	}
}
