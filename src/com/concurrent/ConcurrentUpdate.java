package com.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentUpdate {
	// 测试一下并发更新

	// 用map 保存一下，可以一下更改全部的引用

	Map<String, AtomicLong> map = new HashMap<String, AtomicLong>();

	private void init() {
		map.put("count", new AtomicLong(0));
		map.put("time", new AtomicLong(0));
		map.put("mxTime", new AtomicLong(0));
	}

	/**
	 * <pre>
	 * 添加耗时
	 * </pre>
	 * 
	 * @param t
	 */
	public void addConsume(long t) {
		AtomicLong count = map.get("count");
		AtomicLong time = map.get("time");

		AtomicLong mxTime = map.get("mxTime");

		// 以原子方式将当前值加 1
		count.incrementAndGet();

		// 以原子方式将给定值添加到当前值
		time.addAndGet(t);

		// 判断要添加的t 是否比mxTime大
		setMax(mxTime, t);
	}

	/**
	 * <pre>
	 * 设置最大耗时
	 * </pre>
	 * 
	 * @param mxTime
	 * @param t
	 * @return
	 */
	public long setMax(AtomicLong mxTime, long t) {
		for (;;) {
			long current = mxTime.get();

			if (current >= t) {
				return current;
			} else {
				if (mxTime.compareAndSet(current, t)) {
					return t;
				}
			}
		}
	}

	public static void main(String[] args) {
		ConcurrentUpdate cupdate = new ConcurrentUpdate();
		cupdate.init();

		// 开启10个线程
		List<updateThread> list = new ArrayList<updateThread>();
		for (int i = 0; i < 100; i++) {
			list.add(new updateThread(cupdate));
		}

		for (updateThread updateThread : list) {
			updateThread.start();
		}

		// 定时来获取结果
		// 并且覆盖原来的值

		boolean isRun = true;
		long allCount = 0;
		while (isRun) {
			isRun = false;
			for (updateThread updateThread : list) {
				if (updateThread.isAlive()) {
					isRun = true;
					break;
				}

			}

			Map<String, AtomicLong> mapOld = cupdate.map;
			Map<String, AtomicLong> mapNew = new HashMap<String, AtomicLong>();
			mapNew.put("count", new AtomicLong(0));
			mapNew.put("time", new AtomicLong(0));
			mapNew.put("mxTime", new AtomicLong(0));

			cupdate.map = mapNew;// 替换掉

			// 休眠一下，让其充分运行完成。。。。。猜的
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			AtomicLong count = mapOld.get("count");
			AtomicLong time = mapOld.get("time");
			AtomicLong mxTime = mapOld.get("mxTime");
			allCount += count.get();
			System.out.println("count = " + count.get());
			System.out.println("time = " + time.get());
			System.out.println("maxTime = " + mxTime.get());
			System.out.println("  --   --  --  --  --  -- 总次数： " + allCount);

		}
	}

}

class updateThread extends Thread {
	ConcurrentUpdate c;

	public updateThread(ConcurrentUpdate cc) {
		c = cc;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			Random r = new Random();

			int rint = r.nextInt(5000);
			try {
				sleep(rint/50);// 休眠
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.addConsume(rint);
		}

	}
}
