package com.scriptEngine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ConsolePrintStream extends PrintStream {

	private JTextArea textArea_console;

	public ConsolePrintStream(OutputStream out, JTextArea textArea) {
		super(out);
		this.textArea_console = textArea;
	}
	
//	@Override
//	public void println(Object x) {
//		println(x.toString());
//	}
//
//	@Override
//	public void println(final String x) {
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				textArea_console.append(x);
//				textArea_console.append("\r\n");
//			}
//		});
//
//		super.println(x);
//	}
	
	
	@Override
	public void write(byte[] buf, int off, int len) {
		 final String message = new String(buf, off, len);  
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					textArea_console.append(message);					
				}
			});
		super.write(buf, off, len);
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		System.out.println(new String(b));
		
		super.write(b);
	}
	
	@Override
	public void write(int b) {
		System.out.println(b);
		super.write(b);
	}
	
	public void write(String str) throws IOException {
		System.out.println(str);
		
		write(str.getBytes());
	}
	
}
