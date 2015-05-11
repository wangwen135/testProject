package com.swing.component.spinnerTest;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerTest extends JFrame {

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
					SpinnerTest frame = new SpinnerTest();
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
	public SpinnerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -16, 450, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				textField.setText(spinner.getValue() == null ? "" : spinner
						.getValue().toString());
			}
		});
		MySpinnerNumberModel model = new MySpinnerNumberModel(null, 0, 99999, 1);
		spinner.setModel(model);
		spinner.setEditor(new MySpinnerNumberEditor(spinner, model));
		spinner.setBounds(131, 52, 94, 22);
		contentPane.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		MySpinnerNumberModel model2 = new MySpinnerNumberModel(null,
				new Double(0), null, new Double(1));
		spinner_1.setModel(model2);
		spinner_1.setEditor(new MySpinnerNumberEditor(spinner_1, model2));
		spinner_1.setBounds(131, 98, 94, 22);
		contentPane.add(spinner_1);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_2.setBounds(131, 145, 94, 22);
		contentPane.add(spinner_2);

		textField = new JTextField();
		textField.setBounds(131, 189, 94, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				String text = formattedTextField.getText();
				System.out.println(text);
				if ("".equals(text)) {

					formattedTextField.setText(null);
					try {
						formattedTextField.commitEdit();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return true;
			}
		});
		formattedTextField.setBounds(132, 10, 93, 21);
		formattedTextField.setValue(new Integer(0));

		contentPane.add(formattedTextField);

		JButton btnNewButton = new JButton("getValue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(spinner.getValue());
				// JOptionPane.showMessageDialog(null, spinner.getValue());
			}
		});
		btnNewButton.setBounds(249, 51, 93, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("setNull");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// formattedTextField.setValue(null);
				spinner.setValue(null);
			}
		});
		btnNewButton_1.setBounds(28, 51, 93, 23);
		contentPane.add(btnNewButton_1);
		
		final JSpinner spinner_3 = new JSpinner();
		System.out.println(spinner_3.getEditor());
		
		
		spinner_3.getEditor().addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				JOptionPane.showMessageDialog(null, "测试");
				spinner_3.requestFocus();
			}
		});		
		
		spinner_3.getEditor().setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {				
				System.out.println("value is :"+spinner_3.getValue());
				return true;
			}
		});
		spinner_3.getEditor().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focusLost");
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focusGained");				
			}
		});
		
		
		
		
		spinner_3.setBounds(131, 243, 94, 22);
		contentPane.add(spinner_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(131, 282, 94, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

	}
}
