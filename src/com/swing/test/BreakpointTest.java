package com.swing.test;

public class BreakpointTest {
	private int a = 0;
	private String str;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public BreakpointTest() {
		a = 10;
		str = "测试字符串";
	}

	public static void main(String[] args) {
		BreakpointTest bt = new BreakpointTest();
		System.out.println(bt.getA());
		System.out.println(bt.getStr());
	}
}
