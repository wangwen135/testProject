package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.ui.function.DateFunctionEditor;

/**
 * 描述：日期时间函数
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-8      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class DateFunction extends AbstractFunction {

	private static final long serialVersionUID = 6882597778758255304L;

	public static final String TYPE = "date";

	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat format;

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" \"");
		strbuf.append(pattern);
		strbuf.append("\" ");
		return strbuf.toString();
	}

	@Override
	public Object getValue() {
		Date d = new Date();
		if (format == null) {
			format = new SimpleDateFormat(pattern);
		}
		return format.format(d);
	}

	@Override
	public void setValue(Object value) {
		// 无效
	}

	@Override
	public String getType() {
		return DateFunction.TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<function type=\"");
		sb.append(DateFunction.TYPE);
		sb.append("\" pattern=\"");
		sb.append(this.pattern);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "日期";
	}

	@Override
	public void fromXml(Element xml) {
		String pattern = xml.attributeValue("pattern");
		if (pattern != null && !"".equals(pattern))
			setPattern(pattern);
	}

	@Override
	public boolean canEdit() {
		return true;
	}

	@Override
	public boolean canDelete() {
		return true;
	}

	@Override
	public void showEditor(Window windows) {
		DateFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public boolean containEntity() {
		return false;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
		format = new SimpleDateFormat(pattern);
	}

	@Override
	public void reset() {

	}

}
