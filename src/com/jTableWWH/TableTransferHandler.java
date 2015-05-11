package com.jTableWWH;

import java.awt.Rectangle;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

public class TableTransferHandler extends TransferHandler {

	private static final long serialVersionUID = -6840594145949849717L;

	private int oldrow = -1;
	private int oldcol = -1;
	private JTable table;

	public TableTransferHandler(JTable table) {
		this.table = table;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {

		oldrow = table.getSelectedRow();
		oldcol = table.getSelectedColumn();
		Object tableValue = table.getValueAt(oldrow, oldcol);
		// value 应该是cell 类型的
		if (tableValue == null) {
			return null;
		}

		return new StringSelection(tableValue.toString());
	}

	@Override
	public boolean canImport(TransferSupport support) {

		// 只接受string类型的值
		if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}

		// 只支持放置操作，不支持剪切板粘贴
		if (support.isDrop()) {
			// return false;
			// 不能在同一个单元格内拖动
			JTable.DropLocation dl = (JTable.DropLocation) support
					.getDropLocation();

			int row = dl.getRow();
			int column = dl.getColumn();

			if (oldrow == row && oldcol == column) {
				return false;
			}
			// 如果单元格里面有内容需要判断
			return true;
		} else {
			// 剪切板支持在这里增加代码
			return true;
		}
	}

	@Override
	public boolean importData(TransferSupport support) {
		// 导致从剪贴板或 DND 放置操作向组件的传输。
		if (!canImport(support)) {
			return false;
		}

		// 表格应该结束之前的编辑
		if (table.getCellEditor() != null) {
			table.getCellEditor().stopCellEditing();
		}

		int row = 0;
		int column = 0;
		if (support.isDrop()) {
			JTable.DropLocation dl = (JTable.DropLocation) support
					.getDropLocation();

			row = dl.getRow();
			column = dl.getColumn();

		} else {
			row = table.getSelectedRow();
			column = table.getSelectedColumn();
		}

		Object o = null;
		try {
			o = support.getTransferable().getTransferData(
					DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.setValueAt(o, row, column);

		// 表格滚动
		Rectangle rect = table.getCellRect(row, column, false);
		if (rect != null) {
			table.scrollRectToVisible(rect);
		}

		// 数据写入表格之后重绘
		table.repaint();

		return true;
	}

	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY_OR_MOVE;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		if (action == DnDConstants.ACTION_MOVE) {
			table.setValueAt(null, oldrow, oldcol);
		}
		this.oldcol = -1;
		this.oldrow = -1;
	}

}
