package com.sf.module.EDI.EDIImpl.ui.orderConditionComboBox;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.sf.module.EDI.EDIImpl.ui.KeyAndViewDefine;

/**
 * 描述： 排序条件框渲染器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-15      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class OrderComboBoxCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1212242392996987932L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		String str = KeyAndViewDefine.getViewByKey((String) value);

		return super.getListCellRendererComponent(list, str, index, isSelected,
				cellHasFocus);

	}
}
