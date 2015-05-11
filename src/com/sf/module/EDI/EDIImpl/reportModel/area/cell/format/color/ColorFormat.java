package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.color;

import java.io.Serializable;

import org.dom4j.Element;

 /**
 * 描述：颜色格式
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-11      313921         Create
 * ****************************************************************************
 * </pre>
 * @author 313921
 * @since 1.0
 */
public class ColorFormat implements Serializable {
	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "color";
	/**
	 * 
	 */
	private static final long serialVersionUID = 5303041240795311333L;

	// 0表示没有设置颜色

	/**
	 * 前景颜色
	 */
	private int foreground = 0;

	/**
	 * 背景颜色
	 */
	private int background = 0;

	public int getForeground() {
		return foreground;
	}

	public void setForeground(int foreground) {
		this.foreground = foreground;
	}

	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
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
		sb.append("<color foreground=\"");
		sb.append(foreground);
		sb.append("\" background=\"");
		sb.append(background);
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
		String foreground = xml.attributeValue("foreground");
		if (foreground != null && !"".equals(foreground)) {
			setForeground(Integer.valueOf(foreground));
		}

		String background = xml.attributeValue("background");
		if (background != null && !"".equals(background)) {
			setBackground(Integer.valueOf(background));
		}

	}
}
