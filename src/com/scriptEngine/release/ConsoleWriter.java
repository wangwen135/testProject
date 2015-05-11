package com.scriptEngine.release;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.nio.CharBuffer;

/**
 * 描述：一个管道流<br>
 * 线程不停的将写入流中的数据读取出来写到控制台中
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-8-14      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ConsoleWriter {

	boolean stop = false;

	ConsoleTextPane text_console;

	PrintWriter printWriter;

	PipedReader pipReader;
	PipedWriter pipWriter;

	Thread runThread;

	public ConsoleWriter(ConsoleTextPane console) {
		this.text_console = console;

		// 字符流
		pipReader = new PipedReader();
		pipWriter = new PipedWriter();

		printWriter = new PrintWriter(pipWriter, true);

		try {
			pipReader.connect(pipWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Runnable r = new Runnable() {

			@Override
			public void run() {

				CharBuffer target = CharBuffer.allocate(1024);
				try {
					while (!stop) {

						int i = pipReader.read(target);
						if (i <= 0) {
							break;
						}
						target.flip();
						text_console.append(target.toString());
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (pipWriter != null) {
						try {
							pipWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					if (pipReader != null) {
						try {
							pipReader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
		};

		runThread = new Thread(r, "ConsoleWriterThread");
		runThread.start();
	}

	public PrintWriter getWriter() {
		return printWriter;

	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * <pre>
	 * 中断线程
	 * </pre>
	 */
	public void interrupt() {
		runThread.interrupt();
	}

}
