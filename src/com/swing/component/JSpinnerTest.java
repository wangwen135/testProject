package com.swing.component;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSpinnerTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSpinnerTest frame = new JSpinnerTest();
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
	public JSpinnerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(-2), null, new Integer(1)) );
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("stateChanged");
			}
		});

		spinner.setBounds(145, 90, 92, 22);
		contentPane.add(spinner);

		textField = new JTextField();
		textField.setBounds(171, 150, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JSpinner spinner_1 = new JSpinner();

		spinner_1.setModel(new AbstractSpinnerModel() {

			private Integer value = null;
			private Integer stepSize = 1;
			private Integer minimum = 0;
			private Integer maximum = Integer.MAX_VALUE;

			@Override
			public void setValue(Object value) {

				System.out.println(value);
				if (value == null || "".equals(value)) {
					this.value = null;
				} else {
					this.value = (Integer) value;
				}

				fireStateChanged();
			}

			@Override
			public Object getValue() {
				System.out.println(value);
				if (value != null || "".equals(value)) {
					return value;
				} else {
					return "";
				}
			}

			@Override
			public Object getPreviousValue() {
				if (value == null) {
					return minimum;
				} else {
					int i = value - stepSize;
					if (i > minimum) {
						return i;
					} else {
						return minimum;
					}
				}
			}

			@Override
			public Object getNextValue() {
				if (value == null) {
					return minimum;
				} else {
					int i = value + stepSize;
					if (i > maximum) {
						return maximum;
					} else {
						return i;
					}
				}
			}
		});

		JFormattedTextField ftf = new JFormattedTextField();
		ftf.setEditable(true);
		ftf.setValue(new Integer(0));
		ftf.setHorizontalAlignment(JTextField.RIGHT);

		// spinner_1.setEditor(new JTextField());

		spinner_1.setEnabled(true);
		spinner_1.setBounds(145, 28, 92, 22);
		contentPane.add(spinner_1);

		JLabel label = new JLabel("-----------------------------------");
		label.setBounds(83, 65, 249, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("可以为空转盘");
		label_1.setBounds(83, 10, 92, 15);
		contentPane.add(label_1);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(267, 28, 29, 22);
		contentPane.add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner();
		
		  
		JComponent j= (JComponent) spinner_3.getEditor().getComponent(0);
		j.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("!!!!丢失焦点");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println(e.getID());
				System.out.println(e);
				System.out.println("!!!!!获取焦点");
			}
		});
		
		spinner_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("获取焦点");
			}
		});
		spinner_3.setBounds(54, 150, 86, 22);
		contentPane.add(spinner_3);
		
		JButton btnNewButton = new JButton("New button");
		
		btnNewButton.setBounds(279, 149, 93, 23);
		contentPane.add(btnNewButton);
	}
}
