package com.dateTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		// 两个月之前的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d = new Date();

		try {
			Date d2 = sdf.parse("2014-04-30 12:23:32");
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(d2);
			rightNow.add(Calendar.MONTH, -2);
			Date d3 = rightNow.getTime();
			
			System.out.println(sdf.format(d3));

			System.out.println(d.compareTo(d3));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
