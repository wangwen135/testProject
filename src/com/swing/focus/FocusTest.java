package com.swing.focus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FocusTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label_digit;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FocusTest frame = new FocusTest();
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
	public FocusTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try{
				
				String t = textField.getText();
				if (null == t || "".equals(t)) {
					label_digit.setText("请输入数字");
					return;
				}

				for (int i = 0; i < t.length(); i++) {
					if (!Character.isDigit(t.charAt(i))) {
						
						JOptionPane.showMessageDialog(FocusTest.this, "只能输入数字");
						textField.select(i, t.length());
//						textField.requestFocusInWindow();
//						textField.requestFocus();
						//textField.requestFocus(true);
						return;
					}
				}

				label_digit.setText(Integer.toHexString(Integer.parseInt(t))
						.toUpperCase());
				}finally{
					textField.requestFocus();
				}
				

			}
		});
		textField.setBounds(84, 66, 126, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("输入框失去焦点时");
		label.setBounds(84, 41, 126, 15);
		contentPane.add(label);

		label_digit = new JLabel("");
		label_digit.setBounds(272, 69, 208, 15);
		contentPane.add(label_digit);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(94, 100, 93, 23);
		contentPane.add(btnNewButton);
		
		button = new JButton("取消按钮");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setVerifyInputWhenFocusTarget(false);
		button.setBounds(370, 295, 93, 23);
		contentPane.add(button);
	}
}
