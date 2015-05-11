package com.swing.component.JTextField;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class JFormatTextField extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFormatTextField frame = new JFormatTextField();
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
	public JFormatTextField() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 407);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JFormattedTextField formattedTextField = new JFormattedTextField() {
			@Override
			public void setText(String t) {
				super.setText(t);
			}
		};
		formattedTextField.setBounds(103, 143, 118, 21);
		contentPane.add(formattedTextField);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextField.setText("adfa");
			}
		});
		btnNewButton.setBounds(231, 142, 93, 23);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panel.setBounds(10, 232, 486, 131);
		contentPane.add(panel);

		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(82, 42, 242, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setValue(new Float(100));
		panel_1.add(formattedTextField_1);

		JLabel label = new JLabel("_");
		panel_1.add(label);

		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setValue(new Float(100));
		panel_1.add(formattedTextField_2);
	}
}
