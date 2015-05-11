package com.swing.component.table;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import java.awt.Dimension;

public class RowHeader extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RowHeader frame = new RowHeader();
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
	public RowHeader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null, null, null, null,
						null }, }, new String[] { "New column", "New column",
				"New column", "New column", "New column", "New column",
				"New column", "New column", "New column", "New column",
				"New column" }));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

		JTable headerColumn = new JTable();
		headerColumn.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"\u662F\u5426\u9009\u62E9"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		headerColumn.getColumnModel().getColumn(0).setResizable(false);

		headerColumn.createDefaultColumnsFromModel();
		headerColumn.setColumnSelectionAllowed(false);
		headerColumn.setCellSelectionEnabled(false);

		JViewport jv = new JViewport();
		jv.setView(headerColumn);
		jv.setPreferredSize(headerColumn.getPreferredSize());
		scrollPane.setRowHeader(jv);
		scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
				headerColumn.getTableHeader());

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(100, 35));
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("<html>中文长度<html>\r\n");
		btnNewButton_1.setBounds(0, 0, 81, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(212, 5, 93, 23);
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
	}

}
