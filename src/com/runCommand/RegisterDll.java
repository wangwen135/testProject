package com.runCommand;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class RegisterDll {
	public static void main(String[] args) {
		try {
			JOptionPane.showMessageDialog(null, "Dll未注册，点击确定开始注册");
			int choose = JOptionPane.showConfirmDialog(null, "是否注册Dll未注册？",
					"请选择", JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.YES_OPTION) {
				Runtime.getRuntime().exec("regsvr32 dll/midas.dll");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main2(String[] args) {
		try {
			Process p = Runtime.getRuntime().exec("registerDll.bat");
			// Process p = Runtime.getRuntime().exec("aaa.bat");

			InputStream is = p.getInputStream();

			out o1 = new out(is);
			new Thread(o1).start();

			OutputStream outs = p.getOutputStream();

			InputStream es = p.getErrorStream();
			ErrorOut o2 = new ErrorOut(es);
			new Thread(o2).start();

			try {
				System.out.println(p.waitFor());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("aaaaaaa");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class out implements Runnable {

	BufferedInputStream bis;

	public out(InputStream i) {
		this.bis = new BufferedInputStream(i);
	}

	@Override
	public void run() {
		byte[] b = new byte[1024];
		int length = 0;

		try {
			while ((length = bis.read(b)) > 0) {
				System.out.println(new String(b, 0, length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}

class ErrorOut implements Runnable {

	BufferedInputStream bis;

	public ErrorOut(InputStream i) {
		this.bis = new BufferedInputStream(i);
	}

	@Override
	public void run() {
		byte[] b = new byte[1024];
		int length = 0;

		try {
			while ((length = bis.read(b)) > 0) {
				System.err.println(new String(b, 0, length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}