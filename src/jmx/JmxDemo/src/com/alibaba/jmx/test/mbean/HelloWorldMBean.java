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
 * TODO Comment of HelloMBean
 * 
 * @author ding.lid
 */
public interface HelloWorldMBean {
    public String getName();

    public void setName(String name);

    public void printHello();

    public void printHello(String theName);

    String worldName();
}
