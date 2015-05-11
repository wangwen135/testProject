package com.sf.module.EDI.EDIImpl.ui.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * 描述：Edi表格列模型
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-5      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTableColumnModel extends DefaultTableColumnModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7292490940404550184L;

	@Override
	public TableColumn getColumn(int columnIndex) {

		if (columnIndex < 0 || columnIndex >= getColumnCount()) {
			// 出现这种情况一般是UI出问题了，columnIndex =-1，为了不报错返回第0列

			return super.getColumn(0);
		}

		return super.getColumn(columnIndex);
	}

}
