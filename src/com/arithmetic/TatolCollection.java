package com.arithmetic;

public class TatolCollection {

	/**
	 * <pre>
	 * 全部集合
	 * 采用数字循环的方式，按不同的进制计算
	 * 如三个集合 则为  123 ——>  321  采用三进制
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		int start = Integer.parseInt("012", 3);
		int end = Integer.parseInt("210", 3);
		System.out.println(start);
		System.out.println(end);
		System.out.println(end-start);
		System.out.println("-----------------------------");
		for (int i = start; i <= end; i++) {
			System.out.println(Integer.toString(i, 3));
		}
	}
	
	/**
	 * next 取下一个，记录起始位，然后下一个，然后进位，过程中判断条件
	 */

}
