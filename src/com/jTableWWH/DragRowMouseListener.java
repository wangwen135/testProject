package com.jTableWWH;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-3-21      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DragRowMouseListener extends MouseAdapter {

	private int oldY = 0;

	private int row = 0;
	private int oldHeight = 0;
	private boolean drag = false;

	private int allHeight = 0;

	private boolean reSize = false;
	private int reSizeHeight = 0;

	private MergeTable table;

	/**
	 * 最小高度
	 */
	private int minHeight = 20;

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public DragRowMouseListener(MergeTable table) {
		this.table = table;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		int onRow = table.maxRowAtPoint(e.getPoint());

		int height = 0;
		for (int i = 0; i <= onRow; i++) {
			height = height + table.getRowHeight(i);
		}

		if (0 < height - e.getY() && height - e.getY() < 2) {
			// 应该要进行判断
			//table.setDragEnabled(false);

			drag = true;
			allHeight = height;
			table.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		} else {
			//table.setDragEnabled(true);

			drag = false;
			table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (drag) {
			table.setDragEnabled(false);
			oldY = e.getY();
			row = table.maxRowAtPoint(e.getPoint());
			oldHeight = table.getRowHeight(row);
			// table.setRowSelectionInterval(row, row);

			reSize = false;
		}
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		if (drag) {
			reSize = true;

			int value = oldHeight + e.getY() - oldY;

			int lineSite = allHeight + e.getY() - oldY;

			table.setRowSelectionInterval(row, row);
			table.repaint();

			if (value < minHeight) {
				// table.setRowHeight(row, 20);
				reSizeHeight = minHeight;
				lineSite = allHeight - oldHeight + minHeight;
			} else {
				// table.setRowHeight(row, value);
				reSizeHeight = value;
			}

			final int ls = lineSite;

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					int x = e.getX();
					if (x < 0)
						x = 0;
					if (x > table.getWidth() - 60)
						x = table.getWidth() - 60;

					int y = e.getY();
					if (y < ls)
						y = ls;
					if (y > table.getHeight())
						y = table.getHeight();

					table.getGraphics().drawString(
							"  (" + reSizeHeight + "像素)", x, y - 2);
					table.getGraphics().drawLine(0, ls, table.getWidth(), ls);

				}
			});

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (drag) {
			// 需要判断的
			table.setDragEnabled(true);
			table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			if (reSize) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						table.setRowHeight(row, reSizeHeight);
					}
				});
				// table.setRowHeight(row, reSizeHeight);
				// table.repaint();
			}

			reSize = false;
		}
	}

}
