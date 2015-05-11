package com.swing.edi.reportModel.area.cell.function;

import java.awt.Window;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import org.dom4j.Element;

import com.swing.edi.reportModel.utils.ReportModelUtils;
import com.swing.edi.ui.function.SumFunctionEditor;

/**
 * 描述：总和函数
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
public class SumFunction implements IFunction {

	private static final long serialVersionUID = 5558692767792955154L;

	public static final String TYPE = "sum";

	// 小数部分
	private int maxFraction = 8;
	private int minFraction = 0;

	// 整数部分
	private int maxInteger = 20;
	private int minInteger = 0;

	// 舍入模式
	String round = RoundingMode.HALF_UP.name();

	// 分组
	private boolean useGroup = false;

	private BigDecimal bd = new BigDecimal(0);

	/**
	 * 格式
	 */
	private NumberFormat format;
	private boolean rebuild = true;

	private NumberFormat getFormat() {
		if (format == null || rebuild) {
			rebuild = false;
			format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(maxFraction);
			format.setMinimumFractionDigits(minFraction);
			format.setMaximumIntegerDigits(maxInteger);
			format.setMinimumIntegerDigits(minInteger);
			format.setRoundingMode(RoundingMode.valueOf(round));
			format.setGroupingUsed(useGroup);
		}
		return format;
	}

	@Override
	public Object getValue() {
		// 不带指数
		// return bd.toPlainString();

		// 格式化
		return getFormat().format(bd.doubleValue());
	}

	@Override
	public void setValue(Object value) {
		if (value != null) {
			BigDecimal tmp = ReportModelUtils.getDecimal(value);
			if (tmp != null)
				bd = bd.add(tmp);
		}
	}

	public static void main(String[] args) {
		SumFunction s = new SumFunction();
		s.setValue("113");
		System.out.println(s.getValue());
		s.setValue("113");
		System.out.println(s.getValue());
	}

	@Override
	public String getType() {

		return SumFunction.TYPE;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<function type=\"");
		sb.append(SumFunction.TYPE);
		sb.append("\" maxFraction=\"");
		sb.append(this.maxFraction);
		sb.append("\" minFraction=\"");
		sb.append(this.minFraction);
		sb.append("\" maxInteger=\"");
		sb.append(this.maxInteger);
		sb.append("\" minInteger=\"");
		sb.append(this.minInteger);
		sb.append("\" round=\"");
		sb.append(this.round);
		sb.append("\" useGroup=\"");
		sb.append(this.useGroup);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
		this.rebuild = true;
		String maxFraction = xml.attributeValue("maxFraction");
		if (maxFraction != null && !"".equals(maxFraction))
			setMaxFraction(Integer.valueOf(maxFraction));

		String minFraction = xml.attributeValue("minFraction");
		if (minFraction != null && !"".equals(minFraction))
			setMinFraction(Integer.valueOf(minFraction));

		String maxInteger = xml.attributeValue("maxInteger");
		if (maxInteger != null && !"".equals(maxInteger))
			setMaxInteger(Integer.valueOf(maxInteger));

		String minInteger = xml.attributeValue("minInteger");
		if (minInteger != null && !"".equals(minInteger))
			setMinInteger(Integer.valueOf(minInteger));

		String round = xml.attributeValue("round");
		if (round != null && !"".equals(round))
			setRound(round);

		String useGroup = xml.attributeValue("useGroup");
		if (useGroup != null && !"".equals(useGroup))
			setUseGroup(Boolean.valueOf(useGroup));

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
		SumFunctionEditor.getInstance(windows).showEdit(this);
	}

	@Override
	public String getViewStr() {
		return "总和";
	}

	@Override
	public boolean containEntity() {
		return true;
	}

	@Override
	public void reset() {
		bd = new BigDecimal(0);
	}

	public int getMaxFraction() {
		return maxFraction;
	}

	public void setMaxFraction(int maxFraction) {
		this.rebuild = true;
		this.maxFraction = maxFraction;
	}

	public int getMinFraction() {
		return minFraction;
	}

	public void setMinFraction(int minFraction) {
		this.rebuild = true;
		this.minFraction = minFraction;
	}

	public int getMaxInteger() {
		return maxInteger;
	}

	public void setMaxInteger(int maxInteger) {
		this.rebuild = true;
		this.maxInteger = maxInteger;
	}

	public int getMinInteger() {
		return minInteger;
	}

	public void setMinInteger(int minInteger) {
		this.rebuild = true;
		this.minInteger = minInteger;
	}

	public boolean isUseGroup() {
		return useGroup;
	}

	public void setUseGroup(boolean useGroup) {
		this.rebuild = true;
		this.useGroup = useGroup;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.rebuild = true;
		this.round = round;
	}

}
