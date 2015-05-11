package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.function.SubstringFunctionEditor;
import com.sf.module.EDI.util.EDIUtils;

/**
 * 描述：字符截取函数<br>
 * 支持左边截取，右边截取，指定范围截取，判断中文
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-10      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class SubstringFunction extends AbstractFunction {

	private static final long serialVersionUID = 7102329524586065735L;

	/**
	 * 从左边开始截取
	 */
	public static final int SUB_TYPE_LEFT = 0;
	/**
	 * 从右边开始截取
	 */
	public static final int SUB_TYPE_RIGHT = 1;
	/**
	 * 指定范围截取
	 */
	public static final int SUB_TYPE_DEFINE = 2;

	public static final String TYPE = "sub";

	/**
	 * 值
	 */
	private Object value = ICell.NULL_VALUE;

	/**
	 * 截取方式
	 */
	private int subType = 0;

	/**
	 * 偏移量
	 */
	private int offset = 0;

	/**
	 * 长度
	 */
	private int length = 0;

	/**
	 * 检查中文
	 */
	private boolean checkChinese = true;

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" ");
		strbuf.append(subType);
		strbuf.append(" , ");
		strbuf.append(checkChinese);
		strbuf.append(" , ");
		strbuf.append(offset);
		strbuf.append(" , ");
		strbuf.append(length);
		strbuf.append(" ");
		return strbuf.toString();
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isCheckChinese() {
		return checkChinese;
	}

	public void setCheckChinese(boolean checkChinese) {
		this.checkChinese = checkChinese;
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
		String tmp = value.toString();

		int valueLength = 0;

		if (checkChinese) {
			valueLength = EDIUtils.strLength(tmp);
		} else {
			valueLength = tmp.length();
		}

		if (subType == SUB_TYPE_LEFT) {
			// 如果长度小于小于截取长度不做处理
			if (valueLength <= length) {
				this.value = value;
				return;
			}
			if (checkChinese) {
				int afterLength = valueLength;

				// 先进行一次截取，对于长字符串可以提供一点效率
				if (tmp.length() > length) {
					tmp = tmp.substring(0, length);
					afterLength = EDIUtils.strLength(tmp);
				}

				while (afterLength > length) {
					tmp = tmp.substring(0, tmp.length() - 1);
					afterLength = EDIUtils.strLength(tmp);
				}

			} else {
				tmp = tmp.substring(0, length);
			}
		} else if (subType == SUB_TYPE_RIGHT) {
			if (valueLength <= length) {
				this.value = value;
				return;
			}
			if (checkChinese) {
				int afterLength = valueLength;

				if (tmp.length() > length) {
					tmp = tmp.substring(tmp.length() - length);
					afterLength = EDIUtils.strLength(tmp);
				}

				while (afterLength > length) {
					tmp = tmp.substring(1);
					afterLength = EDIUtils.strLength(tmp);
				}
			} else {
				tmp = tmp.substring(valueLength - length);
			}
		} else if (subType == SUB_TYPE_DEFINE) {
			if (tmp.length() <= offset) {// offset 不判断中文
				this.value = ICell.NULL_VALUE;
				return;
			}
			if (checkChinese) {
				int afterLength = 0;
				if (tmp.length() > (offset + length)) {
					tmp = tmp.substring(offset, offset + length);
					afterLength = EDIUtils.strLength(tmp);
				} else {
					tmp = tmp.substring(offset);
					afterLength = EDIUtils.strLength(tmp);
				}

				while (afterLength > length) {
					tmp = tmp.substring(0, tmp.length() - 1);
					afterLength = EDIUtils.strLength(tmp);
				}

			} else {
				int endIndex = valueLength;
				if (valueLength > (offset + length)) {
					endIndex = offset + length;
				}

				tmp = tmp.substring(offset, endIndex);
			}
		} else {
			throw new IllegalArgumentException("不支持的截取类型：" + subType);
		}

		this.value = tmp;

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
		sb.append("\" subType=\"");
		sb.append(this.subType);
		sb.append("\" length=\"");
		sb.append(this.length);
		sb.append("\" checkChinese=\"");
		sb.append(this.checkChinese);
		sb.append("\" offset=\"");
		sb.append(this.offset);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "截取";
	}

	@Override
	public void fromXml(Element xml) {
		String subType = xml.attributeValue("subType");
		if (subType != null && !"".equals(subType)) {
			setSubType(Integer.valueOf(subType));
		}

		String length = xml.attributeValue("length");
		if (length != null && !"".equals(length)) {
			setLength(Integer.valueOf(length));
		}

		String checkChinese = xml.attributeValue("checkChinese");
		if (checkChinese != null && !"".equals(checkChinese)) {
			setCheckChinese(Boolean.valueOf(checkChinese));
		}

		String offset = xml.attributeValue("offset");
		if (offset != null && !"".equals(offset)) {
			setOffset(Integer.valueOf(offset));
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
		SubstringFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public void reset() {
		subType = 0;
		value = ICell.NULL_VALUE;
		length = 0;
		checkChinese = true;
		offset = 0;
	}

}
