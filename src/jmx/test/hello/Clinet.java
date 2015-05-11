package jmx.test.hello;

import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import jmx.JmxDemo.src.com.alibaba.jmx.test.mbean.HelloWorld;
import jmx.JmxDemo.src.com.alibaba.jmx.test.rmiconnector.JmxAgent;

public class Clinet {
	public static void main(String[] args) throws Exception {
		// The address of the connector server
		JMXServiceURL address = new JMXServiceURL(
				"service:jmx:rmi://127.0.0.1:1234");

		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi://localhost/jndi/rmi://localhost:1234/path");

		Map environment = null;

		JMXConnector cntor = JMXConnectorFactory.connect(url, environment);

		MBeanServerConnection connection = cntor.getMBeanServerConnection();

		String domain = connection.getDefaultDomain();

		System.out.println(domain);

		ObjectName name = new ObjectName("hello:type=Hello");

		System.out.println(connection.isRegistered(name));

		// connection.createMBean(Hello.class.getName(), name);

		ClientListener listener = new ClientListener();
		connection.addNotificationListener(name, listener, null, null);

		System.out.println(connection.getAttribute(name, "Name"));

		Object ret1 = connection.invoke(name, "sayHello",
				new Object[] { "ww" }, new String[] { String.class.getName() });

		System.out.println("sayHello 返回结果：" + ret1);

		long t1 = System.currentTimeMillis();
		System.out.println("time1 = " + t1);

		Object ret = connection.invoke(name, "add", new Object[] { 1, 2 },
				new String[] { "int", "int" });
		System.out.println(ret);

		long t2 = System.currentTimeMillis();
		System.out.println("time2 = " + t2);

		System.out.println("耗时：" + (t2 - t1));
	}

	private static void test0() throws Exception {
		final JMXServiceURL jmxServiceUrl = new JMXServiceURL(
				"service:jmx:rmi://localhost/jndi/rmi://localhost:"
						+ JmxAgent.port + "/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(jmxServiceUrl);

		MBeanServerConnection connection = connector.getMBeanServerConnection();

		ObjectName hwName = new ObjectName(":name=helloWorld"
				+ System.nanoTime());
		connection.createMBean(HelloWorld.class.getName(), hwName);
		connection.invoke(hwName, "printHello", null, null);

		System.out.println(connection.invoke(hwName, "worldName", null, null));
		System.out.println(connection.getAttribute(hwName, "Name"));

		connector.close();
	}
}
