package com.jTableWWH;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * 描述：实现表格行高调整
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
public class DragRowMouseListenerTwo extends MouseAdapter {

	private int oldY = 0;

	private int row = 0;
	private int oldHeight = 0;
	private boolean drag = false;

	private int allHeight = 0;

	private boolean reSize = false;
	private int reSizeHeight = 0;

	private JTable mainTable;

	private JTable headTable;

	/**
	 * 最小高度
	 */
	private int minHeight = 20;

	/**
	 * <pre>
	 * 获取最小行高
	 * </pre>
	 * 
	 * @return
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * <pre>
	 * 设置最小行高
	 * </pre>
	 * 
	 * @param minHeight
	 */
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/**
	 * 构造函数
	 * @param headTable
	 * @param mainTable
	 */
	public DragRowMouseListenerTwo(JTable headTable, JTable mainTable) {
		this.headTable = headTable;
		this.mainTable = mainTable;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		drag = false;
		
		int onRow = headTable.rowAtPoint(e.getPoint());

		int height = 0;
		for (int i = 0; i <= onRow; i++) {
			height = height + headTable.getRowHeight(i);
		}

		if (-3 < height - e.getY() && height - e.getY() < 3) {
			// 应该要进行判断
			// table.setDragEnabled(false);

			drag = true;
			allHeight = height;
			headTable.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		} else {
			// table.setDragEnabled(true);
			drag = false;
			headTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (drag) {
			// headTable.setDragEnabled(false);
			oldY = e.getY();
			row = headTable.rowAtPoint(e.getPoint());
			oldHeight = headTable.getRowHeight(row);
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

			headTable.setRowSelectionInterval(row, row);

			headTable.repaint();

			mainTable.repaint();

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
					// int x = e.getX();
					// if (x < 0)
					// x = 0;
					// if (x > headTable.getWidth() - 60)
					// x = headTable.getWidth() - 60;

					int y = e.getY();
					if (y < ls)
						y = ls;
					if (y > headTable.getHeight())
						y = headTable.getHeight();

					// 画主表格
					mainTable.getGraphics().drawString(
							"  (" + reSizeHeight + "像素)", 0, y - 2);

					headTable.getGraphics().drawLine(0, ls,
							headTable.getWidth(), ls);
					// 画主表格
					mainTable.getGraphics().drawLine(0, ls,
							mainTable.getWidth(), ls);

				}
			});

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (drag) {
			// 需要判断的
			// headTable.setDragEnabled(true);
			
			drag = false;
			
			headTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			if (reSize) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						headTable.setRowHeight(row, reSizeHeight);
						mainTable.setRowHeight(row, reSizeHeight);
					}
				});
				// table.setRowHeight(row, reSizeHeight);
				// table.repaint();
			}

			reSize = false;
		}
	}

}
