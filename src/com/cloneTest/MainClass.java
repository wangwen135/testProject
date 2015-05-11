package com.cloneTest;

public class MainClass {
	public static void main(String[] args) {
		ClassA a= new ClassA();
		a.setStr1("is a");
		a.setStr2("is b");
		a.setStr3("is c");
		
		a.toStr();
		
		try {
			ClassInterface c = (ClassInterface) a.clone();
			c.toStr();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
