package com.character;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubString {
	public static void test2() {
		// System.out.println(substringBeforeDegit("as1df12a"));

		List<String> list = lineFinder("中文字测试1234567890abcdefghi", 5, 7);
		for (String string : list) {
			System.out.println(string);
		}
	}

	public static Pattern p = Pattern.compile("[\\p{Graph}[ ]]");// 数字，字母，标点符号，空格

	public static void main(String[] args) {
		System.out.println(round(1.1f));
	}

	public static float round(float src) {
		float increment = 0.5f;
		float value = 0.5f;
		while (value < src) {
			value += increment;
		}
		return value;
	}

	public static String replaceBracket(String src, String desc) {
		// src.replaceAll(regex, replacement)
		return null;
	}

	// lineLimit >1
	// lineLimit <=0 表示无限制
	/**
	 * <pre>
	 * 分行器
	 * </pre>
	 * 
	 * @param src
	 *            要分行的字符串
	 * @param lineLength
	 *            行长度（中文等占两个长度）
	 * @param lineLimit
	 *            行数限制，如果限制小于1表示无限制
	 * @return
	 */
	private static List<String> lineFinder(String src, int lineLength,
			int lineLimit) {
		if (src == null || "".equals(src))
			return null;

		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		int length = 0;
		for (int i = 0; i < src.length(); i++) {
			char c = src.charAt(i);
			sb.append(c);
			Matcher m = p.matcher(String.valueOf(c));
			if (m.matches()) {
				length++;
			} else {
				length += 2;
			}
			if (length >= lineLength) {
				list.add(sb.toString());
				if (lineLimit > 0 && list.size() >= (lineLimit - 1)) {
					if (i < (src.length() - 1)) {
						list.add(src.substring(i + 1));
					}
					return list;
				}
				sb = new StringBuffer();
				length = 0;
			}
		}
		list.add(sb.toString());

		return list;
	}

	/**
	 * <pre>
	 * 截取数字之前的字符串
	 * </pre>
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果没有数字，则返回str；否则返回数字之前的字符串
	 */
	public static String substringBeforeDegit(String str) {
		int index = -1;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				index = i;
				break;
			}
		}
		if (index > 0) {
			return str.substring(0, index);
		}
		return str;
	}
}
