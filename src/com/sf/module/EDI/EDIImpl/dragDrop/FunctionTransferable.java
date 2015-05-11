package com.sf.module.EDI.EDIImpl.dragDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.function.IFunction;

/**
 * 描述： Function传输数据定义
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-26      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FunctionTransferable implements Transferable {

	public static final DataFlavor FUNCTION_DATA_FLAVOR = new DataFlavor(
			IFunction.class, null);

	private IFunction function;

	static DataFlavor flavors[] = { FunctionTransferable.FUNCTION_DATA_FLAVOR };

	public FunctionTransferable(IFunction function) {
		if (function == null)
			throw new NullPointerException("参数非法，function对象不能为空！");
		this.function = function;
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
			return function;
		}
		return null;
	}

}
