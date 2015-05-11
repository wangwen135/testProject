package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MainClient {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建客户端连接器.
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8")))); // 设置编码过滤器
		
		connector.setConnectTimeout(30);

		connector.setHandler(new ClientHandler());// 设置事件处理器

		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1",
				9988));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String str = sc.next();
			//System.out.println(str);
			cf.getSession().write(str);
			
			if("quit".equals(str)){
				break;
			}
		}
		
		
//		cf.getSession().write("hello ");// 发送消息
		
//		cf.getSession().write("quit");// 发送消息
		
		cf.getSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
		connector.dispose();
	}

}
