package com.sf.module.EDI.EDIImpl.ui.orderConditionComboBox;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.sf.module.EDI.EDIImpl.dragDrop.ComboBoxTransferHandler;
import com.sf.module.EDI.EDIImpl.ui.KeyAndViewDefine;

 /**
 * 描述：排序用的下拉列表框
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-15      313921         Create
 * ****************************************************************************
 * </pre>
 * @author 313921
 * @since 1.0
 */
public class OrderComboBox extends JComboBox {

	private static final long serialVersionUID = 2420047958696219106L;

	public OrderComboBox() {
		super();
		setModel(new DefaultComboBoxModel(KeyAndViewDefine.getOrderByArrays()));
		setPreferredSize(new Dimension(100, 21));
		setMaximumRowCount(20);
		// 设置渲染器
		setRenderer(new OrderComboBoxCellRenderer());
		// 设置拖拽器
		setTransferHandler(new ComboBoxTransferHandler(this));
	}
}
