package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format;

import java.util.Iterator;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.align.AlignFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.border.BorderFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.color.ColorFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.font.FontFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.IValueFormat;
import com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value.ValueFormatUtil;

/**
 * 描述：单元格格式
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class Format implements IFormat {

	private static final long serialVersionUID = -7460677137459308955L;

	/**
	 * 颜色
	 */
	private ColorFormat color;

	/**
	 * 边框
	 */
	private BorderFormat border;
	/**
	 * 字体
	 */
	private FontFormat font;
	/**
	 * 对齐
	 */
	private AlignFormat align;
	/**
	 * 数值格式
	 */
	private IValueFormat format;

	@Override
	public void setBorder(BorderFormat border) {
		this.border = border;
	}

	@Override
	public BorderFormat getBorder() {

		return border;
	}

	@Override
	public void setFont(FontFormat font) {
		this.font = font;
	}

	@Override
	public FontFormat getFont() {
		return font;
	}

	@Override
	public void setAlign(AlignFormat align) {
		this.align = align;
	}

	@Override
	public AlignFormat getAlign() {
		return align;
	}

	@Override
	public void setFormat(IValueFormat format) {
		this.format = format;

	}

	@Override
	public IValueFormat getFormat() {
		return this.format;
	}

	@Override
	public void setColor(ColorFormat color) {
		this.color = color;
	}

	@Override
	public ColorFormat getColor() {
		return color;
	}

	@Override
	public String format(Object obj) {// 这里要注意了，需要考虑一下
		if (this.format != null) {
			return format.format(obj).toString();
		}
		return obj.toString();
	}

	@Override
	public String getViewStr() {
		// 目前没有被使用
		return "单元格格式";
	}

	@Override
	public String toXml() {
		// 如果有一个不为空则构建，否则返回 ""
		if (color == null && border == null && font == null && align == null
				&& format == null)
			return "";
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("<format>");
		if (format != null) {
			strbuf.append(format.toXml());
		}
		if (align != null) {
			strbuf.append(align.toXml());
		}
		if (font != null) {
			strbuf.append(font.toXml());
		}
		if (border != null) {
			strbuf.append(border.toXml());
		}
		if (color != null) {
			strbuf.append(color.toXml());
		}

		strbuf.append("</format>");
		return strbuf.toString();
	}

	@Override
	public void fromXml(Element xml) {

		for (Iterator<?> iterator = xml.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if (IValueFormat.ELEMENT_NAME.equals(e.getName())) {
				setFormat(ValueFormatUtil.getValueFormatByElement(e));
			} else if (AlignFormat.ELEMENT_NAME.equals(e.getName())) {
				AlignFormat ali = new AlignFormat();
				ali.fromXml(e);
				setAlign(ali);
			} else if (FontFormat.ELEMENT_NAME.equals(e.getName())) {
				FontFormat font = new FontFormat();
				font.fromXml(e);
				setFont(font);
			} else if (BorderFormat.ELEMENT_NAME.equals(e.getName())) {
				BorderFormat border = new BorderFormat();
				border.fromXml(e);
				setBorder(border);
			} else if (ColorFormat.ELEMENT_NAME.equals(e.getName())) {
				ColorFormat color = new ColorFormat();
				color.fromXml(e);
				setColor(color);
			}
		}

	}
}
