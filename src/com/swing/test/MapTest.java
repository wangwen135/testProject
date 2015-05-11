package com.swing.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("id1", "123213");
		map1.put("id2", "asdfsd");
		
		Map<String,Object> map2 = Collections.unmodifiableMap(map1);
		
		System.out.println(map2);
		
		map2.put("id3", "33333");
		
		System.out.println(map2);
		
		System.out.println(map1);
	}
	
	public static void main2(String[] args) {
		Map<String ,Integer> map = new LinkedHashMap<String, Integer>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		map.put("4", 4);
		map.put("5", 5);
		map.put("6", 6);
		
		Set<String> s = map.keySet();
		
		System.out.println(s.getClass());
		
		for (String str : s) {
			System.out.println(str);
		}
		for (String str : s) {
			
//			System.out.println(map.get(str));
		}
		
	}
	
	public static void main1(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gdesc", "棉布1米棉衣1件1米");
		map.put("quantity", "1米");
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.putAll(map);
		
		map.put("abc", "def");
		
		String tmpGdesc = (String) map.get("gdesc");
		String quantity = (String) map.get("quantity");
		if(tmpGdesc!=null && quantity!=null){
			if(tmpGdesc.endsWith(quantity)){
				String gdesc = tmpGdesc.substring(0, tmpGdesc.length() - quantity.length());
				map.put("gdesc", gdesc);
			}else{
				
			}
		}
		
		System.out.println(map);
		System.out.println(map2);
	}
}
