package com.sf.module.EDI.EDIImpl.ui.table.editDialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.ConstantEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;

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

	private JTextField tf;

	private IEntity entity = null;

	public EntityCellEditor() {
		tf = new JTextField();
		tf.setForeground(EntityCellRenderer.GREEN);
	}

	@Override
	public Object getCellEditorValue() {
		if (entity != null) {
			String str = tf.getText();
			if (entity.getValue().equals(str)) {
				return entity;
			} else {
				// 先不使用克隆，暂时能编辑的都是常量
				return new ConstantEntity(tf.getText());
			}
		}
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		if (value != null) {
			entity = (IEntity) value;
			if (entity instanceof ConstantEntity) {
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
		if (e instanceof ActionEvent) {
			return true;
		}
		return false;
	}
}
