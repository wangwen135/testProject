package com.swing.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatTest {

	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("d-MMM-yy",Locale.US);
		System.out.println( sf.format(new Date()));
	}
	
	//两万票可以节约1秒钟
	//156
	//1212
	//parse..
	//1901
	//1636
	//1604
	//421
	public static void main2(String[] args) {
		long time1 = System.currentTimeMillis();
		Date d = new Date();
		String strD ="2012-07-03";
		SimpleDateFormat yyyy_MM_dd_HHmmss_simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		
		for (int i = 0; i < 200000; i++) {
						
			//yyyy_MM_dd_HHmmss_simpleDateFormat.format(d);
			try {
				yyyy_MM_dd_HHmmss_simpleDateFormat.parse(strD);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		long time2 = System.currentTimeMillis();

		System.out.println((time2 - time1));

	}

}
