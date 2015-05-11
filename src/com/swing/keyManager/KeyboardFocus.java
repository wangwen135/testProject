package com.swing.keyManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.KeyStroke;

public class KeyboardFocus extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyboardFocus frame = new KeyboardFocus();
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
	public KeyboardFocus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);

		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);

		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox);

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);

		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);

		Set backwardKeySet = new HashSet();
		backwardKeySet.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.setDefaultFocusTraversalKeys(
						KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS,
						backwardKeySet);
	}

}
