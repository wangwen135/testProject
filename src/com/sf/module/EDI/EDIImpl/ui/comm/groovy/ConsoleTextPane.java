package com.sf.module.EDI.EDIImpl.ui.comm.groovy;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * 描述：TextPane，增加了实用方法
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-8-14       313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ConsoleTextPane extends JTextPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6531176689324309543L;

	/**
	 * <pre>
	 * 添加字符串到末尾
	 * </pre>
	 * 
	 * @param str
	 */
	public void append(String str) {

		Document doc = getDocument();
		if (doc != null) {
			try {
				doc.insertString(doc.getLength(), str, null);
			} catch (BadLocationException e) {
			}
		}
	}

	/**
	 * <pre>
	 * 设置光标位置
	 * </pre>
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		setSelectionStart(position);
		setSelectionEnd(position);
	}

	public void clear() {
		Document doc = getDocument();
		if (doc != null) {
			((ConsoleDocument) doc).setEditLenght(0);
		}
		setText("");
		// doc.remove(0, doc.getLength());
	}

}
