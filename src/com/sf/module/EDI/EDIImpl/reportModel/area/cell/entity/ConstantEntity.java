package com.sf.module.EDI.EDIImpl.reportModel.area.cell.entity;

import java.awt.Color;
import java.awt.Window;

import javax.swing.JOptionPane;

import org.dom4j.Element;

import com.sf.module.EDI.util.CodeUtils;

/**
 * 描述：常量Entity
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
public class ConstantEntity extends AbstractEntity implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3029343953452285925L;

	public static final String TYPE = "ConstantEntity";

	private Object value;

	public ConstantEntity() {

	}

	public ConstantEntity(String str) {
		this.value = str;
	}

	@Override
	public String toXml() {
		StringBuilder sbxml = new StringBuilder("<entity type=\"");
		sbxml.append(TYPE);
		sbxml.append("\" value=\"");
		if (this.value != null) {
			// 编码
			sbxml.append(CodeUtils.xmlEncode(value.toString()));
		}
		sbxml.append("\"/>");
		return sbxml.toString();
	}

	@Override
	public void fromXml(Element xml) {
		this.value = xml.attributeValue("value");
	}

	@Override
	public void setValue(Object obj) {
		this.value = obj;

	}

	@Override
	public String getViewStr() {
		if (value == null) {
			return "自定义常量";
		}
		String s = CodeUtils.htmlEncode(value.toString());
		return s;
	}

	@Override
	public Object getValue() {

		return value;
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
		return true;
	}

	@Override
	public IEntity edit(Window window) {
		String str = JOptionPane.showInputDialog(window, "请输入常量值：", getValue());
		if (str != null && !str.equals(getValue())) {
			return new ConstantEntity(str);
		}
		return this;
	}

	public final static Color GREEN = new Color(0, 80, 0);

	@Override
	public Color getColor() {
		return GREEN;
	}
}
