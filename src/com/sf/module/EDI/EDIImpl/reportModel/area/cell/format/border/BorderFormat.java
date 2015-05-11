package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.border;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * 描述：格式化子项，边框
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
public class BorderFormat implements Serializable {
	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "border";
	
	private static final long serialVersionUID = 7499490087596208725L;

	private boolean top = false;
	private boolean left = false;
	private boolean bottom = false;
	private boolean right = false;

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
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
		sb.append("<border t=\"");
		sb.append(top ? "Y" : "N");
		sb.append("\" l=\"");
		sb.append(left ? "Y" : "N");
		sb.append("\" b=\"");
		sb.append(bottom ? "Y" : "N");
		sb.append("\" r=\"");
		sb.append(right ? "Y" : "N");
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
		String t = xml.attributeValue("t");
		if (t != null && !"".equals(t)) {
			setTop("Y".equals(t));
		}
		String l = xml.attributeValue("l");
		if (l != null && !"".equals(l)) {
			setLeft("Y".equals(l));
		}
		String b = xml.attributeValue("b");
		if (b != null && !"".equals(b)) {
			setBottom("Y".equals(b));
		}
		String r = xml.attributeValue("r");
		if (r != null && !"".equals(r)) {
			setRight("Y".equals(r));
		}

	}
}
