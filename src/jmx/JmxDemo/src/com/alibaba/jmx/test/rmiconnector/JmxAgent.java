package jmx.JmxDemo.src.com.alibaba.jmx.test.rmiconnector;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JmxAgent {
	public static final int port = 1099;

	private static MBeanServer mbeanServer = null;

	private static HtmlAdaptorServer adapter = null;

	private static JMXConnectorServer jmxConnectorServer = null;
	private static String connectorPath = "/jmxrmi";

	private static void startHTMLAdapter() throws Exception {
		ObjectName adapterName = new ObjectName(":name=html,port=9092");
		adapter = new HtmlAdaptorServer();
		adapter.setPort(9092);
		mbeanServer.registerMBean(adapter, adapterName);

		adapter.start();

		System.out.println("Html Adaptor Server started.");
		System.out.println("Use URL http://localhost:9092/ to access Server.");
	}

	private static void stopHTMLAdapter() {
		adapter.stop();
	}

	private static void startRMIConnector() throws Exception {
		// start RMI server at local host!
		LocateRegistry.createRegistry(port);

		final JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi://localhost/jndi/rmi://localhost:" + port
						+ connectorPath);
		jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(
				url, null, mbeanServer);

		ObjectName connectorName = new ObjectName(":name=jmxConnectorServer");
		mbeanServer.registerMBean(jmxConnectorServer, connectorName);

		jmxConnectorServer.start();
	}

	private static void startRMIConnector2() throws Exception {
		// start RMI server at local host!
		LocateRegistry.createRegistry(port);

		final JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi://localhost/jndi/rmi://localhost:" + port
						+ connectorPath);
		jmxConnectorServer = new RMIConnectorServer(url, null);
		ObjectName connectorName = new ObjectName(":name=RMIConnector");
		mbeanServer.registerMBean(jmxConnectorServer, connectorName);

		jmxConnectorServer.start();
	}

	private static void startRMIConnector3() throws Exception {
		// start RMI server at local host!
		LocateRegistry.createRegistry(port);

		final JMXServiceURL jmxUrl = new JMXServiceURL(
				"service:jmx:rmi://localhost:" + port);
		jmxConnectorServer = new RMIConnectorServer(jmxUrl, null);
		ObjectName connectorName = new ObjectName(":name=RMIConnector");
		mbeanServer.registerMBean(jmxConnectorServer, connectorName);

		jmxConnectorServer.start();
	}

	private static void stopRMIConnector() throws IOException {
		jmxConnectorServer.stop();
	}

	public static void main(String[] args) throws Exception {
		mbeanServer = MBeanServerFactory.createMBeanServer(JmxAgent.class
				.getName());
		startHTMLAdapter();
		startRMIConnector();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("Bye!");
			}
		}));

		System.out.println("use command \"exit\" to TERMINATE server.");
		System.out.println("use command \"help\" to show help message.");
		// catch user input to exit the program
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("$ ");
			String input = scanner.nextLine();
			if (input.trim().equals("")) {
				// do nothing
			} else if (input.trim().equalsIgnoreCase("exit")) {
				stopHTMLAdapter();
				stopRMIConnector();
				break;
			} else if (input.trim().equalsIgnoreCase("help")) {
				System.out
						.println("    exit    stop Html Adaptor Server and exit program.");
				System.out.println("    help    print this message.");
			} else {
				System.out.println("invalid command: " + input.trim());
				System.out.println("use command help for detail infomation.");
			}
		}
	}
}
