package com.sf.module.EDI.EDIImpl.ui.table;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * 描述：EDI表格
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3885325578698026735L;

	/**
	 * 代码方式移动表格
	 */
	private boolean codeMoved = false;

	/**
	 * 代码方式滚动表格
	 */
	private boolean codeScroll = true;

	public boolean isCodeScroll() {
		return codeScroll;
	}

	public void setCodeScroll(boolean codeScroll) {
		this.codeScroll = codeScroll;
	}

	public boolean isCodeMoved() {
		return codeMoved;
	}

	public void setCodeMoved(boolean codeMoved) {
		this.codeMoved = codeMoved;
	}

	/**
	 * <pre>
	 * 如果 row 和 column 位置的索引在有效范围内，
	 * 并且这些索引处的单元格是可编辑的，则以编程方式启动该位置单元格的函数编辑。
	 * 
	 * </pre>
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean editFunctionAt(int row, int column) {
		TableCellEditor editor = getCellEditor(row, column);
		if (editor == null)
			return false;
		EdiTableCellEditor cellEditor = (EdiTableCellEditor) editor;
		cellEditor.setEditFunctionNext(true);
		return editCellAt(row, column);

	}

}
