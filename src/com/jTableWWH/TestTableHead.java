package com.jTableWWH;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TestTableHead extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestTableHead frame = new TestTableHead();
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
	public TestTableHead() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 510, 314);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null }, }, new String[] {
				"New column", "New column", "New column", "New column",
				"New column" }));
		scrollPane.setViewportView(table);

		AbstractTableModel headTableModel = new AbstractTableModel() {

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return rowIndex;
			}

			@Override
			public int getRowCount() {
				return table.getRowCount();
				// return 14;
			}

			@Override
			public int getColumnCount() {
				return 1;
			}
		};

		final JTable headTable = new JTable(headTableModel) {
			private static final long serialVersionUID = -836077127394430548L;

			@Override
			public int getRowHeight(int row) {
				return table.getRowHeight(row);
				// return 30;
			}
		};

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						System.out.println(e);
						headTable.clearSelection();

						if (!e.getValueIsAdjusting()) {
							System.out.println(e.getFirstIndex());
							System.out.println(e.getLastIndex());
							int[]  rows = table.getSelectedRows();
							headTable.addRowSelectionInterval(rows[0],rows[rows.length-1]);
						}

					}
				});

		headTable.setRowHeight(30);

		headTable.setEnabled(false);

		JViewport view = new JViewport();
		view.setView(headTable);
		view.setPreferredSize(new Dimension(60, 54));

		scrollPane.setRowHeader(view);

		scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
				new JButton("all"));
	}
}
