package com.swing.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {

	private Object delegate;

	public LogHandler(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object o = null;
		try {
			System.out.println("INFO Begin ");
			o = method.invoke(delegate, args);
			System.out.println("INFO End ");
		} catch (Exception e) {
			System.err.println("INOF EXCEPTION");
			e.printStackTrace();
		}
		return o;
	}
}
