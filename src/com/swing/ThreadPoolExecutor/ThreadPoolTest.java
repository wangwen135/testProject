package com.swing.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest extends Thread {
	int poolSize = 3;
	int QueueSize = 5;
	private long sleepTime;
	ThreadPoolExecutor threadPool;

	int taskCount = 0;

	public ThreadPoolTest() {
		sleepTime = 1000l;

		threadPool = new ThreadPoolExecutor(poolSize, poolSize, 60L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QueueSize),
				new BlockRejectedHandler());

		//
		// new ThreadPoolExecutor.DiscardOldestPolicy()
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@@@ 添加一个任务【" + (++taskCount) + "】到池中 。。。。");

			threadPool.execute(getProcess());

			System.out.println("@@@  活动线程树：" + threadPool.getActiveCount());
			System.out.println("@@@  池中队列长度：" + threadPool.getQueue().size());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}

	}

	public ProcessThread getProcess() {
		return new ProcessThread("task:" + taskCount);
	}

	public static void main(String[] args) {
		ThreadPoolTest tt = new ThreadPoolTest();
		tt.start();
	}
}

class ProcessThread implements Runnable {

	String name;

	public ProcessThread(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		String tname = Thread.currentThread().getName();
		System.out
				.println("##############  开始处理任务【 " + name + "】#############");
		System.out.println("线程的名字是：" + tname);
		System.out.println("开始处理约15秒....");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("##############  任务【" + name + "】处理完成 ###########");
	}

}

class RejectedHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		ProcessThread p = (ProcessThread) r;
		System.err.println("线程被拒绝了：" + p.getName());
	}
}

class BlockRejectedHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		if (!executor.isShutdown()) {
			System.out.println("线程池满，等待...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.execute(r);
		}
	}
}
