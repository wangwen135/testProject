package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.ui.KeyAndViewDefine;

/**
 * 描述：基本Entity实现，表示变量
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-1-17      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class BaseEntity extends AbstractEntity implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5271534122386088411L;

	public static final String TYPE = "BaseEntity";

	private String key;
	private Object value;

	public BaseEntity() {

	}

	public BaseEntity(String key) {
		if (key == null || "".equals(key)) {
			throw new IllegalArgumentException("参数非法，key不能为空！");
		}

		this.key = key;
	}

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<entity type=\"");
		sbxml.append(BaseEntity.TYPE);
		sbxml.append("\" key=\"");
		sbxml.append(this.key);
		sbxml.append("\"/>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {
		this.key = xml.attributeValue("key");
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
		return KeyAndViewDefine.getViewByKey(key);
	}

	@Override
	public String getType() {
		return BaseEntity.TYPE;
	}

	@Override
	public boolean canEdit() {
		return false;
	}

	@Override
	public IEntity edit(Window window) {
		return this;
	}

	@Override
	public Color getColor() {
		return null;
	}
}
