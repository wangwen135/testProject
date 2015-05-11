package com.UUID;

import java.util.UUID;

public class TestUUID {

	public static void main(String[] args) {
		String uuid ;
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 15000000; i++) {
			uuid = UUID.randomUUID().toString();
			if(uuid.length()!=36){
				System.out.println(uuid);
			}
		}
		
		System.out.println( System.currentTimeMillis()-t1);
	}
}
