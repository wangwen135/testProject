package com.office.rtfTemplate.expWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.NumberFormat;

import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.UnsupportedRTFTemplate;
import net.sourceforge.rtf.helper.RTFTemplateBuilder;

import org.apache.log4j.Logger;

/**
 * <pre>
 * 描述：word报表工具类
 * 定义从配置文件中读取是否编码、编码方式的方法。其他所有的地方都调用该类的编码方法，由该类的方法来统一判断是否需要编码
 * 编码时将字符转换成字节将字节转16进制形式，如：
 * ‘中文’ 将编码成：‘ \'d6\'d0\'ce\'c4 ’
 * </pre>
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-2-29      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class ExpWordUtils {

	public static Logger logger = Logger.getLogger(ExpWordUtils.class);

	public static NumberFormat format = NumberFormat.getInstance();
	static {
		format.setRoundingMode(RoundingMode.HALF_UP);
		format.setGroupingUsed(false);
	}

	/**
	 * 是否编码
	 */
	private static boolean docode = false;
	/**
	 * 编码方式
	 */
	private static Charset charset = null;

	/**
	 * <pre>
	 * 格式化数字
	 * 使用四舍五入
	 * </pre>
	 * 
	 * @param value
	 *            字符串形式的值
	 * @param integer
	 *            整数位
	 * @param fraction
	 *            小数位
	 * @return 格式化之后的字符串
	 */
	public static String formatNumber(String value, int integer, int fraction) {

		if (value == null || "".equals(value.trim())) {
			return "";
		}
		format.setMaximumIntegerDigits(integer);
		format.setMaximumFractionDigits(fraction);
		format.setMinimumFractionDigits(fraction);
		// 如果格式化失败是否需要返回原值
		return format.format(Double.valueOf(value));
	}

	/**
	 * <pre>
	 * 读取配置文件，配置相关属性
	 * 不从缓存中读取
	 * </pre>
	 */
	public static void readProperties() {
		// 从配置文件中读取编码
		// #是否进行编码
		// expword.docode=true
		// #编码方式(word中使用的字符编码)
		// expword.code=gbk
		try {
			docode = false;
		} catch (Exception e) {
			docode = false;
		}
		try {
			charset = Charset.forName("GB18030");
		} catch (Exception e) {
			charset = Charset.defaultCharset();
		}
	}

	/**
	 * <pre>
	 * 获取编码方式
	 * </pre>
	 * 
	 * @return
	 */
	public static Charset getCharset() {
		if (charset == null) {
			readProperties();
		}
		return charset;
	}

	/**
	 * <pre>
	 * 编码，将字符串编码成word能识别的
	 * 根据配置文件判断是否需要编码，默认不编码
	 * </pre>
	 * 
	 * @param content
	 *            要编码的字符串
	 * @return 返回编码好的字符串
	 */
	public static String encode(String content) {
		if (!docode)
			return content;
		// 实际上像数字和字母之类的可以不用格式化。。。
		StringBuilder sb = new StringBuilder();
		byte[] b;
		b = content.getBytes(getCharset());
		for (int i = 0; i < b.length; i++) {
			sb.append("\\'");
			sb.append(Integer.toHexString(b[i] & 0xff));
		}
		return sb.toString();
	}

	/**
	 * <pre>
	 * 解码
	 * 未实现的方法
	 * </pre>
	 * 
	 * @param content
	 * @return
	 */
	public static String decode(String content) {
		return content;
	}

	/**
	 * <pre>
	 * 验证RTF模板
	 * </pre>
	 * 
	 * @param rtfFile
	 *            要验证的rtf文件
	 * @return
	 */
	public static boolean validateRTFTemplate(File rtfFile) {
		File tmpFile = new File("rtfFile/validate.doc");

		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();

		// 2. Get RTFtemplate with default Implementation of template engine
		// (Velocity)
		RTFTemplate rtfTemplate = null;
		try {
			rtfTemplate = builder.newRTFTemplate();
		} catch (UnsupportedRTFTemplate e) {
			System.err.println("不支持的模板");
			e.printStackTrace();
			return false;
		}

		try {
			rtfTemplate.setTemplate(rtfFile);
		} catch (FileNotFoundException e) {
			System.err.println("文件没有找到");
			e.printStackTrace();
			return false;
		}

		try {
			rtfTemplate.merge(tmpFile);
		} catch (Exception e) {
			System.out.println("merge文件时出错了");
			e.printStackTrace();
			return false;
		}

		return false;
	}

	/**
	 * <pre>
	 * 生成新的标准模板
	 * </pre>
	 * 
	 * @param rtfFile
	 */
	public static void createNewTemplate(File rtfFile) {
		
	}

	public static void main(String[] args) {
		// 验证模板正确性
		validateRTFTemplate(new File("rtfFile/test.rtf"));
	}
}
