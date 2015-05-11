package com.sf.module.EDI.EDIImpl.ui.table;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;

/**
 * 描述：该监听器实现三个表格的列拖拽同步
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
public class DragTableColumnModelListener implements TableColumnModelListener {

	private EdiTable source;
	private EdiTable[] desc;

	public DragTableColumnModelListener(EdiTable source, EdiTable... desc) {
		this.source = source;
		this.desc = desc;

	}

	@Override
	public void columnAdded(TableColumnModelEvent e) {

	}

	@Override
	public void columnMarginChanged(ChangeEvent e) {
		// 列宽拖动同步

		TableColumn col = source.getTableHeader().getResizingColumn();
		if (col == null)
			return;
		int viewColumnIndex = source.convertColumnIndexToView(col
				.getModelIndex());

		int width = col.getWidth();
		for (EdiTable des : desc) {
			if (des.getColumnCount() <= viewColumnIndex)
				continue;
			TableColumn desCol = des.getColumnModel()
					.getColumn(viewColumnIndex);
			desCol.setWidth(width);
			desCol.setPreferredWidth(width);
		}

	}

	@Override
	public void columnMoved(TableColumnModelEvent e) {
		// 表格移动只做标记

		int fromIndex = e.getFromIndex();
		int toIndex = e.getToIndex();
		if (fromIndex != toIndex && fromIndex != -1 && toIndex != -1) {
			source.setCodeMoved(true);
		}

		// 下面是同步移动的代码
		/*
		 * if (source.isCodeMoved()) { source.setCodeMoved(false); return; }
		 * 
		 * JTableHeader head = source.getTableHeader();
		 * 
		 * TableColumn col = head.getDraggedColumn(); int distance =
		 * head.getDraggedDistance();
		 * 
		 * int viewColumnIndex = source.convertColumnIndexToView(col
		 * .getModelIndex());
		 * 
		 * int fromIndex = e.getFromIndex(); int toIndex = e.getToIndex();
		 * 
		 * int max = Math.max(fromIndex, toIndex); max = Math.max(max,
		 * viewColumnIndex);
		 * 
		 * for (EdiTable des : desc) { des.setCodeMoved(true); if
		 * (des.getColumnCount() <= max) {// 找个最大的
		 * des.getTableHeader().setDraggedDistance(0); } else {
		 * 
		 * if (fromIndex != toIndex && fromIndex != -1 && toIndex != -1) {
		 * des.moveColumn(fromIndex, toIndex); }
		 * 
		 * TableColumn col2 = des.getColumnModel().getColumn( viewColumnIndex);
		 * // 设置表格移动列及移动范围 des.getTableHeader().setDraggedColumn(col2);
		 * des.getTableHeader().setDraggedDistance(distance); }
		 * 
		 * // 是表格动起来 TableColumnModelEvent e2 = new TableColumnModelEvent(
		 * des.getColumnModel(), fromIndex, toIndex); des.columnMoved(e2);
		 * des.getTableHeader().columnMoved(e2);
		 * 
		 * }
		 */}

	@Override
	public void columnRemoved(TableColumnModelEvent e) {

	}

	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {

	}

}
