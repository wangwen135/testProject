package com.swing.menu.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPopupMenu;

/**
 * <pre>
 * 描述：菜单布局管理器
 * 从上往下布局，当高度超过屏幕最大高度(可设置)时自动在右侧增加一列
 * 可设置列间距 和 行间距
 * 
 * minimumLayoutSize 、 maximumLayoutSize 返回的结果等于 preferredLayoutSize 方法
 * 
 * <pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2014-9-29      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class MyMenuLayout2 implements LayoutManager2, Serializable {

	private static final long serialVersionUID = 3953825842108590529L;

	/**
	 * 列间隔距离
	 */
	private int colSpace = 0;

	/**
	 * 行间距
	 */
	private int rowSpace = 0;

	/**
	 * 屏幕高度 也可理解为最大高度
	 */
	private int screenHeight;

	public MyMenuLayout2() {
		this(0);
	}

	public MyMenuLayout2(int colspace) {
		this(colspace, 0);
	}

	public MyMenuLayout2(int colspace, int rowspace) {

		this(colspace,rowspace,0);

	}

	public MyMenuLayout2(int colspace, int rowspace, int screenHeight) {
		this.colSpace = colspace;
		this.rowSpace = rowspace;
		this.screenHeight = screenHeight;
	}

	public int getColSpace() {
		return colSpace;
	}

	public void setColSpace(int colSpace) {
		this.colSpace = colSpace;
	}

	public int getRowSpace() {
		return rowSpace;
	}

	public void setRowSpace(int rowSpace) {
		this.rowSpace = rowSpace;
	}

	/**
	 * <pre>
	 * 获取屏幕高度
	 * 如果设置的高度小于等于0，则取实际屏幕高度
	 * </pre>
	 * @return
	 */
	public int getScreenHeight() {

		if (screenHeight <= 0) {
			return (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getHeight();
		} else {
			return screenHeight;
		}
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		clearCache();
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		clearCache();
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		 
		//菜单的UI会改变菜单项的宽度
		//获取最佳大小时，菜单项的宽度会设置为当前menu下最大的宽度
		//如果要解决
		
		// 计算最佳大小
		int width;
		int height;

		int maxHeight = getScreenHeight();
		
		Insets in = parent.getInsets();
		width = in.left + in.right;
		height = in.top + in.bottom;

		List<Integer> widthList = new ArrayList<Integer>();
		List<Integer> heightList = new ArrayList<Integer>();
		int column = 0;
		widthList.add(0);
		heightList.add(height);

		int n = parent.getComponentCount();

		for (int i = 0; i < n; i++) {

			Component c = parent.getComponent(i);
			if (c.isVisible()) {

				Dimension psize = c.getPreferredSize();
				int cw = (int) psize.getWidth();
				int ch = (int) psize.getHeight();

				int _height = heightList.get(column) + ch + rowSpace;

				if (_height <= maxHeight) { // 高度小于屏幕高度

					heightList.set(column, _height);

					widthList.set(column, Math.max(cw, widthList.get(column)));

				} else {// 大于屏幕高度，放到下一列
					column++;
					widthList.add(0);
					heightList.add(height);
					i--;
				}

			}
		}
		// 计算最大的高度
		for (Integer h : heightList) {
			height = Math.max(height, h);
		}
		// 计算总共的宽度
		for (Integer w : widthList) {
			width += w;
		}
		// 计算列间距
		width += colSpace * column;

		return new Dimension(width, height);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// 返回最佳大小
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		// 返回最佳大小
		return preferredLayoutSize(target);
	}

	@Override
	public void layoutContainer(Container parent) {
		int maxHeight = getScreenHeight();
		// 计算每一个容器的X Y
		int x, y;

		Insets in = parent.getInsets();
		x = in.left;
		y = in.top;

		int bottom = in.bottom;

		int maxWidth = 0;

		int n = parent.getComponentCount();

		for (int i = 0; i < n; i++) {

			Component c = parent.getComponent(i);
			if (c.isVisible()) {

				Dimension psize = c.getPreferredSize();
				int cw = (int) psize.getWidth();
				int ch = (int) psize.getHeight();

				int _height = y + ch + rowSpace + bottom;

				if (_height <= maxHeight) { // 高度小于屏幕高度

					// 设置X Y
					c.setBounds(x, y, cw, ch);

					y = y + ch + rowSpace;

					maxWidth = Math.max(cw, maxWidth);

				} else {// 大于屏幕高度，放到下一列
					x = x + maxWidth + colSpace;
					y = in.top;
					maxWidth = 0;

					i--;
				}

			}
		}

	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		clearCache();
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return target.getAlignmentX();
		// return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return target.getAlignmentY();
		// return 0;
	}

	@Override
	public void invalidateLayout(Container target) {
		clearCache();
	}
	
	private void clearCache(){
		
	}

}
