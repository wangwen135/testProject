package com.sf.module.EDI.EDIImpl.dragDrop;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.TreePath;

import com.sf.module.EDI.EDIImpl.ui.tree.EdiMutableTreeNode;

/**
 * 描述：edi报表树传输处理器(拖拽支持)
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
public class EdiTreeTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1583647579583783245L;

	private JTree tree;

	public EdiTreeTransferHandler(JTree tree) {
		this.tree = tree;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		// 设置不能接受拖入
		return false;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		// JTree tree = (JTree)c;
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			Object o = path.getLastPathComponent();
			if (o instanceof EdiMutableTreeNode) {// 只要是edinode节点就可以拖
				EdiMutableTreeNode edinode = (EdiMutableTreeNode) o;
				return new TreeTransferable(edinode.getEntity(),
						edinode.getFunction());
			}
		}
		return null;

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
