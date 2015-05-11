package jmx.JmxDemo.src.com.alibaba.jmx.test.rmiconnector;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;

import jmx.JmxDemo.src.com.alibaba.jmx.test.mbean.HelloWorld;

public class JmxClient {

    public static void main(String args[]) throws Exception {
        test0();
    }

    private static void test0() throws Exception {
        final JMXServiceURL jmxServiceUrl = new JMXServiceURL(
                "service:jmx:rmi://localhost/jndi/rmi://localhost:" + JmxAgent.port + "/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(jmxServiceUrl);

        MBeanServerConnection connection = connector.getMBeanServerConnection();

        ObjectName hwName = new ObjectName(":name=helloWorld" + System.nanoTime());
        connection.createMBean(HelloWorld.class.getName(), hwName);
        connection.invoke(hwName, "printHello", null, null);

        System.out.println(connection.invoke(hwName, "worldName", null, null));
        System.out.println(connection.getAttribute(hwName, "Name"));

        connector.close();
    }

    private static void test1() throws Exception {
        final JMXServiceURL jmxServiceUrl = new JMXServiceURL(
                "service:jmx:rmi://localhost/jndi/rmi://localhost:" + JmxAgent.port + "/jmxrmi");
        RMIConnector connector = new RMIConnector(jmxServiceUrl, null);
        connector.connect();

        MBeanServerConnection connection = connector.getMBeanServerConnection();

        ObjectName hwName = new ObjectName(":name=helloWorld" + System.nanoTime());
        connection.createMBean(HelloWorld.class.getName(), hwName);
        connection.invoke(hwName, "printHello", null, null);

        System.out.println(connection.invoke(hwName, "worldName", null, null));

        connector.close();
    }
}
