package com.character;

import java.io.UnsupportedEncodingException;

public class ToString {
	public static void main(String[] args) throws UnsupportedEncodingException {
		// 我们参照检验药品的方法
		// \'E6\'88\'91\'E4\'BB\'AC\'E5\'8F\'82\'E7\'85\'A7\'E6\'A3\'80\'E9\'AA\'8C\'E8\'8D\'AF\'E5\'93\'81\'E7\'9A\'84\'E6\'96\'B9\'E6\'B3\'95

		strToRtf("中");

		char c = '中';
		System.out.println(Integer.toHexString(c));

		String s = "中";
		byte[] b = s.getBytes("GB2312");
		for (int i = 0; i < b.length; i++) {
			System.out.println("\\'"+Integer.toHexString(b[i] & 0xff));
		}

	}

	public static String strToRtf(String content) {

		// try {
		// System.out.println(content);
		// content = new String(content.getBytes(), "GB2312");
		// System.out.println(content);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		byte[] bs = null;
		bs = content.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append("\\'");
			sb.append(digital[bit]);
			bit = bs[i] & 0x0f;
			sb.append(digital[bit]);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
