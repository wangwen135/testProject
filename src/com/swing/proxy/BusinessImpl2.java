package com.swing.proxy;

public class BusinessImpl2 implements BusinessInterface {

	@Override
	public String processBusiness() {
		System.out.println("这是第二个实现的方法");
		return "这是第二个实现的方法";
	}

}
