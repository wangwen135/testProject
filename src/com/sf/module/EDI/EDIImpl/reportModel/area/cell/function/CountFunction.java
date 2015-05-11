package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;
import java.util.HashSet;
import java.util.Set;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.ui.function.CountFunctionEditor;

/**
 * 描述： 统计函数<br>
 * 可以设置是否忽略空值，是否去除重复<br>
 * 可以指定count的字段，或多个字段<br>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-21      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CountFunction extends AbstractFunction {

	private static final long serialVersionUID = 2775914085871683959L;

	public static final String TYPE = "count";

	/**
	 * 值
	 */
	private int count = 0;

	private Set<Object> set = new HashSet<Object>();

	/**
	 * 忽略空值
	 */
	private boolean ignoreNull = false;

	/**
	 * 去重复
	 */
	private boolean distinct = false;

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" ");
		strbuf.append(ignoreNull);
		strbuf.append(" , ");
		strbuf.append(distinct);
		strbuf.append(" ");
		return strbuf.toString();
	}

	public boolean isIgnoreNull() {
		return ignoreNull;
	}

	public void setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	@Override
	public Object getValue() {
		if (distinct) {
			return set.size();
		} else {
			return count;
		}

	}

	@Override
	public void setValue(Object value) {
		if (ignoreNull) {// 忽略空值，包括null，和“”
			if (value == null || "".equals(value.toString())) {
				return;
			}
		}
		count++;
		set.add(value);
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<function type=\"");
		sb.append(TYPE);
		sb.append("\" ignoreNull=\"");
		sb.append(this.ignoreNull);
		sb.append("\" distinct=\"");
		sb.append(this.distinct);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "总数";
	}

	@Override
	public void fromXml(Element xml) {
		String ignore = xml.attributeValue("ignoreNull");
		if (ignore != null && !"".equals(ignore)) {
			setIgnoreNull(Boolean.valueOf(ignore));
		}

		String disti = xml.attributeValue("distinct");
		if (disti != null && !"".equals(disti)) {
			setDistinct(Boolean.valueOf(disti));
		}

	}

	@Override
	public boolean containEntity() {
		return true;
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
		CountFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public void reset() {
		count = 0;
		set.clear();
	}

}
