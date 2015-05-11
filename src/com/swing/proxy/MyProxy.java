package com.swing.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {

	public static void main(String[] args) throws IllegalArgumentException,
			SecurityException, InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		BusinessInterface businessImpl = new BusinessImpl();
		BusinessInterface businessImpl2 = new BusinessImpl2();

		InvocationHandler handler = new LogHandler(businessImpl2);

		BusinessInterface proxy = (BusinessInterface) Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(),
				new Class[] { BusinessInterface.class }, handler);

		String s = proxy.processBusiness();
		System.out.println("返回值："+s);

		// InvocationHandler handler2 = new LogHandler(businessImpl);
		//
		// Class proxyClass = Proxy.getProxyClass(handler2.getClass()
		// .getClassLoader(), new Class[] { BusinessInterface.class });
		//
		// BusinessInterface f = (BusinessInterface) proxyClass.getConstructor(
		// new Class[] { InvocationHandler.class }).newInstance(
		// new Object[] { handler2 });
		// f.processBusiness();

	}
}
