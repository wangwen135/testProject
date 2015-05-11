package com.swing.edi.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.ListSelectionModel;

public class UiTest extends JFrame {

	private JPanel allTablePanel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiTest frame = new UiTest();
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
	public UiTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 425);
		allTablePanel = new JPanel();
		allTablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(allTablePanel);
		allTablePanel.setLayout(new BoxLayout(allTablePanel, BoxLayout.Y_AXIS));

		JPanel tailPanel = new JPanel();
		allTablePanel.add(tailPanel);
		tailPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		tailPanel.add(panel, BorderLayout.NORTH);

		JButton btnNewButton_1 = new JButton("添加行");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dftm = (DefaultTableModel) table.getModel();
				dftm.addRow(new Vector());
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("添加列");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchModelAndView(table);
				DefaultTableModel dftm = (DefaultTableModel) table.getModel();
				dftm.addColumn(table.getColumnCount());
			}
		});

		JButton btnNewButton_3 = new JButton("删除行");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dftm = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() >= 0) {
					dftm.removeRow(table.getSelectedRow());
				}
			}
		});
		panel.add(btnNewButton_3);
		panel.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("取值");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				Object obj = table.getValueAt(row, column);
				JOptionPane.showMessageDialog(table, obj.toString());
			}
		});

		JButton btnNewButton_4 = new JButton("删除列");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedColumn = table.getSelectedColumn();
				System.out.println(selectedColumn);
				//synchModelAndView(table);
				
				table.getColumnModel().removeColumn(
						table.getColumnModel().getColumn(selectedColumn));

				synchModelAndView(table);

				/*
				 * int column = table.getSelectedColumn(); DefaultTableModel
				 * dftm = (DefaultTableModel) table.getModel(); if (column >= 0)
				 * { if (JOptionPane.showConfirmDialog(table, "是否删除该列？", "请确认",
				 * JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				 * column = table.convertColumnIndexToModel(column);
				 * Vector<Vector<Object>> data = dftm.getDataVector(); for (int
				 * i = 0; i < data.size(); i++) { Vector<Object> row =
				 * data.get(i); Object obj = row.remove(column);
				 * System.out.println("值是：" + obj); } // 列定义 int columnCount =
				 * dftm.getColumnCount() - 1; Vector vColumn = new
				 * Vector(columnCount); for (int i = 0; i < columnCount; i++) {
				 * vColumn.add(i); } dftm.setDataVector(data, vColumn); } }
				 */

			}
		});
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("在当前位置插入列");
		btnNewButton_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int column = table.getSelectedColumn();
				synchModelAndView(table);
				
				DefaultTableModel dftm = (DefaultTableModel) table.getModel();
				dftm.addColumn(table.getColumnCount());
				table.getColumnModel().moveColumn(table.getColumnCount() - 1,
						column);

				// synchModelAndView(table);
			}
		});
		panel.add(btnNewButton_5);
		panel.add(btnNewButton_2);

		JButton btnmodel = new JButton("取model");
		btnmodel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dftm = (DefaultTableModel) table.getModel();
				Vector dataVector = dftm.getDataVector();
				for (int i = 0; i < dataVector.size(); i++) {
					Vector v = (Vector) dataVector.get(i);

					for (int j = 0; j < v.size(); j++) {
						System.out.print(v.get(j) + "    ");
					}

					System.out.println("---");
				}

			}
		});
		panel.add(btnmodel);

		JScrollPane scrollPane = new JScrollPane();
		tailPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowHeight(70);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		scrollPane.setViewportView(table);
	}

	public void synchModelAndView(JTable table) {
		// 由于拖动和新增会导致表格试图与模型不同步
		// 应该只调用model中的方法，调用table中的方法会触发很多事件

		// 删除列之后调用一次同步的方法就可以了，添加列之后调用一次同步的方法

		// 表格中的行和列
		System.out.println("-----------------方法被调用-----------------------");

		int columnCount = table.getColumnCount();
		int rowCount = table.getRowCount();

		DefaultTableModel oldModel = (DefaultTableModel) table.getModel();

		Vector<Vector<Object>> newData = new Vector<Vector<Object>>(rowCount);

		// 只需要同步列
		Vector<Vector<Object>> oldData = oldModel.getDataVector();

		for (int i = 0; i < rowCount; i++) {
			// 添加所有的行
			Vector<Object> row = new Vector<Object>(columnCount);
			newData.add(row);
		}

		for (int i = 0; i < columnCount; i++) {
			int modelColumn = table.convertColumnIndexToModel(i);
			for (int j = 0; j < rowCount; j++) {
				Object o = oldData.elementAt(j).elementAt(modelColumn);
				System.out.println("将 row=" + j + " modelColumn=" + modelColumn
						+ " 的值：" + o + "  移动到 column=" + i);
				// newData.elementAt(j).setElementAt(o, i);
				newData.elementAt(j).add(o);
			}
		}

		Vector<Integer> columnNames = new Vector<Integer>();
		for (int i = 0; i < columnCount; i++) {
			columnNames.add(i);
		}

		DefaultTableModel newModel = new DefaultTableModel(newData, columnNames);
		table.setModel(newModel);
	}

}
