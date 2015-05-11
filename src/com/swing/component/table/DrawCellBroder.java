package com.swing.component.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DrawCellBroder extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblBorder;
	private JCheckBox checkBox_top;
	private JCheckBox checkBox_left;
	private JCheckBox checkBox_right;
	private JCheckBox checkBox_bottom;
	private JCheckBox chckbx_fill;
	private JCheckBox chckbx_showHorizontalLines;
	private JCheckBox chckbx_showVerticalLines;
	myCellRendern cellrendern;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawCellBroder frame = new DrawCellBroder();
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
	public DrawCellBroder() {
		setTitle("表格边框");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 466, 223);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null },
				{ null, null, null, null, null, null }, }, new String[] {
				"Byte", "Double", "Object", "Boolean", "Integer", "String" }) {
			Class[] columnTypes = new Class[] { Byte.class, Double.class,
					Object.class, Boolean.class, Integer.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setRowHeight(32);
		table.setIntercellSpacing(new Dimension(0, 0));
		
		scrollPane.setViewportView(table);
		cellrendern = new myCellRendern();
		table.setDefaultRenderer(Object.class, cellrendern);

		JButton btnTest = new JButton("test");
		btnTest.setBounds(10, 10, 93, 23);
		contentPane.add(btnTest);

		lblBorder = new JLabel("边框");
		lblBorder.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorder.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		lblBorder.setBounds(345, 51, 68, 38);
		contentPane.add(lblBorder);

		checkBox_top = new JCheckBox("上边框");
		checkBox_bottom = new JCheckBox("下边框");
		checkBox_bottom.setSelected(true);
		checkBox_left = new JCheckBox("左边框");
		checkBox_right = new JCheckBox("右边框");

		checkBox_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				drawBroder();
			}
		});
		checkBox_top.setHorizontalTextPosition(SwingConstants.CENTER);
		checkBox_top.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_top.setVerticalTextPosition(SwingConstants.TOP);
		checkBox_top.setSelected(true);
		checkBox_top.setBounds(345, 10, 68, 38);
		contentPane.add(checkBox_top);

		checkBox_left.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				drawBroder();
			}
		});
		checkBox_left.setAutoscrolls(true);
		checkBox_left.setHorizontalTextPosition(SwingConstants.LEFT);
		checkBox_left.setSelected(true);
		checkBox_left.setBounds(271, 53, 72, 36);
		contentPane.add(checkBox_left);

		checkBox_right.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				drawBroder();
			}
		});
		checkBox_right.setSelected(true);
		checkBox_right.setBounds(415, 52, 75, 36);
		contentPane.add(checkBox_right);

		checkBox_bottom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				drawBroder();
			}
		});
		checkBox_bottom.setVerticalTextPosition(SwingConstants.BOTTOM);
		checkBox_bottom.setHorizontalTextPosition(SwingConstants.CENTER);
		checkBox_bottom.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox_bottom.setBounds(345, 91, 68, 38);
		contentPane.add(checkBox_bottom);

		chckbx_fill = new JCheckBox("FillViewPort");
		chckbx_fill.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				table.setFillsViewportHeight(chckbx_fill.isSelected());
			}
		});
		chckbx_fill.setBounds(122, 10, 147, 23);
		contentPane.add(chckbx_fill);

		chckbx_showHorizontalLines = new JCheckBox("ShowHorizontalLines");
		chckbx_showHorizontalLines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				table.setShowHorizontalLines(chckbx_showHorizontalLines
						.isSelected());
			}
		});
		chckbx_showHorizontalLines.setBounds(122, 35, 147, 23);
		contentPane.add(chckbx_showHorizontalLines);

		chckbx_showVerticalLines = new JCheckBox("ShowVerticalLines");
		chckbx_showVerticalLines.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				table.setShowVerticalLines(chckbx_showVerticalLines
						.isSelected());
			}
		});
		chckbx_showVerticalLines.setBounds(122, 59, 147, 23);
		contentPane.add(chckbx_showVerticalLines);

	}

	private void drawBroder() {
		int topb = 0;
		int leftb = 0;
		int rightb = 0;
		int bottomb = 0;

		if (checkBox_top.isSelected()) {
			topb = 1;
		}
		if (checkBox_left.isSelected()) {
			leftb = 1;
		}
		if (checkBox_right.isSelected()) {
			rightb = 1;
		}
		if (checkBox_bottom.isSelected()) {
			bottomb = 1;
		}

		lblBorder.setBorder(new MatteBorder(topb, leftb, bottomb, rightb,
				(Color) new Color(0, 0, 0)));
		this.cellrendern.setBorder(topb, bottomb, leftb, rightb);
		table.repaint();

	}
}

class myCellRendern extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int topb = 0;
	int leftb = 0;
	int rightb = 0;
	int bottomb = 0;

	Border border = new MatteBorder(topb, leftb, bottomb, rightb, Color.BLACK);

	public void setBorder(int top, int bottom, int left, int right) {
		this.topb = top;
		this.leftb = left;
		this.rightb = right;
		this.bottomb = bottom;
		border = new MatteBorder(topb, leftb, bottomb, rightb, Color.BLACK);
		// setBorder(border);
	}

	public myCellRendern() {
		// setBackground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);

		// setBorder(border);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);

		setBorder(border);
		return label;
	}

}
