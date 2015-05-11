package com.swing.format;

import java.text.DecimalFormat;

public class DecimalFormatTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("##,###.####");
		//DecimalFormat df = new DecimalFormat("##,##0.0000");
		
		System.out.println(df.format(-123456789.121223));
		
		System.out.println(Integer.MAX_VALUE);
		
		DecimalFormat df2 = new DecimalFormat("#####0.#####");
		
		System.out.println(df2.format(123456.0));
		
	}

}
