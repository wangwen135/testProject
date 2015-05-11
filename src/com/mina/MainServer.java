package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单Mina Server示例
 * 
 */

public class MainServer {

	static Logger logger = LoggerFactory.getLogger(MainServer.class);

	public static void main(String[] args) throws Exception {

		logger.info("测试logger。。。");

		// 创建一个非阻塞的Server端Socket,用NIO

		SocketAcceptor acceptor = new NioSocketAcceptor();

		// 增加日志过滤器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// 增加编码过滤器
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		// 创建接收数据的过滤器
//		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		// 设定这个过滤器将一行一行(/r/n)的读取数据
//		chain.addLast("myChin", new ProtocolCodecFilter(
//				new TextLineCodecFactory()));

		// 设定服务器端的消息处理器:一个SamplMinaServerHandler对象,

		acceptor.setHandler(new ServerHandler());

		// 设置buffer的长度
		acceptor.getSessionConfig().setReadBufferSize( 2048 );

		//设置连接超时时间
		acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );

		
		// 服务器端绑定的端口
		int bindPort = 9988;

		// 绑定端口,启动服务器
		acceptor.bind(new InetSocketAddress(bindPort));

		System.out.println("Mina Server is Listing on:= " + bindPort);

	}

}