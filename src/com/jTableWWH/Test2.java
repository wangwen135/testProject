package com.jTableWWH;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.jTableWWH.tableRowHead.HeadTable;
import com.jTableWWH.tableRowHead.HeadTableModel;

public class Test2 extends JFrame {

	private JPanel contentPane;

	private HeadTable headTable;
	private MergeTable table;
	private MergeTableModel etableModel;

	private JTextField textField_minCol;
	private JTextField textField_maxCol;
	private JTextField textField_minRow;
	private JTextField textField_maxRow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test2 frame = new Test2();
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
	public Test2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		etableModel = new MergeTableModel() {
			private static final long serialVersionUID = -713760547393939554L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return rowIndex * 30 + "x" + columnIndex * 75;
			}

			@Override
			public int getRowCount() {
				return 20;
			}

			@Override
			public int getColumnCount() {
				return 14;
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		table = new MergeTable(etableModel);

		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(30);

		// 行高度调整
		// DragRowMouseListener listener = new DragRowMouseListener(table);
		// table.addMouseListener(listener);
		// table.addMouseMotionListener(listener);

		// 表格拖拽支持
		table.setDragEnabled(true);
		table.setDropMode(DropMode.USE_SELECTION);
		table.setTransferHandler(new TableTransferHandler(table));

		//table.setFillsViewportHeight(true);
		//scrollPane
		//		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	//	scrollPane
		//		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		headTable = new HeadTable(new HeadTableModel(20), table);

		JViewport view = new JViewport();
		view.setView(headTable);
		view.setPreferredSize(new Dimension(70, 0));

		scrollPane.setRowHeader(view);
		// scrollPane.setRowHeaderView(headTable);

		JButton corner_upper = new JButton("全部选择");
		corner_upper.setMargin(new Insets(1, 1, 1, 1));
		corner_upper.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableCellEditor tce = table.getCellEditor();
				if (tce != null) {
					tce.stopCellEditing();
				}
				table.addRowSelectionInterval(0, table.getRowCount() - 1);
				table.addColumnSelectionInterval(0, table.getColumnCount() - 1);
			}
		});

		scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,
				corner_upper);

		JButton corner_lower = new JButton("清空选择");
		corner_lower.setMargin(new Insets(1, 1, 1, 1));
		corner_lower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableCellEditor tce = table.getCellEditor();
				if (tce != null) {
					tce.stopCellEditing();
				}
				table.clearSelection();
			}
		});

		scrollPane.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER,
				corner_lower);

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 120));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("合并单元格");
		btnNewButton.setBounds(6, 5, 125, 23);
		panel.add(btnNewButton);

		JButton button = new JButton("拆分单元格");
		button.setBounds(141, 5, 125, 23);
		panel.add(button);

		JButton button_1 = new JButton("获取选中单元格");
		button_1.setBounds(392, 5, 140, 23);
		panel.add(button_1);

		textField_minCol = new JTextField();
		textField_minCol.setBounds(246, 61, 66, 21);
		panel.add(textField_minCol);
		textField_minCol.setColumns(10);

		textField_maxCol = new JTextField();
		textField_maxCol.setBounds(322, 61, 66, 21);
		panel.add(textField_maxCol);
		textField_maxCol.setColumns(10);

		JButton btnNewButton_1 = new JButton("选择列");
		btnNewButton_1.setBounds(247, 87, 101, 23);
		panel.add(btnNewButton_1);

		textField_minRow = new JTextField();
		textField_minRow.setBounds(30, 61, 66, 21);
		panel.add(textField_minRow);
		textField_minRow.setColumns(10);

		textField_maxRow = new JTextField();
		textField_maxRow.setBounds(106, 61, 66, 21);
		panel.add(textField_maxRow);
		textField_maxRow.setColumns(10);

		JButton button_2 = new JButton("选择行");
		button_2.setBounds(30, 87, 101, 23);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addRowSelectionInterval(
						Integer.valueOf(textField_minRow.getText()),
						Integer.valueOf(textField_maxRow.getText()));
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addColumnSelectionInterval(
						Integer.valueOf(textField_minCol.getText()),
						Integer.valueOf(textField_maxCol.getText()));
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] rows = table.getSelectedRows();
				System.out.println("row=" + Arrays.toString(rows));

				int[] columns = table.getSelectedColumns();
				System.out.println("column=" + Arrays.toString(columns));
			}

		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				table.split(row, column);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] columns = table.getSelectedColumns();
				int[] rows = table.getSelectedRows();
				table.splitAndMerge(rows, columns);
			}
		});
	}
}
