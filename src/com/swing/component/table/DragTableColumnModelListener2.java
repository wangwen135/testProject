package com.swing.component.table;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class DragTableColumnModelListener2 implements TableColumnModelListener {

	private JTable source;
	private JTable desc1;
	private JTable desc2;

	public DragTableColumnModelListener2(JTable source, JTable desc1,
			JTable desc2) {
		this.source = source;
		this.desc1 = desc1;
		this.desc2 = desc2;
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

		TableColumn col2 = desc1.getColumnModel().getColumn(viewColumnIndex);
		TableColumn col3 = desc2.getColumnModel().getColumn(viewColumnIndex);

		int width2 = col.getWidth();
		// col2.setWidth(width2);
		// col3.setWidth(width2);
		// 代码设置列宽方式
		col2.setPreferredWidth(width2);
		col3.setPreferredWidth(width2);

		// 事件触发方式
		// desc1.getTableHeader().setResizingColumn(col2);
		// desc2.getTableHeader().setResizingColumn(col3);
		//
		// desc1.columnMarginChanged(new ChangeEvent(desc1.getColumnModel()));
		// desc2.columnMarginChanged(new ChangeEvent(desc2.getColumnModel()));
	}

	@Override
	public void columnMoved(TableColumnModelEvent e) {

		System.out.println("columnMoved");
		// 列移动同步

		JTableHeader head = source.getTableHeader();

		TableColumn col = head.getDraggedColumn();
		int distance = head.getDraggedDistance();

		int viewColumnIndex = source.convertColumnIndexToView(col
				.getModelIndex());

		TableColumn col2 = desc1.getColumnModel().getColumn(viewColumnIndex);
		TableColumn col3 = desc2.getColumnModel().getColumn(viewColumnIndex);

		// 使另外一个表格动起来
		desc1.getTableHeader().setDraggedColumn(col2);
		desc1.getTableHeader().setDraggedDistance(distance);

		desc2.getTableHeader().setDraggedColumn(col3);
		desc2.getTableHeader().setDraggedDistance(distance);

		int fromIndex = e.getFromIndex();
		int toIndex = e.getToIndex();

		// desc1.repaint();
		// desc2.repaint();
		// 目标表格1
		TableColumnModelEvent e2 = new TableColumnModelEvent(
				desc1.getColumnModel(), fromIndex, toIndex);
		if (fromIndex != toIndex && fromIndex != -1 && toIndex != -1) {
			desc1.moveColumn(fromIndex, toIndex);
			desc2.moveColumn(fromIndex, toIndex);
		}
		desc1.columnMoved(e2);
		desc1.getTableHeader().columnMoved(e2);

		// 目标表格2
		TableColumnModelEvent e3 = new TableColumnModelEvent(
				desc2.getColumnModel(), fromIndex, toIndex);
		desc2.columnMoved(e3);
		desc2.getTableHeader().columnMoved(e3);
	}

	@Override
	public void columnRemoved(TableColumnModelEvent e) {
	}

	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {
	}

}
