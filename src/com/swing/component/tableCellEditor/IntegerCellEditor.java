package com.swing.component.tableCellEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;

import com.swing.component.JTextField.DecimalTextField;

/**
 * 描述：Integer数字单元格编辑器<br>
 * 例如：只能输入4位整数，可以为空:<br>
 * setCellEditor(new IntegerCellEditor(4,true));
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2011-12-31      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class IntegerCellEditor extends AbstractCellEditor implements
		TableCellEditor {
	/**
	 * 最大长度
	 */
	public static final int MAX_LENGTH = 10;

	private static final long serialVersionUID = -3163526574480804205L;
	private Border errorBorder = BorderFactory.createLineBorder(Color.RED);
	private Border normalBorder = BorderFactory.createLineBorder(Color.GRAY);
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
	public IntegerCellEditor(JTextField textField) {
		this.textField = textField;
		textField.setBorder(normalBorder);
	}

	/**
	 * 构造函数<br>
	 * 默认不能为空，不能输入负号，并且返回Integer值
	 * 
	 * @param numberPart
	 *            整数部分
	 */
	public IntegerCellEditor(Integer numberPart) {
		this(numberPart, false);
	}

	/**
	 * 构造函数<br>
	 * 默认不能输入负号，返回Integer值
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param allowNull
	 *            是否允许为空
	 */
	public IntegerCellEditor(Integer numberPart, boolean allowNull) {
		this(numberPart, allowNull, false);

	}

	/**
	 * 构造函数<br>
	 * 不能输入负号
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param allowNull
	 *            是否允许位空
	 * @param returnString
	 *            是否返回String类型的值
	 */
	public IntegerCellEditor(Integer numberPart, boolean allowNull,
			boolean returnString) {
		this(numberPart, allowNull, returnString, false);
	}

	/**
	 * 构造函数
	 * 
	 * @param numberPart
	 *            整数部分
	 * @param allowNull
	 *            是否允许位空
	 * @param returnString
	 *            是否返回String类型的值
	 * @param minus
	 *            是否允许输入负号
	 */
	public IntegerCellEditor(Integer numberPart, boolean allowNull,
			boolean returnString, boolean minus) {
		if (null == numberPart || numberPart <= 0 || numberPart > MAX_LENGTH) {
			numberPart = MAX_LENGTH;
		}
		this.textField = new DecimalTextField(numberPart, 0, minus);
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
				Integer i = Integer.valueOf(value.toString());
				textField.setText(i.toString());
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
			// 判断是否是Integer类型
			try {
				Integer i = Integer.valueOf(value);
				textField.setText(i.toString());
			} catch (Exception e) {
				textField.setBorder(errorBorder);
				textField.setToolTipText("只能输入整型数字！");
				textField.requestFocus();
				return false;
			}
		}
		return super.stopCellEditing();
	}

	@Override
	public Object getCellEditorValue() {
		String value = textField.getText();
		if (returnString) {
			return value;
		} else {
			// 必须返回Integer类型或者null
			if (value == null || "".equals(value)) {
				return null;
			} else {
				try {
					return Integer.valueOf(value);
				} catch (Exception e) {
					return null;
				}
			}
		}
	}
}