package com.charset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.SortedMap;

import oracle.sql.CharacterSet;
import oracle.sql.CharacterSetWithConverter;
import oracle.sql.converter.CharacterSetMetaData;

public class CharsetTest {

	public static String toStringWithReplacement(byte abyte0[], int i, int j) {
		try {
			char ac[];
			int k;
			ac = new char[abyte0.length];
			int ai[] = new int[1];
			ai[0] = j;
			k = CharacterSet.convertAL32UTF8BytesToJavaChars(abyte0, i, ac, 0,
					ai, true);
			return new String(ac, 0, k);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String sourc = "b22901bf2daa9f57bb4ce30c04e77efe3ad52befae35bb48eee523f2ad55140d";

		ByteBuffer bytebuf = ByteBuffer.allocate(32);

		for (int i = 0; i < sourc.length(); i += 2) {
			int b = Integer.valueOf(sourc.substring(i, i + 2), 16);
			bytebuf.put((byte) b);
		}

		bytebuf.flip();

		byte[] bytes = bytebuf.array();

		String s = toStringWithReplacement(bytes, 0, 1);

		System.out.println(s);

		// System.out.println(bytes.length);
		// new String(bytes, Charset.forName("AL32UTF8"));

		try {
			char[] ch = CharacterSet.AL32UTF8ToJavaChar(bytes, 0, 32, false);
			System.out.println(ch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String s = CharacterSet.AL32UTF8ToString(bytes, 1, 0);
		// System.out.println(s);

		// String s =CharacterSet.AL32UTF8ToString(bytes, 0, 32);
		// System.out.println(s);
		// ??獰W籐?鐍??锂5籋铄#颦U
		// System.out.println(new String(bytes, CharacterSet.AL32UTF8_CHARSET));
	}

	public static void main3(String[] args) {

		System.out.println(Charset.isSupported("AL32UTF8"));

		SortedMap<String, Charset> smap = Charset.availableCharsets();

		for (Entry<String, Charset> e : smap.entrySet()) {
			System.out.println(e.getKey());
			System.out.println(e.getValue());
		}
	}

	public static void main2(String[] args) {
		String str = "中文str";
		try {
			String str2 = new String(str.getBytes(), "GBK");
			System.out.println(str2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str);
		System.out.println(Charset.defaultCharset());
	}
}
