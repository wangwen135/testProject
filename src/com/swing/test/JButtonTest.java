package com.swing.test;

import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JButtonTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JButtonTest frame = new JButtonTest();
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
	public JButtonTest() {
		setTitle("按钮测试");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setMargin(new Insets(2, 4, 2, 4));
		
		Insets insets = btnNewButton.getMargin();
		
		System.out.println(insets);
		
		btnNewButton.setBounds(54, 70, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("测试按钮文本");
		button.setBounds(198, 70, 105, 23);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBounds(66, 208, 505, 70);
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
	}
}
