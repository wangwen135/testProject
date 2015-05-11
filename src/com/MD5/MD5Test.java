package com.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
	public static void main(String[] args) {
		String s = getMD5Str("admin");
		System.out.println(s);
	}

	
	/**
	 * 非标准MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			byte b = byteArray[i];
			md5StrBuff.append(Integer.toHexString(0x0F & b));
		}

		return md5StrBuff.toString();
	}
	
	/**
	 * MD5 加密
	 */
	public static String getSHAStr(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-256");

			
			System.out.println(messageDigest.getDigestLength());
			 

			// messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			byte b = byteArray[i];
			System.out.println(b);
			
			md5StrBuff.append(Integer.toHexString(0x0F & b));
			
//			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
//				md5StrBuff.append("0").append(
//						Integer.toHexString(0xFF & byteArray[i]));
//			} else {
//				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
//			}
		}

		return md5StrBuff.toString();
	}
}
