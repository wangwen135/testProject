package com.swing.edi.reportModel.area.cell.function;

import java.awt.Window;

import org.dom4j.Element;

import com.swing.edi.ui.function.SequenceFunctionEditor;

/**
 * 描述：序列
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-7      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class SequenceFunction implements IFunction {

	private static final long serialVersionUID = 8224942777304919951L;
	public static final String TYPE = "seq";

	// 起始值
	private int start = 0;
	// 增量
	private int increment = 1;
	// 最大值
	private int maxValue = Integer.MAX_VALUE;

	/**
	 * 值
	 */
	private int value = 0;

	@Override
	public Object getValue() {
		value += increment;
		if (value > maxValue) {
			value = start + increment;
		}
		return value;
	}

	@Override
	public void setValue(Object value) {
		// 无效
	}

	@Override
	public String getType() {
		return SequenceFunction.TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<function type=\"");
		sb.append(SequenceFunction.TYPE);
		sb.append("\" start=\"");
		sb.append(this.start);
		sb.append("\" increment=\"");
		sb.append(this.increment);
		sb.append("\" maxValue=\"");
		sb.append(this.maxValue);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public String getViewStr() {
		return "序列";
	}

	@Override
	public void fromXml(Element xml) {
		// <function type="seq" start="" increment="" maxValue=""/>
		String start = xml.attributeValue("start");
		if (start != null && !"".equals(start))
			setStart(Integer.valueOf(start));

		String increment = xml.attributeValue("increment");
		if (increment != null && !"".equals(increment))
			setIncrement(Integer.valueOf(increment));

		String maxValue = xml.attributeValue("maxValue");
		if (maxValue != null && !"".equals(maxValue))
			setMaxValue(Integer.valueOf(maxValue));

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
		SequenceFunctionEditor.getInstance(windows).showEdit(this);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public boolean containEntity() {
		return false;
	}

	@Override
	public void reset() {
		value = 0;
	}

}
