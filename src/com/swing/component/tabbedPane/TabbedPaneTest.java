package com.swing.component.tabbedPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TabbedPaneTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabbedPaneTest frame = new TabbedPaneTest();
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
	public TabbedPaneTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("  数 值  ", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.LEFT);
		panel.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("  常 规  ", null, panel_4, null);

		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_5, null);

		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_6, null);

		JPanel panel_7 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_7, null);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_8, BorderLayout.NORTH);

		JLabel label = new JLabel("分类：");
		panel_8.add(label);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("  对 齐  ", null, panel_1, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("  字 体  ", null, panel_2, null);
		panel_2.setLayout(null);

		textField = new JTextField();
		textField.setBounds(48, 38, 132, 21);
		panel_2.add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 69, 132, 171);
		panel_2.add(scrollPane);

		final JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textField.setText((String) list.getSelectedValue());
			}
		});
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			String[] values = ge.getAvailableFontFamilyNames();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		JLabel lblNewLabel = new JLabel("字体：");
		lblNewLabel.setBounds(48, 13, 54, 15);
		panel_2.add(lblNewLabel);

		JButton btnNewButton = new JButton("选中");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setSelectedValue(textField.getText(), true);
			}
		});
		btnNewButton.setBounds(190, 37, 93, 23);
		panel_2.add(btnNewButton);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("  边 框  ", null, panel_3, null);
	}
}
