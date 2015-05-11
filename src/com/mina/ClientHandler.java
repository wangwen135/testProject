package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

	public ClientHandler() {
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("messageReceived method was called!");
		System.out.println(message);// 显示接收到的消息
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
		System.out.println("messageSent method was called!");
		System.out.println(message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		System.out.println("sessionClosed method was called!");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		System.out.println("sessionCreated method was called!");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		System.out.println("sessionOpened method was called!");
	}

}