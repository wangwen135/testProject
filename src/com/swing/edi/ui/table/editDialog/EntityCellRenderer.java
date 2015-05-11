package com.swing.edi.ui.table.editDialog;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.swing.edi.reportModel.area.cell.entity.IEntity;

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
		String str = "";

		JLabel c = (JLabel) super.getTableCellRendererComponent(table, str,
				isSelected, hasFocus, row, column);
		// c.setBorder(b);
		// if(hasFocus){
		// setFont(new Font("宋体", Font.BOLD, 12));
		// }

		if (value != null) {
			IEntity entity = (IEntity) value;
			if (IEntity.KEY_EDI_CONSTANT.equals(entity.getKey())) {
				str = entity.getValue().toString();
				setForeground(Color.GREEN);
			} else {
				str = entity.getViewStr();
				setForeground(Color.BLACK);
			}
			setText(str);
		}

		return c;
	}
}
