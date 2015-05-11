package com.sf.module.EDI.EDIImpl.ui.comm.groovy;

import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * 描述：ConsoleDocument<br>
 * 换行之后不可删除
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-8-15      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ConsoleDocument extends DefaultStyledDocument {

	private static final long serialVersionUID = 5359918496423825445L;

	ConsoleTextPane textPane;

	// 可编辑的长度，大于这个长度可以编辑
	int editLenght = 0;

	public ConsoleDocument(ConsoleTextPane textPane) {
		this.textPane = textPane;
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		if (offs >= editLenght) {
			super.remove(offs, len);
		} else {
			textPane.setPosition(getLength());
		}
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (offs >= editLenght) {
			if (a != null) {
				if (a instanceof SimpleAttributeSet) {
					StyleConstants.setForeground((SimpleAttributeSet) a,
							Color.GREEN);
				} else {
					SimpleAttributeSet attrSet = new SimpleAttributeSet();
					StyleConstants.setForeground(attrSet, Color.GREEN);
					a = attrSet;
				}

			}
			if ("\n".equals(str)) {
				offs = getLength();
				textPane.setPosition(getLength());
			}

			// \n
			for (int i = 0; i < str.length(); i++) {
				if ('\n' == str.charAt(i)) {
					editLenght = (getLength() + i + 1);
				}
			}
			super.insertString(offs, str, a);

		} else {
			textPane.setPosition(getLength());
		}
	}

	/**
	 * <pre>
	 * 追加文本
	 * </pre>
	 * 
	 * @param str
	 * @throws BadLocationException
	 */
	public void appendString(String str) throws BadLocationException {
		super.insertString(getLength(), str, null);
		editLenght = getLength();
		// textPane.setPosition(getLength());
	}

	public int getEditLenght() {
		return editLenght;
	}

	public void setEditLenght(int editLenght) {
		this.editLenght = editLenght;
	}

}
