package com.swing.component.JComboBox;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JComboboxEditer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JComboboxEditer frame = new JComboboxEditer();
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
	public JComboboxEditer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox = new JComboBox();
		// comboBox.addItemListener(new ItemListener() {
		// public void itemStateChanged(ItemEvent e) {
		// String item = (String) comboBox.getSelectedItem();
		// System.out.println(item);
		//
		// if(item.length()>10){
		// JOptionPane.showMessageDialog(null, "长度超过10");
		// comboBox.requestFocus();
		// }
		//
		// System.out.println("itemChanged $::$ "+e);
		// }
		// });

		comboBox.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				System.out.println("@#@#@##$@#$@#$@#$@#$@#$@#$@#@#$@#$");

				JComboBox box = (JComboBox) input;
				String value = (String) box.getSelectedItem();
				if (value.length() > 10) {
					JOptionPane.showMessageDialog(null, "长度超过10");
					return false;
				}
				return true;
			}
		});

		comboBox.setEditable(true);
		comboBox.setFont(new Font("宋体", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "", "I", "E" }));
		comboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value,
						index, isSelected, cellHasFocus);
				if ("".equals(value)) {
					setText("请选择...");
				} else if ("I".equals(value)) {
					setText("I - 进口");
				} else if ("E".equals(value)) {
					setText("E - 出口");
				}
				return c;
			}
		});
		comboBox.setBounds(118, 51, 169, 21);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedIndex());
				System.out.println(comboBox.getSelectedItem().toString());
			}
		});
		btnNewButton.setBounds(194, 93, 93, 23);
		contentPane.add(btnNewButton);
	}
}
