package com.swing.component;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import com.swing.component.JTextField.JNumTextField;
import com.swing.component.JTextField.JRegexTextField;
import com.swing.component.JTextField.RegexCheckDocument;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JNumTextFieldTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JFormattedTextField textField_2;
	private JLabel lblJnumtextfield;
	private JLabel lblJregextextfield;
	private JTextField textField_3;
	private JLabel lblJtextfield;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JNumTextFieldTest frame = new JNumTextFieldTest();
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
	public JNumTextFieldTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JRegexTextField("\\d{0,5}(([.]{1}\\d{1,4})|[.])?");

		textField.setBounds(228, 34, 129, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JNumTextField(10, 4, "##0.#####", false);
		textField_1.setBounds(25, 34, 129, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblJformattedtextfield = new JLabel("JFormattedTextField");
		lblJformattedtextfield.setBounds(25, 114, 129, 15);
		contentPane.add(lblJformattedtextfield);

		textField_2 = new JFormattedTextField(new DefaultFormatter() {

			private static final long serialVersionUID = 8483067061261667588L;
			DecimalFormat df = new DecimalFormat("####0.########");

			@Override
			public Object stringToValue(String string) throws ParseException {
				System.out.println("stringToValue ===" + string);
				try {
					return Double.valueOf(string);
				} catch (NumberFormatException ne) {
					return "";
				}
				// return super.stringToValue(string);
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				System.out.println("valueToString === " + value);
				try {
					return df.format(value);
				} catch (IllegalArgumentException e) {
					return "";
				}
				// return super.valueToString(value);
			}
		});

		RegexCheckDocument doc = new RegexCheckDocument(
				"\\d{0,5}(([.]{1}\\d{1,4})|[.])?");
		// textField_2.setDocument(doc);

		textField_2.setBounds(25, 139, 129, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		lblJnumtextfield = new JLabel("JNumTextField");
		lblJnumtextfield.setBounds(25, 10, 129, 15);
		contentPane.add(lblJnumtextfield);

		lblJregextextfield = new JLabel("JRegexTextField");
		lblJregextextfield.setBounds(228, 10, 129, 15);
		contentPane.add(lblJregextextfield);

		textField_3 = new JTextField();
		textField_3.setBounds(228, 139, 129, 21);
		//
		textField_3.setDocument(doc);

		contentPane.add(textField_3);
		textField_3.setColumns(10);

		lblJtextfield = new JLabel("JTextField");
		lblJtextfield.setBounds(228, 114, 84, 15);
		contentPane.add(lblJtextfield);

		JLabel label = new JLabel("输满指定位数后跳转");
		label.setBounds(25, 195, 129, 15);
		contentPane.add(label);

		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String t = textField_4.getText();
				if(t.length()!=12){
					
				}
			}
		});
		textField_4.setDocument(new PlainDocument() {
			private static final long serialVersionUID = -7503329819550779036L;

			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (str == null) {
					return;
				}
				int len = getLength();
				if (len < 12) {

					StringBuilder tmp = new StringBuilder();
					char[] insChar = str.toCharArray();
					for (int i = 0; i < insChar.length; i++) {// 校验复制粘贴
						if ('0' <= insChar[i] && insChar[i] <= '9') {
							if ((len + tmp.length()) < 12)
								tmp.append(insChar[i]);
						}
					}
					super.insertString(offs, tmp.toString(), a);
				}
				len = getLength();
				System.out.println("长度是:" + len);
			}
		});

		textField_4.setBounds(25, 219, 129, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}
