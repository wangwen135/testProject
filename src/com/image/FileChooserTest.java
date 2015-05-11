package com.image;

import java.math.BigDecimal;

import javax.swing.JFileChooser;

public class FileChooserTest {
public static void main(String[] args) {
	long t1 = System.currentTimeMillis();
	//JFileChooser jfc = new JFileChooser();
	String s = new String("abc");
	
	long t2 = System.currentTimeMillis();
	
	System.out.println(t2-t1);
}
}
