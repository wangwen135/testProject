package com.swing.drop.advance;

//TransferableTreeNode.java
//A Transferable TreePath to be used with Drag & Drop applications.
//
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.tree.TreePath;

public class TransferableTreeNode implements Transferable {
	public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class,
			"Tree Path");
	// public static DataFlavor TREE_PATH_ARRAY_FLAVOR = new
	// DataFlavor(Arrays.class, "Tree Paths");
	DataFlavor flavors[] = { TREE_PATH_FLAVOR };
	TreePath path;

	public TransferableTreeNode(TreePath tp) {
		path = tp;
	}

	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavor.getRepresentationClass() == TreePath.class);
	}

	public synchronized Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor)) {
			return (Object) path;
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}
}