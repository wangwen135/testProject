package com.swing.test;

public class RunTimeExecptionTest {

	public RunTimeExecptionTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EnumTest a = EnumTest.a;
		EnumTest b = EnumTest.b;

		if (a == EnumTest.b) {
			System.out.println("....");
		}
		if (b == EnumTest.b) {
			System.out.println(b);
		}
	}

	/**
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param a
	 * @param b
	 */
	public static void method2(int a, int b) throws IllegalArgumentException {//也可以不抛出
		if (a == 0) {
			throw new IllegalArgumentException(
					"this is IllgalArgumentException");
		}
		if (Integer.valueOf(b) == null) {
			throw new NullPointerException("this is NullPointException");
		}

	}
}
