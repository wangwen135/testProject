package com.swing.component.table;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TableColumnWidth extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int[] columnWidth;
	private JButton btnmodel;
	private JButton button;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableColumnWidth frame = new TableColumnWidth();
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
	public TableColumnWidth() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 422, 169);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, }, new String[] { "New column",
				"New column", "New column" }));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("取列宽");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 宽度
				int count = table.getColumnCount();
				for (int i = 0; i < count; i++) {
					TableColumn tcolumn = table.getColumnModel().getColumn(i);
					System.out.println(tcolumn.getWidth());
				}
			}
		});
		btnNewButton.setBounds(185, 189, 93, 23);
		contentPane.add(btnNewButton);

		btnmodel = new JButton("设置model");
		btnmodel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(new Object[][] {
						{ null, null, null }, { null, null, null },
						{ null, null, null }, }, new String[] { "a", "b", "c" }));
			}
		});
		btnmodel.setBounds(10, 189, 110, 23);
		contentPane.add(btnmodel);

		button = new JButton("设置列宽");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int count = table.getColumnCount();
				for (int i = 0; i < count; i++) {
					TableColumn tcolumn = table.getColumnModel().getColumn(i);
					tcolumn.setWidth(100);
					tcolumn.setPreferredWidth(100);
				}
			}
		});
		button.setBounds(339, 189, 93, 23);
		contentPane.add(button);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 248, 422, 182);
		contentPane.add(scrollPane_1);

		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() {
				return 5;
			}

			public int getRowCount() {
				return 5;
			}

			public Object getValueAt(int row, int col) {
				return row +" | "+ col;
			}
		};

		TableColumnModel tmodel = new DefaultTableColumnModel();
		TableColumn col = new TableColumn();
		col.setHeaderValue("aaa");
		tmodel.addColumn(col);
		TableColumn col2 = new TableColumn();
		col2.setHeaderValue("bbb");
		col2.setResizable(false);
		col2.setIdentifier("b");
		col2.setModelIndex(3);
		tmodel.addColumn(col2);
		
		
		table_1 = new JTable(dataModel, tmodel);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table_1);
	}
}
