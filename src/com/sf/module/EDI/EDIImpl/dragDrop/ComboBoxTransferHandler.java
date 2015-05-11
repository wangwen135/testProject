package com.sf.module.EDI.EDIImpl.dragDrop;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity.IEntity;
import com.sf.module.EDI.EDIImpl.ui.KeyAndViewDefine;

/**
 * 描述：排序下拉列表传输处理器(拖拽支持)
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-15      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ComboBoxTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1583647579583783245L;

	private JComboBox combobox;

	public ComboBoxTransferHandler(JComboBox combobox) {
		this.combobox = combobox;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (!support
				.isDataFlavorSupported(EntityTransferable.ENTITY_DATA_FLAVOR)) {
			return false;
		}
		// 如果是combobox不包含的可以不能放入
		IEntity entity;
		try {
			entity = (IEntity) support.getTransferable().getTransferData(
					EntityTransferable.ENTITY_DATA_FLAVOR);
			String key = entity.getKey();
			if (!KeyAndViewDefine.containsOrderKey(key)) {
				return false;
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		// 不能从这里拖出去
		return null;

	}

	@Override
	public boolean importData(TransferSupport support) {
		// 导致从剪贴板或 DND 放置操作向组件的传输。
		if (!canImport(support)) {
			return false;
		}
		IEntity entity;
		try {
			entity = (IEntity) support.getTransferable().getTransferData(
					EntityTransferable.ENTITY_DATA_FLAVOR);
			String key = entity.getKey();
			if (KeyAndViewDefine.containsOrderKey(key)) {
				combobox.setSelectedItem(key);
			} else {
				return false;
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		// 在导出数据之后调用
		super.exportDone(source, data, action);
	}

	@Override
	public int getSourceActions(JComponent c) {
		// 返回源支持的传输动作的类型
		return DnDConstants.ACTION_COPY;
	}

}
