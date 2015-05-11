package jmx.test.hello;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Hello extends NotificationBroadcasterSupport implements HelloMBean {

	private String name = "the JMX test";
	private int size = 0;

	public Hello() {
		System.out.println("构造方法被调用");
	}

	public String sayHello(String who) {
		size++;

		String msg = "hello! " + who + " \n you number is " + size;
		System.out.println(msg);

		System.out.println("发送通知");
		sendNotification(new Notification("sayHello", this, 0, who
				+ " 调用了sayHello 方法"));

		return msg;
	}

	public int add(int x, int y) {
		System.out.println("invoke add");

		System.out.println("假设计算非常耗时。。。");

		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			System.out.println("发送通知:" + i);

			sendNotification(new Notification("add", this, i, "add 方法处理进度：" + i));
		}

		System.out.println("计算完成。。。");

		return x + y;
	}

	public void setName(String name) {
		this.name = name;
		// 属性改变

		System.out.println("name 属性改变：" + name);
		// 发送通知？？
	}

	public String getName() {
		System.out.println("invoke getName");
		return this.name;
	}

	public int getSize() {
		System.out.println("invoke getCacheSize");
		return this.size;
	}

	public synchronized void setSize(int size) {
		System.out.println("invoke setSize");

		this.size = size;

		System.out.println("size now " + this.size);
	}

}
