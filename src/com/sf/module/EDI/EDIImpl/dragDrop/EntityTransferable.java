package com.sf.module.EDI.EDIImpl.dragDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;

/**
 * 描述： Entity传输数据定义
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
public class EntityTransferable implements Transferable {

	public static final DataFlavor ENTITY_DATA_FLAVOR = new DataFlavor(
			IEntity.class, null);

	private IEntity entity;

	static DataFlavor flavors[] = { EntityTransferable.ENTITY_DATA_FLAVOR };

	public EntityTransferable(IEntity entity) {
		if (entity == null)
			throw new NullPointerException("参数非法，entity对象不能为空！");
		this.entity = entity;
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
			return entity;
		}
		return null;
	}

}
