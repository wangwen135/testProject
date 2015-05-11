package com.swing.component.my;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SwingTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingTest frame = new SwingTest();
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
	public SwingTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblswing = new JLabel("用于测试乱七八糟的swing组件");
		lblswing.setBounds(10, 10, 309, 15);
		contentPane.add(lblswing);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String txt = textField.getText();
				
				textField.putClientProperty("haha", txt);
			}
		});
		btnNewButton.setBounds(28, 54, 93, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(131, 55, 169, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addPropertyChangeListener("haha", new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("事件触发");
				System.out.println(evt.getNewValue());
				System.out.println(evt.getOldValue());				
				System.out.println(evt.getPropertyName());
				System.out.println(evt);
			}
		});
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value =(String) textField.getClientProperty("haha");
				JOptionPane.showMessageDialog(null, value);
			}
		});
		btnNewButton_1.setBounds(310, 54, 93, 23);
		contentPane.add(btnNewButton_1);
	}
}
