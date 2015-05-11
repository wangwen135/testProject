package com.swing.dll;

import java.io.File;
import java.io.IOException;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

public class cardDLL {

	private static String DLL_NAME;

	static {
		try {
			String path = (new File("dll")).getCanonicalPath() + File.separator;

			DLL_NAME = path + "SPSecureAPI.dll";
			String jnative = path + "JNativeCpp.dll";

			System.setProperty("jnative.loadNative", jnative);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));

		JNative jn = null;
		try {
			jn = new JNative(DLL_NAME, "abc");
			jn.setRetVal(Type.STRING);

			jn.setParameter(0, Type.STRING, "中文测试哦");

			jn.invoke();
			String ret = jn.getRetVal();

			System.out.println(ret);

		} catch (NativeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			freeJNative(jn);
		}

	}

	@SuppressWarnings("deprecation")
	private static void freeJNative(JNative jn) {
		if (jn != null) {
			try {
				jn.dispose();
			} catch (NativeException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
