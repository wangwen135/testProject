/**
 * Project: JmxDemo
 * 
 * File Created at May 5, 2009
 * $Id$
 * 
 * Copyright 2008 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package jmx.JmxDemo.src.com.alibaba.jmx.test.htmladapter;

import java.util.Scanner;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import jmx.JmxDemo.src.com.alibaba.jmx.test.mbean.HelloWorld;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HtmlAgent {

    public static void main(String[] args) throws Exception {
        MBeanServer server = MBeanServerFactory.createMBeanServer(HtmlAgent.class.getName());

        // register bean 1
        ObjectName helloName = new ObjectName(":name=HelloWorld");
        server.registerMBean(new HelloWorld(), helloName);

        // register bean 2
        ObjectName adapterName = new ObjectName(":name=HtmlAdapter");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.setPort(8082);
        server.registerMBean(adapter, adapterName);

        adapter.start();
        System.out.println("Html Adaptor Server started.");
        System.out.println("Use URL http://localhost:8082/ to access Server.");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Bye!");
            }
        }));

        // catch user input to exit the program
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            if (input.trim().equals("")) {
                // do nothing
            } else if (input.trim().equalsIgnoreCase("exit")) {
                adapter.stop();
                break;
            } else if (input.trim().equalsIgnoreCase("help")) {
                System.out.println("    exit    stop Html Adaptor Server and exit program.");
                System.out.println("    help    print this message.");
            } else {
                System.out.println("invalid command: " + input.trim());
                System.out.println("use command help for detail infomation.");
            }
        }
    }
}
