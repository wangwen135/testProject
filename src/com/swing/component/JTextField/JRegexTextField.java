package com.swing.component.JTextField;

import javax.swing.JTextField;

/**
 * 描述： 符合指定正则表达式的文本框
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
public class JRegexTextField extends JTextField {

	private static final long serialVersionUID = -1399451344231662501L;

	private String regex;

	/**
	 * 构造函数
	 * 
	 * @param regex
	 *            正则表达式
	 */
	public JRegexTextField(String regex) {
		this.regex = regex;
		setDocument(new RegexCheckDocument(regex));
	}

	public String getRegex() {
		return regex;
	}

}
