package com.swing.component;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;

import com.swing.component.JTextField.DecimalTextField;
import com.swing.component.JTextField.JRegexTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JTextFieldTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblJregextextfield;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTextFieldTest frame = new JTextFieldTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void postToolTip(JComponent comp, String msg) {
		comp.setToolTipText(msg);
		Action action = comp.getActionMap().get("postTip");
		if (action == null) // no tooltip
			return;
		ActionEvent ae = new ActionEvent(comp, ActionEvent.ACTION_PERFORMED,
				"postTip", EventQueue.getMostRecentEventTime(), 0);
		action.actionPerformed(ae);
	}

	class UpperCaseDocument extends PlainDocument {

		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {

			if (str == null) {
				return;
			}

			StringBuilder sb = new StringBuilder();

			char[] upper = str.toCharArray();
			for (int i = 0; i < upper.length; i++) {
				if ((getLength() + sb.length()) >= 8) {
					postToolTip(textField, "长度不能超过8个字符");
					break;
				}
				if (Pattern.matches(
						"[\\w[\u4e00-\u9fa5][(][)][,][.][;][ ][-][_][%][|]]*",
						String.valueOf(upper[i]))) {
					sb.append(upper[i]);
				} else {
					postToolTip(textField, "不能输入特殊字符");
				}
			}
			super.insertString(offs, sb.toString(), a);
		}
	}

	/**
	 * Create the frame.
	 */
	public JTextFieldTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(144, 66, 141, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setDocument(new UpperCaseDocument());

		textField.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(evt);

			}
		});

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, textField_2.getText());
			}
		});
		btnNewButton.setBounds(251, 218, 93, 23);
		contentPane.add(btnNewButton);

		textField_1 = new JRegexTextField(".{0,5}");
		textField_1.setBounds(46, 168, 127, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		lblJregextextfield = new JLabel("JRegexTextField");
		lblJregextextfield.setBounds(46, 143, 102, 15);
		contentPane.add(lblJregextextfield);

		textField_2 = new DecimalTextField(4, 2);
		textField_2.setBounds(46, 219, 127, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblJdoubletextfield = new JLabel("JDoubleTextField");
		lblJdoubletextfield.setBounds(45, 199, 128, 15);
		contentPane.add(lblJdoubletextfield);

		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("焦点离开了。。。。");
			}
		});
		textField_3.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				String s = getText(0, getLength());
				if (s.length() >= 12) {
					textField_3.transferFocus();
					return;
				}
				super.insertString(offs, str, a);
			}
		});
		textField_3.setBounds(119, 275, 119, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(264, 275, 66, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}
