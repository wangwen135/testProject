package com.swing.test;

import java.util.HashMap;
import java.util.Map;

public class CaseTest {
	public static void main(String[] args) {
		Object o = null;
		String s = (String) o;
		System.out.println("ok  s==null:" + (s == null));
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("aaa", "aaa1");
		map1.put("bbb", "bbb1");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("aaa", "22222222");
		map2.put("ccc", "33333333");
		map2.put("ddd","4444444444");
		
		map2.putAll(map1);
		System.out.println(map2);
	}
}
