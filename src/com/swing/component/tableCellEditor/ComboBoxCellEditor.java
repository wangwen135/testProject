package com.swing.component.tableCellEditor;

import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ComboBoxCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 3171876521091872441L;
	private JComboBox comboBox;

	public ComboBoxCellEditor() {
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "", "I", "E" }));
		comboBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -7569266955214657212L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				if ("".equals(value)) {
					setText("请选择...");
				} else if ("I".equals(value)) {
					setText("I - 进口");
				} else if ("E".equals(value)) {
					setText("E - 出口");
				}
				return c;
			}
		});
	}

	@Override
	public Object getCellEditorValue() {
		return comboBox.getSelectedItem() == null ? "" : comboBox
				.getSelectedItem().toString();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		comboBox.setSelectedItem(value);
		return comboBox;
	}

}
