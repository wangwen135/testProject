package com.cloneTest;

public class ClassA extends AbstractClass {
 
	private String str1;
	
	private String str2;
	
	private String str3;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String tt(String str) {

		return str + str + str;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	@Override
	public void toStr() {
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		
	}
	
	

}
