package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 
 * 自定议的消息处理器,必须实现IoHandlerAdapter类
 * 
 * @author javaFound
 */

public class ServerHandler extends IoHandlerAdapter {
	// 连接异常时处理方法
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	// 当一个客端端连结进入时

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("incomming client : " + session.getRemoteAddress());

	}

	// 当一个客户端关闭时

	@Override
	public void sessionClosed(IoSession session) {

		System.out.println("Clinet Disconnect ："+session.getRemoteAddress());

	}

	// 当客户端发送的消息到达时:

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		// 我们己设定了服务器解析消息的规则是一行一行读取,这里就可转为String:

		String s = (String) message;

		if (s.trim().equalsIgnoreCase("quit")) {
			session.close(true);
			return;
		}

		// Write the received data back to remote peer

		System.out.println("收到客户机发来的消息: " + s);

		// 测试将消息回送给客户端

		session.write(s + count);

		count++;

	}

	/**
	 * 连接超时时处理方法
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("IDLE " + session.getIdleCount(status));
	}

	private int count = 0;

}
