package com.swing.component.my;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class panelEdit extends AbstractCellEditor implements
		TableCellEditor {

	private JPanel jdg= new panel();
	private JDialog dig = new MyDialog();
	JLabel lab = new JLabel();
	/**
	 * 
	 */
	private static final long serialVersionUID = -8816773675865667880L;

	@Override
	public Object getCellEditorValue() {
		return "ok";
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		dig.setVisible(true);
		
		return lab;
	}

}