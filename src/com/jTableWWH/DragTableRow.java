package com.jTableWWH;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DragTableRow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JSpinner spinner_row;
	private JSpinner spinner_height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DragTableRow frame = new DragTableRow();
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
	public DragTableRow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 484, 298);
		contentPane.add(scrollPane);

		table = new JTable();

		table.setDoubleBuffered(true);

		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		table.setDragEnabled(true);

		table.setModel(new DefaultTableModel(new Object[][] {
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
		table.setRowHeight(0, 20);
		table.setRowHeight(1, 30);
		table.setRowHeight(2, 40);
		table.setRowHeight(3, 50);

		table.setCellSelectionEnabled(true);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane.setViewportView(table);

		MouseAdapterListener listener = new MouseAdapterListener(table);
		table.addMouseListener(listener);
		table.addMouseMotionListener(listener);

		JLabel label = new JLabel("设置表格行高   行");
		label.setBounds(10, 318, 109, 15);
		contentPane.add(label);

		spinner_row = new JSpinner();
		spinner_row.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinner_row.setBounds(116, 315, 29, 22);
		contentPane.add(spinner_row);

		JLabel label_1 = new JLabel("高度");
		label_1.setBounds(155, 318, 29, 15);
		contentPane.add(label_1);

		spinner_height = new JSpinner();
		spinner_height.setModel(new SpinnerNumberModel(10, 0, 100, 10));
		spinner_height.setBounds(185, 315, 44, 22);
		contentPane.add(spinner_height);

		JButton button = new JButton("设置高度");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setRowHeight((Integer) spinner_row.getValue(),
						(Integer) spinner_height.getValue());
			}
		});
		button.setBounds(239, 314, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("默认高度");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setRowHeight(30);
			}
		});
		button_1.setBounds(358, 314, 93, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("画线");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.getGraphics().drawLine(0, 10, table.getWidth(), 10);

			}
		});
		button_2.setBounds(358, 362, 93, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("行间距");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getRowMargin());
			}
		});
		button_3.setBounds(239, 362, 93, 23);
		contentPane.add(button_3);

		JButton btnSize = new JButton("size");
		btnSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container c = table.getParent();

				System.out.println("width=" + c.getWidth() + " height="
						+ c.getHeight());
			}
		});
		btnSize.setBounds(135, 362, 93, 23);
		contentPane.add(btnSize);
	}

	class MouseAdapterListener extends java.awt.event.MouseMotionAdapter
			implements MouseListener {
		int oldY = 0;

		int row = 0;
		int oldHeight = 0;
		boolean drag = false;
		int increase = 0;

		int allHeight = 0;

		boolean reSize = false;
		int reSizeHeight = 0;

		// JTable table;

		public MouseAdapterListener(JTable table) {
			// this.table = table;
		}

		public void mouseMoved(MouseEvent e) {

			int onRow = table.rowAtPoint(e.getPoint());

			int height = 0;
			for (int i = 0; i <= onRow; i++) {
				height = height + table.getRowHeight(i);
			}

			if (height - e.getY() < 3) {

				table.setDragEnabled(false);

				drag = true;
				allHeight = height;
				table.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			} else {
				table.setDragEnabled(true);

				drag = false;
				table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		}

		public void mouseDragged(final MouseEvent e) {
			if (drag) {
				reSize = true;

				int value = oldHeight + e.getY() - oldY;

				int lineSite = allHeight + e.getY() - oldY;

				table.setRowSelectionInterval(row, row);
				table.repaint();

				if (value < 20) {
					// table.setRowHeight(row, 20);
					reSizeHeight = 20;
					lineSite = allHeight - oldHeight + 20;
				} else {
					// table.setRowHeight(row, value);
					reSizeHeight = value;
				}

				final int ls = lineSite;

				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						int x = e.getX();
						if (x < 0)
							x = 0;
						if (x > table.getWidth() - 60)
							x = table.getWidth() - 60;

						int y = e.getY();
						if (y < ls)
							y = ls;
						if (y > table.getHeight())
							y = table.getHeight();

						table.getGraphics().drawString(
								"  (" + reSizeHeight + "像素)", x, y-2);
						table.getGraphics().drawLine(0, ls, table.getWidth(),
								ls);

					}
				});

			}
		}

		public void mousePressed(MouseEvent e) {
			System.out.println("鼠标按下");
			oldY = e.getY();
			row = table.rowAtPoint(e.getPoint());
			oldHeight = table.getRowHeight(row);
			table.setRowSelectionInterval(row, row);

			reSize = false;
		}

		public void mouseReleased(MouseEvent e) {
			System.out.println("鼠标释放");
			table.setDragEnabled(true);
			table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			if (reSize) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						System.out.println("设置行高");
						table.setRowHeight(row, reSizeHeight);

					}
				});
				// table.setRowHeight(row, reSizeHeight);
				// table.repaint();
			}

			reSize = false;
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
}
