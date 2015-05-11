package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;

/**
 * 描述：日期格式器
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
public class DateValueFormat implements IValueFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2418792256771212125L;

	public static final String TYPE = "date";

	private String descPattern;
	private String srcPattern;
	private SimpleDateFormat descFormat;
	private SimpleDateFormat srcFormat;

	public String getDescPattern() {
		return descPattern;
	}

	public void setDescPattern(String descPattern) {
		if (descPattern == null || "".equals(descPattern)) {
			this.descPattern = null;
			this.descFormat = null;
		} else {
			this.descPattern = descPattern;
			// 如果格式不匹配不做处理
			try {
				this.descFormat = new SimpleDateFormat(descPattern);
			} catch (Exception e) {
				this.descFormat = null;
			}
		}
	}

	public String getSrcPattern() {
		return srcPattern;
	}

	public void setSrcPattern(String srcPattern) {
		if (srcPattern == null || "".equals(srcPattern)) {
			this.srcPattern = null;
			this.srcFormat = null;
		} else {

			this.srcPattern = srcPattern;
			try {
				srcFormat = new SimpleDateFormat(srcPattern);
			} catch (Exception e) {
				srcFormat = null;
			}
		}
	}

	@Override
	public Object format(Object obj) {
		// 如果格式为空返回原值
		if (descFormat == null)
			return obj;
		if (srcFormat == null) {
			// 判断obj是不是date类型，
			if (obj instanceof Date) {
				// 格式化
				return descFormat.format((Date) obj);
			} else {
				return obj;
			}
		}

		try {
			return descFormat.format(srcFormat.parse(obj.toString()));
		} catch (Exception e) {
			return obj;
		}

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
		sb.append("\" src=\"");
		sb.append(this.srcPattern == null ? "" : this.srcPattern);
		sb.append("\" desc=\"");
		sb.append(this.descPattern == null ? "" : this.descPattern);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		String src = xml.attributeValue("src");
		if (src != null && !"".equals(src)) {
			setSrcPattern(src);
		}

		String desc = xml.attributeValue("desc");
		if (desc != null && !"".equals(desc)) {
			setDescPattern(desc);
		}
	}

}
