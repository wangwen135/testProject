package com.swing.dnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Tablednd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablednd frame = new Tablednd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tablednd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		// table.setDropTarget(new DropTarget(table,new DropTargetListener(){
		//
		// @Override
		// public void dragEnter(DropTargetDragEvent dtde) {
		// dtde.acceptDrag(DnDConstants.ACTION_MOVE);
		// //dtde.rejectDrag();
		//
		// }
		//
		// @Override
		// public void dragExit(DropTargetEvent dte) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void dragOver(DropTargetDragEvent dtde) {
		// Point p = dtde.getLocation();
		//
		// System.out.println( p.x);
		// System.out.println(p.y);
		// int row = table.rowAtPoint(p);
		// int column = table.columnAtPoint(p);
		// if (row != -1 && column != -1) {
		// table.addColumnSelectionInterval(column,column);
		// table.addRowSelectionInterval(row, row);
		// }
		//
		// }
		//
		// @Override
		// public void drop(DropTargetDropEvent dtde) {
		// //dtde.acceptDrop(DnDConstants.ACTION_MOVE);
		// Point p = dtde.getLocation();
		//
		// System.out.println( p.x);
		// System.out.println(p.y);
		// int row = table.rowAtPoint(p);
		// int column = table.columnAtPoint(p);
		//
		// try {
		// String data = (String) dtde.getTransferable().getTransferData(
		// DataFlavor.stringFlavor);
		// System.out.println(data);
		// table.setValueAt(data, row, column);
		// } catch (UnsupportedFlavorException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// dtde.dropComplete(true);
		// }
		//
		// @Override
		// public void dropActionChanged(DropTargetDragEvent dtde) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// }));
		table.setDragEnabled(true);
		table.setDropMode(DropMode.USE_SELECTION);
		table.setTransferHandler(new TransferHandler() {
			int oldrow = -1;
			int oldcol = -1;

			@Override
			protected Transferable createTransferable(JComponent c) {
				System.out.println("开始弄数据了");
				oldrow = table.getSelectedRow();
				oldcol = table.getSelectedColumn();
				return new Transferable() {
					DataFlavor flavors[] = { DataFlavor.stringFlavor };

					@Override
					public Object getTransferData(DataFlavor flavor)
							throws UnsupportedFlavorException, IOException {
						return table.getValueAt(oldrow, oldcol);
					}

					@Override
					public DataFlavor[] getTransferDataFlavors() {
						// TODO Auto-generated method stub
						return new DataFlavor[] { DataFlavor.stringFlavor };
					}

					@Override
					public boolean isDataFlavorSupported(DataFlavor flavor) {
						// TODO Auto-generated method stub
						return true;
					}
				};
			}

			// 指示一个组件在实际尝试导入给定数据 flavor 的集合之前是否接受导入。
			public boolean canImport(TransferSupport support) {
				// for the demo, we'll only support drops (not clipboard paste)
				// if (!support.isDrop()) {
				// return false;
				// }

				// we only import Strings
				// if (!support.isDataFlavorSupported(DataFlavor.stringFlavor))
				// {
				// return false;
				// }

				return true;
			}

			// 导致从剪贴板或 DND 放置操作向组件的传输。
			public boolean importData(TransferSupport support) {
				// if we can't handle the import, say so
				if (!canImport(support)) {
					return false;
				}

				// fetch the drop location
				JTable.DropLocation dl = (JTable.DropLocation) support
						.getDropLocation();

				int row = dl.getRow();
				int column = dl.getColumn();

				// fetch the data and bail if this fails
				String data;
				try {
					// 获得拖动数据
					data = (String) support.getTransferable().getTransferData(
							DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException e) {
					return false;
				} catch (IOException e) {
					return false;
				}

				table.setValueAt(data, row, column);

				Rectangle rect = table.getCellRect(row, column, false);
				if (rect != null) {
					table.scrollRectToVisible(rect);
				}

				return true;
			}

			@Override
			public Icon getVisualRepresentation(Transferable t) {
				// 返回一个建立传输外观的对象
				return super.getVisualRepresentation(t);
			}

			@Override
			public int getSourceActions(JComponent c) {
				// TODO Auto-generated method stub
				return TransferHandler.COPY_OR_MOVE;
			}

			@Override
			protected void exportDone(JComponent source, Transferable data,
					int action) {
				// 在导出数据之后调用。如果该操作为 MOVE，则此方法应该移除已传输的数据
				System.out.println(action);
				// DnDConstants.ACTION_COPY_OR_MOVE
				if (action == DnDConstants.ACTION_MOVE) {
					System.out.println("是移动操作，应该清楚原来的数据");
					table.setValueAt(null, oldrow, oldcol);
				}
				// super.exportDone(source, data, action);
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, }, new String[] { "New column",
				"New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);

		textField = new JTextField();
		textField.setDragEnabled(true);
		textField.setDropMode(DropMode.INSERT);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setDragEnabled(true);
		textField_1.setDropMode(DropMode.INSERT);
		panel.add(textField_1);
		textField_1.setColumns(10);
	}

}
