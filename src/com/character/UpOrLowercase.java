package com.character;

import java.util.Arrays;

public class UpOrLowercase {
	public static void main(String[] args) {
		String str ="12ab中,文１,２ABd";
		
		System.out.println(Arrays.toString(str.split(",")));
		
		String des="D";
		System.out.println(containsStr(str, des, false));
		
		boolean b = t("abc")|| t("aaa") || t("bcd")|| t("aaa");
	}
	
	public static boolean t(String t){
		System.out.println(t);
		return t.contains("aaa");
	}

	public static boolean containsStr(String src, String des,
			boolean caseSensitive) {
		if (caseSensitive) {
			return src.contains(des);
		}else{
			return src.toUpperCase().contains(des.toUpperCase());	
		}
	}
}
