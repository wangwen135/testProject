package com.swing.component.JTextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class RegexCheckDocument extends PlainDocument {
	private static final long serialVersionUID = 6920989155359487627L;

	private Pattern p;

	public RegexCheckDocument(String regex) {
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
