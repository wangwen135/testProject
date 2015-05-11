package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import org.dom4j.Element;

/**
 * 描述：TabEntity
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-30      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class TabEntity extends AbstractEntity implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5279158647642857863L;

	public static final String TYPE = "TabEntity";

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<entity type=\"");
		sbxml.append(TabEntity.TYPE);
		sbxml.append("\"/>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {

	}

	@Override
	public void setValue(Object obj) {

	}

	@Override
	public String getViewStr() {
		return "TAB";
	}

	@Override
	public Object getValue() {
		return "\t";
	}

	@Override
	public String getKey() {
		return KEY_EDI_CONSTANT;
	}

	@Override
	public String getType() {
		return TYPE;
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
