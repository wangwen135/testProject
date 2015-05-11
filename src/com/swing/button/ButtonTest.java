package com.swing.button;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class ButtonTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ButtonTest frame = new ButtonTest();
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
	public ButtonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("测试翻转效果");
		button.setRolloverIcon(new ImageIcon(ButtonTest.class.getResource("/org/codehaus/groovy/tools/groovy.ico")));
		button.setSelectedIcon(new ImageIcon(ButtonTest.class.getResource("/com/newClient/Image/Button/close_pressed1.png")));
		button.setIcon(new ImageIcon(ButtonTest.class.getResource("/com/trayIcon/tool.png")));
		button.setBounds(143, 29, 147, 41);
		
		button.setRolloverEnabled(true);
		
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(343, 39, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 39, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 116, 93, 23);
		contentPane.add(btnNewButton);
	}
}
