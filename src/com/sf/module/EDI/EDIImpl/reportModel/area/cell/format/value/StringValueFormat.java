package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value;

import org.dom4j.Element;

/**
 * 描述：字符格式器
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-11      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class StringValueFormat implements IValueFormat {

	private static final long serialVersionUID = 1233915404107832656L;

	public static final String TYPE = "string";

	private boolean toUpCase = false;
	private boolean toLowerCase = false;
	private boolean trim = false;

	public boolean isToUpCase() {
		return toUpCase;
	}

	public void setToUpCase(boolean toUpCase) {
		this.toUpCase = toUpCase;
	}

	public boolean isToLowerCase() {
		return toLowerCase;
	}

	public void setToLowerCase(boolean toLowerCase) {
		this.toLowerCase = toLowerCase;
	}

	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	@Override
	public Object format(Object obj) {
		if (obj == null || "".equals(obj)) {// 到这里不应该出现null值
			return obj;
		}
		String str = obj.toString();
		if (trim) {
			str = str.trim();
		}
		if (toUpCase) {
			str = str.toUpperCase();
		} else if (toLowerCase) {
			str = str.toLowerCase();
		}
		return str;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<valueFormat type=\"");
		sb.append(TYPE);
		sb.append("\" up=\"");
		sb.append(this.toUpCase);
		sb.append("\" lower=\"");
		sb.append(this.toLowerCase);
		sb.append("\" trim=\"");
		sb.append(this.trim);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		String up = xml.attributeValue("up");
		if (up != null && !"".equals(up)) {
			setToUpCase(Boolean.valueOf(up));
		}

		String lower = xml.attributeValue("lower");
		if (lower != null && !"".equals(lower)) {
			setToLowerCase(Boolean.valueOf(lower));
		}

		String trim = xml.attributeValue("trim");
		if (trim != null && !"".equals(trim)) {
			setTrim(Boolean.valueOf(trim));
		}

	}

}
