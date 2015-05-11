package com.swing.component.tableCellEditor;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class DoubleTextEditor extends DefaultCellEditor {

	private static final long serialVersionUID = -1420479200925510293L;
	private Border errorBorder = BorderFactory.createLineBorder(Color.RED);
	private Border normalBorder = BorderFactory.createLineBorder(Color.GRAY);

	private boolean allowNull = false;
	private JTextField textField;
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

	public DoubleTextEditor(JTextField textField) {
		super(textField);
		this.textField = textField;
		editorComponent.setBorder(normalBorder);
		setClickCountToStart(1);
	}

	public DoubleTextEditor(JTextField textField, boolean allowNull) {
		this(textField);
		setAllowNull(allowNull);
	}

	@Override
	public boolean stopCellEditing() {
		if ("".equals(getCellEditorValue())) {
			if (!allowNull) {
				editorComponent.setBorder(errorBorder);
				editorComponent.setToolTipText("该字段不能为空！");
				editorComponent.requestFocus();
				return false;
			}
		} else {
			// 判断是否是double类型
			try {
				Double.valueOf((String) getCellEditorValue());
			} catch (NumberFormatException e) {
				editorComponent.setBorder(errorBorder);
				editorComponent.setToolTipText("只能输入数字！");
				editorComponent.requestFocus();
				return false;
			}
		}
		editorComponent.setToolTipText((String) getCellEditorValue());
		editorComponent.setBorder(normalBorder);
		return super.stopCellEditing();
	}
}