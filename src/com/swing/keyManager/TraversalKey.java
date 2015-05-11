package com.swing.keyManager;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.KeyEventDispatcher;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;

public class TraversalKey extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private boolean selectedAllText = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraversalKey frame = new TraversalKey();
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
	public TraversalKey() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(28, 22, 54, 15);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(92, 19, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(28, 68, 54, 15);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(92, 65, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(200, 22, 54, 15);
		panel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(264, 19, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(200, 68, 54, 15);
		panel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(264, 65, 66, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(92, 109, 66, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(28, 112, 54, 15);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(200, 112, 54, 15);
		panel.add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setBounds(264, 109, 66, 21);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(92, 187, 93, 23);
		panel.add(btnNewButton);

		JButton button = new JButton("焦点组件");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyboardFocusManager currentKeyboardFocusManager = KeyboardFocusManager
						.getCurrentKeyboardFocusManager();
				Component c = currentKeyboardFocusManager.getFocusOwner();
				System.out.println(c);
			}
		});
		button.setBounds(237, 187, 93, 23);
		panel.add(button);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(92, 150, 66, 22);
		panel.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(264, 150, 66, 22);
		panel.add(spinner_1);

		initTraversalKeys();

	}

	/**
	 * <pre>
	 * 设置焦点遍历的按键
	 * </pre>
	 */
	public void initTraversalKeys() {
		final KeyboardFocusManager currentKeyboardFocusManager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		Set<AWTKeyStroke> forwardKeySet = new HashSet<AWTKeyStroke>();
		forwardKeySet.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		forwardKeySet.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
		forwardKeySet.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,
				InputEvent.CTRL_DOWN_MASK));
		currentKeyboardFocusManager.setDefaultFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeySet);
		currentKeyboardFocusManager
				.addKeyEventPostProcessor(new KeyEventPostProcessor() {
					@Override
					public boolean postProcessKeyEvent(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER
								&& e.getID() == KeyEvent.KEY_PRESSED) {
							selectedAllText = true;
						}
						return false;
					}
				});

		currentKeyboardFocusManager.addPropertyChangeListener(
				"permanentFocusOwner", new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (selectedAllText) {
							selectedAllText = false;
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									Component c = currentKeyboardFocusManager
											.getFocusOwner();
									if (c instanceof JTextField) {
										JTextField t = (JTextField) c;
										t.selectAll();
									}
								}
							});
						}
					}
				});
	}
}
