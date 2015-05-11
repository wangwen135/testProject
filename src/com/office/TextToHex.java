package com.office;

import java.io.UnsupportedEncodingException;

public class TextToHex {
	public static void main(String[] args) {
		System.out.println(encode("参数测试，传入的值是："));
	}

	public static String encode(String content) {
		StringBuilder sb = new StringBuilder();
		byte[] b;
		try {
			// 编码应该需要注意
			b = content.getBytes("GB2312");
			for (int i = 0; i < b.length; i++) {
				sb.append("\\\\'");
				sb.append(Integer.toHexString(b[i] & 0xff));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
