package com.swing.test;

import java.text.MessageFormat;

public class MessageFormatTest {
	public static void main(String[] args) {
		System.out.println(MessageFormat.format("({0})导出完成", new Object[0]));
		meth("abc","ef");
	}
	public static void meth(String s1,String ... s2){
		System.out.println(s2);
		System.out.println("调用的是方法一");
	}
	
	public static void meth2(String s1,String s2){
		System.out.println("调用的是方法二");
	}
	
	
}
