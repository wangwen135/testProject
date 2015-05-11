package com.swing.component.radioButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RadioButtonTest extends JFrame {

	private JPanel contentPane;

	private JLabel lbl_msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RadioButtonTest frame = new RadioButtonTest();
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
	public RadioButtonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMsg = new JLabel("MSG:");
		lblMsg.setBounds(30, 139, 54, 15);
		contentPane.add(lblMsg);

		lbl_msg = new JLabel("");
		lbl_msg.setBounds(89, 139, 343, 15);
		contentPane.add(lbl_msg);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("按钮1");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("选择按钮1");
				lbl_msg.setText("您选择了按钮11111 ");
			}
		});
		rdbtnNewRadioButton
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(
							PropertyChangeEvent propertychangeevent) {
						System.out.println(propertychangeevent);

					}
				});

		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(86, 56, 121, 23);
		contentPane.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("按钮2");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("选择按钮2");
				lbl_msg.setText("您选择了按钮2222 ");
			}
		});
		rdbtnNewRadioButton_1.setBounds(242, 56, 121, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);

	}

}
