package com.swing.component.my;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class panel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public panel() {
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		add(spinner);
		
		textField_2 = new JTextField();
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);

	}

}
