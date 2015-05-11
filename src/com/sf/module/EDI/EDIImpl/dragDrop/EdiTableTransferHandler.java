package com.sf.module.EDI.EDIImpl.dragDrop;

import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.Cell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述：Edi报表表格传输处理器(拖拽支持)
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-7      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTableTransferHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840594145949849717L;

	private int oldrow = -1;
	private int oldcol = -1;
	private JTable table;

	public EdiTableTransferHandler(JTable table) {
		this.table = table;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {

		oldrow = table.getSelectedRow();
		oldcol = table.getSelectedColumn();
		Object tableValue = table.getValueAt(oldrow, oldcol);
		// value 应该是cell 类型的
		if (tableValue == null || tableValue.equals("")) {
			return null;
		}

		return new CellTransferable((ICell) tableValue);
	}

	@Override
	public boolean canImport(TransferSupport support) {

		// 只接受string类型的值
		// if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		// return false;
		// }

		// 只接受 entity，function 和 cell 类型的传输
		if (!support.isDataFlavorSupported(TreeTransferable.ENTITY_DATA_FLAVOR)
				&& !support
						.isDataFlavorSupported(TreeTransferable.FUNCTION_DATA_FLAVOR)
				&& !support
						.isDataFlavorSupported(CellTransferable.CELL_DATA_FLAVOR)) {
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
			if (support
					.isDataFlavorSupported(TreeTransferable.ENTITY_DATA_FLAVOR)) {

				Object obj = table.getValueAt(row, column);
				if (obj != null && obj instanceof ICell) {
					ICell cell = (ICell) obj;
					return cell.containEntity();
				}
			}
			return true;
		} else {
			// 剪切板支持在这里增加代码
			return false;
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

		JTable.DropLocation dl = (JTable.DropLocation) support
				.getDropLocation();

		int row = dl.getRow();
		int column = dl.getColumn();

		if (support.isDataFlavorSupported(CellTransferable.CELL_DATA_FLAVOR)) {
			// 是表格内的拖动
			ICell cell;
			try {
				cell = (ICell) support.getTransferable().getTransferData(
						CellTransferable.CELL_DATA_FLAVOR);
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			cell.setColumn(column);
			cell.setRow(row);
			table.setValueAt(cell, row, column);

		} else if (support
				.isDataFlavorSupported(TreeTransferable.ENTITY_DATA_FLAVOR)) {
			// Entity
			IEntity entity;
			try {
				entity = (IEntity) support.getTransferable().getTransferData(
						TreeTransferable.ENTITY_DATA_FLAVOR);
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			Object obj = table.getValueAt(row, column);
			if (obj != null && obj instanceof ICell) {
				ICell cell = (ICell) obj;
				if (!cell.containEntity())
					return false;
				cell.addEntityAndPart(entity);
			} else {
				ICell cell = new Cell();
				cell.setColumn(column);
				cell.setRow(row);
				cell.addEntityAndPart(entity);
				table.setValueAt(cell, row, column);
			}
		} else if (support
				.isDataFlavorSupported(TreeTransferable.FUNCTION_DATA_FLAVOR)) {
			// Function
			IFunction function;
			try {
				function = (IFunction) support.getTransferable()
						.getTransferData(TreeTransferable.FUNCTION_DATA_FLAVOR);
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			Object obj = table.getValueAt(row, column);
			if (obj != null && obj instanceof ICell) {
				ICell cell = (ICell) obj;
				cell.addFunction(function);
				if (!function.containEntity()) {
					cell.clear();
				}
			} else {
				ICell cell = new Cell();
				cell.setColumn(column);
				cell.setRow(row);
				cell.addFunction(function);
				table.setValueAt(cell, row, column);
			}
		}

		// 表格滚动
		Rectangle rect = table.getCellRect(row, column, false);
		if (rect != null) {
			table.scrollRectToVisible(rect);
		}

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
