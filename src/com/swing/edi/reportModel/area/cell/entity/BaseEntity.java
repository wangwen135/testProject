package com.swing.edi.reportModel.area.cell.entity;

import org.dom4j.Element;

public class BaseEntity implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5271534122386088411L;

	public static final String TYPE = "BaseEntity";

	// 所有的Entity从此开始，其他Entity需要从此开始包装

	private String key;
	private String viewStr;
	private Object value;

	public BaseEntity() {

	}

	public BaseEntity(String key, Object value, String viewStr) {
		if (key == null || "".equals(key)) {
			throw new IllegalArgumentException("参数非法，key不能为空！");
		}
		if (viewStr == null || "".equals(viewStr)) {
			throw new IllegalArgumentException("参数非法，viewStr不能为空！");
		}

		this.key = key;
		this.value = value;
		this.viewStr = viewStr;
	}

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<entity type=\"");
		sbxml.append(BaseEntity.TYPE);
		sbxml.append("\" key=\"");
		sbxml.append(this.key);
		sbxml.append("\" value=\"");// 精简xml先不改了
		if (this.value != null) {
			sbxml.append(this.value.toString());
		}
		sbxml.append("\" viewStr=\"");
		sbxml.append(viewStr);
		sbxml.append("\"/>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {
		this.key = xml.attributeValue("key");
		this.value = xml.attributeValue("value");
		this.viewStr = xml.attributeValue("viewStr");
	}

	@Override
	public void setValue(Object obj) {
		this.value = obj;

	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String getKey() {

		return key;
	}

	@Override
	public String getViewStr() {
		return viewStr;
	}

	@Override
	public String getType() {
		return BaseEntity.TYPE;
	}
}
