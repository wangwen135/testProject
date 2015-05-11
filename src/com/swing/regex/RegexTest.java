package com.swing.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		Pattern letterPattern = Pattern.compile("[a-zA-Z-]+");
		Matcher matcher = letterPattern.matcher("asdfsf asdf asdf - asdf-as df-as df");
		
		System.out.println(matcher.replaceAll("<$0>").replaceAll("[\\s]+", ""));
	}
	
	public static void main4(String[] args) {
		Pattern p = Pattern.compile("^(\\d+(\\.\\d+)?){1}(－\\d+(\\.\\d+)?)?$");
		//Pattern p = Pattern.compile("^\\d+(\\.\\d+)?(aa)?$");
		
		Matcher matcher= p.matcher("111－1222");
		
		System.out.println(matcher.matches());
		
	}
	
	
	public static void main3(String[] args) {
		String str = "16米m16千克《》，。1231！@#@#￥";

		Pattern p = Pattern.compile("[^\u4e00-\u9fa5A-Za-z0-9.,\\-;()#_%|，*\\s&]");
		
		Matcher m = p.matcher(str);
		
		System.out.println(m.replaceAll(""));
	}
	
	public static void main2(String[] args) {
		String quantity = "16米m16千克";

		Pattern p = Pattern.compile("\\d+\\D+");
		Matcher m = p.matcher(quantity);
		
		
		while (m.find()) {			
			
			System.out.println(m.group());
		}
	}
	
	public static String filterString(Pattern pattern, Object str) {
		if (str != null && !str.equals("")) {
			Matcher m = pattern.matcher(str.toString());
			// 剔除首尾空格
			return m.replaceAll("").trim();
		} else {
			return "";
		}
	}
}
