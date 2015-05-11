package com.swing.component.table;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableModel;

public class DragDropRowTableUI extends BasicTableUI {

	private boolean draggingRow = false;
	private int startDragPoint;
	private int dyOffset;

	public DragDropRowTableUI() {

	}

	protected MouseInputListener createMouseInputListener() {
		DragDropRowMouseInputHandler dss = new DragDropRowMouseInputHandler();

		DragSource ds = DragSource.getDefaultDragSource();
		ds.addDragSourceMotionListener(dss);
		ds.createDefaultDragGestureRecognizer(table,
				DnDConstants.ACTION_COPY_OR_MOVE, dss);
		// ds.startDrag(null, null, o, null);
		return dss;
	}

	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);

		if (draggingRow) {
			g.setColor(table.getParent().getBackground());
			Rectangle cellRect = table.getCellRect(table.getSelectedRow(), 0,
					false);
			g.copyArea(cellRect.x, cellRect.y, table.getWidth(),
					table.getRowHeight(), cellRect.x, dyOffset);

			if (dyOffset < 0) {
				g.fillRect(cellRect.x, cellRect.y
						+ (table.getRowHeight() + dyOffset), table.getWidth(),
						(dyOffset * -1));
			} else {
				g.fillRect(cellRect.x, cellRect.y, table.getWidth(), dyOffset);
			}
		}
	}

	class DragDropRowMouseInputHandler extends MouseInputHandler implements
			DragGestureListener, Transferable, DragSourceMotionListener {

		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			startDragPoint = (int) e.getPoint().getY();
		}

		public void mouseDragged(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);

			draggingRow = false;
			table.repaint();
		}

		public void dragGestureRecognized(DragGestureEvent event) {

			try {
				Cursor cursor = null;
				if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
					cursor = DragSource.DefaultMoveDrop;

				}

				event.startDrag(cursor, this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { DataFlavor.stringFlavor };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return true;
		}

		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			// crate id's string in table
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < table.getSelectedRows().length; i++) {
				sb.append(table.getValueAt(table.getSelectedRows()[i], 0) + ",");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length() - 1);
			}
			return table;
		}

		public void dragMouseMoved(DragSourceDragEvent e) {
			int fromRow = table.getSelectedRow();

			if (fromRow >= 0) {
				draggingRow = true;

				int rowHeight = table.getRowHeight();
				int middleOfSelectedRow = (rowHeight * fromRow)
						+ (rowHeight / 2);

				int toRow = -1;
				int yMousePoint = (int) e.getY();

				if (yMousePoint < (middleOfSelectedRow - rowHeight)) {
					// Move row up
					toRow = fromRow - 1;
				} else if (yMousePoint > (middleOfSelectedRow + rowHeight)) {
					// Move row down
					toRow = fromRow + 1;
				}
				table.setTransferHandler(new TransferHandler("aaaa"));
				/*
				 * if(!(SwingUtilities.getDeepestComponentAt(table.getParent() ,
				 * e.getX(), e.getY()) instanceof JTable)){ DragSource ds =
				 * DragSource.getDefaultDragSource();
				 * ds.createDefaultDragGestureRecognizer(table,
				 * DnDConstants.ACTION_COPY_OR_MOVE, a); }
				 */
				if (toRow >= 0 && toRow < table.getRowCount()) {
					TableModel model = table.getModel();

					for (int i = 0; i < model.getColumnCount(); i++) {
						Object fromValue = model.getValueAt(fromRow, i);
						Object toValue = model.getValueAt(toRow, i);

						model.setValueAt(toValue, fromRow, i);
						model.setValueAt(fromValue, toRow, i);
					}
					table.setRowSelectionInterval(toRow, toRow);
					startDragPoint = yMousePoint;
				}

				dyOffset = (startDragPoint - yMousePoint) * -1;
				table.repaint();
			}
		}
	}

}
