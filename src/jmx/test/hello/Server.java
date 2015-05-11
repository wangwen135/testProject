package jmx.test.hello;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class Server {

	public static void main(String[] args) throws Exception {

		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		// MBeanServer server =
		// MBeanServerFactory.createMBeanServer("helloDomain");

		LocateRegistry.createRegistry(1234);

		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi://localhost/jndi/rmi://localhost:1234/path");

		Map environment = null;

		ObjectName name = new ObjectName("hello:type=Hello");
		Hello mbean = new Hello();
		server.registerMBean(mbean, name);

		// Create the JMXCconnectorServer
		JMXConnectorServer cntorServer = JMXConnectorServerFactory
				.newJMXConnectorServer(url, environment, server);

		ObjectName connectorName = new ObjectName("hello:name=RMIConnector");
		server.registerMBean(cntorServer, connectorName);

		// Start the JMXConnectorServer
		cntorServer.start();

		System.out.println("start");

		// 使当前线程不停止
		Thread.sleep(Long.MAX_VALUE);
	}
}
