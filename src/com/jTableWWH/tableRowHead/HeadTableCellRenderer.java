package com.jTableWWH.tableRowHead;

import java.awt.Component;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 * 描述：表格行头渲染器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-9-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class HeadTableCellRenderer implements TableCellRenderer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3949861990587881611L;
	private JPanel contentPanel;
	private JLabel lbl_rowIndex;
	private JCheckBox checkBox;
	private JLabel lbl_msg;

	public HeadTableCellRenderer() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		lbl_rowIndex = new JLabel("11");

		lbl_rowIndex.setPreferredSize(new Dimension(18, 15));
		lbl_rowIndex.setSize(new Dimension(18, 15));
		lbl_rowIndex.setMaximumSize(new Dimension(18, 15));
		lbl_rowIndex.setHorizontalAlignment(SwingConstants.CENTER);

		contentPanel.add(lbl_rowIndex);

		checkBox = new JCheckBox("");
		contentPanel.add(checkBox);

		lbl_msg = new JLabel("38px");
		contentPanel.add(lbl_msg);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		RowHeadEntity entity = (RowHeadEntity) value;

		lbl_rowIndex.setText("" + row);

		if (entity.isAuto()) {
			checkBox.setSelected(true);
			lbl_msg.setText("自动");
		} else {
			checkBox.setSelected(false);
			lbl_msg.setText(entity.getHeight() + "px");
		}

		return contentPanel;
	}

}
