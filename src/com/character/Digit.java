package com.character;

public class Digit {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int a = '中';
		//
		System.out.println(a);
		// Unicode 字符范围 == \\u+(16进制的值)
		System.out.println(Integer.toHexString(a));
		
		System.out.println();
		System.out.println("ISO-LATIN-1 数字");
		// 数字
		char ca0 = '\u0030';
		System.out.print(ca0);
		
		System.out.println();
		System.out.println("梵文数字");
		// 梵文数字
		char cf0 = '\u0966';
		System.out.print(cf0);
		char cf1 = '\u0967';
		System.out.print(cf1);
		char cf2 = '\u0968';
		System.out.print(cf2);
		char cf3 = '\u0969';
		System.out.print(cf3);
		
		System.out.println();
		System.out.println("全形数字");
		// 全形数字
		char cq0 = '\uFF10';
		System.out.print(cq0);
		char cq1 = '\uFF11';
		System.out.print(cq1);
		char cq2 = '\uFF12';
		System.out.print(cq2);

	}
}
