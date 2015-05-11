package com.swing.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	
	public static void main(String[] args) throws ParseException {
		String lastTime = "2013-03-10 00:00:00";
		
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTime);
					
		System.out.println(lt3month(d));
	}
	
	/**
	 * <pre>
	 * 时间小于3个月
	 * </pre>
	 * 
	 * @param d
	 * @return
	 */
	public static boolean lt3month(Date d) {
		if(d== null)
			return false;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-3);
		
		System.out.println(c.getTime().toLocaleString());
		
		return c.getTime().compareTo(d) > 0;		
		
	}
	
	public static void main4(String[] args) throws Throwable {
		System.out.println(System.currentTimeMillis());
		Calendar rightNow = Calendar.getInstance();
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
		Thread.sleep(1000);
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
		System.out.println( rightNow.getTimeInMillis());
	}
	
	public static void main2(String[] args) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, -1);
		
		Date d = rightNow.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(d));
	}
	
	public static void main3(String[] args) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(Calendar.MONTH, 0);
		rightNow.set(Calendar.DAY_OF_MONTH, 1);
		rightNow.set(Calendar.HOUR_OF_DAY, 0);
		rightNow.set(Calendar.MINUTE , 0);
		rightNow.set(Calendar.SECOND, 0);
		Date d = rightNow.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(d));
		
		
		//rightNow = Calendar.getInstance();
		rightNow.set(Calendar.MONTH, 11);
		rightNow.set(Calendar.DAY_OF_MONTH, 31);
		rightNow.set(Calendar.HOUR_OF_DAY, 23);
		rightNow.set(Calendar.MINUTE , 59);
		rightNow.set(Calendar.SECOND, 59);
		d = rightNow.getTime();
		System.out.println(sdf.format(d));
	}
}
