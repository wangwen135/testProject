package jmx.JmxDemo.src.com.alibaba.jmx.test.rmijmxbrowser;

import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RmiJmxBrowser {
	public static void main(String args[]) throws Exception {
		final JMXServiceURL jmxServiceUrl = new JMXServiceURL(
				"service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(jmxServiceUrl);

		MBeanServerConnection connection = connector.getMBeanServerConnection();

		// get general info
		System.out.println("Default Domain:");
		System.out.println("    " + connection.getDefaultDomain());
		System.out.println("All domain on server:");
		for (String s : connection.getDomains()) {
			System.out.println("    " + s);
		}
		System.out.print("MBean count on Server: ");
		System.out.println(connection.getMBeanCount());

		// get info of the activemq
		ObjectName filterName = new ObjectName("*:*");
		Set<?> objectNames = connection.queryNames(filterName, null);

		for (Object name : objectNames) {
			ObjectName objName = (ObjectName) name;
			System.out
					.println("    Object Name: " + objName.getCanonicalName());
		}

		connector.close();
	}
}
