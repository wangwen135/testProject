package com.sf.module.EDI.EDIImpl.reportModel.area.cell.function;

import java.awt.Window;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.area.cell.ICell;
import com.sf.module.EDI.EDIImpl.ui.function.FillFunctionEditor;
import com.sf.module.EDI.util.EDIUtils;

/**
 * 描述： 填充函数<br>
 * 可以设置左边填充，右边填充；填充长度；计算中文<br>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-4-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class FillFunction extends AbstractFunction {

	private static final long serialVersionUID = -2915208059028700565L;

	public static final String TYPE = "fill";

	/**
	 * 值
	 */
	private Object value = ICell.NULL_VALUE;

	/**
	 * true左边开始补充;false 右边开始补充
	 */
	private boolean leftFill = true;

	/**
	 * 长度
	 */
	private int lenght = 0;

	/**
	 * 检查中文
	 */
	private boolean checkChinese = true;

	/**
	 * 填充字符
	 */
	private String fillCharacter = "";

	@Override
	public String getParameterViewStr() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" ");
		strbuf.append(leftFill ? "1" : "2");
		strbuf.append(" , ");
		strbuf.append(lenght);
		strbuf.append(" , ");
		strbuf.append(checkChinese);
		strbuf.append(" , \"");
		strbuf.append(fillCharacter);
		strbuf.append("\" ");
		return strbuf.toString();
	}

	public boolean isLeftFill() {
		return leftFill;
	}

	public void setLeftFill(boolean leftFill) {
		this.leftFill = leftFill;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public boolean isCheckChinese() {
		return checkChinese;
	}

	public void setCheckChinese(boolean checkChinese) {
		this.checkChinese = checkChinese;
	}

	public String getFillCharacter() {
		return fillCharacter;
	}

	public void setFillCharacter(String fillCharacter) {
		this.fillCharacter = fillCharacter;
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
		// 进行转换 处理
		if (fillCharacter == null || "".equals(fillCharacter)) {
			this.value = value;
			return;
		}
		StringBuffer tmp = new StringBuffer(value.toString());
		if (lenght == 0 || tmp.length() > lenght) {
			this.value = value;
			return;
		}
		// 计算中文比较麻烦，先计算长度，然后再计算填充字符长度，然后再进行计算
		int valueLenght = EDIUtils.strLength(tmp.toString());
		if (valueLenght >= lenght) {
			this.value = value;
			return;
		}
		int characterLenght = EDIUtils.strLength(fillCharacter);

		while (valueLenght < lenght) {
			if (leftFill) {
				tmp.insert(0, fillCharacter);
			} else {
				tmp.append(fillCharacter);
			}
			valueLenght += characterLenght;
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
		sb.append("\" leftFill=\"");
		sb.append(this.leftFill);
		sb.append("\" lenght=\"");
		sb.append(this.lenght);
		sb.append("\" checkChinese=\"");
		sb.append(this.checkChinese);
		sb.append("\" fillCharacter=\"");
		sb.append(this.fillCharacter);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "填充";
	}

	@Override
	public void fromXml(Element xml) {
		String leftFill = xml.attributeValue("leftFill");
		if (leftFill != null && !"".equals(leftFill)) {
			setLeftFill(Boolean.valueOf(leftFill));
		}

		String lenght = xml.attributeValue("lenght");
		if (lenght != null && !"".equals(lenght)) {
			setLenght(Integer.valueOf(lenght));
		}

		String checkChinese = xml.attributeValue("checkChinese");
		if (checkChinese != null && !"".equals(checkChinese)) {
			setCheckChinese(Boolean.valueOf(checkChinese));
		}

		String fillCharacter = xml.attributeValue("fillCharacter");
		if (fillCharacter != null && !"".equals(fillCharacter)) {
			setFillCharacter(fillCharacter);
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
		FillFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public void reset() {
		leftFill = true;
		value = ICell.NULL_VALUE;
		lenght = 0;
		checkChinese = true;
		fillCharacter = null;
	}

}
