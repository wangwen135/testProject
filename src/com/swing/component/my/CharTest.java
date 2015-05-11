package com.swing.component.my;

public class CharTest {
	public static void main(String[] args) {
		// 数字不可能太大
		// column 从0开始
		int column = 877;

		if (column < 26) {// 小于Z
			char c = (char) (65 + column);
			System.out.println(c);
		} else {
			// 目标
			StringBuffer sb = new StringBuffer();

			int a = column / 26;
			int b = column % 26;

			System.out.println(a);
			System.out.println(b);

			char prefix = (char) (64 + a);
			System.out.println(prefix);
			sb.append(prefix);

			char c = (char) (65 + b);
			System.out.println(c);
			
			sb.append(c);
			System.out.println(sb.toString());
		}
	}
}
