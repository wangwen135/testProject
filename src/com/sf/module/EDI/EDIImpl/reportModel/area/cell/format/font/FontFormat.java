package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.font;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * 描述：格式化子项，字体<br>
 * 默认值是 宋体、常规、10
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
public class FontFormat implements Serializable {

	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "font";

	private static final long serialVersionUID = -4533406742724854300L;

	/**
	 * 正常的
	 */
	public static final int NORMAL = 0;

	/**
	 * 加粗的
	 */
	public static final int BOLD = 1;

	/**
	 * 倾斜的
	 */
	public static final int ITALIC = 2;

	/**
	 * 加粗+倾斜
	 */
	public static final int BOLD_ITALIC = 3;

	/**
	 * 字体
	 */
	private String family = "宋体";

	/**
	 * 字形
	 */
	private int style = NORMAL;

	/**
	 * 字号
	 */
	private int size = 10;

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
		sb.append("<font family=\"");
		sb.append(family);
		sb.append("\" style=\"");
		sb.append(style);
		sb.append("\" size=\"");
		sb.append(size);
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
		String family = xml.attributeValue("family");
		if (family != null && !"".equals(family)) {
			setFamily(family);
		}

		String style = xml.attributeValue("style");
		if (style != null && !"".equals(style)) {
			setStyle(Integer.valueOf(style));
		}

		String size = xml.attributeValue("size");
		if (size != null && !"".equals(size)) {
			setSize(Integer.valueOf(size));
		}

	}
}
