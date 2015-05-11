package com.swing.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.jca.cci.connection.ConnectionSpecConnectionFactoryAdapter;

public class ListTest {
	
	
	
	public static void main(String[] args) {
		List<Integer> widthList = new ArrayList<Integer>();
		for (int i = 0; i < 21; i++) {
			widthList.add(i);
		}
		
		List<Integer> all =  new ArrayList<Integer>();
		
		
		int fromIndex = 0;
		int toIndex = widthList.size();
		int step = 7;
		for (int i = step; i < toIndex; fromIndex = i, i += step) {

			List<Integer> sublist = widthList.subList(fromIndex, i);
			System.out.println(sublist);
			all.addAll(sublist);
		}

		if (fromIndex < toIndex) {
			List<Integer> sublist = widthList.subList(fromIndex, toIndex);
			System.out.println(sublist);
			all.addAll(sublist);
		}

		System.out.println("ok");

		System.out.println(all);

	}
}
