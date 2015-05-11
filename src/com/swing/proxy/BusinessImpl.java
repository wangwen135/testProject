package com.swing.proxy;

public class BusinessImpl implements BusinessInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String processBusiness() {
		System.out.println("Processing business logic ");
		return "第一个实现";
	}

}
