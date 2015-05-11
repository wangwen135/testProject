package com.booleans;

public class Test {
	public static void main(String[] args) {
		boolean a = false;
		a = a | true;
		System.out.println("a | true = "+a);
		a = a || false;
		System.out.println("a || false = "+a);
		
		System.out.println("test over");
	}
}
