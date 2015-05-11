package com.swing.component.tableCellEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;

import com.swing.component.JTextField.DecimalTextField;

/**
 * 描述： double数字单元格编辑器<br>
 * 例如：5位整数4位小数，可以为空<br>
 * setCellEditor(new DoubleCellEditor(5, 4,true));
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2011-12-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DoubleCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = -1420479200925510293L;
	private Border errorBorder = BorderFactory.createLineBorder(Color.RED);
	private Border normalBorder = BorderFactory.createLineBorder(Color.GRAY);
	private DecimalFormat df = new DecimalFormat("####0.########");
	/**
	 * 是否允许位空
	 */
	private boolean allowNull = false;
	/**
	 * 是否返回String到model中
	 */
	private boolean returnString = false;
	private int clickCountToStart = 1;
	private JTextField textField;

	/**
	 * 构造函数
	 * 
	 * @param textField
	 *            文本框
	 */
	public DoubleCellEditor(JTextField textField) {
		this.textField = textField;
		textField.setBorder(normalBorder);
	}

	/**
	 * 构造函数<br>
	 * 默认不能为空，不能输入负号，并且返回double值
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param decimalPart
	 *            小数部分
	 */
	public DoubleCellEditor(int numberPart, int decimalPart) {
		this(numberPart, decimalPart, false);
	}

	/**
	 * 构造函数<br>
	 * 默认返回double值，不能输入负号
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param decimalPart
	 *            小数部分
	 * @param allowNull
	 *            是否允许为空
	 */
	public DoubleCellEditor(int numberPart, int decimalPart, boolean allowNull) {
		this(numberPart, decimalPart, allowNull, false);

	}

	/**
	 * 构造函数<br>
	 * 不能输入负号
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param decimalPart
	 *            小数部分
	 * @param allowNull
	 *            是否允许位空
	 * @param returnString
	 *            是否返回String类型的值
	 */
	public DoubleCellEditor(int numberPart, int decimalPart, boolean allowNull,
			boolean returnString) {
		this(numberPart, decimalPart, allowNull, returnString, false);

	}

	/**
	 * 构造函数
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param decimalPart
	 *            小数部分
	 * @param allowNull
	 *            是否允许位空
	 * @param returnString
	 *            是否返回String类型的值
	 * @param minus
	 *            是否允许输入负号
	 */
	public DoubleCellEditor(int numberPart, int decimalPart, boolean allowNull,
			boolean returnString, boolean minus) {
		this.textField = new DecimalTextField(numberPart, decimalPart, minus);
		textField.setBorder(normalBorder);
		setAllowNull(allowNull);
		setReturnString(returnString);
		setClickCountToStart(1);
	}

	/**
	 * <pre>
	 * 是否返回String
	 * </pre>
	 * 
	 * @return 如果true返回到model中的是String
	 */
	public boolean isReturnString() {
		return returnString;
	}

	/**
	 * <pre>
	 * 设置是否返回String
	 * </pre>
	 * 
	 * @param returnString
	 *            如果为true返回到model中的是String
	 */
	public void setReturnString(boolean returnString) {
		this.returnString = returnString;
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
	public boolean shouldSelectCell(EventObject anEvent) {
		textField.setToolTipText(textField.getText());
		textField.setBorder(normalBorder);
		return super.shouldSelectCell(anEvent);
	}

	public void setClickCountToStart(int count) {
		clickCountToStart = count;
	}

	public int getClickCountToStart() {
		return clickCountToStart;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		if (e instanceof MouseEvent) {
			return ((MouseEvent) e).getClickCount() >= clickCountToStart;
		}
		return true;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value != null && !"".equals(value)) {
			try {
				double d = Double.valueOf(value.toString());
				textField.setText(df.format(d));
			} catch (Exception e) {
				textField.setText("");
			}
		} else {
			textField.setText((String) value);
		}
		return textField;
	}

	@Override
	public boolean stopCellEditing() {
		String value = textField.getText();
		if (null == value || "".equals(value)) {
			if (!allowNull) {
				textField.setBorder(errorBorder);
				textField.setToolTipText("该字段不能为空！");
				textField.requestFocus();
				return false;
			}
		} else {
			// 判断是否是double类型
			try {
				double d = Double.valueOf(value);
				textField.setText(df.format(d));
			} catch (Exception e) {
				textField.setBorder(errorBorder);
				textField.setToolTipText("只能输入数字！");
				textField.requestFocus();
				return false;
			}
		}
		return super.stopCellEditing();
	}

	@Override
	public Object getCellEditorValue() {
		// 为了解决表格排序时出问题
		String value = textField.getText();

		if (returnString) {
			return value;
		} else {
			// 必须返回double类型或者null
			if (value == null || "".equals(value)) {
				return null;
			} else {
				try {
					return Double.valueOf(value);
				} catch (Exception e) {
					return null;
				}
			}
		}
	}
}