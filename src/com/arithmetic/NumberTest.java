package com.arithmetic;

public class NumberTest {

	public static void main(String[] args) {
		// String str = "111.12aaa";
		// String[] strs = str.split("\\d+([.]{1}\\d+)?", 2);
		// System.out.println(strs[0]);
		// System.out.println(strs[1]);
		System.out.println(substringDigit("1233.123123.ASDFQsdf"));

		System.out.println(substringAfterDigit("1233.123123.ASDFQsdf"));

	}

	public static double substringDigit(String str) {
		if (str == null || "".equals(str)) {
			return 0D;
		}

		String[] strs = str.split("\\d+([.]{1}\\d+)?", 2);

		String digit = str.replace(strs[1], "");
		System.out.println("数字：" + digit);

		if (digit == null || "".equals(digit)) {
			return 0;
		} else {
			return Double.valueOf(digit);
		}
	}

	public static String substringAfterDigit(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		String[] strs = str.split("\\d+([.]{1}\\d+)?", 2);
		return strs[1];
	}

}
