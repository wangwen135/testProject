package com.swing.component.JTextField;

import javax.swing.text.PlainDocument;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * 数字和Double类型的JTextFiled
 * 
 * @author 262064
 * 
 */
public class JNumTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	private boolean initialized = true;

//	private int gDecimalLenght = -1;// 精确控制小数点输入位数
//	private int nMaxStringLen = 14;
	private int gDecimalLenght = -1;// 精确控制小数点输入位数
	private int nMaxStringLen = 18;
	// private String Formatter = "#,##0.00";
//	private String Formatter = "#.######";
	private String Formatter = "##0.######";
	private boolean zero2100 = false;// 只能输入0~100之间的值
	private boolean noFS = false;

	public JNumTextField() {
		super();
		init();
	}

	public JNumTextField(int MaxStringLen) {
		super();
		init();
		this.nMaxStringLen = MaxStringLen;
	}

	public JNumTextField(int MaxStringLen, String Formatter) {
		super();
		init();
		this.nMaxStringLen = MaxStringLen;
		this.Formatter = Formatter;
	}

	// 精确控制小数点输入位数
	public JNumTextField(int MaxStringLen, int decimalLenght, String Formatter) {
		super();
		init();
		this.nMaxStringLen = MaxStringLen;
		this.Formatter = Formatter;
		this.gDecimalLenght = decimalLenght;
	}

	// 精确控制小数点输入位数并且只能输入0~100之间的值
	public JNumTextField(int MaxStringLen, int decimalLenght, String Formatter,
			boolean zero2100) {
		super();
		init();
		this.nMaxStringLen = MaxStringLen;
		this.Formatter = Formatter;
		this.gDecimalLenght = decimalLenght;
		this.zero2100 = zero2100;
	}

	public JNumTextField(int MaxStringLen, String Formatter, boolean noFS) {
		super();
		init();
		this.nMaxStringLen = MaxStringLen;
		this.Formatter = Formatter;
		this.noFS = noFS;
	}

	private void init() {
		this.setHorizontalAlignment(SwingConstants.RIGHT);

		this.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(FocusEvent e) {
				NumEdit_FocusLost(e);
			}
		});

		this.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(FocusEvent e) {
				NumEdit_FocusGained(e);
			}
		});
	}

	void NumEdit_FocusLost(FocusEvent e) {
		if (!this.getText().trim().equals(""))
			setValueToTextField(getValueFromTextField());
	}

	void NumEdit_FocusGained(FocusEvent e) {
		this.selectAll();
	}

	public void setText(String text) {
		setValueToTextField(convert(text));
	}

	public double getValueFromTextField() {
		return convert(this.getText());
	}

	public void setValueToTextField(double d) {
		String ss = this.getText();
		String tt = formattedText(d);
		if (!ss.equals(tt))
			super.setText(tt);
		System.out.println();
	}

	public void setValueToTextField(String s) {
		String ss = this.getText();
		String tt = formattedText(convert(s));
		if (!ss.equals(tt))
			this.setText(tt);
	}

	public String formattedText(double Value) {
		String ValueText = "";
		java.text.DecimalFormat dfStandard = new java.text.DecimalFormat(Formatter);
		ValueText = dfStandard.format(Value);
		return ValueText;
	}

	public static double convert(String TextString) {
		double d = 0;
		TextString = TextString.replaceAll(",", "");
		String TempString = "";
		TempString = TextString.replaceAll("-", "");
		if (!TempString.trim().equals(""))
			try {
				d = Double.parseDouble(TextString);
			} catch (Exception ex) {
				d = 0;
			}
		return d;
	}

	protected Document createDefaultModel() {
		return new NumCheck();
	}

	public void setDocument(Document d) {
		if (!initialized || d instanceof NumCheck)
			super.setDocument(d);
		else
			throw new UnsupportedOperationException();
	}

	private class NumCheck extends PlainDocument {
		private static final long serialVersionUID = -8053281738479884485L;

		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			// "."=46; ","=44; "-"=45; "0"=48;
			if (getLength() > nMaxStringLen - 1) {
				return;
			}

			if (null == str || str.equals("")) {
				super.insertString(offs, str, a);
			} else {
				if (offs == 0) {
					if (noFS) {
						if (((int) str.charAt(0) == 45)
								&& ((int) str.charAt(0) != 46)
								&& ((int) str.charAt(0) < 48 || (int) str
										.charAt(0) > 57)) {
							return;
						}
					} else {
						if (((int) str.charAt(0) != 45)
								&& ((int) str.charAt(0) != 46)
								&& ((int) str.charAt(0) < 48 || (int) str
										.charAt(0) > 57)) {
							return;
						}
					}
					if (str.length() > 1) {
						for (int i = 1; i < str.length(); i++) {
							if (((int) str.charAt(i) < 48 || (int) str
									.charAt(i) > 57)
									&& ((int) str.charAt(i) != 44)
									&& ((int) str.charAt(i) != 46)) {
								return;
							}
						}
					}
				} else {
					if (offs == 1) {
						for (int i = 0; i < str.length(); i++) {
							if ((((int) getText(0, 1).charAt(0)) < 48 || ((int) getText(
									0, 1).charAt(0)) > 57)
									&& (((int) str.charAt(i) < 48 || (int) str
											.charAt(i) > 57) && ((int) str
											.charAt(i) != 44))) {
								return;
							}

						}
					}

					for (int i = 0; i < str.length(); i++) {
						if (((int) str.charAt(i) < 48 || (int) str.charAt(i) > 57)
								&& ((int) str.charAt(i) != 44)
								&& ((int) str.charAt(i) != 46)) {
							return;
						}

						if (((int) str.charAt(i) == 46)) {
							String strOld = getText(0, getLength());
							for (int j = 0; j < strOld.length(); j++) {
								if ((int) strOld.charAt(j) == 46) {
									return;
								}
							}
						}
					}
				}
				for (int i = 0; i < str.length(); i++) {
					if (((int) str.charAt(i) == 46)) {
						String strOld = getText(0, getLength());
						for (int j = 0; j < strOld.length(); j++) {
							if ((int) strOld.charAt(j) == 46) {
								return;
							}
						}
					}

					if (((int) str.charAt(i) == 45)) {
						String strOld = getText(0, getLength());
						for (int j = 0; j < strOld.length(); j++) {
							if ((int) strOld.charAt(j) == 45) {
								return;
							}
						}
					}
				}
				int iFlag = str.lastIndexOf(".");
				if (iFlag >= 0) {
					if ((str.length() - iFlag) > 10) {
						str = str.substring(0, iFlag + 11);
					}
				}

				String oldString = getText(0, getLength());
				String newString = oldString.substring(0, offs) + str
						+ oldString.substring(offs);
				iFlag = newString.lastIndexOf(".");

				if (iFlag >= 0) {
					if ((getLength() - iFlag) > 10) {
						return;
					}
				}
				if (gDecimalLenght > -1) {// 精确控制小数点输入位数
					int decimalPosition = newString.lastIndexOf(".");
					if (decimalPosition > -1) {
						if ((newString.length() - (decimalPosition + 1)) > gDecimalLenght)
							return;
					}
				}
				if (zero2100) {// 只能输入0~100之间的值
					if (str.equals("-"))
						return;
					if (newString.equals(""))
						newString = "0";
					newString = newString.replaceAll(",", "");
					double value = 0;
					if (!newString.equals("."))
						value = Double.valueOf(newString);
					if (value < 0 || value >= 100)
						return;
				}
				super.insertString(offs, str, a);
			}
		}
	}

}
