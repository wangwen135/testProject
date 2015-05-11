package com.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BigDecimalTest {

	public static void main(String[] args) {

		BigDecimal p = new BigDecimal("6.2322");
		
		System.out.println(p);
		
		System.out.println(new BigDecimal("6.2322").setScale(2,BigDecimal.ROUND_UP));
	}
	
	public static void main15(String[] args) {
		BigDecimal minPrice = new BigDecimal("0.1"), maxPrice = new BigDecimal("0.10"),_price;
		
		//获取其最大的小数位
		int scale = Math.max(minPrice.scale(),maxPrice.scale());
		
		Random r = new Random();
		Double d = r.nextDouble();
		_price = maxPrice.subtract(minPrice).multiply(new BigDecimal(d)).setScale(scale,RoundingMode.HALF_UP).add(minPrice);
		
		System.out.println(_price);
		System.out.println(_price.scale());
		_price = _price.stripTrailingZeros();
		System.out.println(_price);
		System.out.println(_price.scale());
		
	}
	
	public static void main14(String[] args) {
		//申报价值*6.056*关税税率/100+申报价值*6.056*增值 税税率/100+申报价值*6.056*消费税税率/100+
		BigDecimal cusValue = new BigDecimal("100");
		
		BigDecimal tmp = cusValue.multiply(new BigDecimal("6.056")).multiply(new BigDecimal("关税税率"));
		
	}
	
	public static void main13(String[] args) {
		BigDecimal p = new BigDecimal("6.");
		System.out.println(p);
		String tvalue ="222";
		
		BigDecimal BigValue =p.multiply(new BigDecimal(tvalue)).setScale(2, BigDecimal.ROUND_HALF_UP);

	}
	
	public static void main12(String[] args) {
		BigDecimal aweight = new BigDecimal("5");
		
		int count  = 5;
				
		//判断
		
		calcWeight(aweight, count);
	}

	private static List<BigDecimal> calcWeight(BigDecimal aweight, int count) {
		BigDecimal newWeight = BigDecimal.ZERO;
		List<BigDecimal> listw = new ArrayList<BigDecimal>();
		
		BigDecimal minWeight = new BigDecimal("0.01");
		
		BigDecimal avg = aweight.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
		if(minWeight.compareTo(avg)>0){
			for (int i = 0; i < count; i++) {
				listw.add(minWeight);
				newWeight = newWeight.add(minWeight);	
			}
			
			listw.add(newWeight);
		}else{
			for (int i = 0; i < count-1; i++) {
				listw.add(avg);
				newWeight = newWeight.add(avg);
			}
			//计算最后一个品名
			BigDecimal last = aweight.subtract(newWeight);
			if(minWeight.compareTo(last)>0){
				last = minWeight;
			}
			listw.add(last);
			newWeight = newWeight.add(last);
			
			listw.add(newWeight);
		}
		
		System.out.println(avg);
		System.out.println(newWeight);
		System.out.println(listw);
		
		return listw;
	}
	
	public static void main4(String[] args) {
		String no1Quantity ="2,4,6,8,9,7";
		String[] arrquantity = no1Quantity.split(",");
		int tmpindex = getRandom(0, arrquantity.length - 1);
		no1Quantity = arrquantity[tmpindex];
		System.out.println(no1Quantity);
		//实际重量
		BigDecimal aweight = new BigDecimal("2.2");
		if(aweight!=null){
			BigDecimal b = new BigDecimal(no1Quantity);
			b = b.multiply(aweight).setScale(0, RoundingMode.HALF_UP);
			System.out.println(b);
		}
	}
	private static int getRandom(int min, int max) {
		Random random = new Random();
		int ran = random.nextInt(max - min);
		return ran + min;
	}
	
	public static void main1(String[] args) {
		BigDecimal b1 = new BigDecimal("1222.1650000");
		System.out.println(b1);
		DecimalFormat df = new DecimalFormat("####.####");
		System.out.println(df.format(b1));
	}
	
//	public static void main(String[] args) {
//		BigDecimal b1 = new BigDecimal("100");
//		BigDecimal b2 = new BigDecimal("100");
//
//		System.out.println(b2.multiply(b1).setScale(6, RoundingMode.HALF_UP));
//
//		System.out.println(b2.divide(b1, 6, RoundingMode.HALF_UP));
//		
//		
//	}

	public static void main323(String[] args) {
		BigDecimal b1 = new BigDecimal(15242.151);
		BigDecimal b2 = new BigDecimal(1.236569545);

		BigDecimal newValue = b1.multiply(b2).setScale(5, RoundingMode.HALF_UP);

		System.out.println(newValue);

	}

	public static void main6(String[] args) {
		BigDecimal b1 = new BigDecimal("0.10000");
		b1 = b1.stripTrailingZeros();
		System.out.println(b1.toPlainString());
	}

	public static void main223(String[] args) {
		BigDecimal b1 = new BigDecimal("0.532000");
		b1 = b1.setScale(0, RoundingMode.HALF_UP);
		System.out.println(b1);
	}

	/**
	 * <pre>
	 * 精度计算
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main3() {
		BigDecimal b1 = new BigDecimal(13.3);
		BigDecimal b2 = new BigDecimal(3);

		// MathContext mc = new MathContext(6, RoundingMode.HALF_UP);
		BigDecimal b3 = b1.divide(b2);
		System.out.println(b3);
		System.out.println(b3.setScale(6, RoundingMode.HALF_UP));

		BigDecimal b4 = b1.multiply(b2);
		System.out.println(b4);
		System.out.println(b4.setScale(4, RoundingMode.HALF_UP));
	}

	public static void main2() {
		BigDecimal b = new BigDecimal("0.000");

		b = b.stripTrailingZeros();
		System.out.println(b.toPlainString());
	}

	public static void main1() {
		BigDecimal b = new BigDecimal("1.1201000");
		BigDecimal c = b.stripTrailingZeros();
		System.out.println(b.toString());
		System.out.println(b.doubleValue());
		System.out.println(b.toEngineeringString());
		System.out.println(b.toPlainString());
		System.out.println(c.toString());

	}

	public static BigDecimal getDecimal(Object o) {
		if (o == null)
			return null;
		try {
			if (o instanceof BigDecimal)
				return (BigDecimal) o;
			if (o instanceof BigInteger)
				return new BigDecimal((BigInteger) o);
			if (o instanceof char[])
				return new BigDecimal((char[]) o);
			if (o instanceof Short)
				return new BigDecimal((Short) o);
			if (o instanceof Integer)
				return new BigDecimal((Integer) o);
			if (o instanceof Float)
				return new BigDecimal(Float.toString((Float) o));
			if (o instanceof Double)
				return new BigDecimal(Double.toString((Double) o));
			// 剩余的都做string处理
			return new BigDecimal(o.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
}
