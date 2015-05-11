package com.swing.component.tableCellEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;

import com.swing.component.JTextField.RegexCheckDocument;

/**
 * 描述： 符合正则表达式的单元格编辑器
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
public class RegexCellEditor extends AbstractCellEditor implements
		TableCellEditor {
	private static final long serialVersionUID = -1809694226782961099L;

	private Border errorBorder = BorderFactory.createLineBorder(Color.RED);
	private Border normalBorder = BorderFactory.createLineBorder(Color.GRAY);

	private int clickCountToStart = 1;
	private boolean allowNull = false;
	private JTextField textField;
	private String inputRegex;
	private String checkRegex;
	private Pattern endCheckPattern;
	private boolean check = false;

	/**
	 * 构造函数
	 * 
	 * @param inputRegex
	 *            匹配输入的正则表达式
	 * @param endCheckRegex
	 *            输入完成后校验的正则表达式
	 */
	public RegexCellEditor(String inputRegex, String endCheckRegex) {
		textField = new JTextField();
		textField.setBorder(normalBorder);
		this.inputRegex = inputRegex;
		textField.setDocument(new RegexCheckDocument(inputRegex));

		if (endCheckRegex != null && !"".equals(endCheckRegex)) {
			this.checkRegex = endCheckRegex;
			endCheckPattern = Pattern.compile(endCheckRegex);
			check = true;
		}

		setClickCountToStart(1);
	}

	/**
	 * 构造函数
	 * 
	 * @param inputRegex
	 *            匹配输入的正则表达式
	 * @param checkRegex
	 *            输入完成后校验的正则表达式
	 * @param allowNull
	 *            是否允许为空
	 */
	public RegexCellEditor(String inputRegex, String checkRegex,
			boolean allowNull) {
		this(inputRegex, checkRegex);
		setAllowNull(allowNull);
	}

	public void setClickCountToStart(int count) {
		clickCountToStart = count;
	}

	public int getClickCountToStart() {
		return clickCountToStart;
	}

	/**
	 * <pre>
	 * 获取用于编辑的文本框
	 * </pre>
	 * 
	 * @return
	 */
	public JTextField getTextField() {
		return textField;
	}

	public String getInputRegex() {
		return inputRegex;
	}

	public String getCheckRegex() {
		return checkRegex;
	}

	public boolean isCheck() {
		return check;
	}

	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
		}
		return true;
	}

	/**
	 * <pre>
	 * 设置是否可以为空 
	 *  默认不能为空
	 * </pre>
	 * 
	 * @param isNull
	 *            是否可以为空
	 */
	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}

	/**
	 * <pre>
	 * 是否可以为空
	 * </pre>
	 * 
	 * @return 是否可以为空
	 */
	public boolean isAllowNull() {
		return this.allowNull;
	}

	@Override
	public Object getCellEditorValue() {
		return textField.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		textField.setText(value == null ? "" : value.toString());
		return textField;
	}

	@Override
	public boolean stopCellEditing() {
		if ("".equals(getCellEditorValue())) {
			if (!allowNull) {
				textField.setBorder(errorBorder);
				textField.setToolTipText("该字段不能为空！");
				textField.requestFocus();
				return false;
			}
		} else {
			// 判断是否符合正则表达式
			if (this.check) {
				Matcher m = endCheckPattern.matcher(getCellEditorValue()
						.toString());

				if (!m.matches()) {
					textField.setBorder(errorBorder);
					textField.setToolTipText("输入内容不匹配格式！");
					textField.requestFocus();
					return false;
				}
			}
		}
		return super.stopCellEditing();
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		textField.setToolTipText(textField.getText());
		textField.setBorder(normalBorder);
		return super.shouldSelectCell(anEvent);
	}
}
