package com.character;

import java.io.UnsupportedEncodingException;

public class Bytes {
public static void main(String[] args) {
	String s = "〇";
	try {
		byte[] b = s.getBytes("utf-8");
		System.out.println(b.length);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	
}
}
