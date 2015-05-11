package com.scriptEngine;

import java.io.BufferedInputStream;
import java.io.IOException;

public class ConsoleTest {
	public static void main(String[] args) {

		Runnable r1 = new OutRunnable("R1");
		Runnable r2 = new OutRunnable("R2");

		new Thread(r1).start();
		new Thread(r2).start();
	}

}

class OutRunnable implements Runnable {

	String flag;

	public OutRunnable(String flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		BufferedInputStream buffis = new BufferedInputStream(System.in);
		byte[] b = new byte[1024];
		int lenght;
		try {
			while ((lenght = buffis.read(b)) > 0) {

				System.out.println(flag + " : " + new String(b, 0, lenght));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}