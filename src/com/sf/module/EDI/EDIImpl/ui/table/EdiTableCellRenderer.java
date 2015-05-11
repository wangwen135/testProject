package com.sf.module.EDI.EDIImpl.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.IFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.border.BorderFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.color.ColorFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.font.FontFormat;
import com.sf.module.EDI.EDIImpl.ui.format.FormatEditor;

/**
 * 描述：表格单元格渲染器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class EdiTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -7713481716727078607L;

	/**
	 * 边框缓存
	 */
	private Map<String, Border> borderMap = new HashMap<String, Border>();

	/**
	 * 字体缓存
	 */
	private Map<String, Font> fontMap = new HashMap<String, Font>();

	/**
	 * 默认字体
	 */
	private Font defaultFont = new Font("宋体", Font.PLAIN, 12);

	public EdiTableCellRenderer() {
		setFont(defaultFont);// 字体
		setVerticalAlignment(SwingConstants.TOP);
		setHorizontalAlignment(SwingConstants.LEFT);// 靠左
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// String str = "";
		// if (value != null) {
		// ICell cell = (ICell) value;
		// str = cell.getViewStr();
		// }

		JLabel c = (JLabel) super.getTableCellRendererComponent(table, "",
				isSelected, hasFocus, row, column);

		// if (row % 2 == 0) {
		// c.setBackground(Color.WHITE);
		// } else {
		// c.setBackground(SystemColor.inactiveCaptionText);
		// }

		// 设置值
		if (value != null) {
			ICell cell = (ICell) value;
			setText(cell.getViewStr());
			// 设置边框
			setBorder(getCellBorder(cell));
			// 设置字体
			setFont(getCellFont(cell));

			// 设置前景颜色
			c.setForeground(getCellForeground(cell));

			// 设置背景颜色
			c.setBackground(getCellBackground(cell));
			if (isSelected) {
				Border b1 = getBorder();
				if (b1 != null) {
					setBorder(new CompoundBorder(b1, new BevelBorder(
							BevelBorder.RAISED, null, Color.LIGHT_GRAY,
							null, null)));
				} else {
					setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY,
							null, null, null));
				}
			}

		} else {
			setBackground(Color.WHITE);
			if (isSelected) {
				setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY,
						null, null, null));
			}
		}

		return c;
	}

	/**
	 * <pre>
	 * 获取背景色
	 * </pre>
	 * 
	 * @param cell
	 * @return
	 */
	public Color getCellBackground(ICell cell) {
		if (cell == null || cell.getFormat() == null
				|| cell.getFormat().getColor() == null) {
			return Color.WHITE;
		}

		ColorFormat cf = cell.getFormat().getColor();
		Color c = FormatEditor.colorsTable.get(cf.getBackground());
		if (c != null)
			return c;
		// 默认白色
		return Color.WHITE;
	}

	/**
	 * <pre>
	 * 获取前景色
	 * </pre>
	 * 
	 * @param cell
	 * @return
	 */
	public Color getCellForeground(ICell cell) {
		if (cell == null || cell.getFormat() == null
				|| cell.getFormat().getColor() == null) {
			return Color.BLACK;
		}

		ColorFormat cf = cell.getFormat().getColor();
		Color c = FormatEditor.colorsTable.get(cf.getForeground());
		if (c != null)
			return c;

		// 默认黑色
		return Color.BLACK;
	}

	/**
	 * <pre>
	 * 获取单元格字体
	 * </pre>
	 * 
	 * @param cell
	 * @return
	 */
	public Font getCellFont(ICell cell) {
		if (cell == null)
			return defaultFont;
		IFormat format = cell.getFormat();
		if (format == null)
			return defaultFont;
		FontFormat font = format.getFont();
		if (font == null)
			return defaultFont;
		StringBuffer key = new StringBuffer("f");
		key.append(font.getFamily());
		key.append(font.getStyle());
		key.append(font.getSize());

		Font f = fontMap.get(key.toString());
		if (f == null) {
			f = new Font(font.getFamily(), font.getStyle(), font.getSize() + 2);
			fontMap.put(key.toString(), f);
		}
		return f;
	}

	/**
	 * <pre>
	 * 获取单元格边框
	 * </pre>
	 * 
	 * @param cell
	 * @return
	 */
	public Border getCellBorder(ICell cell) {
		if (cell == null)
			return null;
		IFormat format = cell.getFormat();
		if (format == null)
			return null;
		BorderFormat borderf = format.getBorder();
		if (borderf == null)
			return null;

		int top = borderf.isTop() ? 1 : 0;
		int left = borderf.isLeft() ? 1 : 0;
		int bottom = borderf.isBottom() ? 1 : 0;
		int right = borderf.isRight() ? 1 : 0;
		StringBuffer key = new StringBuffer("b");
		key.append(top);
		key.append(left);
		key.append(bottom);
		key.append(right);

		Border b = borderMap.get(key.toString());
		if (b == null) {
			b = new MatteBorder(top, left, bottom, right, Color.BLACK);
			borderMap.put(key.toString(), b);
		}
		return b;
	}

}
