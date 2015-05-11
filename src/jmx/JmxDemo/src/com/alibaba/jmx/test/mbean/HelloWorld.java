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
package jmx.JmxDemo.src.com.alibaba.jmx.test.mbean;

/**
 * TODO Comment of Hello
 * 
 * @author ding.lid
 */
public class HelloWorld implements HelloWorldMBean {

    private String name = "Alibaba";

    public String getName() {
        return name;
    }

    public void printHello() {
        System.out.println("Hello, " + name);
    }

    public void printHello(String theName) {
        System.out.println("Hello, " + theName + "!");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String worldName() {
        return name + " world";
    }

}
