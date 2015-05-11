package com.swing.component.table;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;

public class DragRowTableUI extends BasicTableUI {

	private int startDragPoint;
	private int dyOffset;

	private boolean draggingRow = false;
	private boolean mousePressed = false;

	public DragRowTableUI() {

	}

	@Override
	protected MouseInputListener createMouseInputListener() {
		new MouseInputAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e);
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (e.getClickCount() == 1) {
						mousePressed = true;
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println(e);
				mousePressed = false;
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if(mousePressed){
					System.out.println("拖动。。。"+e);
				}
				super.mouseMoved(e);
			}
		};
		return super.createMouseInputListener();
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		System.out.println("paint");
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
}
