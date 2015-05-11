package com.swing.edi.dragDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.swing.edi.reportModel.area.cell.ICell;

/**
 * 描述：Cell传输数据定义
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
public class CellTransferable implements Transferable {

	/**
	 * Cell 元信息
	 */
	public static final DataFlavor CELL_DATA_FLAVOR = new DataFlavor(
			ICell.class, null);

	private ICell cell;
	static DataFlavor flavors[] = { CellTransferable.CELL_DATA_FLAVOR };

	public CellTransferable(ICell cell) {
		if (cell == null)
			throw new NullPointerException("参数非法，cell对象不能为空！");
		this.cell = cell;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {

		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		for (DataFlavor d : flavors) {
			if (d.equals(flavor)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (flavors[0].equals(flavor)) {
			return cell;
		}

		return null;
	}

}
