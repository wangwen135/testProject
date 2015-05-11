package com.swing.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class CompareTest {
	public static void main(String[] args) {
		Integer value1 = 0;
		Integer value2 = 0;

		System.out.println(compareValue(value1, value2));
	}

	public static boolean compareValue(Object value1, Object value2) {

		// null,String,integer,BigDecimal,Date

		if (value1 == null && value2 == null) {
			return true;
		}
		if (value1 == null) {
			value1 = "";
		}
		if (value2 == null) {
			value2 = "";
		}

		if (value2 instanceof Timestamp) {
			value2 = new Date(((Timestamp) value2).getTime());
		}
		// 如果两个比较的类型不相等则按照字符串对比

		if (value1.getClass() != value2.getClass()) {
			return value1.toString().equals(value2);
		}

		if (value1 instanceof String) {
			return value1.equals(value2);
		}

		if (value1 instanceof BigDecimal) {
			return ((BigDecimal) value1).compareTo((BigDecimal) value2) == 0;
		}

		if (value1 instanceof Integer) {
			return ((Integer) value1).compareTo((Integer) value2) ==0;
		}

		if (value1 instanceof Date) {
			return ((Date) value1).compareTo((Date) value2) == 0;
		}

		return false;
	}
}
