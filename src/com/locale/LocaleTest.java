package com.locale;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleTest {

	public static void main(String[] args) {
		System.out.println(getEnDate());
	}
	
    public static String getEnDate(){
    	SimpleDateFormat df = new SimpleDateFormat(" MMMM dd, yyyy", Locale.UK); 
    	return df.format(new Date());
    }
}
