package com.swing.drop.advance;

//TreeToTable.java
//Init GUI and Tree
import java.awt.*;
import javax.swing.*;

public class TreeToTable {
	JTree tree;
	JTable table;
	final static String TARGET_EXT = "java";

	public class InitJTree extends JTree {
		public InitJTree() {
			// Init JTree with default JTree example
			super();
		}
	}

	private JPanel getContent() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = gbc.BOTH;
		gbc.insets.right = 2;
		panel.add(getTreeComponent(), gbc);
		gbc.insets.left = 2;
		panel.add(getTableComponent(), gbc);
		return panel;
	}

	private JScrollPane getTreeComponent() {
		tree = new JTree();
		tree.setDragEnabled(true);
		tree.setTransferHandler(new TreeTransferHandler());
		return new JScrollPane(tree);
	}

	private JScrollPane getTableComponent() {
		table = new JTable(8, 2);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setTransferHandler(new TableTransferHandler());
		return new JScrollPane(table);
	}

	public static void main(String[] args) {
		TreeToTable test = new TreeToTable();
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(test.getContent());
		f.setSize(500, 400);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}