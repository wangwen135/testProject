package com.sf.module.EDI.EDIImpl.reportModel.area.cell.property;

import java.io.Serializable;

import org.dom4j.Element;

/**
 * 描述： 单元格属性类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-29      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CellProperty implements Serializable {

	/**
	 * XML节点名称
	 */
	public static final String ELEMENT_NAME = "property";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6876464524940185197L;

	/**
	 * 多品名拆分时显示多品名的默认值
	 */
	public static final boolean DEFAULT_SHOWONMULTGOODS = true;
	/**
	 * 多品名时是否显示
	 */
	private boolean showOnMultGoods = DEFAULT_SHOWONMULTGOODS;

	/**
	 * <pre>
	 * 非默认值
	 * </pre>
	 * 
	 * @return
	 */
	private boolean unDefaultValue() {
		if (showOnMultGoods != DEFAULT_SHOWONMULTGOODS)
			return true;
		return false;
	}

	public String toXml() {
		// 如果属性没有修改则不需要保存到xml中
		if (!unDefaultValue())
			return "";
		// 以后再根据需求改
		// <property showOnMultGoods="true"/>
		StringBuilder sb = new StringBuilder();
		sb.append("<property showOnMultGoods=\"");
		sb.append(this.showOnMultGoods);
		sb.append("\"/>");
		return sb.toString();
	}

	public void fromXml(Element xml) {
		// <property showOnMultGoods="true"/>
		String show = xml.attributeValue("showOnMultGoods");
		if (show != null && !"".equals(show)) {
			setShowOnMultGoods(Boolean.valueOf(show));
		}
	}

	/**
	 * <pre>
	 * 多品名时是否显示
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isShowOnMultGoods() {
		return showOnMultGoods;
	}

	/**
	 * <pre>
	 * 设置多品名时是否显示
	 * </pre>
	 * 
	 * @param showOnMultGoods
	 */
	public void setShowOnMultGoods(boolean showOnMultGoods) {
		this.showOnMultGoods = showOnMultGoods;
	}

}
