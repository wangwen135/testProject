package com.swing.test;

import java.util.Arrays;

public class ArraysTest {
	public static void main(String[] args) {
		int[] i1 = new int[]{10,20};
		System.out.println(Arrays.toString(i1));
		int[] i2 = Arrays.copyOf(i1, 5);
		System.out.println(Arrays.toString(i2));
	}
}
