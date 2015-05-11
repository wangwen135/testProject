package com.collection;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
	public static void main(String[] args) {
		Set<Object> set = new HashSet<Object>();
		set.add(null);
		set.add(null);
		set.add("");
		set.add("");
		System.out.println(set.size());
	}
}
