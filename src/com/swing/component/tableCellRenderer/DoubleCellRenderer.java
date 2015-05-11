package com.swing.component.tableCellRenderer;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 描述： double数字单元格渲染器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2011-12-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DoubleCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1093711326860963261L;

	private DecimalFormat df = new DecimalFormat("####0.########");

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		if (value != null && !"".equals(value)) {
			String var = df.format(Double.valueOf(value.toString()));
			setText(var);
		}
		setHorizontalAlignment(RIGHT);
		return c;

	}
}
