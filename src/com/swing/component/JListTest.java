package com.swing.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class JListTest extends JFrame {

	private JPanel contentPane;

	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListTest frame = new JListTest();
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
	public JListTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 10, 93, 23);
		contentPane.add(btnNewButton);

		textPane = new JTextPane();
		textPane.setBounds(35, 76, 224, 140);
		contentPane.add(textPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 76, 103, 140);
		contentPane.add(scrollPane);

		final JList list = new JList();
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textPane.replaceSelection(list.getSelectedValue().toString());

				System.out.println(list.getSelectedValue());
			}
		});
		// list.addListSelectionListener(new ListSelectionListener(){
		// @Override
		// public void valueChanged(ListSelectionEvent e) {
		// // TODO Auto-generated method stub
		// System.out.println("######## ListSelectionListener "+list.getSelectedValue());
		// }
		// });
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "1", "2", "3", "4", "5", "6", "7",
					"8", "88888888", "9999999999", "111111111" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setCellRenderer(new MyCellRenderer());
		list.setForeground(Color.BLUE);

	}
}

class MyCellRenderer extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if ("1".equals(value)) {
			value = "一";
		} else if ("2".equals(value)) {
			value = "二";
		} else {
			value = "其他";
		}

		return super.getListCellRendererComponent(list, value, index,
				isSelected, cellHasFocus);
	}
}
