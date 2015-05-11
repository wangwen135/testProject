package com.swing.component.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.swing.edi.ui.table.TableHeadCellRenderer;

import javax.swing.ScrollPaneConstants;

public class DragTables extends JFrame {

	private JPanel contentPane;
	private MyTable table;
	private MyTable table_1;
	private MyTable table_2;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel label;
	private JLabel lblColumnWidth;
	private JPanel panel_ctrl;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;

	private int[] columnWidth;
	private JButton btnmodel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DragTables frame = new DragTables();
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
	public DragTables() {
		setTitle("移除拖动多个表格");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setPreferredSize(new Dimension(2, 150));

		table = new MyTable();
		table.setName("table");
		table.setRowHeight(32);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, }, new String[] { "E", "D", "C",
				"B" }));
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);

		table_1 = new MyTable();
		table_1.setName("table_1");
		table_1.setRowHeight(32);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null }, }, new String[] { "E",
				"D", "C", "B", "A", "a" }));
		// table_1.getTableHeader().setReorderingAllowed(false);
		// table_1.getTableHeader().setResizingAllowed(false);

		scrollPane_1.setViewportView(table_1);

		scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2, BorderLayout.SOUTH);
		scrollPane_2.setPreferredSize(new Dimension(2, 150));

		table_2 = new MyTable();
		table_2.setName("table_2");
		table_2.setRowHeight(32);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_2.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null }, }, new String[] { "E", "D",
				"C", "B", "A" }));
		// table_2.getTableHeader().setReorderingAllowed(false);
		// table_2.getTableHeader().setResizingAllowed(false);

		scrollPane_2.setViewportView(table_2);

		// 设置表头
		table.getTableHeader().setDefaultRenderer(new TableHeadCellRenderer());

		table_1.getTableHeader()
				.setDefaultRenderer(new TableHeadCellRenderer());

		table_2.getTableHeader()
				.setDefaultRenderer(new TableHeadCellRenderer());
		// ###### 同步拖拽
		table.getColumnModel().addColumnModelListener(
				new DragTableColumnModelListener(table, table_1, table_2));
		table_1.getColumnModel().addColumnModelListener(
				new DragTableColumnModelListener(table_1, table, table_2));
		table_2.getColumnModel().addColumnModelListener(
				new DragTableColumnModelListener(table_2, table, table_1));
		// ###### 表头鼠标点击释放
		table.getTableHeader().addMouseListener(
				new CtrlScrollMouseListener(table, table_1, table_2));
		table_1.getTableHeader().addMouseListener(
				new CtrlScrollMouseListener(table, table_1, table_2));
		table_2.getTableHeader().addMouseListener(
				new CtrlScrollMouseListener(table, table_1, table_2));
		// ###### 同步滚动
		scrollPane.getHorizontalScrollBar().addAdjustmentListener(
				new ScrollAdjustmentListener(scrollPane_1, scrollPane_2));

		scrollPane_1.getHorizontalScrollBar().addAdjustmentListener(
				new ScrollAdjustmentListener(scrollPane, scrollPane_2));

		scrollPane_2.getHorizontalScrollBar().addAdjustmentListener(
				new ScrollAdjustmentListener(scrollPane, scrollPane_1));

		// #############################################################
		// #############################################################
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1, BorderLayout.NORTH);

		label = new JLabel("列宽：");
		panel_1.add(label);

		lblColumnWidth = new JLabel("");
		panel_1.add(lblColumnWidth);

		panel_ctrl = new JPanel();
		panel_ctrl.setPreferredSize(new Dimension(110, 10));
		contentPane.add(panel_ctrl, BorderLayout.EAST);
		panel_ctrl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		btnNewButton = new JButton("取列宽");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableColumnModel columnModel = table.getColumnModel();
				int count = columnModel.getColumnCount();
				columnWidth = new int[count];
				for (int i = 0; i < count; i++) {
					TableColumn tcolumn = columnModel.getColumn(i);
					columnWidth[i] = tcolumn.getWidth();
				}
				lblColumnWidth.setText(Arrays.toString(columnWidth));
			}
		});
		panel_ctrl.add(btnNewButton);

		btnNewButton_1 = new JButton("设置列宽");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (columnWidth == null)
					return;
				aa(table);
				aa(table_1);
				aa(table_2);
			}

			public void aa(JTable t) {
				TableColumnModel columnModel = t.getColumnModel();
				int count = columnModel.getColumnCount();
				for (int i = 0; i < count; i++) {
					TableColumn tcolumn = columnModel.getColumn(i);
					// table.getTableHeader().setResizingColumn(tcolumn);
					if (columnWidth.length <= i) {
						tcolumn.setWidth(75);
						tcolumn.setPreferredWidth(75);
						// tcolumn.setWidth(75);
					} else {
						tcolumn.setWidth(columnWidth[i]);
						tcolumn.setPreferredWidth(columnWidth[i]);
						// tcolumn.setWidth(columnWidth[i]);
					}
				}
				t.repaint();
			}

		});
		panel_ctrl.add(btnNewButton_1);

		btnNewButton_2 = new JButton("设置滚动条");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.getHorizontalScrollBar().setValue(10);
				// scrollPane.repaint();
			}
		});
		panel_ctrl.add(btnNewButton_2);

		btnmodel = new JButton("设置Model");
		btnmodel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//设置新的内容到表格模型中
				DefaultTableModel defaultTM = (DefaultTableModel) table.getModel();
				defaultTM.setDataVector(new Object[][] {
				{ 1, 2, 3, 4, 5 },
				{ null, null, "DDD", null, null },
				{ null, null, "BBB", null, null }, }, new String[] { "E", "D",
				"C", "B", "A" });

				//DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
				DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();

				//如果说我需要5列
				int needColumn = 4;
				//如果原来的列数小于5，那么要添加新的列
				while(columnModel.getColumnCount()<needColumn){
					columnModel.addColumn(new TableColumn());
				}
				//如果原来的列数大于5，那么需要删除老的列
				while(columnModel.getColumnCount()>needColumn){
					columnModel.removeColumn(columnModel.getColumn(0));					
				}
				//更新列的模型索引和宽度
				for (int i = 0; i < columnModel.getColumnCount(); i++) {
					TableColumn tc = columnModel.getColumn(i);
					tc.setModelIndex(i);
					tc.setWidth(i*10);
					tc.setPreferredWidth(i*10);
				}

				//table.setColumnModel(columnModel);
//				columnModel
//						.addColumnModelListener(new DragTableColumnModelListener(
//								table, table_1, table_2));

			}
		});
		panel_ctrl.add(btnmodel);
	}

}
