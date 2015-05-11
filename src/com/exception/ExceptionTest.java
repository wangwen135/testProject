package com.exception;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
			String[] strArrays = new String[] { "aaa", "bbb" };
			System.out.println(strArrays[0]);
			System.out.println(strArrays[3]);
		} catch (Exception e) {
			e.printStackTrace();
			RuntimeException re = new RuntimeException("重新定义异常",e);
			throw re;
		}
	}
}
