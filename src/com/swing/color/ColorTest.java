package com.swing.color;

import java.awt.Color;

public class ColorTest {
	public static void main(String[] args) {
		Color c = Color.BLUE;
		System.out.println(colorToHtmlColor(c));
	}

	public static String colorToHtmlColor(Color c) {
		if (c == null) {
			return "#000000";
		}
		StringBuffer colorBuf = new StringBuffer();
		String t = Integer.toHexString(c.getRed());
		if (t.length() == 1) {
			colorBuf.append("0");
		}
		colorBuf.append(t);
		t = Integer.toHexString(c.getGreen());
		if (t.length() == 1) {
			colorBuf.append("0");
		}
		colorBuf.append(t);
		t = Integer.toHexString(c.getBlue());
		if (t.length() == 1) {
			colorBuf.append("0");
		}
		colorBuf.append(t);

		return colorBuf.toString().toUpperCase();
	}
}
