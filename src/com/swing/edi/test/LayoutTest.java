package com.swing.edi.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Rectangle;

public class LayoutTest extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel panel_1;
	private JTextField txtAaa;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LayoutTest frame = new LayoutTest();
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
	public LayoutTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(30);
		table.setRowMargin(5);
		table.setIntercellSpacing(new Dimension(5, 5));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null },
				{ null, null }, { null, null }, { null, null }, },
				new String[] { "\u5B9E\u4F53\u5BF9\u8C61",
						"\u8FDE\u63A5\u7B26\u53F7" }) {
			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setResizable(false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		// 表头
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton("选择文件");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "gif");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ chooser.getSelectedFile().getName());
				}

			}
		});
		panel.add(btnNewButton_1);

		btnNewButton = new JButton("保存文件");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"XML", "xml");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ chooser.getSelectedFile().getName());
				}
			}
		});
		panel.add(btnNewButton);

		btnNewButton_2 = new JButton("New button");
		final JDialog dl = new JDialog(this);

		dl.add(new JButton("一个测试按钮"));
		dl.setBounds(150, 150, 150, 150);
		dl.setModal(true);
		//dl.setAlwaysOnTop(true);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dl.setVisible(true);
			}
		});
		panel.add(btnNewButton_2);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);

		txtAaa = new JTextField();
		txtAaa.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtAaa.setAlignmentY(0);
		txtAaa.setPreferredSize(new Dimension(16, 45));
		txtAaa.setBounds(new Rectangle(4, 0, 0, 0));
		txtAaa.setText("aaa");
		txtAaa.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtAaa);
		txtAaa.setColumns(10);
	}
}
