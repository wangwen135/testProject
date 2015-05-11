package com.concurrent;

import java.util.ArrayList;
import java.util.List;

public class Synchronise extends Thread {

	// 弄一个定长的队列，保存每个节点的时间
	private int listMaxSize = 10;
	// 保存结果
	private List<Integer> list = new ArrayList<Integer>();

	private void add(Integer coord) {
		if (list.size() >= listMaxSize) {
			// 应该会有并发的问题
			synchronized (this) {
				list.remove(0);
			}

		}

		list.add(coord);
	}

	public List<Integer> getList() {
		// 复制一个新的
		// 复制当前时间点上的长度--不考虑超不多时间点进来的数据
		List<Integer> rtList = new ArrayList<Integer>();
		synchronized (this) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				rtList.add(list.get(i));
			}
		}

		return list;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			add(i++);
		}
	}

	public static void main(String[] args) {
		Synchronise sy = new Synchronise();
		sy.start();
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<Integer> list = sy.getList();
			System.out.println("#######---------");
			for (Integer i : list) {
				System.out.print(i);
			}
		}
	}
}
