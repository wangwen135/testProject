package com.swing.edi.reportModel.area.cell.part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.swing.edi.reportModel.utils.ReportModelUtils;

public final class BasePart implements IPart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6080859566759609775L;

	public static final String TYPE = "BasePart";

	private PartEnum pe;
	private String symbol;
	private String viewStr;

	private BasePart(PartEnum part) {
		this.pe = part;
		switch (part) {
		case add:
			// this.symbol = "＋";
			this.symbol = "+";
			this.viewStr = "相加";
			break;
		case subtract:
			this.symbol = "－";
			this.viewStr = "相减";
			break;
		case multiply:
			this.symbol = "×";
			this.viewStr = "相乘";
			break;
		case divide:
			this.symbol = "÷";
			this.viewStr = "相除";
			break;
		case remainder:
			this.symbol = "%";
			this.viewStr = "取余";
			break;
		case none:
			this.symbol = "∞";
			this.viewStr = "空";
			break;
		case CRLF:
			this.symbol = "┘";
			this.viewStr = "换行";
			break;
		default:
		}
	}

	public static Map<PartEnum, IPart> map = new HashMap<PartEnum, IPart>();

	// 必须枚举中定义的才能用
	public static IPart getPart(PartEnum part) {
		if (map.containsKey(part)) {
			return map.get(part);
		} else {
			IPart p = new BasePart(part);
			map.put(part, p);
			return p;
		}

	}

	@Override
	public String getType() {
		return BasePart.TYPE;
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public String toXml() {
		StringBuilder sb = new StringBuilder("<part type=\"");
		sb.append(BasePart.TYPE);
		sb.append("\" name=\"");
		sb.append(pe.name());
		sb.append("\" />");
		return sb.toString();
	}

	// @Override
	// public void fromXml() {
	//
	// }

	@Override
	public String getViewStr() {
		return this.viewStr;
	}

	@Override
	public Object operation(Object o1, Object o2) {
		// 如果其中一个为null则返回另外一个
		if (o1 == null)
			return o2;
		if (o2 == null)
			return 01;

		switch (pe) {
		case none:// 空
			return o1.toString() + o2.toString();
		case CRLF:// 换行
			return o1.toString() + "\r\n" + o2.toString();
		}

		// 如果其中一个不为数字，则返回两者的字符串拼接
		BigDecimal b1 = ReportModelUtils.getDecimal(o1);
		BigDecimal b2 = ReportModelUtils.getDecimal(o2);
		if (b1 == null || b2 == null) {
			return o1.toString() + o2.toString();
		}

		try {
			// 异常处理
			switch (pe) {
			case add:// 相加
				return b1.add(b2);
			case subtract:// 相减
				return b1.subtract(b2);
			case multiply:// 相乘
				return b1.multiply(b2);
			case divide:// 相除
				return b1.divide(b2, 10, RoundingMode.HALF_UP);
			case remainder:// 取模
				return b1.remainder(b2);
			default:
				return o1.toString() + o2.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 如果运算出现异常则返回null
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("  symbol=");
		sb.append(this.symbol);
		sb.append("  viewStr=");
		sb.append(this.viewStr);
		return sb.toString();
	}

	@Override
	public PartEnum getEnum() {
		return pe;
	}
}
