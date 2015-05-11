package com.swing.drop.advance;

//TableTransferHandler.java
//Handle table get transfer data
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.TreePath;

public class TableTransferHandler extends TransferHandler {
	public boolean canImport(JComponent comp, DataFlavor[] flavors) {
		return true;
		/*
		 * for (int j = 0; j < flavors.length; j++) { //
		 * if(t.isDataFlavorSupported(flavors[j])) // if
		 * (DataFlavor.stringFlavor.equals(flavors[j])) {
		 * System.out.println("Flavor is : " + flavors[j]); return true; } }
		 * System.out.println("Not supported flavor"); return false;
		 */
	}

	ArrayList<TreePath> paths;
	JTable table;

	public boolean importData(JComponent comp, Transferable t) {
		table = (JTable) comp;
		for (int j = 0; j < t.getTransferDataFlavors().length; j++) {
			if (t.isDataFlavorSupported(t.getTransferDataFlavors()[j])) {
				try {
					paths = new ArrayList<TreePath>(
							(ArrayList) t.getTransferData(t
									.getTransferDataFlavors()[j]));
					for (int i = 0; i < paths.size(); i++) {
						table.setValueAt(paths.get(i).toString(), i, 0);
					}
				} catch (UnsupportedFlavorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;

	}

}
