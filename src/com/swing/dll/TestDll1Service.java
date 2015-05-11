package com.swing.dll;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class TestDll1Service {

	public interface KDEncodeCli extends Library {
		KDEncodeCli INSTANCE = (KDEncodeCli) Native.loadLibrary("kdencode",
				KDEncodeCli.class);

		public int KDEncode(int paramInt, byte[] paramArrayOfByte1,
				byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

		public int KDReEncode(byte[] paramArrayOfByte1,
				byte[] paramArrayOfByte2, int paramInt1,
				byte[] paramArrayOfByte3, int paramInt2,
				byte[] paramArrayOfByte4);

	}

	public String getEncode(String key, String src) {
		byte[] dest = new byte[256];
		int result = KDEncodeCli.INSTANCE.KDEncode(6, "123123".getBytes(),
				dest, "410301".getBytes());
		
		return new String(dest, 0, result);
	}

	/**
	 * 
	 */
	public TestDll1Service() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] ttt = new byte[256];
		
		System.out.println("1111");
		
		int result = KDEncodeCli.INSTANCE.KDEncode(4, "123123".getBytes("UTF-8"), ttt,
				"410301".getBytes("UTF-8"));
		
		System.out.println("2222");
		
		System.out.println(new String(ttt));
		System.out.println(result);
	}
}
