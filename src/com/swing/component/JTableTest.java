package com.swing.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.swing.component.tableCellEditor.ComboBoxCellEditor;
import com.swing.component.tableCellEditor.DoubleTextEditor;
import com.swing.component.tableCellRenderer.DecimalTableCellRenderer;

public class JTableTest extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTableTest frame = new JTableTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JTableTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(473, 368));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
				{ true, "String", new Double(1234.1234), "ObjectClass" },
				{ false, "", 0, "" }, { false, "", 0, "" },
				{ true, "", 0, "" }, }, new String[] { "boolean", "String",
				"double", "object" }) {
			Class[] columnTypes = new Class[] { Boolean.class, String.class,
					Double.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumn("object").setCellRenderer(new bgColorCellRenderer());

		scrollPane.setViewportView(table);
		JButton btnNewButton = new JButton("取值");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
				JOptionPane.showMessageDialog(table, "取到的值是：" + o);

				/*
				 * TableColumn tm = table.getColumn("double"); TableCellEditor
				 * tce = tm.getCellEditor(); System.out.println(tce);
				 * TableCellRenderer tcr = tm.getCellRenderer();
				 * System.out.println(tcr);
				 */
			}
		});
		scrollPane.setRowHeaderView(btnNewButton);

		JButton btnNewButton_1 = new JButton("设置颜色");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bgColorCellRenderer tcr = (bgColorCellRenderer) table
						.getColumn("object").getCellRenderer();
				tcr.setBgColor(2, Color.RED);
				table.repaint();
			}
		});
		getContentPane().add(btnNewButton_1, BorderLayout.SOUTH);

		TableColumn tm = table.getColumn("double");
		tm.setCellRenderer(new DecimalTableCellRenderer());

		TableColumn sm = table.getColumn("String");
		// sm.setCellEditor(new DoubleTextEditor(new JTextField()));
		sm.setCellEditor(new ComboBoxCellEditor());
		sm.setCellRenderer(new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 4210876995216333913L;

			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
				if (value != null) {
					if ("".equals(value)) {
						// setText("请选择...");
					} else if ("I".equals(value)) {
						setText("I - 进口");
					} else if ("E".equals(value)) {
						setText("E - 出口");
					}
				}

				return c;
			}

		});

	}

	class bgColorCellRenderer extends DefaultTableCellRenderer {
		private int row;
		private Color bgColor;
		private Color defaultColor = Color.WHITE;
		private boolean reDraw = false;

		public void setBgColor(int row, Color bgColor) {
			if (bgColor == null) {
				this.reDraw = false;
			} else {
				this.row = row;
				this.bgColor = bgColor;
				this.reDraw = true;
			}
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			if (reDraw && row == this.row) {
				setBackground(bgColor);
			} else {
				setBackground(defaultColor);
			}
			return c;
		}
	}

}
