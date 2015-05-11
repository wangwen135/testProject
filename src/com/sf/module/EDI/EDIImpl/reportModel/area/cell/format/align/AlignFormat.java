package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.align;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * 描述：格式化子项，对齐方式
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-12      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class AlignFormat implements Serializable {

	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "align";

	private static final long serialVersionUID = -4520365340273615684L;

	/**
	 * 常规
	 */
	public static final short ALIGN_GENERAL = 0;
	/**
	 * 靠左
	 */
	public static final short ALIGN_LEFT = 1;
	/**
	 * 居中
	 */
	public static final short ALIGN_CENTER = 2;
	/**
	 * 靠右
	 */
	public static final short ALIGN_RIGHT = 3;
	/**
	 * 填充
	 */
	public static final short ALIGN_FILL = 4;
	/**
	 * 两端对齐
	 */
	public static final short ALIGN_JUSTIFY = 5;
	/**
	 * 跨列居中
	 */
	public static final short ALIGN_CENTER_SELECTION = 6;
	
	/**
	 * 靠上
	 */
	public static final short VERTICAL_TOP = 0;
	/**
	 * 居中
	 */
	public static final short VERTICAL_CENTER = 1;
	/**
	 * 靠下
	 */
	public static final short VERTICAL_BOTTOM = 2;
	/**
	 * 两端对齐
	 */
	public static final short VERTICAL_JUSTIFY = 3;

	/**
	 * 水平对齐
	 */
	private int alignment = ALIGN_LEFT;

	/**
	 * 垂直对齐
	 */
	private int verticalAlignment = VERTICAL_BOTTOM;

	/**
	 * 自动换行
	 */
	private boolean wrap = false;

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public int getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(int verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public boolean isWrap() {
		return wrap;
	}

	public void setWrap(boolean wrap) {
		this.wrap = wrap;
	}

	/**
	 * <pre>
	 * 转换成XML
	 * </pre>
	 * 
	 * @return
	 */
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<align h=\"");
		sb.append(alignment);
		sb.append("\" v=\"");
		sb.append(verticalAlignment);
		sb.append("\" wrap=\"");
		sb.append(wrap);
		sb.append("\"/>");
		return sb.toString();
	}

	/**
	 * <pre>
	 * 从XML元素构建对象
	 * </pre>
	 * 
	 * @param xml
	 */
	public void fromXml(Element xml) {
		String h = xml.attributeValue("h");
		if (h != null && !"".equals(h)) {
			setAlignment(Integer.valueOf(h));
		}

		String v = xml.attributeValue("v");
		if (v != null && !"".equals(v)) {
			setVerticalAlignment(Integer.valueOf(v));
		}

		String wrap = xml.attributeValue("wrap");
		if (wrap != null && !"".equals(wrap)) {
			setWrap(Boolean.valueOf(wrap));
		}
	}

}
