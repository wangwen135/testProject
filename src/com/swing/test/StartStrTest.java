package com.swing.test;

public class StartStrTest {
	public static void main(String[] args) {
		
	        String s = System.getProperty("sun.java.command");
	        System.out.println(s);
	        
	        String s1 = getCmdParam(s, "-host=");
	        System.out.println(s1);
	        String s2 = getCmdParam(s, "-port=");
	        System.out.println(s2);
	        
//	        String s3 = s1 != null ? s1 : PropertyConfig.getValue("rmi.host");
//	        int i = s2 != null ? Integer.parseInt(s2) : port;
//	        return s3 != null ? new InetSocketAddress(s3, i) : new InetSocketAddress(i);
	    }

	    public static String getCmdParam(String s, String s1)
	    {
	        String s2 = null;
	        int i = s.indexOf(s1);
	        if(i != -1)
	        {
	            s2 = s.substring(i + s1.length());
	            i = s2.indexOf(' ');
	            if(i != -1)
	                s2 = s2.substring(0, i);
	        }
	        return s2 != null ? s2.trim() : null;
	    }
}
