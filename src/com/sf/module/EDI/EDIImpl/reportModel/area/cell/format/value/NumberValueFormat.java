package com.sf.module.EDI.EDIImpl.reportModel.area.cell.format.value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import org.dom4j.Element;

import com.sf.module.EDI.EDIImpl.reportModel.utils.ReportModelUtils;

/**
 * 描述：数字格式器
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
public class NumberValueFormat implements IValueFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = -515850379668899776L;
	public static final String TYPE = "number";

	// 小数部分
	private int maxFraction = 8;
	private int minFraction = 0;

	// 整数部分
	private int maxInteger = 20;
	private int minInteger = 1;

	// 分组
	private boolean useGroup = false;

	// 舍入模式
	// String round = RoundingMode.HALF_UP.name();

	/**
	 * 格式
	 */
	private NumberFormat format;

	// private boolean rebuild = true;

	private NumberFormat getFormat() {
		if (format == null) {
			format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(maxFraction);
			format.setMinimumFractionDigits(minFraction);
			format.setMaximumIntegerDigits(maxInteger);
			format.setMinimumIntegerDigits(minInteger);
			format.setRoundingMode(RoundingMode.HALF_UP);// 默认的四舍五入
			format.setGroupingUsed(useGroup);
		}
		return format;
	}

	public int getMaxFraction() {
		return maxFraction;
	}

	public void setMaxFraction(int maxFraction) {
		this.maxFraction = maxFraction;
	}

	public int getMinFraction() {
		return minFraction;
	}

	public void setMinFraction(int minFraction) {
		this.minFraction = minFraction;
	}

	public int getMaxInteger() {
		return maxInteger;
	}

	public void setMaxInteger(int maxInteger) {
		this.maxInteger = maxInteger;
	}

	public int getMinInteger() {
		return minInteger;
	}

	public void setMinInteger(int minInteger) {
		this.minInteger = minInteger;
	}

	public boolean isUseGroup() {
		return useGroup;
	}

	public void setUseGroup(boolean useGroup) {
		this.useGroup = useGroup;
	}

	@Override
	public Object format(Object obj) {
		BigDecimal tmp = ReportModelUtils.getDecimal(obj);
		if (tmp != null) {
			return getFormat().format(tmp);
		} else {
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
		sb.append("\" maxFraction=\"");
		sb.append(this.maxFraction);
		sb.append("\" minFraction=\"");
		sb.append(this.minFraction);
		sb.append("\" maxInteger=\"");
		sb.append(this.maxInteger);
		sb.append("\" minInteger=\"");
		sb.append(this.minInteger);
		sb.append("\" useGroup=\"");
		sb.append(this.useGroup);
		sb.append("\"/>");
		return sb.toString();
	}

	@Override
	public void fromXml(Element xml) {
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

		String useGroup = xml.attributeValue("useGroup");
		if (useGroup != null && !"".equals(useGroup))
			setUseGroup(Boolean.valueOf(useGroup));
	}
}
