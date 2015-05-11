package com.swing.regex;

import java.util.regex.Pattern;

public class FilterSonderzeichen {
	public static String matcherStr = "[\\w[\u4e00-\u9fa5][(][)][,][.][;][ ][-][_][%][|]]";
	public static Pattern pattern = Pattern.compile(matcherStr);

	static String str = "abc#$def中文|}{_(^%>><<测试";
	
	public static void main(String[] args) {
		System.out.println(method2(str));
	}
	
	public static void main2(String[] args) {
		if(str.length()>=1000){
			System.out.println(str.substring(0, 1000));
		}else{
			System.out.println("no");
		}
	}
	
	
	public static void main1(String[] args) {

		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			String ret = method1(str);
			boolean b1 = Pattern.matches(matcherStr + "*", ret);
			if (b1)
				System.out.println("成功!");
		}

		long t2 = System.currentTimeMillis();
		System.out.println((t2 - t1));

		System.out.println("##########################################");

		long t3 = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			String ret2 = method2(str);
			boolean b2 = Pattern.matches(matcherStr + "*", ret2);
			if (b2)
				System.out.println("成功!");
		}
		long t4 = System.currentTimeMillis();
		System.out.println((t4 - t3));

	}

	// 比较匹配速度
	private static String method1(String src) {
		StringBuffer sbr = new StringBuffer();
		char[] chars = src.toCharArray();
		for (char c : chars) {
			if (Pattern.matches(matcherStr, new String(new char[] { c }))) {
				sbr.append(c);
			} else {
				// System.out.println(c);
			}
		}
		return sbr.toString();
	}

	private static String method2(String src) {
		StringBuffer sbr = new StringBuffer();
		char[] chars = src.toCharArray();
		for (char c : chars) {
			if (pattern.matcher(new String(new char[] { c })).matches()) {
				sbr.append(c);
			} else {
				// System.out.println(c);
			}
		}

		return sbr.toString();
	}

}
