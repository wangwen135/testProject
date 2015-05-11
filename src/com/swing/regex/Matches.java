package com.swing.regex;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Matches {
	
	public static void main(String[] args) {
		//[^\u4e00-\u9fa5A-Za-z0-9.,-;()#_%|，*\\s&]
		
		Pattern p = Pattern.compile("[^\u4e00-\u9fa5A-Za-z0-9.,-;()#_%|，*\\s&]");
		Matcher m = p.matcher("   ABC\\def-:--asdf");
		System.out.println( m.replaceAll("").trim());
		
		 m = p.matcher("   EFG，。：Ｆ《》６７ def :; asdf.,-;()#_%|，*\\s");
		System.out.println( m.replaceAll("").trim());
	}
	
	public static void main2(String[] args) {
		String m = "5中文xxxx.tif";
		if (m.matches("^[^5].+(\\.tif|\\.TIF)")) {
			System.out.println("匹配");
		} else {
			System.err.println("不匹配");
		}
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		// Pattern
		// ^[1-7[_]]*$
		// ^1?2?3?4?5?6?7?$ ---1到7 不重复
		// ^[1|_]?[2|_]?[3|_]?[4|_]?[5|_]?[6|_][7|_]?$ ---1到7的数字，或者'_'

		// \\d{1,6}.\\d{4}

		// \\d{1,5}([.]{1}\\d{1,4})? -- 五位整数四位小数校验
		// \\d{1,5}(([.]{1}\\d{1,4})|[.])? ---可以带点的
		// -?\\d{1,11}(\\.{1}\\d{1,6})? --正负

		// [\u4e00-\u9fa5]* -- 校验汉字

		// [\\w[\u4e00-\u9fa5][(][)][,][.][;][ ][-][_][%][|]]* --- 除去特殊字符

		// \p{Upper} --大写字母

		// 0([.]{1}\d{0,4})?

		// 刚好多少位或者为空

		// .+(\\.rtf|\\.RTF) //后缀名为rtf

		String m = "@";
		// \uFF00-\uFFEF
		// \\p{Punct}

		// 可以输入数字+点
		// [Alnum]|
		if (m.matches("[\\p{Graph}[ ]]")) {
			System.out.println("匹配");
		} else {
			System.err.println("不匹配");
		}
	}

}
