package com.sf.module.EDI.EDIImpl.dragDrop;

import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.part.PartEnum;

/**
 * 描述： 编辑对话框TransferHandler
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-6      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EditDialogTableTransferHandler extends TransferHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1195485999598662449L;

	private int oldrow = -1;
	private int oldcol = -1;

	private JTable table;

	public EditDialogTableTransferHandler(JTable table) {
		this.table = table;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {

		oldrow = table.getSelectedRow();
		oldcol = table.getSelectedColumn();
		if (oldcol != 0) {
			// 如果不是第0列是不能拖动的
			return null;
		}

		Object tableValue = table.getValueAt(oldrow, oldcol);
		// value 应该是IEntity 类型的
		if (tableValue == null || tableValue.equals("")) {
			return null;
		}

		return new EntityTransferable((IEntity) tableValue);
	}

	public boolean canImport(TransferSupport support) {

		// 只接受 entity 类型的传输
		if (!support
				.isDataFlavorSupported(EntityTransferable.ENTITY_DATA_FLAVOR)) {
			return false;
		}

		// 只支持放置操作，不支持剪切板粘贴
		if (support.isDrop()) {

			// 不能在同一个单元格内拖动
			JTable.DropLocation dl = (JTable.DropLocation) support
					.getDropLocation();

			int row = dl.getRow();
			int column = dl.getColumn();
			// 只能在第一列内拖动
			if (column == 1) {
				return false;
			}

			// 如果是从树中拖过来的
			Transferable t = support.getTransferable();
			if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				// 支持string类型，是从树中拖过来的，暂时没有更好的办法判断
				return true;
			}

			if (oldrow == row && oldcol == column) {
				return false;
			}

			return true;
		} else {
			// 剪切板支持在这里增加代码
			return false;
		}

	}

	public boolean importData(TransferSupport support) {

		if (!canImport(support)) {
			return false;
		}

		// 表格应该结束之前的编辑
		if (table.getCellEditor() != null) {
			table.getCellEditor().stopCellEditing();
		}

		IEntity entity;
		try {
			entity = (IEntity) support.getTransferable().getTransferData(
					new DataFlavor(IEntity.class, null));
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		JTable.DropLocation dl = (JTable.DropLocation) support
				.getDropLocation();

		int row = dl.getRow();
		int column = dl.getColumn();
		// 如果行和列都为-1则为添加
		if (row == -1 && column == -1) {
			table.setValueAt(entity, table.getRowCount(), 0);
			table.setValueAt(PartEnum.none.getPart(), table.getRowCount(), 1);
		} else {
			Object obj = table.getValueAt(row, column);
			if (obj != null && obj instanceof IEntity) {
				IEntity currentEntity = (IEntity) obj;
				table.setValueAt(entity, row, column);
				if (oldrow != -1 && oldcol != -1) {
					// 如果是表格内的拖动，需要移动值
					table.setValueAt(currentEntity, oldrow, oldcol);
				}
			} else {
				return false;
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
		return TransferHandler.MOVE;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		// 在导出数据之后调用。如果该操作为 MOVE，则此方法应该移除已传输的数据
		// DnDConstants.ACTION_COPY_OR_MOVE
		// if (action == DnDConstants.ACTION_MOVE) {
		// table.setValueAt(null, oldrow, oldcol);
		// }
		// 清空原来的
		this.oldcol = -1;
		this.oldrow = -1;
	}

}
