package com.sf.module.EDI.EDIImpl.ui.table.editDialog;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.IPart;

/**
 * 描述：用于弹出框中的连接符号的渲染器
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
public class PartCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 4210876995216333913L;

	public PartCellRenderer() {
		setVerticalAlignment(SwingConstants.BOTTOM);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String str = "";
		if (value != null) {
			IPart part = (IPart) value;
			str = part.getViewStr() + " " + part.getSymbol();
		}
		return super.getTableCellRendererComponent(table, str, isSelected,
				hasFocus, row, column);
	}
}
