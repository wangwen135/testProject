package com.sf.module.EDI.util;

import java.math.BigDecimal;

public class DoubleUtil {
	
	/**
	 * 两个数相乘
	 * @param value 乘数
	 * @param multiply 被乘数
	 * @return 相乘的结果
	 */
	public static double doubleMultiply(double value, double multiply){
		String valueStr = String.valueOf(value);
		String multiplyStr = String.valueOf(multiply);
		int length1 = 0;
		if (valueStr.indexOf(".") > -1){
			length1 = valueStr.length() - valueStr.indexOf(".");
		}
		int length2 = 0;
		if (multiplyStr.indexOf(".") > -1){
			length2 = multiplyStr.length() - multiplyStr.indexOf(".");
		}
		return new BigDecimal(value).multiply(new BigDecimal(multiply)).setScale(length1 + length2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 两个数相除
	 * @param value 除数
	 * @param devide 被除数
	 * @return 相除的结果
	 */
	public static double doubleDivide(double value, double divide){
		String valueStr = String.valueOf(value);
		String multiplyStr = String.valueOf(divide);
		int length1 = 0;
		if (valueStr.indexOf(".") > -1){
			length1 = valueStr.length() - valueStr.indexOf(".");
		}
		int length2 = 0;
		if (multiplyStr.indexOf(".") > -1){
			length2 = multiplyStr.length() - multiplyStr.indexOf(".");
		}
		return new BigDecimal(value).divide(new BigDecimal(divide), length1 + length2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 使用四舍五入法保留length位小数
	 * @param value 传入的值
	 * @param length 保留小数位的长度
	 * @return
	 */
	public static double doubleFormat1(double value, int length){
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
		return bigDecimal.setScale(length, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * 使用四舍五入法保留length位小数，如果小于0.1取0.1
	 * @param value 传入的值
	 * @param length 保留小数位的长度
	 * @return
	 */
	public static double doubleFormat2(double value, int length){
		value = doubleFormat1(value, length);
		if (value < 0.1){
			return 0.1;
		}
		return value;
	}
	
	
	public static void main(String[] args) {
		System.out.println(DoubleUtil.doubleFormat1(0.85, 1));
		System.out.println(DoubleUtil.doubleFormat2(DoubleUtil.doubleMultiply(1, 0.85),1));
	}
}
