package com.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CreateImage {

	/**
	 * <pre>
	 * java 写图片
	 * </pre>
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedImage bi = readImagetBuffer();

		Graphics2D g2d = bi.createGraphics();

		// 透明度
		// g2d.setColor(Color.RED);
		// Composite c = g2d.getComposite();
		// AlphaComposite alp = AlphaComposite.getInstance(
		// AlphaComposite.SRC_OVER, 0.5f);
		// g2d.setComposite(alp);
		// 用白色 覆盖条码区域,
		// g2d.fillRect(430, 0, 330, 150);

		// "200727521396"

		writeBarCode(g2d, "755517225090");

		// 写文字
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(g2d.getFont().getName(), g2d.getFont().getStyle(),
				16));

		// 写二维码数字
		// g2d.drawString("200 727 521 396", 450, 120);

		g2d.drawString("这是一个测试地址", 80, 200);

		bi.flush();
		writeImage("测试写图片.JPG", bi);
	}

	public static void writeBarCode(Graphics2D g2d, String msg)
			throws IOException {
		// 画一个条码
		g2d.drawImage(BarCodeImage.create(msg), null, 441, 20);
		// 写条码数字
		Font font = g2d.getFont();
		System.out.println(font);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 38));
		g2d.drawString("755 517 225 090", 450, 135);
		g2d.setFont(font);
	}

	public static void writeImage(String newImage, BufferedImage img)
			throws IOException {
		File out = new File(newImage);
		ImageIO.write(img, "JPG", out);
	}

	public static BufferedImage readImagetBuffer() throws IOException {
		InputStream i = CreateImage.class.getResourceAsStream("01.jpg");
		return ImageIO.read(i);
	}
}
