package com.swing.edi.ui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.swing.edi.reportModel.area.cell.ICell;

/**
 * 描述：表格单元格渲染器
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
public class EdiTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -7713481716727078607L;

	public EdiTableCellRenderer() {
		//
		setVerticalAlignment(SwingConstants.TOP);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String str = "";
		if (value != null) {
			ICell cell = (ICell) value;
			str = cell.getViewStr();
		}

		JLabel c = (JLabel) super.getTableCellRendererComponent(table, str,
				isSelected, hasFocus, row, column);
		
		c.setForeground(Color.BLACK);
		// if (row % 2 == 0) {
		// c.setBackground(Color.WHITE);
		// } else {
		// c.setBackground(SystemColor.inactiveCaptionText);
		// }

		// 设置值
		// if (value != null) {
		// ICell cell = (ICell) value;
		// setText(cell.getViewStr());
		// } else {
		// setText("");
		// }
		return c;
	}

}
