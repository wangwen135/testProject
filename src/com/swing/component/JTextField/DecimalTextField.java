package com.swing.component.JTextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 描述：只能输入数字的文本框<br>
 * 构造时指定整数部分位数和小数部分位数
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2011-12-19      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DecimalTextField extends JTextField {

	private static final long serialVersionUID = 6266258300051698114L;

	private int numberPart = 0;
	private int decimalPart = 0;
	private String regex;
	private boolean canInsertMinus = false;

	public int getNumberPart() {
		return numberPart;
	}

	public int getDecimalPart() {
		return decimalPart;
	}

	public String getRegex() {
		return regex;
	}

	public boolean isCanInsertMinus() {
		return canInsertMinus;
	}

	/**
	 * 构造函数<br>
	 * 默认不能输入‘-’号
	 * 
	 * @param number
	 *            整数部分位数
	 * @param decimal
	 *            小数部分位数
	 */
	public DecimalTextField(int number, int decimal) {
		this(number, decimal, false);
	}

	/**
	 * 构造函数
	 * 
	 * @param number
	 *            整数部分位数
	 * @param decimal
	 *            小数部分位数
	 * @param minus
	 *            是否可以输入‘-’号
	 */
	public DecimalTextField(int number, int decimal, boolean minus) {
		this.numberPart = number;
		this.decimalPart = decimal;
		this.canInsertMinus = minus;
		StringBuilder sb = new StringBuilder();
		if (canInsertMinus) {
			sb.append("[-]?");
		}
		if (number > 0) {
			sb.append("\\d{0,");
			sb.append(numberPart);
			sb.append("}");
		} else {
			sb.append("[0]?");
		}
		if (decimal > 0) {
			sb.append("([.]{1}\\d{0,");
			sb.append(decimalPart);
			sb.append("})?");
		}

		this.regex = sb.toString();
		System.out.println(this.regex);

		NumberDocument doc = new NumberDocument(regex);
		this.setDocument(doc);
	}
}

class NumberDocument extends PlainDocument {
	private static final long serialVersionUID = 6920989155359487627L;

	private Pattern p;

	public NumberDocument(String regex) {
		p = Pattern.compile(regex);
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (str == null) {
			return;
		}

		System.out.println("RegexCheckDocument 的 insertString 方法  str=" + str);

		String oldText = getText(0, getLength());

		StringBuilder tmp = new StringBuilder();
		char[] insChar = str.toCharArray();
		for (int i = 0; i < insChar.length; i++) {// 校验复制粘贴
			StringBuilder newText = new StringBuilder(oldText);
			newText.insert(offs, tmp.toString() + insChar[i]);
			Matcher m = p.matcher(newText.toString());
			if (m.matches()) {
				tmp.append(insChar[i]);
			} else {
				System.err
						.println("RegexCheckDocument 的 insertString方法 ----不匹配");
			}
		}

		if (getLength() == 0) {
			if (tmp.toString().startsWith(".")) {
				// 如果输入以.开始自动补0
				tmp.insert(0, '0');
			}
		} else if (getLength() == 1 && "-".equals(oldText)) {
			if (tmp.toString().startsWith(".")) {
				// 如果输入以.开始自动补0
				tmp.insert(0, '0');
			}
		}

		super.insertString(offs, tmp.toString(), a);
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		StringBuilder text = new StringBuilder(getText(0, getLength()));
		text.delete(offs, offs + len);
		Matcher m = p.matcher(text.toString());
		if (m.matches()) {
			super.remove(offs, len);
		}
	}
}
