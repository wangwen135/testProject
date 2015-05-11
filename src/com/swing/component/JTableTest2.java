package com.swing.component;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.swing.component.JTextField.DecimalTextField;
import com.swing.component.JTextField.JRegexTextField;
import com.swing.component.JTextField.RegexCheckDocument;
import com.swing.component.tableCellEditor.DoubleCellEditor;
import com.swing.component.tableCellEditor.IntegerCellEditor;
import com.swing.component.tableCellEditor.RegexCellEditor;
import com.swing.component.tableCellRenderer.DoubleCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JTableTest2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTableTest2 frame = new JTableTest2();
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
	public JTableTest2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, "0", 0d, 0 },
				{ null, null, "0", 0d, 0 }, }, new String[] {
				"\u6B63\u5219\u8868\u8FBE\u5F0F2",
				"\u6B63\u5219\u8868\u8FBE\u5F0F",
				"\u6574\u6570\u548C\u5C0F\u6570", "\u5C0F\u6570",
				"\u6574\u6570" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, Double.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("取值");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn()));
				System.out.println(table
						.getModel()
						.getValueAt(table.getSelectedRow(),
								table.getSelectedColumn()).getClass().getName());
			}
		});
		scrollPane.setRowHeaderView(btnNewButton);

		textField = new DecimalTextField(5, 3);

		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(50);

		// table.getColumn("doubleColumn").setCellRenderer(
		// new DoubleCellRenderer());

		// setTableDoubleColumnStyle(table.getColumn("整数和小数"), 5, 4, false);
		table.getColumn("整数和小数").setCellEditor(
				new DoubleCellEditor(5, 4, true, true, true));
		// setTableDoubleColumnStyle(table.getColumn("小数"), 0, 4, false);

		table.getColumn("小数").setCellEditor(
				new DoubleCellEditor(0, 4, false, false, true));
		// table.getColumn("小数").setCellEditor(
		// new DoubleCellEditor(new DecimalTextField(0, 4, true)));

		table.getColumn("整数").setCellEditor(
				new IntegerCellEditor(4, false, false, true));

		// setTableDoubleColumnStyle(table.getColumn("整数"), 4, 0, false);
		table.getColumn("正则表达式").setCellEditor(
				new DefaultCellEditor(new JRegexTextField(".{0,5}")));

		table.getColumn("正则表达式2").setCellEditor(
				new RegexCellEditor("\\d{0,6}", "\\d{6}", false));

	}

	private void setTableDoubleColumnStyle(TableColumn column, int numberPart,
			int decimalPart, boolean allowNull) {
		column.setCellEditor(new DoubleCellEditor(numberPart, decimalPart,
				allowNull));
		column.setCellRenderer(new DoubleCellRenderer());

	}
}
