package com.swing.test;

import java.util.Calendar;
import java.util.Date;

public class ScriptTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		StringBuffer sbuf = new StringBuffer();
		if(hour>6 && hour<12){
			sbuf.append("早上好！");
		}else if(hour>=12 && hour<18){
			sbuf.append("下午好！");
		}else{
			sbuf.append("晚上好！");
		}
		int day = c.get(Calendar.DAY_OF_YEAR);
		sbuf.append("今年还剩");
		sbuf.append((365-day));
		sbuf.append("天");
		System.out.println(sbuf);
	}

}
