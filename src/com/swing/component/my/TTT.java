package com.swing.component.my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class TTT extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JTextField textField;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTT frame = new TTT();
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
	public TTT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		JTableHeader th = table.getTableHeader();
		th.setDefaultRenderer(new TableHeadCellRenderer());

		table.setRowHeight(80);
		// 无效的
		// table.setRowHeight(2, 180);
		// table.setRowMargin(10);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setIntercellSpacing(new Dimension(3, 3));
		table.setGridColor(SystemColor.activeCaption);

		table.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (row != -1 && col != -1) {
					table.addColumnSelectionInterval(col, col);
					table.addRowSelectionInterval(row, row);
				}

			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);

		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null }, { null, null, null },
				{ null, null, null }, }, new String[] { "A", "B", "C" }));
		// 表格拖动
		table.setDragEnabled(true);
		table.setDropMode(DropMode.ON);

		// 在组件之间传输数据的机制
		table.setTransferHandler(new TransferHandler("text"));

		// table.setDropTarget(getDropTarget());

		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(75, 10));
		// JViewport jvp = new JViewport();
		// jvp.setPreferredSize(new Dimension(55, 10));
		// scrollPane.setRowHeaderView(jvp);
		scrollPane.setRowHeaderView(panel);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("表体");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 79, 54, 15);
		panel.add(label_1);

		JLabel label_2 = new JLabel("表尾");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 162, 54, 15);
		panel.add(label_2);

		JLabel label_3 = new JLabel("表头");
		label_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 0, 54, 15);
		panel.add(label_3);

		textField = new JTextField();
		textField.setBounds(10, 119, 55, 21);
		panel.add(textField);
		textField.setColumns(10);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 104, 54, 15);
		panel.add(lblNewLabel);

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		contentPane.add(panel_1, BorderLayout.EAST);

		btnNewButton_3 = new JButton("addColum");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableColumn tc = new TableColumn();
				int index = table.getColumnCount();
				char c = (char) (65 + index);
				tc.setIdentifier(c);
				tc.setHeaderValue(c);
				table.addColumn(tc);
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				tm.addColumn(c);
			}
		});
		panel_1.add(btnNewButton_3);

		btnNewButton_4 = new JButton("getModel");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel t = table.getModel();
				System.out.println(t.getColumnCount());
			}
		});
		panel_1.add(btnNewButton_4);

		btnNewButton_5 = new JButton("getValue");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 取值
				Object o = table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
				JOptionPane.showMessageDialog(null, o.toString());
			}
		});
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton = new JButton("showDialog");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyDialog md = new MyDialog();
				md.setVisible(true);
			}
		});
		panel_1.add(btnNewButton);
	}
}

class TableHeadCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 6010435127783723003L;
	Border border = new BevelBorder(BevelBorder.RAISED, null, null, null, null);

	public TableHeadCellRenderer() {
		setBackground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);

		// 如果结果到Z,则用AA、AB 这种形式
		if (column < 26) {// 小于Z
			char c = (char) (65 + column);
			label.setText(String.valueOf(c));
		} else {
			StringBuffer sb = new StringBuffer();
			int a = column / 26;
			int b = column % 26;

			char prefix = (char) (64 + a);
			sb.append(prefix);
			char c = (char) (65 + b);
			sb.append(c);
			label.setText(sb.toString());
		}
		setBorder(border);
		return label;
	}

}
