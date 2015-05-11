package com.swing.test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class CloneTest implements Cloneable {

	private Date d;

	private BigDecimal bd;

	private String name;

	private int id;

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public BigDecimal getBd() {
		return bd;
	}

	public void setBd(BigDecimal bd) {
		this.bd = bd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.id);
		sb.append(" | ");
		sb.append(this.name);
		sb.append(" | ");
		sb.append(this.bd);
		sb.append(" | ");
		sb.append(this.d);
		return sb.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		CloneTest ct = (CloneTest) super.clone();
		
//		Field[] f = CloneTest.class.getDeclaredFields();
//		System.out.println("###############");
//		System.out.println(Arrays.toString(f));
//		System.out.println("###############");
		ct.setBd(new BigDecimal(ct.getBd().toPlainString()));
		ct.setD((Date) ct.getD().clone());

		return ct;
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		CloneTest t = new CloneTest();
		BigDecimal b1 = new BigDecimal("10.2");
		System.out.println("t.bd.hscod=" + b1.hashCode());
		t.setBd(b1);
		Date d = new Date();
		System.out.println("t.d.hscode=" + d.hashCode());
		t.setD(d);
		t.setId(123456);
		t.setName("abc");

		System.out.println(t.toString());
		CloneTest t2 = (CloneTest) t.clone();
		System.out.println(t2.toString());
		
		BigDecimal bigDecimal = t.getBd();
		bigDecimal = bigDecimal.divide(new BigDecimal(2.0), 1);
		t.setBd(bigDecimal);
		System.out.println("=========" + t.toString());
		System.out.println("=========" + t2.toString());
		System.out.println("t2.bd.hscod=" + t2.getBd().hashCode());

		System.out.println("t2.d.hscode=" + t2.getD().hashCode());

		System.out.println(b1 == t2.getBd() ? "相等" : "不相等");
	}

}
