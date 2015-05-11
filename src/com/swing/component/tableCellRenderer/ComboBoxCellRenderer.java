package com.swing.component.tableCellRenderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ComboBoxCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 4210876995216333913L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		if (value != null) {
			if ("".equals(value)) {
				// setText("请选择...");
			} else if ("I".equals(value)) {
				setText("I - 进口");
			} else if ("E".equals(value)) {
				setText("E - 出口");
			}
		}

		return c;
	}
}
