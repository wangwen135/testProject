package com.swing.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HideTableColumn extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HideTableColumn frame = new HideTableColumn();
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
	public HideTableColumn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "column1", "column2", "column3", "column4" }));
		scrollPane.setViewportView(table);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		btnNewButton_2 = new JButton("获取选择行的值");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int columnCount = table.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					System.out.println(table.getValueAt(row, i));
				}

				System.out.println("model中的值");
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				Vector<Vector<Object>> data = dtm.getDataVector();
				Vector<Object> rowData = data.elementAt(table
						.convertRowIndexToModel(row));

				for (Object object : rowData) {
					System.out.println(object);
				}
			}
		});
		panel.add(btnNewButton_2);

		btnNewButton_1 = new JButton("隐藏列");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.removeColumn(table.getColumn("column2"));
			}
		});
		panel.add(btnNewButton_1);

		btnNewButton = new JButton("显示列");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addColumn(table.getColumn("column2"));
			}
		});
		panel.add(btnNewButton);

		// table.getColumn("column2").setWidth(0);
		// table.getColumn("column2").setMaxWidth(0);
		// table.getColumn("column2").setMinWidth(0);
		// table.getColumn("column2").setPreferredWidth(0);

	}

}
