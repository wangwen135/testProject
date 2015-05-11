package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.function.JFConvertFunctionEditor;

/**
 * 描述：简繁转换函数<br>
 * 默认使用 tw(台湾)配置文件
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-7-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class JFConvertFunction extends AbstractFunction {

	private static final long serialVersionUID = 1136850979526173229L;

	public static final String TYPE = "j2f";

	/**
	 * 简体转换成繁体
	 */
	private boolean j2f = true;

	/**
	 * 值
	 */
	private Object value = ICell.NULL_VALUE;

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" ");
		strbuf.append(j2f);
		strbuf.append(" ");
		return strbuf.toString();
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		if (value == null) {
			this.value = ICell.NULL_VALUE;
			return;
		}
		// 进行计算
		String tmp = value.toString();
		try {
			if (j2f) {
//				this.value = J2FUtil.j2f_tw(tmp);// 默认使用tw的配置文件
			} else {
//				this.value = J2FUtil.f2j_tw(tmp);
			}
		} catch (Exception e) {
			this.value = value;
		}

	}

	@Override
	public String getType() {
		return JFConvertFunction.TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<function type=\"");
		sb.append(JFConvertFunction.TYPE);
		sb.append("\" j2f=\"");
		sb.append(this.j2f);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "简繁转换";
	}

	@Override
	public void fromXml(Element xml) {
		String j2f = xml.attributeValue("j2f");
		if (j2f != null && !"".equals(j2f)) {
			setJ2f(Boolean.valueOf(j2f));
		}
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
		JFConvertFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public boolean containEntity() {
		return true;
	}

	@Override
	public void reset() {
		value = ICell.NULL_VALUE;
		j2f = true;
	}

	public boolean isJ2f() {
		return j2f;
	}

	public void setJ2f(boolean j2f) {
		this.j2f = j2f;
	}

}
