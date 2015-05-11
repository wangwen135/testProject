package com.sf.module.EDI.EDIImpl.ui.table.editDialog;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;

/**
 * 描述：entity渲染器
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
public class EntityCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3475877166774169952L;

	/**
	 * 和外面一样的绿色
	 */
	public final static Color GREEN = new Color(0, 128, 0);

	// private static Border b = new EmptyBorder(0, 0, 0, 0);

	public EntityCellRenderer() {
		// 居中
		setHorizontalAlignment(SwingConstants.CENTER);
		// setHorizontalAlignment(SwingConstants.RIGHT);
		setVerticalAlignment(SwingConstants.TOP);
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
			IEntity entity = (IEntity) value;
			if (entity.getColor() != null) {
				setForeground(entity.getColor());
			} else {
				setForeground(Color.BLACK);
			}
			setText("<html>" + entity.getViewStr() + "<html>");
		}

		return c;
	}
}
