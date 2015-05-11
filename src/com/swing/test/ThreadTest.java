package com.swing.test;

import java.util.Date;

public class ThreadTest {

	private static Object lock = new Object();
	private static Object obj;

	public static void setO(Object o) {
		obj = o;
		synchronized (lock) {
			lock.notify();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						synchronized (lock) {

							lock.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("新的OBJ的值是：");
					System.out.println(lock);
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setO(new Date());
				}
			}
		});

		t1.start();
		t2.start();
	}

	public static void main1(String[] args) {

		Thread t = new Thread(new Runnable() {
			int i = 0;

			@Override
			public void run() {
				while (i < 3) {
					System.out.println("222222222222222");
					i++;
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
		for (int i = 0; i < 10; i++) {
			System.out.println("11111111111");
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
