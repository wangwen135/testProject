package jmx.test.hello;

import javax.management.Notification;
import javax.management.NotificationListener;

public class ClientListener implements NotificationListener {
	public void handleNotification(Notification notification, Object handback) {
		System.out.println("接收到JMX通知：" + notification.getMessage());
		System.out.println("\nReceived notification: " + notification);
	}
}
