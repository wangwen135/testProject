package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;
import java.util.regex.Pattern;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.function.ReplaceFunctionEditor;
import com.sf.module.EDI.util.CodeUtils;

/**
 * 描述： 替换函数
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-5-25      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ReplaceFunction extends AbstractFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6772425148486143886L;

	public static final String TYPE = "replace";

	/**
	 * 替换方式1： 替换所有
	 */
	public static final String REPLACE_ALL = "0";
	/**
	 * 替换方式2： 替换第一个
	 */
	public static final String REPLACE_FIRST = "1";

	/**
	 * 替换方式
	 */
	private String replaceType = REPLACE_ALL;

	/**
	 * 使用正则表达
	 */
	private boolean useRegex = true;

	/**
	 * 正则表达式
	 */
	private String regex = "";

	/**
	 * 替换字符串
	 */
	private String replacement = "";

	/**
	 * 值
	 */
	private Object value = ICell.NULL_VALUE;

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" ");
		strbuf.append(replaceType);
		strbuf.append(" , ");
		strbuf.append(useRegex);
		strbuf.append(" , \"");
		strbuf.append(regex);
		strbuf.append("\" , \"");
		strbuf.append(replacement);
		strbuf.append("\" ");
		return strbuf.toString();
	}

	public String getReplaceType() {
		return replaceType;
	}

	public void setReplaceType(String replaceType) {
		this.replaceType = replaceType;
	}

	public boolean isUseRegex() {
		return useRegex;
	}

	public void setUseRegex(boolean useRegex) {
		this.useRegex = useRegex;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		if (regex == null) {
			this.regex = "";
		} else {
			this.regex = regex;
		}
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		if (replacement == null) {
			this.replacement = "";
		} else {
			this.replacement = replacement;
		}
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
			String tregex = regex;
			if (!useRegex) {// 不启用正则表达式
				tregex = Pattern.quote(regex);
			}
			if (REPLACE_ALL.equals(replaceType)) {
				this.value = tmp.replaceAll(tregex, replacement);
			} else {
				this.value = tmp.replaceFirst(tregex, replacement);
			}
		} catch (Exception e) {
			// 当替换出现异常时不再进行替换
			this.value = tmp;
		}

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
		sb.append("\" replaceType=\"");
		sb.append(this.replaceType);
		sb.append("\" useRegex=\"");
		sb.append(this.useRegex);
		sb.append("\" regex=\"");// xml编码
		sb.append(CodeUtils.xmlEncode(this.regex));
		sb.append("\" replacement=\"");// xml编码
		sb.append(CodeUtils.xmlEncode(this.replacement));
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "替换";
	}

	@Override
	public void fromXml(Element xml) {
		String replType = xml.attributeValue("replaceType");
		if (replType != null && !"".equals(replType)) {
			setReplaceType(replType);
		}

		String useReg = xml.attributeValue("useRegex");
		if (useReg != null && !"".equals(useReg)) {
			setUseRegex(Boolean.valueOf(useReg));
		}

		String regex = xml.attributeValue("regex");
		if (regex != null && !"".equals(regex)) {
			setRegex(regex);
		}

		String replacement = xml.attributeValue("replacement");
		if (replacement != null && !"".equals(replacement)) {
			setReplacement(replacement);
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
		ReplaceFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public void reset() {
		replaceType = REPLACE_ALL;
		value = ICell.NULL_VALUE;
		useRegex = true;
		regex = "";
		replacement = "";
	}

}
