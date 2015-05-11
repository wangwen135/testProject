package com.swing.drop.advance;

//TreeTransferHandler.java
//Handle transferable data generated  
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class TreeTransferHandler extends TransferHandler {
	protected Transferable createTransferable(JComponent c) {
		JTree tree = (JTree) c;
		// DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		// tree.getLastSelectedPathComponent();
		ArrayList<TreePath> treeList = new ArrayList<TreePath>(
				Arrays.asList(tree.getSelectionPaths()));
		System.out.println("generated the tranferable "
				+ treeList.getClass().toString());
		System.out.println("ArrayList.class :" + ArrayList.class);
		System.out.println("TreePath: " + TreePath.class);
		//return new TransferableTreeNode(treeList);// 调用自定义的TransferableTreeNode
		return null;
	}

	protected void exportDone(JComponent source, Transferable data, int action) {
		if (action == MOVE) {
			JTree tree = (JTree) source;
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			model.removeNodeFromParent(node);
		}
	}

	public int getSourceActions(JComponent c) {
		return COPY;
	}
}
