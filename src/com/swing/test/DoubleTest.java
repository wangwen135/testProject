package com.swing.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DoubleTest {

	public static DecimalFormat df = new DecimalFormat("0.##");

	public static void main(String[] args) {
		long l = 2;

		double t = (double) l / 20000 * 1000;

		System.out.println(t);

		System.out.println(df.format(t));
	}

	public int a, b;

	public double c, d;

	public static void main4(String[] args) {
		BigDecimal bd1 = new BigDecimal("102.54");
		double count = 2.5;
		System.out.println(bd1.divide(BigDecimal.valueOf(count), 2,
				RoundingMode.HALF_UP));
		BigDecimal bd2 = BigDecimal.valueOf(bd1.doubleValue() / count);
		System.out.println(bd2.toString());
	}

	public static void main3(String[] args) {
		String[] mquantityArray = "1,份,0.0,0.0;1,块,0.1,0.0".split(";");
		BigDecimal allValue = new BigDecimal(0);
		for (int i = 0; i < mquantityArray.length; i++) {
			String[] onebatch = mquantityArray[i].split(",", 4);
			BigDecimal oneValue = new BigDecimal(onebatch[2]);// 如果错误抛出
			// 数量/单位中所显示的价值视为单价，然后用数量相乘后替换；“申报价值”则取替换过后的所有品名的价值和。
			oneValue = oneValue.multiply(new BigDecimal(onebatch[0]));

			allValue = allValue.add(oneValue);
			System.out.println(oneValue);
		}
		System.out.println(allValue);

	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main2(String[] args) {
		String d = "00.000000000000000";

		System.out.println(System.getenv().toString());
		System.out.println(System.getenv().get("Path"));

		Double db = Double.valueOf(d);
		System.out.println(db);

		if (db == 0) {
			System.out.println("为0");
		} else {
			System.out.println("非0");
		}

		new DoubleTest().method1();
	}

	public void method1() {

		System.out.println("this si method1");
		a++;
		b += 2;
		methodTest();
	}

	public String methodTest() {
		System.out.println("this is method2");
		System.out.println("a=" + a);
		System.out.println("b=" + b);
		int b = 0;
		if (a < 10) {
			method1();
		}
		return "";
	}

}
