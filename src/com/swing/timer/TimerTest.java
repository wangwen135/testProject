package com.swing.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DATE, 3);
		//rightNow.roll(Calendar.DATE, 3);
		System.out.println(rightNow.after(new Date()));
		System.out.println(rightNow.before(new Date()));

		System.out.println("YEAR:" + rightNow.get(Calendar.YEAR));
		System.out.println("MONTH:" + rightNow.get(Calendar.MONTH));
		System.out.println("DATE:" + rightNow.get(Calendar.DATE));
		System.out.println("AM_PM:" + rightNow.get(Calendar.AM_PM));
		System.out.println(rightNow.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.CHINA));
		System.out.println("HOUR:" + rightNow.get(Calendar.HOUR));
		System.out.println("HOUR_OF_DAY:" + rightNow.get(Calendar.HOUR_OF_DAY));
		System.out.println("----------------------");
		System.out.println("分钟：" + rightNow.get(Calendar.MINUTE));
		System.out.println("秒钟：" + rightNow.get(Calendar.SECOND));
		System.out.println("毫秒：" + rightNow.get(Calendar.MILLISECOND));
		System.out.println("----------------------");

	
		System.out.println("WEEK_OF_MONTH："+rightNow.get(Calendar.WEEK_OF_MONTH));
		System.out.println("WEEK_OF_YEAR："+rightNow.get(Calendar.WEEK_OF_YEAR));

		System.out.println("--------------------------");
		System.out.println("DAY_OF_YEAR："+rightNow.get(Calendar.DAY_OF_YEAR));
		System.out.println("DAY_OF_MONTH："+rightNow.get(Calendar.DAY_OF_MONTH));
		System.out.println("DAY_OF_WEEK："+rightNow.get(Calendar.DAY_OF_WEEK));
		System.out.println("DAY_OF_WEEK_IN_MONTH："+rightNow.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println("--------------------------");
		System.out.println(rightNow.get(Calendar.ZONE_OFFSET));

		System.out.println(rightNow.getTime());

		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println(".......");

			}
		}, 10000);

		t.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("system.exit");
				System.exit(0);
			}
		}, 20000);
	}

}
