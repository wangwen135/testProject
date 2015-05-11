package com.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAdd {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("aaa");
		l.add("bbb");
		l.add("ccc");
		
		List<String> l2 = new ArrayList<String>();
		l2.add("111");
		
		l2.addAll(l);
		
		System.out.println(l2);
		
				
	}
}
