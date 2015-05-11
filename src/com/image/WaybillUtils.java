package com.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class WaybillUtils {

	public static Pattern p = Pattern.compile("[\\p{Graph}[ ]]");// 数字，字母，标点符号，空格

	/**
	 * 运单图片
	 */
	public static final String WAYBILL_IMAGE = "waybill.jpg";

	public static File waybillFile;
	static {
		// 读取配置文件，如果配置错误或者没有配置则取默认值
		waybillFile = new File(WAYBILL_IMAGE);
	}

	public static void main(String[] args) {

	}

	/**
	 * <pre>
	 * 生成运单图片
	 * </pre>
	 * 
	 * @param data
	 *            数据
	 *            <ul>
	 *            <li>运单号</li>
	 *            <li>寄件公司</li>
	 *            <li>寄件地址</li>
	 *            <li>寄件联系人电话</li>
	 *            <li>收件公司</li>
	 *            <li>寄件地址</li>
	 *            <li>收件联系人电话</li>
	 *            <li>托寄物内容</li>
	 *            <li>数量</li>
	 *            <li>件数</li>
	 *            <li>计费重量d</li>
	 *            <li>实际重量</li>
	 *            <li>原寄地</li>
	 *            <li>目的地</li>
	 *            <li>收件员</li>
	 *            <li>日期</li>
	 *            </ul>
	 * @param outDir
	 *            输出目录
	 * 
	 * @param fileName
	 *            文件名称
	 * @throws IOException
	 */
	public static void createWaybillImage(Map<String, Object> data,
			File outDir, String fileName) throws IOException {
		BufferedImage bi = readImagetBuffer(waybillFile);

		Graphics2D g2d = bi.createGraphics();

		// 运单号
		writeBarCode(g2d, (String) data.get("运单号"));

		// 设置颜色
		g2d.setColor(Color.BLACK);

		// 设置字体
		Font font = new Font(g2d.getFont().getName(), g2d.getFont().getStyle(),
				16);
		g2d.setFont(font);

		// 寄件公司
		g2d.drawString((String) data.get("寄件公司"), 80, 242);

		// 寄件地址
		writeSrcCorp(g2d, (String) data.get("寄件地址"));

		// 寄件联系人电话
		g2d.drawString((String) data.get("寄件联系人电话"), 190, 375);

		// 收件公司
		g2d.drawString((String) data.get("收件公司"), 80, 482);

		// 收件公司地址
		writeDescCorp(g2d, (String) data.get("收件地址"));

		// 收件联系人电话
		g2d.drawString((String) data.get("收件联系人电话"), 190, 605);

		// 托寄物内容
		g2d.drawString((String) data.get("托寄物内容"), 10, 720);

		// 数量
		g2d.drawString(data.get("数量").toString(), 400, 710);

		// 件数
		g2d.drawString(data.get("件数").toString(), 500, 765);

		// 计费重量
		double aweight = (Double) data.get("计费重量");
		g2d.drawString(round(aweight) + "", 580, 765);

		// 实际重量
		g2d.drawString(data.get("实际重量").toString(), 570, 800);

		// 费用合计
		g2d.drawString(data.get("费用合计").toString(), 705, 800);

		// 原寄地
		g2d.drawString((String) data.get("原寄地"), 830, 135);

		// 目的地
		g2d.drawString((String) data.get("目的地"), 920, 135);

		// 收件员
		writeSJY(g2d, (String) data.get("收件员"));

		// 日期
		writeDate(g2d, (Date) data.get("日期"));

		g2d.dispose();
		bi.flush();

		File outFile = new File(outDir, fileName + ".JPG");

		writeImage(outFile, bi);
	}

	/**
	 * <pre>
	 * 按照0.5递增
	 * </pre>
	 * 
	 * @param src
	 * @return
	 */
	public static double round(double src) {
		double increment = 0.5;
		double value = 0.5;
		while (value < src) {
			value += increment;
		}
		return value;
	}

	/**
	 * <pre>
	 * 写日期
	 * </pre>
	 * 
	 * @param g2d
	 * @param date
	 */
	public static void writeDate(Graphics2D g2d, Date date) {
		// 格式化日期
		// MMddHHss

		String msg = "02210636";

		float x = 847f;
		float y = 568f;
		float increment = 24.5f;
		for (int i = 1; i <= msg.length(); i++) {
			g2d.drawString(String.valueOf(msg.charAt(i - 1)), x, y);
			x += increment;
			if (i % 2 == 0) {
				x += 19f;
			}
		}

	}

	/**
	 * <pre>
	 * 写收件员
	 * </pre>
	 * 
	 * @param g2d
	 * @param msg
	 */
	public static void writeSJY(Graphics2D g2d, String msg) {
		Font f = g2d.getFont();

		Font font = new Font(f.getName(), Font.BOLD, f.getSize());
		g2d.setFont(font);

		float x = 815f;
		float y = 415f;
		float increment = 24.5f;
		for (int i = 0; i < msg.length(); i++) {
			g2d.drawString(String.valueOf(msg.charAt(i)), x, y);
			x += increment;
		}

		g2d.setFont(f);
	}

	private static void writeSrcCorp(Graphics2D g2d, String msg) {
		if (msg == null || "".equals(msg)) {
			return;
		}
		List<String> list = lineFinder(msg, 50, 2);
		// 如果有两行

		g2d.drawString(list.get(0), 70, 285);

		if (list.size() > 1) {
			g2d.drawString(list.get(1), 10, 330);
		}

	}

	private static void writeDescCorp(Graphics2D g2d, String msg) {
		if (msg == null || "".equals(msg)) {
			return;
		}
		List<String> list = lineFinder(msg, 50, 2);
		// 如果有两行

		g2d.drawString(list.get(0), 70, 525);

		if (list.size() > 1) {
			g2d.drawString(list.get(1), 10, 565);
		}

	}

	/**
	 * <pre>
	 * 分行器
	 * </pre>
	 * 
	 * @param src
	 *            要分行的字符串
	 * @param lineLength
	 *            行长度（中文等占两个长度）
	 * @param lineLimit
	 *            行数限制，如果限制小于1表示无限制
	 * @return
	 */
	private static List<String> lineFinder(String src, int lineLength,
			int lineLimit) {
		if (src == null || "".equals(src))
			return null;

		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		int length = 0;
		for (int i = 0; i < src.length(); i++) {
			char c = src.charAt(i);
			sb.append(c);
			Matcher m = p.matcher(String.valueOf(c));
			if (m.matches()) {
				length++;
			} else {
				length += 2;
			}
			if (length >= lineLength) {
				list.add(sb.toString());
				if (lineLimit > 0 && list.size() >= (lineLimit - 1)) {
					if (i < (src.length() - 1)) {
						list.add(src.substring(i + 1));
					}
					return list;
				}
				sb = new StringBuffer();
				length = 0;
			}
		}
		list.add(sb.toString());

		return list;
	}

	/**
	 * <pre>
	 * 写条码，和条码数字
	 * </pre>
	 * 
	 * @param g2d
	 *            绘画对象
	 * @param msg
	 *            运单编号
	 * @throws IOException
	 */
	private static void writeBarCode(Graphics2D g2d, String msg)
			throws IOException {
		// 画一个条码
		g2d.drawImage(BarCodeImage.create(msg), null, 441, 20);
		// 写条码数字
		Font font = g2d.getFont();
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 38));// 设置字体写运单

		StringBuffer sb = new StringBuffer(msg);
		for (int i = 3; i < sb.length(); i += 4) {
			sb.insert(i, " ");
		}
		g2d.drawString(sb.toString(), 450, 135);// 坐标
		g2d.setFont(font);// 还原字体
	}

	/**
	 * <pre>
	 * 写图片
	 * </pre>
	 * 
	 * @param newImage
	 * @param img
	 * @throws IOException
	 */
	private static void writeImage(File outFile, BufferedImage img)
			throws IOException {

		ImageIO.write(img, "JPG", outFile);
	}

	/**
	 * <pre>
	 * 读取原图
	 * </pre>
	 * 
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage readImagetBuffer(File file) throws IOException {
		InputStream i = new FileInputStream(file);
		// InputStream i = CreateImage.class.getResourceAsStream("01.jpg");
		return ImageIO.read(i);
	}
}
