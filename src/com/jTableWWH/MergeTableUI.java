package com.jTableWWH;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableCellRenderer;

public class MergeTableUI extends BasicTableUI {

	@Override
	public void paint(Graphics g, JComponent c) {
		Rectangle r = g.getClipBounds();
		int firstRow = table.rowAtPoint(new Point(0, r.y));
		int lastRow = table.rowAtPoint(new Point(0, r.y + r.height));
		if (lastRow < 0)
			lastRow = table.getRowCount() - 1;
		for (int i = firstRow; i <= lastRow; i++)
			paintRow(i, g);
	}

	private void paintRow(int row, Graphics g) {
		Rectangle r = g.getClipBounds();
		for (int i = 0; i < table.getColumnCount(); i++) {
			Rectangle r1 = table.getCellRect(row, i, true);
			if (r1.intersects(r)) // at least a part is visible
			{
				if (MergeBlock.BY_OTHER_MERGE_STATE == ((MergeTable) table)
						.getCellState(row, i))
					continue;

				paintCell(row, i, g, r1);
			}
		}
	}

	private void paintCell(int row, int column, Graphics g, Rectangle area) {
		int verticalMargin = table.getRowMargin();//获取单元格之间的间距，以像素为单位
		
		int horizontalMargin = table.getColumnModel().getColumnMargin();//每列中单元格之间的宽度
		
		Color c = g.getColor();
		
		g.setColor(table.getGridColor());//返回用来绘制网格线的颜色
		
		g.drawRect(area.x, area.y, area.width - 1, area.height - 1);//绘制指定矩形的边框
		
		g.setColor(c);
		
		// 将此 Rectangle 的边界 Rectangle 设置为指定的 x、y、width 和 height
		area.setBounds(area.x + horizontalMargin / 2, area.y + verticalMargin
				/ 2, area.width - horizontalMargin, area.height
				- verticalMargin);
		
		
		if (table.isEditing() && table.getEditingRow() == row
				&& table.getEditingColumn() == column) {
			Component component = table.getEditorComponent();
			component.setBounds(area);
			component.validate();
		} else {
			TableCellRenderer renderer = table.getCellRenderer(row, column);
			Component component = table.prepareRenderer(renderer, row, column);
			if (component.getParent() == null)
				rendererPane.add(component);
			rendererPane.paintComponent(g, component, table, area.x, area.y,
					area.width, area.height, true);
		}
	}
}
