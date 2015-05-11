package com.sf.module.EDI.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 描述：EDI工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-3-27      313921         Create
 *  2    2012-3-30      310928         Modify:增加新方法
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921&310928
 * @since 1.0
 */
public class EDIUtils {

	public static Pattern p = Pattern.compile("[\\p{Graph}[ ]]");// 数字，字母，标点符号，空格

	/**
	 * <pre>
	 * 根据指定的正则表达式对象过滤特殊字符
	 * </pre>
	 * 
	 * @param pattern
	 *            正则表达式对象
	 * @param str
	 *            需要过滤的字符串
	 * @return 返回过滤后的字符串，如果str=null 则返回 ""
	 */
	public static String filterString(Pattern pattern, Object str) {
		if (str != null && !str.equals("")) {
			Matcher m = pattern.matcher(str.toString());
			// 剔除首尾空格
			return m.replaceAll("").trim();
		} else {
			return "";
		}
	}

	/**
	 * <pre>
	 * 计算长度，中文等占两个长度
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static int strLength(String str) {
		if (str == null)
			return 0;
		int i = 0;
		Matcher m;
		char[] chars = str.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			m = p.matcher(String.valueOf(chars[j]));
			if (m.matches()) {
				i++;
			} else {
				i += 2;
			}
		}
		return i;
	}

	/**
	 * <pre>
	 * 截取字符串，中文占两个长度
	 * </pre>
	 * 
	 * @param str
	 *            要截取的字符串
	 * @param length
	 *            要截取的长度
	 * @return 截取后的字符，如果length 小于等于 0 ，返回原字符
	 */
	public static String substringChinese(String str, int length) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return str;
		}
		int valueLength = strLength(str);
		if (valueLength <= length) {
			return str;
		}
		int afterLength = valueLength;

		// 先进行一次截取，对于长字符串可以提供一点效率
		if (str.length() > length) {
			str = str.substring(0, length);
			afterLength = strLength(str);
		}

		while (afterLength > length) {
			str = str.substring(0, str.length() - 1);
			afterLength = strLength(str);
		}

		return str;
	}

	/**
	 * <pre>
	 * 复制文件
	 * </pre>
	 * 
	 * @param src
	 *            源文件
	 * @param desc
	 *            目标文件
	 * @throws IOException
	 */
	public static void copyFile(File src, File desc) throws IOException {
		BufferedInputStream ins = null;
		BufferedOutputStream outs = null;
		try {
			ins = new BufferedInputStream(new FileInputStream(src));

			if (!desc.getParentFile().mkdirs()) {
				if (!desc.getParentFile().exists()) {
					throw new IOException("创建目标文件必要的上级目录失败");
				}
			}

			outs = new BufferedOutputStream(new FileOutputStream(desc));

			byte[] b = new byte[8192];
			int len;
			while ((len = ins.read(b)) != -1) {
				outs.write(b, 0, len);
			}
			outs.flush();

		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (outs != null) {
				try {
					outs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
	 *            行数限制，如果限制小于等于1表示无限制
	 * @return
	 */
	public static List<String> lineFinder(String src, int lineLength,
			int lineLimit) {
		if (src == null || "".equals(src)) {
			return null;
		}

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
				sb = new StringBuffer();
				length = 0;

				if (lineLimit > 1 && list.size() == (lineLimit - 1)) {
					if (i < (src.length() - 1)) {
						list.add(src.substring(i + 1));
					}
					return list;
				}
			}
		}
		if (length > 0) {
			list.add(sb.toString());
		}

		return list;
	}

	/**
	 * <pre>
	 * 填充字符串
	 * </pre>
	 * 
	 * @param src
	 *            源字符串
	 * @param fillCharacter
	 *            要填充的内容
	 * @param length
	 *            长度
	 * @param leftFill
	 *            是否从左边开始补
	 * @return
	 */
	public static String fillStr(String src, String fillCharacter, int length,
			boolean leftFill) {
		StringBuffer sbf = new StringBuffer(src);
		while (sbf.length() < length) {
			if (leftFill) {
				sbf.insert(0, fillCharacter);
			} else {
				sbf.append(fillCharacter);
			}
		}
		return sbf.toString();
	}

	/**
	 * <pre>
	 * 截取数字之前的字符串
	 * 如果第一个字符是数字会返""
	 * </pre>
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果没有数字，则返回""；否则返回数字之前的字符串
	 */
	public static String substringBeforeDigit(String str) {
		if (str == null)
			return null;
		int index = -1;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				index = i;
				break;
			}
		}
		if (index == 0) {
			return "";
		}
		if (index > 0) {
			return str.substring(0, index);
		}
		return str;
	}

	/**
	 * <pre>
	 * 截取数字之后的字符串
	 * 如果第一个是字符会返回str
	 * </pre>
	 * 
	 * @param str
	 *            要截取的字符串
	 * @return 如果第一个是字符会返回str
	 */
	public static String substringAfterDigit(String str) {
		if (str == null)
			return null;
		int index = -1;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			return str.substring(index);
		}
		return "";
	}

	/**
	 * <pre>
	 * 截取第一个数字之后第二个数字之前的字符串
	 * </pre>
	 * 
	 * @param str
	 *            要截取的字符串
	 * @return 截取后的字符串str
	 */
	public static String subStringBetweenFirestDigitAndSecondDigit(String str) {
		if (str == null)
			return null;
		int first = -1;
		int end = -1;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				if (str.charAt(i) == '.'
						&& Character.isDigit(str.charAt(i - 1))
						&& Character.isDigit(str.charAt(i + 1))) {
					continue;
				}
				first = i;
				break;
			}
		}
		for (int i = first + 1; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				end = i;
				break;
			}
		}
		if (first == -1) {
			return "";
		}

		if (end == -1) {
			return str.substring(first);
		} else {
			return str.substring(first, end);
		}
	}

	/**
	 * <pre>
	 * 截取数字串
	 * 如果第一个字符不是数字会返回“”
	 * </pre>
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 数字串，如果str为null返回null，如果第一个字符不是数字会返回“”
	 */
	public static String substringDigit(String str) {
		if (str == null)
			return null;
		int index = str.length();
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				index = i;
				break;
			}
		}
		if (index > 0) {
			return str.substring(0, index);
		}
		return "";
	}

	/**
	 * <pre>
	 * 截取数字之后的字符串，包括小数位
	 * 如果第一个是字符会返回str
	 * </pre>
	 * 
	 * @param str
	 *            要截取的字符串
	 * @return 如果第一个是字符会返回str
	 */
	public static String substringAfterDecimal(String str) {
		if (str == null)
			return null;
		int index = -1;
		boolean radixPoint = false;
		for (int i = 0; i < str.length(); i++) {
			char charAt = str.charAt(i);
			if (!Character.isDigit(charAt)) {
				if (charAt == '.' && !radixPoint) {
					radixPoint = true;
				} else {
					index = i;
					break;
				}
			}
		}
		if (index != -1) {
			return str.substring(index);
		}
		return "";
	}

	/**
	 * <pre>
	 * 截取数字串，包括小数位
	 * </pre>
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 如果第一个字符不是.和数字,将返回""
	 */
	public static String subStringDecimal(String str) {
		if (str == null)
			return null;
		int index = str.length();
		boolean radixPoint = false;
		for (int i = 0; i < str.length(); i++) {
			char charAt = str.charAt(i);
			if (!Character.isDigit(charAt)) {
				if (charAt == '.' && !radixPoint) {
					radixPoint = true;
				} else {
					index = i;
					break;
				}
			}
		}
		if (index > 0) {
			// 如果最后一位是‘.’，要不要补‘0’
			// 如果最先一位是'.'，要不要前补'0'
			return str.substring(0, index);
		}
		return "";
	}

	/**
	 * 根据报关部门三字代码查找对应的货币符号
	 * 
	 * @param cusDeptCode
	 *            报关部门三字代码
	 * @return 报关部门对应的货币符号
	 */
	public static String findCurrencysymbolByCusDeptCode(String cusDeptCode) {
		
		return null;
	}

	/**
	 * 根据货币符号查找货币代码
	 * 
	 * @param currencysymbol
	 *            货币符号
	 * @return 货币代码
	 */
	public static String findCurrencyCodeByCurrencysymbol(String currencysymbol) {
		return null;
	}

	/**
	 * 根据货币符号查找货币名称
	 * 
	 * @param currencysymbol
	 *            货币符号
	 * @return 货币名称
	 */
	public static String findCurrencyNameByCurrencysymbol(String currencysymbol) {

		return null;
	}

	/**
	 * 根据英文城市名查找国别地区
	 * 
	 * @param cityEName
	 *            英文城市名
	 * @return 国别地区
	 */
	public static String findInterdnaByCityEName(String cityEName) {
		return null;
	}

	/**
	 * 根据报关批次取报关部门三字代码，type为1取前三位，type为2取后三位
	 * 
	 * @param cusBatch
	 *            报关批次
	 * @param type
	 *            类别（type为1取前三位，type为2取后三位）
	 * @return 报关部门三字代码
	 */
	public static String getCusDeptCodeByCusBatch(String cusBatch, String type) {
		if (null == cusBatch || "".equals(cusBatch)) {
			return "";
		}
		if (cusBatch.length() < 3) {
			return "";
		}
		if ("1".equals(type)) {
			return cusBatch.substring(0, 3);
		}
		return cusBatch.substring(cusBatch.length() - 3);
	}

	/**
	 * 截取字符串，超过length取length位，不超过不截取
	 * 
	 * @param str
	 *            被截取的字符串
	 * @param length
	 *            长度
	 * @return 截取后的字符串
	 */
	public static String subString(String str, int length) {
		str = (str == null ? "" : str);
		if (str.length() > length) {
			return str.substring(0, length);
		}
		return str;
	}

	/**
	 * 去掉字符串中的空格
	 * 
	 * @param str
	 *            字符串
	 * @return 去掉空格后的字符串
	 */
	public static String removeSpace(String str) {
		if (null == str) {
			return "";
		}
		return str.trim().replace(" ", "");
	}

	/**
	 * 去掉字符串中"-"
	 * 
	 * @param str
	 *            字符串
	 * @return 去掉空格后的字符串
	 */
	public static String removeHorizontal(String str) {
		if (null == str) {
			return "";
		}
		return str.trim().replace("-", "");
	}

	/**
	 * 取袋包号的前三位
	 * 
	 * @param packageno
	 *            袋包号
	 * @return 袋包号的前三位
	 */
	public static String getThreeFromPackageno(String packageno) {
		if (null == packageno) {
			return "";
		}
		if (packageno.length() < 3) {
			return "";
		}
		return packageno.substring(0, 3);
	}

	/**
	 * 取袋包号除前三位后的字符
	 * 
	 * @param packageno
	 *            袋包号
	 * @return 袋包号除前三位后的字符
	 */
	public static String getLastFromPackageno(String packageno) {
		if (null == packageno) {
			return "";
		}
		if (packageno.length() > 3) {
			return packageno.substring(3);
		}
		return "";
	}

	/**
	 * 得到与date相差N年的日期
	 * 
	 * @param year
	 *            需要相差多少年
	 * @return 相加或相减year年后的日期
	 */
	public static Date getDateByYearOff(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * 日期date的年份减year年得到多少年
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static String twoYearSubtract(Date date, int year) {

		return null;
	}

	/**
	 * 去除BigDecimal后面的0，返回字符串表现形式
	 * 
	 * @param value
	 * @return
	 */
	public static String removeZeroByBigDecimal(BigDecimal value) {
		if (value.compareTo(new BigDecimal(0)) == 0) {
			return "0";
		} else {
			value = value.stripTrailingZeros();
			return value.toPlainString();
		}
	}

	/**
	 * 格式化数字为会计格式
	 * 
	 * @param value
	 * @return
	 */
	public static String formatNumberToAccounting(Double value) {
		if (null == value) {
			return "";
		}
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));
		bigDecimal = bigDecimal.add(new BigDecimal("0.00"));
		String str = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)
				.toString();
		int index = str.indexOf(".");
		if (index < 4) {
			return str;
		}
		int num = index % 3 == 0 ? index / 3 - 1 : index / 3;
		StringBuffer sBuffer = new StringBuffer();
		int beginIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < num; i++) {
			if (index % 3 == 0) {
				beginIndex = endIndex;
				endIndex = (i + 1) * 3;
			} else {
				beginIndex = endIndex;
				endIndex = i * 3 + index % 3;
			}
			sBuffer.append(str.substring(beginIndex, endIndex)).append(",");
		}
		sBuffer.append(str.substring(endIndex));
		return sBuffer.toString();
	}

	/**
	 * XML文件中的特殊字符转换
	 * @param str 需要转换的字符串
	 * @return
	 */
	public static String changeHTMLTag(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		return str.replace("&", "&amp;").replace("<", "&lt;").replace(">",
				"&gt;").replace("\"", "&quot;").replace("\'", "&#39;");
	}
	
}
