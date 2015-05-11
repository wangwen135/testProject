package jmx.test.helloWorld;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class HelloWorld extends NotificationBroadcasterSupport implements
		HelloWorldMBean {

	private String greeting = "null";

	public HelloWorld() {
		this.greeting = "Hello World! I am a standard MBean";
	}

	public HelloWorld(String greeting) {
		this.greeting = greeting;
	}

	/**
	 * 这个方法用于设定这个MBean的问候语，这其中代码就演示了如何让这个MBean发送通知
	 * 
	 */

	public void setGreeting(String greeting) {
		// TODO Auto-generated method stub

		System.out.println("HelloWorldBean->setGreeting(String) is invoked...");

		this.greeting = greeting;

		// 以下这段代码显示了MBean如何发送通知
		// 首先，它创建了一个通知对象，其构造函数包括：
		// 第一个参数(type),用于唯一标示一个通知
		// 第二个参数(source),表示由哪个MBean来发出这个通知（必须是MBean实例名或者是MBean实例的ObjectName)
		// 第三个参数(sequence),表示这个通知的序列号
		// 第四个参数(timestamp),表示这个通知发出的时间戳
		// 第五个参数(message),表示这个通知包含的消息内容
		Notification notification = new Notification(
				"com.charles.helloWorld.test", this, -1,
				System.currentTimeMillis(), greeting);

		System.out.println("HelloWorldMBean->Before sending notification...");

		// 等创建好通知对象之后，让这个MBean吧这个通知广播出去，这样所有该MBean的监听者就可以接受到这个通知
		// 谁是这个MBean的监听器呢，要在对方的MBean里面用 这个mbean实例.addNotificationListener()来注册
		sendNotification(notification);

		System.out.println("HelloWorldMBean->After sending notification...");
	}

	public String getGreeting() {
		return greeting;
	}

	@Override
	public void printGreeting() {
		System.out.println(greeting);

	}
}
