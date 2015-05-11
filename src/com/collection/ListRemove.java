package com.collection;

import java.util.ArrayList;
import java.util.List;

public class ListRemove {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("aaa");
		l.add("1bbb");
		l.add("ccc");
		l.add("1aaa");
		l.add("bbb");
		l.add("1ccc");
		
		//java.util.ConcurrentModificationException
//		for (String s : l) {
//			if(s.startsWith("1")){
//				l.remove(s);
//			}
//		}
		
		for (int i = 0; i < l.size(); i++) {
			String s = l.get(i);
			if(s.startsWith("1")){
				System.out.println(s);
				l.remove(i);
				i--;
			}
		}
		
		
		System.out.println(l);
	}
}
