package com.swing.edi.ui.table.editDialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import com.swing.edi.reportModel.area.cell.entity.BaseEntity;
import com.swing.edi.reportModel.area.cell.entity.IEntity;

/**
 * 描述： Entity单元格编辑器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-6      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EntityCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2956037769112382001L;

	JTextField tf;

	IEntity entity = null;

	public EntityCellEditor() {
		tf = new JTextField();
		tf.setForeground(Color.GREEN);
	}

	@Override
	public Object getCellEditorValue() {
		if (entity != null) {
			String str = tf.getText();
			if (entity.getValue().equals(str)) {
				return entity;
			} else {
				// 先不使用克隆，暂时能编辑的都是常量
				return new BaseEntity(entity.getKey(), tf.getText(),
						entity.getViewStr());
			}
		}
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		if (value != null) {
			entity = (IEntity) value;
			if (IEntity.KEY_EDI_CONSTANT.equals(entity.getKey())) {
				tf.setText(entity.getValue().toString());
				return tf;
			}
		} else {
			entity = null;
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
		return true;
	}
}
