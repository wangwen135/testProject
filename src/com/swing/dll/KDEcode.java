package com.swing.dll;

import java.io.File;
import java.io.IOException;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

public class KDEcode {
	private static String DLL_NAME;
	static {
		try {
			String path = (new File("dll")).getCanonicalPath() + File.separator;

			DLL_NAME = path + "kdencode.dll";

			System.out.println(DLL_NAME);

			String jnative = path + "JNativeCpp.dll";

			System.setProperty("jnative.loadNative", jnative);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public int KDEncode(int paramInt, byte[] paramArrayOfByte1,
	// byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

	public static void main(String[] args) throws Exception {
		JNative jn = null;
		try {
			jn = new JNative(DLL_NAME, "KDEncode");
			jn.setRetVal(Type.INT);
			jn.setParameter(0, 6);
			jn.setParameter(1, Type.STRING, "aaa");
			jn.setParameter(2, Type.STRING, "bbb");
			jn.setParameter(3, Type.STRING, "bbb");
			jn.invoke();
			String ret = jn.getRetVal();

			System.out.println(ret);
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
