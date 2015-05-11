package com.swing.edi.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class NumberFormatTest {
	public static void main(String[] args) {
		BigDecimal decimal = new BigDecimal("12351.2234963");
		NumberFormat formate = NumberFormat.getInstance();

		System.out.println(formate.getMaximumIntegerDigits());
		System.out.println(formate.getMinimumIntegerDigits());
		
		System.out.println(formate.getRoundingMode());
		System.out.println(formate.isGroupingUsed());
		
		// 小数部分
		formate.setMaximumFractionDigits(-3);
		formate.setMinimumFractionDigits(0);

		// 整数部分
		formate.setMaximumIntegerDigits(7);
		formate.setMinimumIntegerDigits(7);

		// 舍入模式
		formate.setRoundingMode(RoundingMode.UP);

		// 分组
		formate.setGroupingUsed(true);

		double d = decimal.doubleValue();
		
		// 测试
		System.out.println(formate.format(decimal.doubleValue()));
		
		
		RoundingMode r =RoundingMode.valueOf("HALF_EVEN");
		System.out.println(r);
	}
}
