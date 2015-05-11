package com.swing.Queue;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {

	public static void main(String[] args) {
		BlockingQueue q = new ArrayBlockingQueue(10);
		Producer p = new Producer(q);

		Consumer c1 = new Consumer(q, "c1");
		Consumer c2 = new Consumer(q, "c2");
		new Thread(p).start();
		new Thread(c1).start();

		new Thread(c2).start();
	}

}

class Producer implements Runnable {
	private final BlockingQueue queue;

	Producer(BlockingQueue q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				queue.put(produce());
				System.out.println("加入队列");
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	Object produce() throws InterruptedException {
		Thread.sleep(500);
		System.out.println("生产完成");
		return new Date().toLocaleString();

	}
}

class Consumer implements Runnable {
	private final BlockingQueue queue;
	private String name;

	Consumer(BlockingQueue q, String name) {
		queue = q;
		this.name = name;
	}

	public void run() {
		try {
			while (true) {
				consume(queue.take());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	void consume(Object x) {
		System.out.println(name + "开始消耗：" + x);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println(name + "消耗完成:" + x);
	}
}