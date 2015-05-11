package com.jTableWWH.tableRowHead;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

/**
 * 描述：表格行头编辑器
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
public class HeadTableCellEdit extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 7745694574236134269L;
	private JPanel contentPanel;
	private JLabel lbl_rowIndex;
	private JCheckBox checkBox;
	private JLabel lbl_msg;

	private RowHeadEntity tmpEntity;

	public HeadTableCellEdit() {
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

		checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (checkBox.isSelected()) {
					lbl_msg.setText("自动");
				} else {
					lbl_msg.setText(tmpEntity.getHeight() + "px");
				}
			}
		});
	}

	@Override
	public Object getCellEditorValue() {

		tmpEntity.setAuto(checkBox.isSelected());
		// 目前不支持直接编辑行高
		return tmpEntity;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		tmpEntity = (RowHeadEntity) value;

		lbl_rowIndex.setText(" " + row);
		if (tmpEntity.isAuto()) {
			checkBox.setSelected(true);
			lbl_msg.setText("自动");
		} else {
			checkBox.setSelected(false);
			lbl_msg.setText(tmpEntity.getHeight() + "px");
		}

		return contentPanel;
	}

}
