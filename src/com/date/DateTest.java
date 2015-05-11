package com.date;

import java.text.DateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		String s = DateFormat.getDateTimeInstance().format(new Date());
		System.out.println(s);
	}
}
