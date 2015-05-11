package com.locale;

public class SystemEncod {
	public static void main(String[] args) {
		String encoding = System.getProperty("file.encoding");
		System.out.println("Default System Encoding:" + encoding);
	}
}
