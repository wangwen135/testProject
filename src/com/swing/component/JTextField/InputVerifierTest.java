package com.swing.component.JTextField;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InputVerifierTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputVerifierTest frame = new InputVerifierTest();
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
	public InputVerifierTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("关闭");
		button.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("关闭按钮获取到了焦点");
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(InputVerifierTest.this, "关闭");
				System.exit(0);
			}
		});
		button.setBounds(331, 229, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("确定");
		button_1.setBounds(211, 229, 93, 23);
		contentPane.add(button_1);

		textField = new JTextField();
		textField.setBounds(40, 39, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(150, 39, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				System.out.println("verify  方法触发");
				KeyboardFocusManager manager = KeyboardFocusManager
						.getCurrentKeyboardFocusManager();
				
				System.out.println(manager.getFocusOwner());
				
				System.out.println(manager.getPermanentFocusOwner());
				
				//manager.focusNextComponent();
				
				JTextField tf = (JTextField) input;
				if(! "ABC".equals(tf.getText())){
					JOptionPane.showMessageDialog(null, "输入ABC通过");
					return false;
				}
				return true;
			}
		});

		textField_2 = new JTextField();
		textField_2.setBounds(261, 39, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblabc = new JLabel("输入ABC焦点通过");
		lblabc.setBounds(149, 14, 109, 15);
		contentPane.add(lblabc);
	}
}
