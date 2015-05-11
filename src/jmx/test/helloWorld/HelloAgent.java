package jmx.test.helloWorld;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * @author charles.wang (Senior Software Engineer) mailto:
 *         charles_wang888@126.com
 * @created 2011-12-31 6:46:20
 * 
 *          Description: 这个JMXAgent会创建MBeanServer，来作为MBean的容器，
 *          并且它实现了NotificationListener接口从而可以监听到来自MBean的消息
 * 
 */
public class HelloAgent implements NotificationListener {

	// MBeanServer是HelloAgent创建并且属于HelloAgent的
	private MBeanServer mbs = null;

	/**
	 * HelloAgent做了以下事情：<br>
	 * （1）创建MBeanServer作为MBean的容器 <br>
	 * （2）它创建了一个HTMLAdaptor来处理HTML客户端的连接（说白了就是让我们的Web Browser能看这些Bean信息）
	 * （回想我们有两种方式，一种是adaptor,一种是connector，这里是adaptor，所以只存在在 Agent中）
	 * （3）因为我们有一个MBean叫HelloWorld，所以我们创建这个Mbean的实例，并且注册到MBeanServer中
	 */
	public HelloAgent() {

		// 用MbeanServerFactory来创建一个MBeanServer,这个参数是Agent的domain名称，
		// 这个domain名称可以用来标识一组MBean,
		// 创建完这个MBeanserver之后，它就可以用来注册，存储，查询和操作MBean
		mbs = MBeanServerFactory.createMBeanServer("HelloAgent");

		// 现在我们有了一个Agent，那么如何访问它呢，我们需要一个管理应用程序(Management Application)
		// 所以我们我们创建一个adaptor,从而可以通过html方式（也就是web浏览器)来访问Agent
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();

		// 现在，我们到了instrumental layer,既然我们已经定义了MBean的接口和实现类，所以我们这里将MBean实例化
		HelloWorld hw = new HelloWorld();

		// 光注册MBean还不够，我们必须还要区分多个MBean,这时候我们就需要ObjectName帮忙了
		// ObjectName提供了MBean的命名系统
		// 每一个ObjectName有两部分组成，一个是domain name,一个是kev-value对
		// domainName不一定要和MBeanServer的domain name一致
		// key-value对则是用来唯一标识MBean以及提供MBean的信息
		ObjectName adapterName = null;
		ObjectName helloWorldName = null;

		// 所以，我们这里分别为adaptor和HelloWorld MBean注册ObjectName
		try {

			// 我们为adapter来分配一个ObjectName
			// 新建的adapterName有它的domain name
			// 然后key-value对之间用逗号分开，这些属性不一定要是MBean的真实属性，但是必须是唯一，从而与同MBeanServer的其他MBean区分
			adapterName = new ObjectName(
					"HelloAgent:name=htmladapter,port=9092");
			// 因为这是个adaptor，所以必须绑定端口从而对外提供服务（这里是HTML访问服务)
			adapter.setPort(9092);
			// 最终吧这种adaptor类型的MBean实例关联到ObjectName，并且注册到MBeanServer上
			mbs.registerMBean(adapter, adapterName);

			// 为了让管理应用程序通过指定的端口可以和这个adaptor交互，必须将这adpator启动起来
			// 这样之后，这个adaptor就可以接受客户端的调用了
			adapter.start();

			// 同样，创建helloWorldMbean的ObjectName，并且绑定到helloWorldMbean实例上并且注册到MBeanServer
			// MBeanServer中的所有MBean的ObjectName必须是唯一的
			helloWorldName = new ObjectName("HelloAgent:name=helloWorld1");
			mbs.registerMBean(hw, helloWorldName);

			// 因为我们在先前HelloWorld MBean的类定义中已经让其实现了NotificationBroadcasterSupport
			// 接口，也就是说那个MBean已经可以发送消息
			// 所以，我们这里为了让这个HelloAgent能监听HelloWorld
			// MBean的定义，就必须让HelloWorldMBean其添加自身为其监听器
			// 注册HelloAgent成为helloWorldBean的监听器，从而它可以监听helloWorldBean的事件
			hw.addNotificationListener(this, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 定义了一个Main方法从而可以让这个Agent以standalone的形式启动
	 */
	public static void main(String[] args) {
		System.out.println("HelloAgent is running ");
		HelloAgent agent = new HelloAgent();
	}

	/**
	 * 因为我们这个HelloAgent已经实现了NotificationListener接口，所以必须实现接口中定义的方法
	 * handleNotification 这个方法定义了当通知到的这个监听器时候，所采取的动作
	 */
	@Override
	public void handleNotification(Notification notification, Object handback) {
		// TODO Auto-generated method stub

		// 我们这里动作逻辑很简单，仅仅打印出通知的类型和通知的消息
		System.out.println("Receiving Notification...");
		System.out.println("Notification Type: " + notification.getType());
		System.out
				.println("Notification Message: " + notification.getMessage());

	}

}