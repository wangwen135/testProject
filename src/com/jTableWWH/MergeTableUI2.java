package com.jTableWWH;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MergeTableUI2 extends BasicTableUI {

	@Override
	public void paint(Graphics g, JComponent c) {
		Rectangle clip = g.getClipBounds();

		Rectangle bounds = table.getBounds();
		// account for the fact that the graphics has already been translated
		// into the table's bounds
		bounds.x = bounds.y = 0;

		if (table.getRowCount() <= 0 || table.getColumnCount() <= 0 ||
		// this check prevents us from painting the entire table
		// when the clip doesn't intersect our bounds at all
				!bounds.intersects(clip)) {

			paintDropLines(g);
			return;
		}

		boolean ltr = table.getComponentOrientation().isLeftToRight();

		Point upperLeft = clip.getLocation();
		if (!ltr) {
			upperLeft.x++;
		}

		Point lowerRight = new Point(clip.x + clip.width - (ltr ? 1 : 0),
				clip.y + clip.height);

		int rMin = table.rowAtPoint(upperLeft);
		int rMax = ((MergeTable) table).maxRowAtPoint(lowerRight);
		// This should never happen (as long as our bounds intersect the clip,
		// which is why we bail above if that is the case).
		if (rMin == -1) {
			rMin = 0;
		}
		// If the table does not have enough rows to fill the view we'll get -1.
		// (We could also get -1 if our bounds don't intersect the clip,
		// which is why we bail above if that is the case).
		// Replace this with the index of the last row.
		if (rMax == -1) {
			rMax = table.getRowCount() - 1;
		}

		int cMin = table.columnAtPoint(ltr ? upperLeft : lowerRight);
		int cMax = table.columnAtPoint(ltr ? lowerRight : upperLeft);
		// This should never happen.
		if (cMin == -1) {
			cMin = 0;
		}
		// If the table does not have enough columns to fill the view we'll get
		// -1.
		// Replace this with the index of the last column.
		if (cMax == -1) {
			cMax = table.getColumnCount() - 1;
		}

		// Paint the grid.
		paintGrid(g, rMin, rMax, cMin, cMax);

		// Paint the cells.
		paintCells(g, rMin, rMax, cMin, cMax);

		paintDropLines(g);

	}

	/**
	 * <pre>
	 * 修改自BasicTableUI
	 * 
	 * </pre>
	 * 
	 * @param g
	 * @param rMin
	 * @param rMax
	 * @param cMin
	 * @param cMax
	 */
	private void paintCells(Graphics g, int rMin, int rMax, int cMin, int cMax) {

		System.out.println("rMin=" + rMin + "rMax=" + rMax + "cMin=" + cMin
				+ "cMax=" + cMax);

		JTableHeader header = table.getTableHeader();
		TableColumn draggedColumn = (header == null) ? null : header
				.getDraggedColumn();

		TableColumnModel cm = table.getColumnModel();
		int columnMargin = cm.getColumnMargin();

		Rectangle cellRect;
		TableColumn aColumn;
		int columnWidth;
		if (table.getComponentOrientation().isLeftToRight()) {
			for (int row = rMin; row <= rMax; row++) {

				// 修改代码
				for (int column = cMin; column <= cMax; column++) {
					aColumn = cm.getColumn(column);

					// 直接从表格取单元格的范围
					cellRect = table.getCellRect(row, column, false);

					// 测试
					// System.out.println("格子 row=" + row + " col=" + column);
					// System.out.println(cellRect);

					if (aColumn != draggedColumn) {

						MergeTable mt = (MergeTable) table;
						MergeBlock mergeBlock = mt.findMergeBlock(row, column);
						if (mergeBlock != null) {
							int newRow = mergeBlock.getRow();
							int newCol = mergeBlock.getColumn();
							cellRect = table.getCellRect(newRow, newCol, false);
							paintCell(g, cellRect, newRow, newCol);
						} else {
							paintCell(g, cellRect, row, column);
						}
					}
				}
			}
		} else {
			for (int row = rMin; row <= rMax; row++) {
				cellRect = table.getCellRect(row, cMin, false);
				aColumn = cm.getColumn(cMin);
				if (aColumn != draggedColumn) {
					columnWidth = aColumn.getWidth();
					cellRect.width = columnWidth - columnMargin;
					paintCell(g, cellRect, row, cMin);
				}
				for (int column = cMin + 1; column <= cMax; column++) {
					aColumn = cm.getColumn(column);
					columnWidth = aColumn.getWidth();
					cellRect.width = columnWidth - columnMargin;
					cellRect.x -= columnWidth;
					if (aColumn != draggedColumn) {
						paintCell(g, cellRect, row, column);
					}
				}
			}
		}

		// Paint the dragged column if we are dragging.
		if (draggedColumn != null) {
			paintDraggedArea(g, rMin, rMax, draggedColumn,
					header.getDraggedDistance());
		}

		// Remove any renderers that may be left in the rendererPane.
		rendererPane.removeAll();
	}

	/**
	 * <pre>
	 * 绘制拖动区域
	 * </pre>
	 * 
	 * @param g
	 * @param rMin
	 * @param rMax
	 * @param draggedColumn
	 * @param distance
	 */
	private void paintDraggedArea(Graphics g, int rMin, int rMax,
			TableColumn draggedColumn, int distance) {

		// 获取拖动列的索引
		int draggedColumnIndex = viewIndexForColumn(draggedColumn);

		MergeTable mTable = (MergeTable) table;
		for (int row = rMin; row <= rMax; row++) {

			// 返回正常单元格的矩形
			Rectangle rectangle = mTable.getNormalCellRect(row,
					draggedColumnIndex, true);

			if (rectangle != null) {
				// 填充灰色
				g.setColor(table.getParent().getBackground());
				g.fillRect(rectangle.x, rectangle.y, rectangle.width - 1,
						rectangle.height);

				// 移动
				rectangle.x += distance;

				// 填充背景色
				g.setColor(table.getBackground());
				g.fillRect(rectangle.x, rectangle.y, rectangle.width,
						rectangle.height);

				// 画垂直线
				if (table.getShowVerticalLines()) {
					g.setColor(table.getGridColor());
					int x1 = rectangle.x;
					int y1 = rectangle.y;
					int x2 = x1 + rectangle.width - 1;
					int y2 = y1 + rectangle.height - 1;
					// Left
					g.drawLine(x1 - 1, y1, x1 - 1, y2);
					// Right
					g.drawLine(x2, y1, x2, y2);
				}

				// 画水平线
				if (table.getShowHorizontalLines()) {
					g.setColor(table.getGridColor());

					int x1 = rectangle.x;
					int y1 = rectangle.y;
					int x2 = x1 + rectangle.width - 1;
					int y2 = y1 + rectangle.height - 1;

					// 画上面的线
					g.drawLine(x1, y1 - 1, x2, y1 - 1);

					g.drawLine(x1, y2, x2, y2);
				}

				// 画单元格
				paintCell(g, rectangle, row, draggedColumnIndex);

			}

		}

		// Rectangle minCell = table.getCellRect(rMin, draggedColumnIndex,
		// true);
		// Rectangle maxCell = table.getCellRect(rMax, draggedColumnIndex,
		// true);
		//
		// // 计算此 minCell 与指定 maxCell 的并集
		// Rectangle vacatedColumnRect = minCell.union(maxCell);
		//
		// // 画一个灰色区域以代替移动的列
		// g.setColor(table.getParent().getBackground());
		// g.fillRect(vacatedColumnRect.x, vacatedColumnRect.y,
		// vacatedColumnRect.width, vacatedColumnRect.height);
		//
		// // 移动到已被拖动的位置
		// vacatedColumnRect.x += distance;
		//
		// // 再次以当前背景色填充拖动区域
		// g.setColor(table.getBackground());
		// g.fillRect(vacatedColumnRect.x, vacatedColumnRect.y,
		// vacatedColumnRect.width, vacatedColumnRect.height);
		//
		// // 如有必要，画垂直网格线
		// // 需要修改，因为可能有些单元格被合并了
		// if (table.getShowVerticalLines()) {
		// g.setColor(table.getGridColor());
		// int x1 = vacatedColumnRect.x;
		// int y1 = vacatedColumnRect.y;
		// int x2 = x1 + vacatedColumnRect.width - 1;
		// int y2 = y1 + vacatedColumnRect.height - 1;
		// // Left
		// g.drawLine(x1 - 1, y1, x1 - 1, y2);
		// // Right
		// g.drawLine(x2, y1, x2, y2);
		// }
		//
		// for (int row = rMin; row <= rMax; row++) {
		// // Render the cell value
		// Rectangle r = table.getCellRect(row, draggedColumnIndex, false);
		// r.x += distance;
		// paintCell(g, r, row, draggedColumnIndex);
		//
		// // Paint the (lower) horizontal grid line if necessary.
		// if (table.getShowHorizontalLines()) {
		// g.setColor(table.getGridColor());
		// Rectangle rcr = table
		// .getCellRect(row, draggedColumnIndex, true);
		// rcr.x += distance;
		// int x1 = rcr.x;
		// int y1 = rcr.y;
		// int x2 = x1 + rcr.width - 1;
		// int y2 = y1 + rcr.height - 1;
		// g.drawLine(x1, y2, x2, y2);
		// }
		// }
	}

	private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
		if (table.isEditing() && table.getEditingRow() == row
				&& table.getEditingColumn() == column) {
			Component component = table.getEditorComponent();
			component.setBounds(cellRect);
			component.validate();
		} else {
			TableCellRenderer renderer = table.getCellRenderer(row, column);
			Component component = table.prepareRenderer(renderer, row, column);

			// if (component instanceof JComponent) {
			// ((JComponent) component).setBorder(BorderFactory
			// .createLineBorder(table.getGridColor()));
			// }

			rendererPane.paintComponent(g, component, table, cellRect.x,
					cellRect.y, cellRect.width, cellRect.height, true);
		}
	}

	private int viewIndexForColumn(TableColumn aColumn) {
		TableColumnModel cm = table.getColumnModel();
		for (int column = 0; column < cm.getColumnCount(); column++) {
			if (cm.getColumn(column) == aColumn) {
				return column;
			}
		}
		return -1;
	}

	private void paintDropLines(Graphics g) {
		JTable.DropLocation loc = table.getDropLocation();
		if (loc == null) {
			return;
		}

		Color color = UIManager.getColor("Table.dropLineColor");
		Color shortColor = UIManager.getColor("Table.dropLineShortColor");
		if (color == null && shortColor == null) {
			return;
		}

		Rectangle rect;

		rect = getHDropLineRect(loc);
		if (rect != null) {
			int x = rect.x;
			int w = rect.width;
			if (color != null) {
				extendRect(rect, true);
				g.setColor(color);
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			if (!loc.isInsertColumn() && shortColor != null) {
				g.setColor(shortColor);
				g.fillRect(x, rect.y, w, rect.height);
			}
		}

		rect = getVDropLineRect(loc);
		if (rect != null) {
			int y = rect.y;
			int h = rect.height;
			if (color != null) {
				extendRect(rect, false);
				g.setColor(color);
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
			}
			if (!loc.isInsertRow() && shortColor != null) {
				g.setColor(shortColor);
				g.fillRect(rect.x, y, rect.width, h);
			}
		}
	}

	private Rectangle getHDropLineRect(JTable.DropLocation loc) {
		if (!loc.isInsertRow()) {
			return null;
		}

		int row = loc.getRow();
		int col = loc.getColumn();
		if (col >= table.getColumnCount()) {
			col--;
		}

		Rectangle rect = table.getCellRect(row, col, true);

		if (row >= table.getRowCount()) {
			row--;
			Rectangle prevRect = table.getCellRect(row, col, true);
			rect.y = prevRect.y + prevRect.height;
		}

		if (rect.y == 0) {
			rect.y = -1;
		} else {
			rect.y -= 2;
		}

		rect.height = 3;

		return rect;
	}

	private Rectangle getVDropLineRect(JTable.DropLocation loc) {
		if (!loc.isInsertColumn()) {
			return null;
		}

		boolean ltr = table.getComponentOrientation().isLeftToRight();
		int col = loc.getColumn();
		Rectangle rect = table.getCellRect(loc.getRow(), col, true);

		if (col >= table.getColumnCount()) {
			col--;
			rect = table.getCellRect(loc.getRow(), col, true);
			if (ltr) {
				rect.x = rect.x + rect.width;
			}
		} else if (!ltr) {
			rect.x = rect.x + rect.width;
		}

		if (rect.x == 0) {
			rect.x = -1;
		} else {
			rect.x -= 2;
		}

		rect.width = 3;

		return rect;
	}

	private Rectangle extendRect(Rectangle rect, boolean horizontal) {
		if (rect == null) {
			return rect;
		}

		if (horizontal) {
			rect.x = 0;
			rect.width = table.getWidth();
		} else {
			rect.y = 0;

			if (table.getRowCount() != 0) {
				Rectangle lastRect = table.getCellRect(table.getRowCount() - 1,
						0, true);
				rect.height = lastRect.y + lastRect.height;
			} else {
				rect.height = table.getHeight();
			}
		}

		return rect;
	}

	/**
	 * <pre>
	 * 修改自BasicTableUI
	 * </pre>
	 * 
	 * @param g
	 * @param rMin
	 * @param rMax
	 * @param cMin
	 * @param cMax
	 */
	private void paintGrid(Graphics g, int rMin, int rMax, int cMin, int cMax) {
		g.setColor(table.getGridColor());

		// 划线的方法需要重写
		// 有个简单的方法给每一个单元格画上边框

		// Rectangle minCell = table.getCellRect(rMin, cMin, true);
		// Rectangle maxCell = table.getCellRect(rMax, cMax, true);
		// Rectangle damagedArea = minCell.union(maxCell);

		if (table.getShowHorizontalLines()) {
			// int tableWidth = damagedArea.x + damagedArea.width;
			// int y = damagedArea.y;
			// for (int row = rMin; row <= rMax; row++) {
			// y += table.getRowHeight(row);
			// g.drawLine(damagedArea.x, y - 1, tableWidth - 1, y - 1);
			// }

			for (int row = rMin; row <= rMax; row++) {
				for (int col = cMin; col <= cMax; col++) {

					int newRow = row;
					int newCol = col;

					MergeTable mt = (MergeTable) table;
					MergeBlock mergeBlock = mt.findMergeBlock(row, col);
					if (mergeBlock != null) {
						newRow = mergeBlock.getRow();
						newCol = mergeBlock.getColumn();
					}

					Rectangle cell = table.getCellRect(newRow, newCol, true);

					if (cell.width != 0 && cell.height != 0) {

						// g.drawLine(cell.x, cell.y - 1, cell.x + cell.width -
						// 1,
						// cell.y - 1);

						g.drawLine(cell.x, cell.y + cell.height - 1, cell.x
								+ cell.width - 1, cell.y + cell.height - 1);
					}
				}
			}

		}
		if (table.getShowVerticalLines()) {

			for (int row = rMin; row <= rMax; row++) {
				for (int col = cMin; col <= cMax; col++) {
					int newRow = row;
					int newCol = col;

					MergeTable mt = (MergeTable) table;
					MergeBlock mergeBlock = mt.findMergeBlock(row, col);
					if (mergeBlock != null) {
						newRow = mergeBlock.getRow();
						newCol = mergeBlock.getColumn();
					}

					Rectangle cell = table.getCellRect(newRow, newCol, true);

					if (cell.width != 0 && cell.height != 0) {
						// g.drawLine(cell.x - 1, cell.y, cell.x - 1, cell.y
						// + cell.height - 1);

						g.drawLine(cell.x + cell.width - 1, cell.y, cell.x
								+ cell.width - 1, cell.y + cell.height - 1);
					}
				}
			}

			// TableColumnModel cm = table.getColumnModel();
			// int tableHeight = damagedArea.y + damagedArea.height;
			// int x;
			// if (table.getComponentOrientation().isLeftToRight()) {
			// x = damagedArea.x;
			// for (int column = cMin; column <= cMax; column++) {
			// int w = cm.getColumn(column).getWidth();
			// x += w;
			// g.drawLine(x - 1, 0, x - 1, tableHeight - 1);
			// }
			// } else {
			// x = damagedArea.x;
			// for (int column = cMax; column >= cMin; column--) {
			// int w = cm.getColumn(column).getWidth();
			// x += w;
			// g.drawLine(x - 1, 0, x - 1, tableHeight - 1);
			// }
			// }
		}
	}
}
