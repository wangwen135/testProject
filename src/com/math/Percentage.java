package com.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 百分比
 */
public class Percentage {
	public static void main(String[] args) {
		// 将list中40%的记录修改
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("B");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");

		double d = list.size() * 0.4;
		BigDecimal b = new BigDecimal(d);
		b = b.setScale(0, RoundingMode.HALF_UP);

		int changeSize = b.intValue();
		System.out.println(changeSize);

		Random r = new Random();
		for (int i =0 ;i< 10 ; i++) {
			int index = r.nextInt(10);
			System.out.println(index);
		}
		
		for (int i =0 ;i< 10 ; i++) {
			int index = r.nextInt(10);
			System.out.println(index);
		}
		
//		for (int i =0 ;i< changeSize ; i++) {
//			int index = r.nextInt(list.size());
//			System.out.println(index);
//		}
	}
}
