package com.sf.module.EDI.EDIImpl.ui.table.editDialog;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;

/**
 * 描述：描述： Entity单元格编辑器2，原编辑器编辑常量时和输入法冲突，改为弹出输入框
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-20      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EntityCellEditor2 extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = -6079164110625202232L;

	private Window window;

	public EntityCellEditor2(Window window) {
		this.window = window;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		if (value != null) {
			IEntity entity = (IEntity) value;
			if (entity.canEdit()) {
				IEntity newEntity = entity.edit(window);
				if (newEntity != null)
					table.setValueAt(newEntity, row, column);
			}
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
