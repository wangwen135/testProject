package com.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

	public static void main(String[] args) {
		AtomicLong rxBytes = new AtomicLong(0L);
		for (int i = 0; i < 10; i++) {
			rxBytes.addAndGet(100+i);
			System.out.println(rxBytes);
		}
		

	}
}
