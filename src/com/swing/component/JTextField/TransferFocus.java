package com.swing.component.JTextField;

import java.awt.AWTKeyStroke;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class TransferFocus extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferFocus frame = new TransferFocus();
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
	public TransferFocus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(116, 78, 107, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFocusAccelerator('a');

		Set<AWTKeyStroke> set = textField
				.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		for (AWTKeyStroke awtKeyStroke : set) {
			System.out.println(awtKeyStroke);
			System.out.println(awtKeyStroke.getKeyChar()+"   keycode="+awtKeyStroke.getKeyCode()+"  "+awtKeyStroke.getKeyEventType()+"   "+awtKeyStroke.getModifiers());
		}

		Set<AWTKeyStroke> keyset = new HashSet<AWTKeyStroke>();
		keyset.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_TAB,InputEvent.CTRL_DOWN_MASK));
		textField.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keyset);

		System.out.println(textField.getFocusAccelerator());
		// textField.setFocusTraversalKeysEnabled(false);

		textField_1 = new JTextField();
		textField_1.setBounds(116, 126, 107, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(116, 187, 93, 23);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		Set<AWTKeyStroke> keyset2 = new HashSet<AWTKeyStroke>();
		keyset2.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER,0));
		textArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keyset2);
		textArea.setBounds(81, 10, 136, 24);
		contentPane.add(textArea);
	}

}
