package com.swing.edi.dragDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.swing.edi.reportModel.area.cell.entity.IEntity;
import com.swing.edi.reportModel.area.cell.function.IFunction;

/**
 * 描述： 为传输操作提供数据所使用的类<br>
 * 提供String 和 Entity 两种类型的支持
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-1-18      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TreeTransferable implements Transferable {

	/**
	 * Entity 元信息
	 */
	public static final DataFlavor ENTITY_DATA_FLAVOR = new DataFlavor(
			IEntity.class, null);

	/**
	 * Function 元信息
	 */
	public static final DataFlavor FUNCTION_DATA_FLAVOR = new DataFlavor(
			IFunction.class, null);

	private IEntity entity;
	private IFunction function;

	static DataFlavor flavors_entity[] = { DataFlavor.stringFlavor,
			TreeTransferable.ENTITY_DATA_FLAVOR };

	static DataFlavor flavors_function[] = { DataFlavor.stringFlavor,
			TreeTransferable.FUNCTION_DATA_FLAVOR };

	public TreeTransferable(IEntity entity, IFunction function) {
		this.entity = entity;
		this.function = function;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		if (entity != null) {
			return flavors_entity;
		}
		if (function != null) {
			return flavors_function;
		}
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (DataFlavor.stringFlavor.equals(flavor)) {
			return true;
		}
		if (entity != null) {
			if (TreeTransferable.ENTITY_DATA_FLAVOR.equals(flavor)) {
				return true;
			}
		}
		if (function != null) {
			if (TreeTransferable.FUNCTION_DATA_FLAVOR.equals(flavor)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (DataFlavor.stringFlavor.equals(flavor)) {
			if (entity != null) {
				return entity.toXml();
			}
			if (function != null) {
				return function.toXml();
			}
		}
		if (TreeTransferable.ENTITY_DATA_FLAVOR.equals(flavor)) {
			return entity;
		}
		if (TreeTransferable.FUNCTION_DATA_FLAVOR.equals(flavor)) {
			return function;
		}
		return null;
	}

}
