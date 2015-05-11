package com.swing.regex;

import java.util.regex.Pattern;

public class ReplaceTest {

	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			String s = Pattern.compile(" 中  文", Pattern.LITERAL)
					.matcher("中文中\r\n文 中  文[0]中文中文").replaceAll("XX");
			System.out.println(s);
		}
		long time2 = System.currentTimeMillis();
		
		System.out.println((time2-time1));
		// System.out.println(s);
	}

	public static void main2(String[] args) {
		String str = "12300000,12134000";
		String str2 = str.replaceAll("[0][0]$", "");
		str2 = str2.replaceAll("[0][0][,]", ",");
		System.out.println(str2);
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		// Pattern.compile(regex).matcher(this).replaceAll(replacement);
		String pattern = Pattern.quote("   abc");
		System.out.println(pattern);

		String str = "   abc!@#$%";
		String str2 = str.replaceAll(pattern, "");
		System.out.println(str2);
	}

}
