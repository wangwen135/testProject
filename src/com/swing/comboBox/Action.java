package com.swing.comboBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Action extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Action frame = new Action();
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
	public Action() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = radioButton.isSelected();
				radioButton.setSelected(!selected);
				radioButton.firePropertyChange("selected", selected, !selected);
				// radioButton.
				// radioButton.processEvent(new ActionEvent(radioButton, 1,
				// ""));
				radioButton_1.doClick();
				//.setSelected(selected);
				radioButton_1.firePropertyChange("selected", !selected,
						selected);

			}
		});
		btnNewButton.setBounds(142, 73, 93, 23);
		contentPane.add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setBounds(38, 199, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(268, 199, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		ButtonGroup bg = new ButtonGroup();

		radioButton = new JRadioButton("1");
		radioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				textField_1.setEditable(true);
				textField_2.setEditable(false);

			}
		});
		radioButton.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(evt);

			}
		});

		radioButton.setSelected(true);
		bg.add(radioButton);
		radioButton.setBounds(38, 154, 121, 23);
		contentPane.add(radioButton);

		radioButton_1 = new JRadioButton("2");
		radioButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(false);
				textField_2.setEditable(true);
			}
		});

		bg.add(radioButton_1);
		radioButton_1.setBounds(268, 154, 121, 23);
		contentPane.add(radioButton_1);

	}
}
