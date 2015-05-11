package com.memoryManage;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

public class MemoryPoolTest {
	public static void main(String[] args) {
		List<MemoryPoolMXBean> mpools = ManagementFactory
				.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean bean : mpools) {
			System.out.println(bean.getName());
			System.out.println(bean.getUsage());
			System.out.println(bean.getPeakUsage());
			if (bean.isUsageThresholdSupported()) {
				System.out.println("此内存池支持使用量阈值");
				System.out.println("内存使用量超过其阈值的次数："+bean.getUsageThresholdCount());
			} else {
				System.out.println("！！此内存池不支持使用量阈值！！");
			}

			System.out.println("----------  END  ----------\r\n");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
