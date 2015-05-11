package com.scriptEngine;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class ConsoleOutPutStream extends OutputStream{
	private JTextArea textArea_console;

	public ConsoleOutPutStream(JTextArea console) {
		this.textArea_console = console;	
	}
	
	@Override
	public void write(int b) throws IOException {
		
		System.out.println(b);
		textArea_console.setText(""+b);
	}

}
