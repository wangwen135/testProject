package com.swing.edi.ui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 描述：表头渲染器
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
public class TableHeadCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 6010435127783723003L;
	Border border = new BevelBorder(BevelBorder.RAISED, null, null, null, null);

	public TableHeadCellRenderer() {
		setBackground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				TableUtils.convertIntegerToString(column), isSelected,
				hasFocus, row, column);

		setBorder(border);
		return label;
	}

}
