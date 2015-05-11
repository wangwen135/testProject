package com.swing.edi.ui.table.editDialog;

import java.awt.Component;
import java.awt.Font;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.swing.edi.reportModel.area.cell.part.IPart;
import com.swing.edi.reportModel.area.cell.part.PartEnum;

/**
 * 描述：连接符号的下拉框编辑器
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
public class PartCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 3171876521091872441L;
	private JComboBox comboBox;

	// 下拉列表需要从PartEnum中去取

	public PartCellEditor() {
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 12));

		Vector<IPart> vector = new Vector<IPart>();

		for (PartEnum p : PartEnum.values()) {
			vector.add(p.getPart());
		}

		comboBox.setModel(new DefaultComboBoxModel(vector));
		comboBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -7569266955214657212L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				IPart p = (IPart) value;

				return super.getListCellRendererComponent(list, p.getViewStr()
						+ " " + p.getSymbol(), index, isSelected, cellHasFocus);
			}
		});
	}

	@Override
	public Object getCellEditorValue() {
		return comboBox.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value == null) {
			return null;
		}
		comboBox.setSelectedItem(value);
		return comboBox;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		// if (e == null) {
		// return true;
		// }
		// if (e instanceof MouseEvent) {
		// return ((MouseEvent) e).getClickCount() >= 2;
		// }
		return true;
	}
}
