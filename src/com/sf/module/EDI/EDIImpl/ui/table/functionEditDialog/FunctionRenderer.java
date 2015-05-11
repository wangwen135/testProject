package com.sf.module.EDI.EDIImpl.ui.table.functionEditDialog;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：function渲染器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-26      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FunctionRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1318825308105058821L;

	/**
	 * 红色
	 */
	public final static Color RED = new Color(255, 0, 0);

	public FunctionRenderer() {
		// 居中
		setHorizontalAlignment(SwingConstants.CENTER);
		setForeground(RED);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel c = (JLabel) super.getTableCellRendererComponent(table, "",
				isSelected, hasFocus, row, column);
		// c.setBorder(b);
		// if(hasFocus){
		// setFont(new Font("宋体", Font.BOLD, 12));
		// }

		if (value != null) {
			IFunction func = (IFunction) value;
			StringBuffer strbuf = new StringBuffer();
			strbuf.append(func.getViewStr());
			strbuf.append(" ");
			strbuf.append(func.getType());
			strbuf.append("(");
			strbuf.append(func.getParameterViewStr());
			strbuf.append(")");
			setText(strbuf.toString());
		}

		return c;
	}
}
